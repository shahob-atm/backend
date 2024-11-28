package org.example.backend.service.post;

import lombok.RequiredArgsConstructor;
import org.example.backend.repository.PostRepo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    @Override
    public HttpEntity<?> handleGetService() {
        return ResponseEntity.ok(postRepo.getPostProjections());
    }
}
