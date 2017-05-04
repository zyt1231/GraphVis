//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.yuting.newsarticle;
//
//import edu.stanford.nlp.ie.AbstractSequenceClassifier;
//import edu.stanford.nlp.ie.crf.CRFClassifier;
//import edu.stanford.nlp.io.IOUtils;
//import edu.stanford.nlp.ling.CoreLabel;
//import static edu.stanford.nlp.pipeline.DefaultPaths.DEFAULT_NER_THREECLASS_MODEL;
//import edu.stanford.nlp.util.Triple;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class NER {
//
//    Set p = new HashSet();
//    Set l = new HashSet();
//    Set o = new HashSet();
//    List<String> location_array = new ArrayList<>();
//    List<Integer> offset_array = new ArrayList<>();
//    static String fileContent;
//    AbstractSequenceClassifier<CoreLabel> classifier;
//    JSONArray peopleName = new JSONArray();
//
//    public NER() throws Exception {
//        String serializedClassifier = DEFAULT_NER_THREECLASS_MODEL;
//        // String serializedClassifier = "classifiers/english.all.7class.distsim.crf.ser.gz";
//        classifier = CRFClassifier.getClassifier(serializedClassifier);
//     //   String path = "C:\\My Program Files\\NetBeansProjects\\ArticleGraph\\src\\main\\java\\com\\mycompany\\"
//      //          + "articlegraph\\files\\python.result.volkswagen___.json";
//        String path="C:\\Users\\zyt12_000\\Documents\\Visual Studio 2015\\Projects\\WebCrawl\\WebCrawl\\data\\1.json";
//        String jsonData = readFile(path);
//        JSONArray jsonAry = new JSONArray(jsonData);
/////System.out.println(jsonAry.length());
//        for (int i = 0; i < jsonAry.length(); i++) {
////        for (int i = 0; i < 50; i++) {
//            JSONObject theArticle= (JSONObject)jsonAry.get(i);
//
//            fileContent=theArticle.getString("lead_paragraph")+((JSONObject)(theArticle.get("headline"))).getString("main");
//            System.out.println(fileContent);
//            //fileContent = theArticle.getString("content");
//            this.classify(fileContent);
//            //System.out.println("fileContent:"+jsonAry.toString());
//            theArticle.put("people", p);
//            theArticle.put("organizations", o);
//            theArticle.put("locations", l);
//            theArticle.remove("type_of_material");
//            theArticle.remove("blog");
//            theArticle.remove("abstract");
//            theArticle.remove("print_page");
//            theArticle.remove("word_count");
//            theArticle.remove("slideshow_credits");
//            theArticle.remove("multimedia");
//            theArticle.remove("subsection_name");
//            theArticle.remove("byline");
//            theArticle.remove("document_type");
//            System.out.println(theArticle);
//
//            p.clear();
//            o.clear();
//            l.clear();
//        //    System.out.println(theArticle.toString());
//
//
//        }
////        System.out.println(jsonAry.toString());
//        this.write2json(jsonAry.toString(), "C:\\Users\\zyt12_000\\Documents\\Visual Studio 2015\\Projects\\WebCrawl\\WebCrawl\\data\\news_nyt_keywords.json");
//
//    }
//
//    public NER(File file) throws Exception {
////
////        String serializedClassifier = "classifiers/english.all.3class.distsim.crf.ser.gz";
////        // String serializedClassifier = "classifiers/english.all.7class.distsim.crf.ser.gz";
////        classifier = CRFClassifier.getClassifier(serializedClassifier);
////        fileContents = IOUtils.slurpFile(file);
////        this.classify(fileContents);
////        System.out.println("---");
//
//    }
//
//    public List<String> getLocations() {
//        return location_array;
//    }
//
//    public List<Integer> getOffsets() {
//        return offset_array;
//    }
//
//    static String getSubString(int a, int b) {
//        int start;
//        int end;
//        if (a < b) {
//            start = a;
//            end = b;
//        } else {
//            start = b;
//            end = a;
//        }
//        return fileContent.substring(start, end);
//    }
//
//    public void classify(String filecontent) {
//        //   List<Triple<String, Integer, Integer>> list = classifier.classifyToCharacterOffsets(fileContents);
//        List<Triple<String, Integer, Integer>> list = classifier.classifyToCharacterOffsets(fileContent);
//        for (Triple<String, Integer, Integer> item : list) {
//            if (item.first().equalsIgnoreCase("LOCATION")) {
//                String lname = fileContent.substring(item.second(), item.third()).replaceAll("\\.", "").toLowerCase();
//                location_array.add(fileContent.substring(item.second(), item.third()).replaceAll("\\.", "").toLowerCase());
//                offset_array.add(item.second);
//                l.add(lname);
//                System.out.println("LOCATION: " + lname);
//            }
//
//            if (item.first().equalsIgnoreCase("PERSON")) {
//                //String pname=item.first() + ": " + fileContents.substring(item.second(), item.third());
//                String pname = fileContent.substring(item.second(), item.third());
//                System.out.println("PEOPLE: " + pname);
//                p.add(pname);//不加重复的元素
//            }
//            if (item.first().equalsIgnoreCase("ORGANIZATION")) {
//                //String oname=item.first() + ": " + fileContents.substring(item.second(), item.third());
//                String oname = fileContent.substring(item.second(), item.third());
//                System.out.println("ORGANIZATION: " + oname);
//                o.add(oname);//不加重复的元素
//
//            }
//        }
//        peopleName.put(p.toArray());
//
//    //    System.out.println("---");
//  //      System.out.println("Locations:");
//        //     System.out.println(Arrays.toString(location_array.toArray()));
//        //   System.out.println("Offset:");
//        //  System.out.println(Arrays.toString(offset_array.toArray()));
//    }
//
//    public Object[] getPeopleName() {
//
//        return p.toArray();
//    }
//
//    public Object[] getLocation() {
//        return l.toArray();
//    }
//
//    public Object[] getOrganization() {
//        return o.toArray();
//    }
//
//    public static String readFile(String filename) {
//        String result = "";
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(filename));
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//            while (line != null) {
//                sb.append(line);
//                line = br.readLine();
//            }
//            result = sb.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//        public void write2json(String str, String path) throws IOException {
//        FileWriter fw = new FileWriter(path, true);
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(str);
//        bw.close();
//        fw.close();
//        System.out.println("Successfully copied string to nytResult.json.");
//
//    }
//}
