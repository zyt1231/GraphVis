package com.yuting.newsarticle.services;

/**
 * Created by Ting on 5/2/17.
 */

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import com.yuting.newsarticle.models.Article;

public interface ArticleService {


    public List<Article> query(String hql);

//    @Cacheable(value = "userCache", key = "#id")
    public Article findById(String id);

//    @CachePut(value = "userCache", key = "#result.id")
    public Article saveArticle(Article user);

    //注意key的类型要一致，不要一个是long，一个object or string
//    @CacheEvict(value = "userCache", key = "#id")
    public void deleteArticle(String id);

    public Article updateArticle(Article user);
}