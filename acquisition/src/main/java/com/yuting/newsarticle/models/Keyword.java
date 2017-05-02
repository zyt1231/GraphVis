package com.yuting.newsarticle.models;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
/**
 * Created by Ting on 4/26/17.
 */

@Entity
@Table(name = "keyword")
public class Keyword {
    @Id
    @Column(name = "keyword_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "users_id_seq")
    private Integer kId;

    @Column(name = "type")
    private String type;

    @Column(name = "value", unique = true)
    private String value;

//    @Column(name = "comment")
//    private String comment;


    public Keyword() {}

    public Keyword(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public Integer getkId() {
        return kId;
    }

    public void setkId(Integer kId) {
        this.kId = kId;
    }

    public String getType() {
        return type;
    }
    public void setType( String type ) {
        this.type = type;
    }
    public String getValue() {
        return value;
    }
    public void setValue( String value ) {
        this.value = value;
    }

}