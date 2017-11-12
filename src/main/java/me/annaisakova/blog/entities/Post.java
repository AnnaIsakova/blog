package me.annaisakova.blog.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldType.*;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "blog", type = "posts", shards = 1, replicas = 0)
public class Post {

    @Id
    private String id;
    private String title;
    private String text;

//    @Field(type = Date, format = DateFormat.custom, pattern = "dd-MM-yyyy")
    private Long createdAt;

    @Field(type = Date, format = DateFormat.custom, pattern = "dd-MM-yyyy")
    private String updatedAt;

    public Post(String id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
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
