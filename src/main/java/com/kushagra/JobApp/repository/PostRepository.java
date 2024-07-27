package com.kushagra.JobApp.repository;

import com.kushagra.JobApp.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PostRepository extends MongoRepository<Post, String> {
}
