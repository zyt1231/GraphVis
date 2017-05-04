//package com.yuting.newsarticle;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//
///**
// *
// * @author zyt12_000
// */
//public class NYTAPI {
//
//    public NYTAPI() throws IOException {
//        String path = "C:\\My Program Files\\NetBeansProjects\\ArticleGraph\\src\\main\\java\\com\\mycompany\\"
//                + "articlegraph\\files\\nyt."
//                + "presidential.json";
//
//        int page;
//        String key = "fd8555d7b66bf356cb5b2e1c477d5461:18:73041805";
//        URL url = null;
//        String inputLine = "[";
//        String str;
//
//        for (page = 1; page < 15; page++) {
//            url = new URL("http://api.nytimes.com/svc/search/v2/articlesearch.json?"
//       //             + "q=riot%20gray&"
//        //            + "q=volkswagen%20emission&"
//                    + "q=Presidential&"
//                    + "page=" + page + "&"
//                    + "sort=oldest&"
////                    + "begin_date=20150423&"
////                    + "end_date=20151101&"
//                    + "begin_date=20150901&"
//                    + "end_date=20151115&"
//                    + "api-key=fd8555d7b66bf356cb5b2e1c477d5461:18:73041805");
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//            while ((str = in.readLine()) != null) {
//                 inputLine += str;
//                  inputLine += ",";
//                inputLine += "\n";
//            //    inputLine += str;
//            }
//
//        }
//        inputLine+="]";
//        this.write2json(inputLine, path);
//
//    }
//
//    public void write2json(String str, String path) throws IOException {
//        FileWriter fw = new FileWriter(path, true);
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(str);
//        bw.close();
//        fw.close();
//        System.out.println("Successfully copied string to nytResult.json.");
//
//    }
//
//}
