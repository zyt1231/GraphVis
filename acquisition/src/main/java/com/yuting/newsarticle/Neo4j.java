///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.yuting.newsarticle;
//
//import static com.mycompany.articlegraph.NER.readFile;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.neo4j.graphdb.DynamicLabel;
//import org.neo4j.graphdb.GraphDatabaseService;
//import org.neo4j.graphdb.Node;
//import org.neo4j.graphdb.Relationship;
//import org.neo4j.graphdb.RelationshipType;
//import org.neo4j.graphdb.Transaction;
//import org.neo4j.graphdb.factory.GraphDatabaseFactory;
//
///**
// *
// * @author zyt12_000
// */
//public class Neo4j {
//
//    public Neo4j() throws JSONException {
//        //  this.findDuplicates();
//       // this.buildDB1();
////         this.buildDB2();
//        this.buildDB3();
//
//    }
//
//    private static enum RelTypes implements RelationshipType {
//
//        //KNOWS
//        SAME_ARTICLE,
//        ARTICLE_CONTAINS,
//        SHARE
//    }
//
//    //make sure the database shuts down when the JVM exits
//    public static void registerShutdownHook(final GraphDatabaseService graphDb) {
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                graphDb.shutdown();
//            }
//        });
//    }
//
//    public void buildDB1() throws JSONException {
//        String pub_date;
//        String title;
//        JSONArray people;
//        JSONArray orgs;
//        //nodes to connect for each article
//        ArrayList<Node> nodeToConnect = new ArrayList<Node>();
//        //get json data
//        String path = "C:\\My Program Files\\NetBeansProjects\\ArticleGraph\\src\\main\\java\\com\\mycompany\\"
//                + "articlegraph\\files\\python.result.presidential.json";
//        //volkswagen
//        //riots
//        //presidential
//        String jsonData = readFile(path);
//        JSONArray jsonAry = new JSONArray(jsonData);
//
//        //connect neo4jDB
//        Relationship relationship;
//        GraphDatabaseService graphDB = new GraphDatabaseFactory().newEmbeddedDatabase(
//                new File("C:\\Users\\zyt12_000\\Documents\\Neo4j\\graphDB.presidential.1"));
//        registerShutdownHook(graphDB);
//        Transaction tx = graphDB.beginTx();
//
//        //build
//        for (int i = 0; i < jsonAry.length(); i++) {//each article
//            JSONObject theArticle = (JSONObject) jsonAry.get(i);
//            title = theArticle.getString("title");
//            pub_date = theArticle.getString("pub_date");
//            people = theArticle.getJSONArray("people");
//            orgs = theArticle.getJSONArray("organization");
//
//            for (int j = 0; j < people.length(); j++) {
//                String person = people.getString(j);
//                if (graphDB.findNode(DynamicLabel.label("Person"), "Name", person) == null) {
//                    Node pnode = graphDB.createNode();
//                    pnode.addLabel(DynamicLabel.label("Person"));
//                    pnode.setProperty("Name", people.getString(j));
//                    nodeToConnect.add(pnode);
//                    System.out.println("create a Person node " + people.getString(j) + "!");
//                } else {
//                    System.out.println("not null?");
//                    nodeToConnect.add(graphDB.findNode(DynamicLabel.label("Person"), "Name", person));
//                }
//
//            }
//            for (int k = 0; k < orgs.length(); k++) {
//                String orgname = orgs.getString(k);
//                if (graphDB.findNode(DynamicLabel.label("Organization"), "Name", orgname) == null) {
//                    Node onode = graphDB.createNode();
//                    onode.addLabel(DynamicLabel.label("Organization"));
//                    onode.setProperty("Name", orgs.getString(k));
//                    nodeToConnect.add(onode);
//                    System.out.println("create a Org node " + orgs.getString(k) + "!");
//                } else {
//                    System.out.println("not null?");
//                    nodeToConnect.add(graphDB.findNode(DynamicLabel.label("Organization"), "Name", orgname));
//                }
//            }
//
//            //connect
//            for (int l = 0; l < nodeToConnect.size(); l++) {
//                for (int m = l + 1; m < nodeToConnect.size(); m++) {
//                    relationship = nodeToConnect.get(l).createRelationshipTo(nodeToConnect.get(m), RelTypes.SAME_ARTICLE);
//                    relationship.setProperty("Title", title);
//                    relationship.setProperty("pub_date", pub_date);
//                }
//            }
//            //empty array
//            nodeToConnect.clear();
//
//        }
//        //close DB
//        tx.success();
//        tx.close();
//
//    }
//
//    public void buildDB2() throws JSONException {
//        String pub_date;
//        String title;
//        JSONArray people;
//        JSONArray orgs;
//        //nodes to connect from each article
//        ArrayList<Node> nodeToConnect = new ArrayList<Node>();
//        //get json data
//        String path = "C:\\My Program Files\\NetBeansProjects\\ArticleGraph\\src\\main\\java\\com\\mycompany\\"
//                + "articlegraph\\files\\python.result.presidential.json";
//
//        String jsonData = readFile(path);
//        JSONArray jsonAry = new JSONArray(jsonData);
//
//        //connect neo4jDB
//        Relationship relationship;
//        GraphDatabaseService graphDB = new GraphDatabaseFactory().newEmbeddedDatabase(
//                new File("C:\\Users\\zyt12_000\\Documents\\Neo4j\\graphDB.presidential.2"));
//        registerShutdownHook(graphDB);
//        Transaction tx = graphDB.beginTx();
//
//        //build
//        for (int i = 0; i < jsonAry.length(); i++) {//each article
//            JSONObject theArticle = (JSONObject) jsonAry.get(i);
//            title = theArticle.getString("title");
//            pub_date = theArticle.getString("pub_date");
//            people = theArticle.getJSONArray("people");
//            orgs = theArticle.getJSONArray("organization");
//
//            for (int j = 0; j < people.length(); j++) {
//                String person = people.getString(j);
//                if (graphDB.findNode(DynamicLabel.label("Person"), "Name", person) == null) {
//                    Node pnode = graphDB.createNode();
//                    pnode.addLabel(DynamicLabel.label("Person"));
//                    pnode.setProperty("Name", people.getString(j));
//                    nodeToConnect.add(pnode);
//                    System.out.println("create a Person node " + people.getString(j) + "!");
//                } else {
//                    System.out.println("not null?");
//                    nodeToConnect.add(graphDB.findNode(DynamicLabel.label("Person"), "Name", person));
//                }
//            }
//            for (int k = 0; k < orgs.length(); k++) {
//                String orgname = orgs.getString(k);
//                if (graphDB.findNode(DynamicLabel.label("Organization"), "Name", orgname) == null) {
//                    Node onode = graphDB.createNode();
//                    onode.addLabel(DynamicLabel.label("Organization"));
//                    onode.setProperty("Name", orgs.getString(k));
//                    nodeToConnect.add(onode);
//                    System.out.println("create a Org node " + orgs.getString(k) + "!");
//                } else {
//                    System.out.println("not null?");
//                    nodeToConnect.add(graphDB.findNode(DynamicLabel.label("Organization"), "Name", orgname));
//                }
//            }
//
//            //connect
//            Node articleNode = graphDB.createNode();
//            articleNode.addLabel(DynamicLabel.label("Article"));
//            articleNode.setProperty("title", title);
//            articleNode.setProperty("pub_date", pub_date);
//
//            for (int l = 0; l < nodeToConnect.size(); l++) {
//
//                relationship = articleNode.createRelationshipTo(
//                        nodeToConnect.get(l), RelTypes.ARTICLE_CONTAINS);
//            }
//            //empty array
//            nodeToConnect.clear();
//
//        }
//        //close DB
//        tx.success();
//        tx.close();
//
//    }
//
//    public void buildDB3() throws JSONException {
//
//        //shared attribute in a pair of articles
//        // ArrayList<String> sharedPeople = new ArrayList<String>();
//        //  ArrayList<String> sharedOrgs = new ArrayList<String>();
//        JSONArray sharedPeople = new JSONArray();
//        JSONArray sharedOrgs = new JSONArray();
//        JSONArray sharedLocations = new JSONArray();
//        JSONArray sharedkeywords = new JSONArray();
//
//        //get json data
////        String path = "C:\\My Program Files\\NetBeansProjects\\ArticleGraph\\src\\main\\java\\com\\mycompany\\"
////                + "articlegraph\\files\\python.result.presidential.json";
//        String path = "C:\\Users\\zyt12_000\\Documents\\Visual Studio 2015\\Projects\\WebCrawl\\WebCrawl\\data\\news_nyt_keywords.json";
//        String jsonData = readFile(path);
//        JSONArray jsonAry = new JSONArray(jsonData);
//
//        //connect neo4jDB
//        Relationship relationship;
//        GraphDatabaseService graphDB = new GraphDatabaseFactory().newEmbeddedDatabase(
//                new File("C:\\Users\\zyt12_000\\Documents\\Neo4j\\graphDB.US"));
//        registerShutdownHook(graphDB);
//        Transaction tx = graphDB.beginTx();
//
//        //build
//        for (int i = 0; i < jsonAry.length(); i++) {
//            for (int j = i + 1; j < jsonAry.length(); j++) {
//                //each article
//
//                JSONObject article_a = (JSONObject) jsonAry.get(i);
//                JSONObject article_b = (JSONObject) jsonAry.get(j);
//                JSONArray a = article_a.getJSONArray("people");
//                JSONArray b = article_b.getJSONArray("people");
//                JSONArray c = article_a.getJSONArray("organizations");
//                JSONArray d = article_b.getJSONArray("organizations");
//                JSONArray ka = article_a.getJSONArray("keyword_list");
//                JSONArray kb = article_b.getJSONArray("keyword_list");
////                JSONArray la = article_a.getJSONArray("locations");
////                JSONArray lb = article_b.getJSONArray("locations");
//                if (article_a.get("headline").equals(article_b.get("headline"))) {
//
//                } else {
//
//                    //loop people array
//                    for (int k = 0; k < a.length(); k++) {//loop people array
//                        for (int l = 0; l < b.length(); l++) {
//                            if (a.getString(k).equals(b.getString(l))) {
//                                sharedPeople.put(a.getString(k));
//                                System.out.println("Get shared person name " + a.getString(k));
//                            } else {
//                                // System.out.println("Not Match!");
//                            }
//                        }
//                    }
//                    //loop orgs array
//                    for (int m = 0; m < c.length(); m++) {
//                        for (int n = 0; n < d.length(); n++) {
//                            if (c.getString(m).equals(d.getString(n))) {
//                                sharedOrgs.put(c.getString(m));
//                                System.out.println("Get shared Org name " + c.getString(m));
//                            } else {
//                                // System.out.println("Not Match!");
//                            }
//                        }
//                    }
//                        //loop keyword_list array
//                    for (int ii = 0;ii < ka.length(); ii++) {
//                        for (int jj = 0; jj < kb.length(); jj++) {
//                            if (ka.getString(ii).equals(kb.getString(jj))) {
//                                sharedkeywords.put(ka.getString(ii));
//                                System.out.println("Get shared keyword " + ka.getString(ii));
//                            } else {
//                                // System.out.println("Not Match!");
//                            }
//                        }
//                    }
//
////                    for (int p = 0;p < la.length(); p++) {
////                        for (int q = 0; q < lb.length(); q++) {
////                            if (la.getString(p).equals(lb.getString(q))) {
////                                sharedLocations.put(la.getString(p));
////                                System.out.println("Get shared location " + la.getString(p));
////                            } else {
////                                // System.out.println("Not Match!");
////                            }
////                        }
////                    }
//
//
//
//                    //connect
//                    if (sharedPeople.length() == 0 && sharedOrgs.length() == 0 && sharedkeywords.length() == 0 && sharedLocations.length() == 0) {
//                        System.out.println("Not Match!");
//                    } else {
//                        Node node_article_a;
//                        Node node_article_b;
//                        String titlea=((JSONObject)(article_a.get("headline"))).get("main").toString();
//                        System.out.println("titlea " + titlea);
//                        String titleb=((JSONObject)(article_b.get("headline"))).get("main").toString();
//                        if (graphDB.findNode(DynamicLabel.label("Article"), "title", titlea) == null) {
//                            node_article_a = graphDB.createNode();
//                        } else {
//                            node_article_a = graphDB.findNode(DynamicLabel.label("Article"), "title", titlea);
//                        }
//                        if (graphDB.findNode(DynamicLabel.label("Article"), "title", titleb) == null) {
//                            node_article_b = graphDB.createNode();
//                        } else {
//                            node_article_b = graphDB.findNode(DynamicLabel.label("Article"), "title", titleb);
//                        }
//
//                        node_article_a.addLabel(DynamicLabel.label("Article"));
//                        node_article_a.setProperty("title", titlea);
//                        node_article_a.setProperty("pub_date", article_a.get("pub_date"));
//                        node_article_a.setProperty("_id", article_a.get("_id"));
//
//                        node_article_b.addLabel(DynamicLabel.label("Article"));
//                        node_article_b.setProperty("title", titleb);
//                        node_article_b.setProperty("pub_date", article_b.get("pub_date"));
//                        node_article_a.setProperty("_id", article_b.get("_id"));
//
//                        relationship = node_article_a.createRelationshipTo(
//                                node_article_b, RelTypes.SHARE);
//                        relationship.setProperty("Shared_People", sharedPeople.toString());
//                        relationship.setProperty("Shared_Orgs", sharedOrgs.toString());
//                        relationship.setProperty("shared_Keywords", sharedkeywords.toString());
//                       //  relationship.setProperty("shared_Locations", sharedLocations.toString());
//
//                        //calculate weight
//                        relationship.setProperty("weight", sharedPeople.length() + sharedOrgs.length() +sharedkeywords.length());
//                                //+sharedLocations.length());
//                        sharedPeople = new JSONArray();
//                        sharedOrgs = new JSONArray();
//                        sharedkeywords=new JSONArray();
//                        sharedLocations=new JSONArray();
//                    }
//                }
//            }
//        }
//
//        //close DB
//        tx.success();
//        tx.close();
//
//    }
//    public void findDuplicates() throws JSONException{
//        String path = "C:\\My Program Files\\NetBeansProjects\\ArticleGraph\\src\\main\\java\\com\\mycompany\\"
//                + "articlegraph\\files\\python.result.presidential.json";
//
//        String jsonData = readFile(path);
//        JSONArray jsonAry = new JSONArray(jsonData);
//        Set<String> set = new HashSet<String>();
//        for(int i=0;i<jsonAry.length();i++){
//            if(!set.add(jsonAry.getJSONObject(i).getString("title"))){
//                System.out.println(jsonAry.getJSONObject(i).get("title").toString());
//            }
//        }
//
//
//    }
//}
