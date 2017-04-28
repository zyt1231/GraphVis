import com.yuting.newsarticle.Article;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;


import javax.persistence.metamodel.EntityType;
import java.util.Map;
import com.yuting.newsarticle.Keyword;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Set;
import java.util.HashSet;
/**
 * Created by Ting on 4/26/17.
 */
public class Main {
    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);         }
    }



    public static void main(final String[] args) throws Exception {
        Session session = factory.openSession();
        try {
            System.out.println("querying all the managed entities...");

            Transaction tx = null;
            tx = session.beginTransaction();
            Keyword keyword = new Keyword();
            keyword.setType("people");
            keyword.setValue("YutingZHOU");
            Keyword keyword2 = new Keyword();
            keyword2.setType("people");
            keyword2.setValue("Neil");
            session.save(keyword);
             session.save(keyword2);


            //create article
            Article article = new Article("12345");
            Set<Keyword> keywords = new HashSet<Keyword>();
            keywords.add(keyword);
            keywords.add(keyword2);
            article.setKeywords(keywords);
            article.setWebUrl("http://abcd.com");
            session.save(article);
            tx.commit();
            System.out.println("Added one record..."+ article.getArticleId());

        } finally {
            session.close();
        }
    }
}