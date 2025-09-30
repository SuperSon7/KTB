package com.vani.week4.sb_api.user.entity;

import com.vani.week4.sb_api.user.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    protected User() {}
    public User(String id, String email, String provider) {
        this.id = id;
        this.email = email;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.provider = provider;
        this.userStatus = UserStatus.ACTIVE;
    }
}
