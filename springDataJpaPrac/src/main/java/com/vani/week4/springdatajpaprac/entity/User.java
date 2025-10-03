package com.vani.week4.springdatajpaprac.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private String nickname;

    @OneToMany(mappedBy = "author")
    List<Post> posts = new ArrayList<>();

    protected User() {}
    
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
