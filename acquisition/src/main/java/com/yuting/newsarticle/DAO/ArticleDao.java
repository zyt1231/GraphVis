package com.yuting.newsarticle.DAO;
import com.yuting.newsarticle.models.Article;

import java.util.HashMap;
import java.util.List;


public interface ArticleDao {

    public List<Article> query(String hql);

    public HashMap<String, HashMap<String, Integer>> generateArticleMap(String hql);

}