package com.yuting.newsarticle.controllers;

import com.yuting.newsarticle.DAO.ArticleDao;
import com.yuting.newsarticle.models.Article;
import com.yuting.newsarticle.services.ArticleService;
import hello.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yuting.newsarticle.services.*;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @CrossOrigin
    @RequestMapping(value = "/articles", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<HashMap> getArticle(@RequestParam(value = "from", defaultValue = "2016-01-01", required = true) String from,
                                              @RequestParam(value = "to", defaultValue = "2017-04-01", required = true) String to) {
        String hql = "from Article where pubDate between '" + from + "' and '" + to + "'";
        HashMap map = articleDao.generateArticleMap(hql);
        return new ResponseEntity<HashMap>(map, HttpStatus.OK);

    }
}
