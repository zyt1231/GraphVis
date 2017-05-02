package com.yuting.newsarticle.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "article")
public class Article {
    @Id
    @Column(name = "article_id", unique = true, nullable = false)
    private String articleId;

    @Column(name = "news_desk")
    private String newsDesk;

    @Column(name = "section_name")
    private String sectionName;

    @Column(name = "sub_section_name")
    private String subsectionName;

    @Column(name = "pub_date")
    @Temporal(TemporalType.DATE)
    private Date pubDate;

    @Column(name = "web_url", length = 200)
    private String webUrl;

    @Column(name = "snippet", length = 2000)
    private String snippet;

    @Column(name = "headline")
    private String headLine;

//    @OneToMany(mappedBy="id",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "article_keyword", joinColumns = { @JoinColumn(name = "article_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "keyword_id") })
    private Set<Keyword> keywords;

    public Article(String article_id) {
        this.articleId = article_id;
    }

    public Article(String article_id, String newsDesk, String sectionName, String subsectionName, Date pubDate, String webUrl, String snippet, String headLine, Set<Keyword> keywords) {
        this.articleId = article_id;
        this.newsDesk = newsDesk;
        this.sectionName = sectionName;
        this.subsectionName = subsectionName;
        this.pubDate = pubDate;
        this.webUrl = webUrl;
        this.snippet = snippet;
        this.headLine = headLine;
        this.keywords = keywords;
    }


    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getHeadLine() {
        return headLine;
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }
}
