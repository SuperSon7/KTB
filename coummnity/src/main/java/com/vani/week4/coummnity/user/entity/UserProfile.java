package com.vani.week4.coummnity.user.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="userprofiles")
public class UserProfile {

    @Id
    @Column(length = 26) //ULID
    @JoinColumn(name = "id")
    private User user;

    @Column()

}
