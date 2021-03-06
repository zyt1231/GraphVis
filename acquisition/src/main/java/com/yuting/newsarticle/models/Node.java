package com.yuting.newsarticle.models;

/**
 * Created by Ting on 5/3/17.
 */
public class Node {
    private String id;
    private String label;
    private String group;
    private String type;
    private String pubDate;

    public Node() {
    }

    public Node(String id, String label, String group, String type, String pubDate) {
        this.id = id;
        this.label = label;
        this.group = group;
        this.type = type;
        this.pubDate = pubDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
