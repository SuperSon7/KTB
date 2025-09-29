package com.ktb.vani.jpa_practice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @Column(length = 26)
    private String id;

    @ManyToOne
    private User user;

    @Column(length = 100)
    private String title;

    private Integer view_count;

    private Integer comment_count;

    private Integer like_count;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private LocalDateTime deleted_at;

    @Column(length = 20)
    private String status;

    //기본 생성자는 JPA의 필수 요구사항 무분별한 생성 방지 위해 public은 잘 안씀
    protected Post() {}

    public Post(User user, String title) {
        this.id = UUID.randomUUID().toString(); //실제 구현시에는 ULID라이브러리 가져와야함.
        this.user = user;
        this.title = title;
        this.view_count = 1;
        this.comment_count = 1;
        this.like_count = 1;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.status = "posted";
    }
}
