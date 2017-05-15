package com.yuting.newsarticle.controllers;

import com.yuting.newsarticle.DAO.NetworkDao;
import com.yuting.newsarticle.models.Article;
import com.yuting.newsarticle.models.Network;
import com.yuting.newsarticle.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ting on 5/3/17.
 */
@RestController
public class NetworkController {
    @Autowired
    private NetworkDao networkDao;

    @CrossOrigin
    @RequestMapping(value = "/network", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<Network> getNetwork(@RequestParam(value = "from", defaultValue = "2016-01-01", required = true)  String from,
                                              @RequestParam(value = "to", defaultValue = "2017-04-01", required = true) String to) {
        String hql = "from Article where pubDate between '" + from + "' and '" + to + "'";
        Network nw = networkDao.generateNetwork(hql);
        return new ResponseEntity<Network>(nw, HttpStatus.OK);
    }
}
