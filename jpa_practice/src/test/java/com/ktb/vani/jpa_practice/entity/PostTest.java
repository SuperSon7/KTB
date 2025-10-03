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
class PostTest {
    @PersistenceContext
    EntityManager en;

    @Test
    @Rollback(false)
    void unidirectionalMTOTest() {
        // 유저 저장
        User user = new User("akjkj113jfojll", "Test@Test.Test", "Google" );
        en.persist(user);
        en.flush();

        // 게시글 저장
        Post post = new Post(user, "qhjd1ikkd11", "실험중입니다.");
        en.persist(post);
        en.flush();

        // 1차 캐기 초기화 후 조회 DB에서 데이터 가져오도록
        en.clear();
        Post findPost = en.find(Post.class, post.getId());
        System.out.println("findPost.getId() :" + findPost.getId());
        System.out.println("findPost.getTitle() :" + findPost.getTitle());
        System.out.println("findPost.getUser() :" + findPost.getUser().getEmail());

    }
    @Test
    @Rollback(false)
    void bidirctionalOTMTest() {
        // 유저 생성 저장
        User user = new User("bidirctional111", "Test2@Test.Test",  "Naver");
        en.persist(user);

        // 게시글 3개 생성
        Post noticePost = new Post(user, "notice1", "공지사항 : 테스트입니다.");
        Post post1 = new Post(user, "free1", "자유로운 게시글입니다.");
        Post post2 = new Post(user, "free2", "덜 자유로운 게시글입니다.");

        //연관 관계 설정(편의 메서드)
        user.addPost(noticePost);
        user.addPost(post1);
        user.addPost(post2);
        //게시글 저장
        en.persist(noticePost);
        en.persist(post1);
        en.persist(post2);
        // post.user 에 FK값이 들어가므로 persist 시 INSERT 쿼리에 포함

        // flush로 DB 반영 후 clear로 영속성 컨텍스트 초기화
        en.flush();
        en.clear();

        // 유저 다시 조회 후 posts 컬렉션으로 연관 게시글 확인
        User findUser =  en.find(User.class, user.getId());
        System.out.println("조회된 유저 이메일 : " + findUser.getEmail());
        System.out.println("연관된 게시글 수 = " + findUser.getPosts().size());
        findUser.getPosts().forEach(post ->
                System.out.println("post.id = " + post.getId() + ", title = " + post.getTitle())
        );

        // 특정 게시글 다시 조회 후 user_id로 유저 확인
        Post findPost = en.find(Post.class, post1.getId());
        System.out.println("조회된 게시글 제목 :" + findPost.getTitle());
        System.out.println("작성자 닉네임 = " + findPost.getUser().getEmail());

    }
}