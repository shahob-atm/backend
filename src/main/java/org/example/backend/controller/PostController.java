package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.service.post.PostService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public HttpEntity<?> handleGetPosts(){
        return postService.handleGetService();
    }
}
