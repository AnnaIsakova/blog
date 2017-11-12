package me.annaisakova.blog.repositories;

import me.annaisakova.blog.entities.Post;
import me.annaisakova.blog.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends ElasticsearchRepository<Post, String> {
    List<Post> findAllByTitle(String title);
    List<Post> findAllByCreatedAtBetween(Long start, Long end);
    List<Post> findAllByText(String text);
    List<Post> findAllByTitleAndCreatedAtBetween(String title, Long start, Long end);
    List<Post> findAllByAuthor_Id(String authorId);
    List<Post> findAllByAuthor_IdAndCreatedAtBetween(String authorId, Long start, Long end);
}
