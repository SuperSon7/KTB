package com.vani.week4.springdatajpaprac.controller;

import com.vani.week4.springdatajpaprac.service.PostQuerydslService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/qdsl/posts")
@RequiredArgsConstructor
public class PostQuerydslController {
    private final PostQuerydslService postQuerydslService;

    @GetMapping("/count")
    public Long countAllPostWithCustomAlias() {
        return postQuerydslService.countAllPostsWithStaticImport();
    }
}
