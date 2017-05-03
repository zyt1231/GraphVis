package com.yuting.newsarticle.DAO;

import com.yuting.newsarticle.models.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Ting on 5/2/17.
 */
@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao{
    private static SessionFactory sessionFactory;

    // TODO make it autowired
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
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
        this.getCurrentSession().beginTransaction();
        return this.getCurrentSession().createQuery(hql,Article.class).list();

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
}
