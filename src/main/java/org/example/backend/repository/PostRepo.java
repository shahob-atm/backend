package org.example.backend.repository;

import org.example.backend.entity.Post;
import org.example.backend.projection.post.PostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    String get_all = "select\n" +
            "p.id,\n" +
            "p.title,\n" +
            "p.content,\n" +
            "u.first_name,\n" +
            "u.last_name\n" +
            "from post p join users u on p.author_id = u.id";

    @Query(value = get_all, nativeQuery = true)
    List<PostProjection> getPostProjections();
}
