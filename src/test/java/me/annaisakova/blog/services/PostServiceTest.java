package me.annaisakova.blog.services;


import me.annaisakova.blog.BlogApplication;
import me.annaisakova.blog.entities.Post;
import me.annaisakova.blog.services.impl.PostServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class PostServiceTest {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(Post.class);
        esTemplate.createIndex(Post.class);
        esTemplate.putMapping(Post.class);
        esTemplate.refresh(Post.class);
    }


    @Test
    public void testSave(){
        Post post = new Post("222", "Post title", "Post text. Very very long text.");
        post.setCreatedAt(System.currentTimeMillis());

        Post testPost = postService.save(post);

        assertNotNull(testPost.getId());
        assertEquals(post.getTitle(), testPost.getTitle());
        assertEquals(post.getText(), testPost.getText());
        assertEquals(post.getCreatedAt(), testPost.getCreatedAt());
    }

    @Test
    public void testDelete(){
        Post post = new Post("111", "Post title", "Post text. Very very long text.");
        postService.save(post);
        postService.delete(post);
        Post testPost = postService.findOne(post.getId());
        assertNull(testPost);
    }

    @Test
    public void testFindOne(){
        Post post = new Post("111", "Post title", "Post text. Very very long text.");
        postService.save(post);
        Post testPost = postService.findOne(post.getId());

        assertNotNull(testPost.getId());
        assertEquals(post.getTitle(), testPost.getTitle());
        assertEquals(post.getText(), testPost.getText());
        assertEquals(post.getCreatedAt(), testPost.getCreatedAt());
    }

    @Test
    public void testFindAll(){
        Post post1 = new Post("1", "Post title", "Post text. Very very long text.");
        Post post2 = new Post("2", "Post title", "Post text. Very very long text.");
        Post post3 = new Post("3", "Post title", "Post text. Very very long text.");
        Post post4 = new Post("4", "Post title", "Post text. Very very long text.");
        Post post5 = new Post("5", "Post title", "Post text. Very very long text.");
        postService.save(post1);
        postService.save(post2);
        postService.save(post3);
        postService.save(post4);
        postService.save(post5);

        Iterable<Post> posts = postService.findAll();
        int counter = 0;
        for (Post post : posts) {
            counter++;
        }
        assertEquals(5, counter);
    }

    @Test
    public void testFindByTitle(){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("1", "Title", "Post text. Very very long text."));
        posts.add(new Post("2", "Title", "Post text. Very very long text."));
        posts.add(new Post("3", "Title", "Post text. Very very long text."));
        posts.add(new Post("4", "not Title", "Post text. Very very long text."));
        posts.add(new Post("5", "nottitle", "Post text. Very very long text."));

        for (Post post:posts) {
            postService.save(post);
        }
        List<Post> postsByTitle = postService.findByTitle("Title");
        assertEquals(4, postsByTitle.size());
        postsByTitle = postService.findByTitle("not Title");
        assertEquals(1, postsByTitle.size());

    }

    @Test
    public void testFindByDates(){
        Calendar calendar = new GregorianCalendar(2017, 10, 8);
        Post post1 = new Post("1001", "Post 1", "Text text text");
        post1.setCreatedAt(calendar.getTimeInMillis());
        Post post2 = new Post("1002", "Post 2", "Text text text");
        Date today = new Date(System.currentTimeMillis());
        post2.setCreatedAt(today.getTime());
        postService.save(post1);
        postService.save(post2);

        System.out.println(System.currentTimeMillis());
        System.out.println(calendar.getTimeInMillis());
        System.out.println(today.getTime());
        List<Post> posts = postService.findByDates(calendar.getTimeInMillis(), calendar.getTimeInMillis() + 86399999);
        assertEquals(1, posts.size());

        posts = postService.findByDates(calendar.getTimeInMillis(), today.getTime());
        assertEquals(2, posts.size());
    }

    @Test
    public void testFindByText(){
        Post post1 = new Post("1001", "Post 1", "Text text text");
        Post post2 = new Post("1002", "Post 2", "Hello, world!");
        postService.save(post1);
        postService.save(post2);

        List<Post> posts = postService.findByText("hello");
        assertEquals(1, posts.size());
    }

    @Test
    public void testFindByTitleAndDates(){
        Post post1 = new Post("1001", "Post 1", "Text text text");
        Calendar calendar = new GregorianCalendar(2017, 10, 8);
        post1.setCreatedAt(calendar.getTimeInMillis());
        postService.save(post1);

        List<Post> posts = postService.findByTitleAndDates("Post", calendar.getTimeInMillis(), calendar.getTimeInMillis() + 86399999);
        assertEquals(1, posts.size());

        posts = postService.findByTitleAndDates("Post 2", calendar.getTimeInMillis(), calendar.getTimeInMillis() + 86399999);
        assertEquals(0, posts.size());

        posts = postService.findByTitleAndDates("Post 1", System.currentTimeMillis(), System.currentTimeMillis() + 86399999);
        assertEquals(0, posts.size());
    }
}
