package com.yuting.newsarticle;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Ingester {
    private String filedir = "/Users/Ting/dev/GraphVis/acquisition/src/main/resources/json/nyt1704.json";
    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public JSONObject getData() {
        JSONObject jObj = new JSONObject();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filedir));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            String jsonStr = sb.toString();
            JSONParser jParser = new org.json.simple.parser.JSONParser();
            try {
                Object obj = jParser.parse(jsonStr);
                jObj = (JSONObject) obj;
                return jObj;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jObj;

    }

    public Date parseDate(String str) {
        Date date = new Date();
        str = str.substring(0, 10);
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date = formatter.parse(str);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public void ingestData(JSONObject obj) {
        Session session = factory.openSession();
        JSONObject response = (JSONObject) obj.get("response");
        JSONArray docs = (JSONArray) response.get("docs");
        Transaction tx = null;
        tx = session.beginTransaction();
        for (int i = 0; i < docs.size(); i++) {
            try {


                JSONObject theArticle = (JSONObject) docs.get(i);
                String article_id = (String) theArticle.get("_id");
                String web_url = (String) theArticle.get("web_url");
                String snippet = (String) theArticle.get("snippet");
                JSONArray keywords = (JSONArray) theArticle.get("keywords");
                String pub_date_str = (String) theArticle.get("pub_date");
                String news_desk = (String) theArticle.get("news_desk");
                String section_name = (String) theArticle.get("section_name");
                String subsection_name = (String) theArticle.get("subsection_name");
                String headline = (String) ((JSONObject) theArticle.get("headline")).get("main");

                if (section_name.equals("U.S.")) {
                    if (snippet != null) {
                        if (snippet.length() >= 2000) {
                            snippet = snippet.substring(0, 2000);
                        }
                    }
                    // save to keyword model
                    Set<Keyword> keywordSet = new HashSet<Keyword>();
                    for (int j = 0; j < keywords.size(); j++) {
                        JSONObject keyword = (JSONObject) keywords.get(j);
                        String value = (String) keyword.get("value");
                        String type = (String) keyword.get("name");

                        // get keyword
                        Criteria criteria = session.createCriteria(Keyword.class);
                        Keyword k = (Keyword)criteria.add(Restrictions.eq("value", value)).uniqueResult();
                        if (k==null){
                            k = new Keyword(type, value);
                        }
                        keywordSet.add(k);
                    }


                    if (pub_date_str == null) {
                        System.out.println("null date");

                    }
                    Date pub_date = this.parseDate(pub_date_str);
                    Article article = new Article(article_id, news_desk, section_name, subsection_name, pub_date, web_url, snippet, headline, keywordSet);
                    session.save(article);
                    System.out.println("Saved one record..." + article.getArticleId());

                }
            }catch (Exception ex){
                System.err.println("Failed to created object." + ex);
            }


        }
        tx.commit();
        session.close();
    }

    @Test
    public void test_ingester() {
        Ingester ig = new Ingester();
        JSONObject obj = ig.getData();
        ig.ingestData(obj);


    }
}
