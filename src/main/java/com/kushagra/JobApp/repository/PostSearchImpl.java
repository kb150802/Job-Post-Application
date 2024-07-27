package com.kushagra.JobApp.repository;

import com.kushagra.JobApp.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PostSearchImpl implements  PostSearchRepository{
    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter converter;
    @Override
    public List<Post> search(String text) {

        MongoDatabase database = client.getDatabase("JobApplication");
        MongoCollection<Document> collection = database.getCollection("JobPosts");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$search",
                new Document("index", "default")
                .append("text",
                new Document("query", text)
                .append("path", Arrays.asList("profile", "desc", "exp", "techs")))),
                new Document("$sort",
                new Document("exp", 1L)),
                new Document("$limit", 5L)));

        List<Post> posts = new ArrayList<>();
        result.forEach(doc -> posts.add(converter.read(Post.class, doc)));
        return posts;
    }
}
