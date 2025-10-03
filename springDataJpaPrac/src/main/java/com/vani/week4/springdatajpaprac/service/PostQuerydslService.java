package com.vani.week4.springdatajpaprac.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vani.week4.springdatajpaprac.entity.QPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.vani.week4.springdatajpaprac.entity.QPost.post;
// 특정 클래스의 static 멤버(필드나 메서드)를 사용할 때 클래스 이름 없이 바로 접근할 수 있도록 해주는 구문

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQuerydslService {
    private final JPAQueryFactory queryFactory;

    public Long countALLPostsWithCustomAlias() {
        QPost post = new QPost("p");
        return queryFactory
                .select(post.count())
                .from(post)
                .fetchOne();
    }

    public Long countAllPostsWithDefaultAlias() {
        QPost post = QPost.post;
        return queryFactory
                .select(post.count())
                .from(post)
                .fetchOne();
    }

    // static import 방식
    public Long countAllPostsWithStaticImport() {
        return queryFactory
                .select(post.count())
                .from(post)
                .fetchOne();
    }
}
