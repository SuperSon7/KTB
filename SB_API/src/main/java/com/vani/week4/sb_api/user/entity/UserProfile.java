package com.vani.week4.sb_api.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserProfile {
    @Id
    @GeneratedValue
    private Long id;

    private String profileImageUrl;

    @OneToOne(mappedBy = "userProfile")
    private User user;

    protected  UserProfile() {
    }
    public UserProfile(User user, String profileImageUrl) {
        this.user = user;
        this.profileImageUrl = profileImageUrl;
    }


}
