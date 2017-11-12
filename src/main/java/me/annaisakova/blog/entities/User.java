package me.annaisakova.blog.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.annaisakova.blog.entities.enums.UserRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "blog", type = "users", shards = 1, replicas = 0)
public class User {

    @Id
    private String id;
    private String login;
    private String password;
    private UserRole role;

    public User(String id, String login, String password, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
