package com.kushagra.JobApp.controller;

import com.kushagra.JobApp.repository.PostRepository;
import com.kushagra.JobApp.model.Post;
import com.kushagra.JobApp.repository.PostSearchImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostSearchImpl searchPostRepo;
    @GetMapping("/posts")
    private List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    @GetMapping("/posts/{text}")
    private  List<Post> search(@PathVariable String text) {
        System.out.println(text);
        return searchPostRepo.search(text);
    }
    @PostMapping("/post")
    private Post addPost(@RequestBody Post post) {
        return postRepository.save(post);

    }

}
