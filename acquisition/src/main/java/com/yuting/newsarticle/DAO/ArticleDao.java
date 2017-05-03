package com.yuting.newsarticle.DAO;
import com.yuting.newsarticle.models.Article;
import java.util.List;
import java.util.Map;


public interface ArticleDao {

    public List<Article> query(String hql);

    public int saveArticle(Map<String, Object> param);

    public void deleteArticle(Map<String, Object> param);

    public int updateArticle(Article article);
}