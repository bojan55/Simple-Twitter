package org.example.simpletwitter.repository;

import org.example.simpletwitter.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
