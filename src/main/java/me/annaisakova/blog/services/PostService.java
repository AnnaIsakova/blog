package me.annaisakova.blog.services;

import me.annaisakova.blog.entities.Post;
import me.annaisakova.blog.entities.User;

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
    List<Post> findAllByAuthor(User author);
    List<Post> findByAuthorAndDates(User author, Long start, Long end);
}
