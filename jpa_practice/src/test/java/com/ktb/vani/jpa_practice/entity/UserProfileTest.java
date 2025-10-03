package com.ktb.vani.jpa_practice.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserProfileTest {

    @PersistenceContext
    EntityManager en;

    @Test
    @Rollback(false)
    void OneToOneTest() {
        //프로필 생성
        UserProfile profile = new UserProfile();
        profile.setProfileImageUrl("https://www.KTB.com/");

        //유저 생성
        User user = new User("1t1Test111", "Test3@Test.com", "baidu");

        //연관 관계 설정 (User -> UserProfile) 실제로 매핑 해주는거
        user.setUserProfile(profile);

        //저장
        en.persist(profile); //profile 먼저 저장
        en.persist(user); // User 저장

        en.flush();
        en.clear();

        // 다시 조회
        User findUser = en.find(User.class, user.getId());
        System.out.println("조회된 유저 이메일" + findUser.getEmail());
        System.out.println("프로필 이미지 " + findUser.getUserProfile().getProfileImageUrl());

        UserProfile findProfile = en.find(UserProfile.class, profile.getId());
        System.out.println("조회된 프로필 id = " + findProfile.getId());
        System.out.println("프로필에 연결된 유저 이메일 = " + findProfile.getUser().getEmail());

    }
}