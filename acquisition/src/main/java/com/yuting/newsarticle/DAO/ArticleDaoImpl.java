package com.yuting.newsarticle.DAO;

import com.yuting.newsarticle.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import org.hibernate.Query;

import java.util.*;

/**
 * Created by Ting on 5/2/17.
 */

@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Article> query(String hql) {
        try {
            Session session = this.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery(hql);
            List results = query.list();
            return results;
        } finally {
            this.getCurrentSession().close();
        }
    }
    @Override
    public HashMap<String, HashMap<String, Integer>> generateArticleMap(String hql) {
        List<Article> articles = query(hql);
        HashMap<String, HashMap<String, Integer>> toReturn = new HashMap<String, HashMap<String,Integer>>();
        for(Article article:articles){
            //get if keyword map for the date
            Date pubDate = article.getPubDate();
            HashMap<String, Integer> kwMap = toReturn.get(pubDate.toString());
            if(kwMap==null){
                kwMap = new HashMap<String, Integer>();
            }
            else{
                System.out.println();
            }
            //loop keywords for the article
            Set<Keyword> keywords = article.getKeywords();
            for(Keyword kw : keywords){
                String value = kw.getValue();
                //if has keyword in map
                if(value.toLowerCase().contains("trump")){
                    System.out.println();
                }
                Integer ct = kwMap.get(value);
                if(ct==null){
                    kwMap.put(value, 1);
                }else{
                    ct += 1;
                    kwMap.put(value, ct);
                }

            }
            toReturn.put(pubDate.toString(), kwMap);
        }

        return toReturn;
    }
    @Test
    public void test_articleMap() {
        String from = "2017-01-01";
        String to = "2017-01-02";
        String hql = "from Article where pubDate between '" + from + "' and '" + to + "'";
        HashMap<String, HashMap<String, Integer>> map = this.generateArticleMap(hql);
    }


}
