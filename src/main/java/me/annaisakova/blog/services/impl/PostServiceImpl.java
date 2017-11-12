package me.annaisakova.blog.services.impl;

import me.annaisakova.blog.entities.Post;
import me.annaisakova.blog.entities.User;
import me.annaisakova.blog.repositories.PostRepository;
import me.annaisakova.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

     @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Post findOne(String id) {
        return postRepository.findOne(id);
    }

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findByTitle(String title) {
        return postRepository.findAllByTitle(title);
    }

    @Override
    public List<Post> findByDates(Long start, Long end) {
        return postRepository.findAllByCreatedAtBetween(start, end);
    }

    @Override
    public List<Post> findByText(String text) {
        return postRepository.findAllByText(text);
    }

    @Override
    public List<Post> findByTitleAndDates(String title, Long start, Long end) {
        return postRepository.findAllByTitleAndCreatedAtBetween(title, start, end);
    }

    @Override
    public List<Post> findAllByAuthor(User author) {
        return postRepository.findAllByAuthor_Id(author.getId());
    }

    @Override
    public List<Post> findByAuthorAndDates(User author, Long start, Long end) {
        return postRepository.findAllByAuthor_IdAndCreatedAtBetween(author.getId(), start, end);
    }
}
