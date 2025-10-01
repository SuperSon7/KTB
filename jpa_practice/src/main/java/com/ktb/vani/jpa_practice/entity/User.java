package com.ktb.vani.jpa_practice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User와 Post 엔티티 구현하기
 */
@Entity
@Getter @Setter
@Table(name = "users")
public class User {
    @Id
    @Column(length = 26) //ULID
    private String id;

    @Column(length = 100)
    private String email;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

    @Column(length = 20)
    private String provider;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    protected User() {}
    public User(String id, String email, String provider) {
        this.id = id;
        this.email = email;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.provider = provider;
        this.userStatus = UserStatus.ACTIVE;
    }

    //편의 메서드 : 양쪽 동기화
    public void addPost(Post post) {
        this.posts.add(post);
        post.setUser(this);
    }

    public void removePost(Post post) {
        this.posts.remove(post);
        post.setUser(null);
    }
}
