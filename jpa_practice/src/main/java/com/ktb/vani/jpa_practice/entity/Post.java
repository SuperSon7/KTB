package com.ktb.vani.jpa_practice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


/*
데이터베이스 jpa_practice 에 post 테이블 생성
스프링 코드에 post 엔티티 생성
Post 엔티티에서 user 엔티티를 참조하는 필드를 만들어보세요
 */
@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @Column(length = 26)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 100)
    private String title;

    private Integer view_count;
    private Integer like_count;
    private Integer comment_count;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    //기본 생성자는 JPA의 필수 요구사항 무분별한 생성 방지 위해 public은 잘 안씀
    protected Post() {}

    public Post(User user, String id, String title) {
        this.id = id; //실제 구현시에는 ULID라이브러리 가져와야함.
        this.user = user;
        this.title = title;
        this.view_count = 1;
        this.comment_count = 1;
        this.like_count = 1;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.status = PostStatus.ACTIVE;
    }
}
