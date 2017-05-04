package com.yuting.newsarticle.DAO;

import com.yuting.newsarticle.models.Article;
import com.yuting.newsarticle.models.Ingester;
import com.yuting.newsarticle.models.Keyword;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by Ting on 5/2/17.
 */
@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao {
    private static SessionFactory sessionFactory;
//    private static Session session;

//    public ArticleDaoImpl() {

        // TODO make it autowired
static {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }

//            session = sessionFactory.openSession();

        }
//    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Article> query(String hql) {
    try {
//        this.getCurrentSession().beginTransaction();
        Session session = this.getCurrentSession();
        session.beginTransaction();
//        String hql = "from Article where 1=1";
        Query query = session.createQuery(hql);
        query.setFirstResult(1);
        List results = query.list();
        return results;
    }finally {
        this.getCurrentSession().close();
    }

    }

    @Override
    public int saveArticle(Map<String, Object> param) {
        return 0;
    }

    @Override
    public void deleteArticle(Map<String, Object> param) {

    }

    @Override
    public int updateArticle(Article article) {
        return 0;
    }


    @Test
    public void test_dao() {
//        ArticleDaoImpl dao = new ArticleDaoImpl();
        Session session = this.getCurrentSession();
                session.beginTransaction();
        String hql = "from Article where 1=1";
        Query query = session.createQuery(hql);
        query.setFirstResult(1);
        List results = query.list();


}


}
