package me.annaisakova.blog.repositories;

import me.annaisakova.blog.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String> {

}
