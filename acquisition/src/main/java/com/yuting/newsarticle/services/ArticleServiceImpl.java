//package com.yuting.newsarticle.services;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.yuting.newsarticle.DAO.ArticleDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.yuting.newsarticle.models.Article;
//
//
//@Service("articleService")
//public class ArticleServiceImpl implements ArticleService {
//
//    @Autowired
//    private ArticleDao articleDao;
//
//    @Override
//    public List<Article> query(String hql) {
//        return articleDao.query(hql);
//    }
//
//    @Override
//    public Article findById(String id) {
////        String hql = String.format("from Article as a where 1=1", id);
//        String hql = "from Article as a where 1=1";
//        List<Article> Articles = this.query(hql);
//        return Articles.size() > 0 ? Articles.get(0) : null;
//    }
//
//    @Override
//    public Article saveArticle(Article article) {
//        return article;
//    }
//
//    @Override
//    public void deleteArticle(String id) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("id", id);
//        articleDao.deleteArticle(param);
//    }
//
//    @Override
//    public Article updateArticle(Article article) {
//        articleDao.updateArticle(article);
//        return article;
//    }
//
//}