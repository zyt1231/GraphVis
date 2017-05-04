package com.yuting.newsarticle.DAO;
import com.yuting.newsarticle.models.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("networkDao")
public class NetworkDaoImpl implements NetworkDao {
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
    public List<Object> query(String hql) {
        try {
            Session session = this.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery(hql);
            List results = query.list();
            return results;
        }finally {
            this.getCurrentSession().close();
        }
    }

    @Override
    public Network generateNetwork(String hql) {
        List<Object> articles;
        List<String> article_ids;
        Set<Node> nodes = new HashSet<Node>();
        Set<Edge> edges = new HashSet<Edge>();
        Set<Keyword> keywords;
        //Todo remove hardcode hql
        hql = "from Article where pubDate = '2017-01-01'";
        articles = query(hql);
//        article_ids =  articles.stream().map((Object obj)-> ((Article)obj).getArticleId()).collect(Collectors.toList());
        for (Object article : articles) {
            //generate nodes
            String groupK = "K";
            String groupA = "A";
            String aId = ((Article) article).getArticleId();
            String headline = ((Article) article).getHeadLine();
            Node aN = new Node(aId, headline, groupA);
            nodes.add(aN);
            keywords = ((Article) article).getKeywords();
            for (Keyword keyword : keywords) {
                String kId = Integer.toString(keyword.getkId());
                Node kN = new Node(kId, keyword.getValue(), groupK);
                nodes.add(kN);
                // generate edges
                Edge edge = new Edge(aId, kId, "");
                edges.add(edge);
            }
        }
        return (new Network(nodes, edges));

    }

//    @Test
//    public void NetworkDaoTest() {
//        List<Object> articles;
//        List<String> article_ids;
//        Set<Node> nodes = new HashSet<Node>();
//        Set<Edge> edges = new HashSet<Edge>();
//        Set<Keyword> keywords;
//        String hql = "from Article where pubDate = '2017-01-01'";
//
//
//        articles = query(hql);
////        article_ids =  articles.stream().map((Object obj)-> ((Article)obj).getArticleId()).collect(Collectors.toList());
//        for (Object article : articles) {
//            //generate nodes
//            String groupK = "K";
//            String groupA = "A";
//            String aId = ((Article)article).getArticleId();
//            String headline = ((Article) article).getHeadLine();
//            Node aN = new Node(aId, headline, groupA);
//            nodes.add(aN);
//            keywords = ((Article)article).getKeywords();
//            for (Keyword keyword : keywords) {
//                String kId = Integer.toString(keyword.getkId());
//                Node kN = new Node(kId, keyword.getValue(), groupK);
//                nodes.add(kN);
//                // generate edges
//                Edge edge = new Edge(aId, kId, "");
//                edges.add(edge);
//            }
//        }
//        for (Node node : nodes) {
//            System.out.println(node.getLabel());
//        }
//        System.out.println();
//    }
}
