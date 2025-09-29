package com.ktb.vani.jpa_practice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * User와 Post 엔티티 구현하기
 */
@Entity
@Getter @Setter
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
    @Column(length = 10)
    private String status;

    protected User() {}
    public User(String id, String email, String provider) {
        this.id = id;
        this.email = email;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.provider = provider;
        this.status = "active";

    }

}
