package com.jawad.Instagram.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private String postData;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post(String postData, User user) {
        this.createdDate = new Timestamp(new Date().getTime());
        this.updatedDate = new Timestamp(new Date().getTime());
        this.postData = postData;
        this.user = user;
    }
}
