package me.annaisakova.blog.services;

import me.annaisakova.blog.entities.Post;

import java.util.List;

public interface PostService {

    Post save(Post post);
    void delete(Post post);
    Post findOne(String id);
    Iterable<Post> findAll();
    List<Post> findByTitle(String title);
    List<Post> findByDates(Long start, Long end);
    List<Post> findByText(String text);
    List<Post> findByTitleAndDates(String title, Long start, Long end);
}
