package com.kushagra.JobApp.repository;

import com.kushagra.JobApp.model.Post;

import java.util.List;

public interface PostSearchRepository{
    public List<Post> search(String text);
}
