package com.yuting.newsarticle.controllers;

import com.yuting.newsarticle.models.Article;
import com.yuting.newsarticle.services.ArticleService;
import hello.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yuting.newsarticle.services.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
//@RequestMapping(value = "/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//        @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=utf-8")
//        public ResponseEntity<List<Article>> getUserList(
//                @RequestParam(value = "offset", defaultValue = "0") long offset,
//                @RequestParam(value = "limit", defaultValue = MAX_LONG_AS_STRING) long limit)
//        {
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("offset", offset);
//            param.put("limit", limit);
//            List<User> userList = userService.query(param);
//            if (userList.size() == 0)
//            {
//                return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
//        }

    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<Article> getUserById(@PathVariable String id) {

        Article article = articleService.findById(id);
        if (article == null) {
            return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Article>(articleService.findById(id), HttpStatus.OK);
    }
    @RequestMapping("/testing")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World", required = true) String name) {
        String template = "Hello, %s!";
        AtomicLong counter = new AtomicLong();
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }


}
