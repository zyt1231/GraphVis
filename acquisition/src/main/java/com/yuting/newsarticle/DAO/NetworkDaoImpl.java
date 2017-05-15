package com.yuting.newsarticle.DAO;

import com.yuting.newsarticle.models.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
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
        } finally {
            this.getCurrentSession().close();
        }
    }

    @Override
    public Network generateNetwork(String hql) {
        List<Object> articles;
        Set<Edge> edges = new HashSet<Edge>();
        Map<String, Node> mNodes = new HashMap<String, Node>();
        Set<Keyword> keywords;
        articles = query(hql);
        for (Object article : articles) {
            //generate nodes
            String groupK = "K";
            String groupA = "A";
            String aId = ((Article) article).getArticleId();
            String headline = ((Article) article).getHeadLine();
            String pubDate = ((Article) article).getPubDate().toString();
            String type = "article";
            Node aN = new Node(aId, headline, groupA, type, pubDate);
            mNodes.put(aId, aN);
            keywords = ((Article) article).getKeywords();
            for (Keyword keyword : keywords) {
                String kId = Integer.toString(keyword.getkId());
                Node kN = new Node(kId, keyword.getValue(), groupK, keyword.getType(), pubDate);
                mNodes.put(kId, kN);
                // generate edges
                Edge edge = new Edge(aId, kId, "");
                edges.add(edge);
            }
        }
        // remove dups
        Set<Node> nodes = new HashSet<>(mNodes.values());
        return (new Network(nodes, edges));

    }

    @Test
    public void NetworkDaoTest() {
        String from = "2017-01-01";
        String to = "2017-01-02";
        String hql = "from Article where pubDate between '" + from + "' and '" + to + "'";
        Network nw = this.generateNetwork(hql);
        System.out.println(nw);
    }

}
