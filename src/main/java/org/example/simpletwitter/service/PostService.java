package org.example.simpletwitter.service;

import org.example.simpletwitter.model.Post;
import org.example.simpletwitter.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public void save(Post post){
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }
}
