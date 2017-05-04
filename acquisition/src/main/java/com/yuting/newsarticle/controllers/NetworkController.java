package com.yuting.newsarticle.controllers;

import com.yuting.newsarticle.DAO.NetworkDao;
import com.yuting.newsarticle.models.Article;
import com.yuting.newsarticle.models.Network;
import com.yuting.newsarticle.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ting on 5/3/17.
 */
@RestController
public class NetworkController {
    @Autowired
    private NetworkDao networkDao;

    @RequestMapping(value = "/network", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<Network> getNetwork() {
        Network nw = networkDao.generateNetwork("");
        return new ResponseEntity<Network>(nw, HttpStatus.OK);
    }
}
