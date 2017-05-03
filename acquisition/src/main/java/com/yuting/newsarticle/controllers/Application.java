package com.yuting.newsarticle.controllers;

/**
 * Created by Ting on 4/24/17.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.yuting.newsarticle")
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

//    public static void main(String args[]) {
//        RestTemplate restTemplate = new RestTemplate();
//        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
//        log.info(quote.toString());
//    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
