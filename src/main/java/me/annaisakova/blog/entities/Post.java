package me.annaisakova.blog.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "blog", type = "posts", shards = 1, replicas = 0)
public class Post {

    @Id
    private String id;
    private String title;
    private String text;
    private User author;
    private Long createdAt;
    private Long updatedAt;

    public Post(String id, String title, String text, User author) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
