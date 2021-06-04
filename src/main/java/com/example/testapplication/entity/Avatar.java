package com.example.testapplication.entity;

import javax.persistence.*;
import java.util.Date;

@Entity// This tells Hibernate to make a table out of this class
@Table(name = "avatar")
public class Avatar {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String fileUri;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }
}
