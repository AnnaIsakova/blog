package me.annaisakova.blog.services;


import me.annaisakova.blog.BlogApplication;
import me.annaisakova.blog.entities.User;
import me.annaisakova.blog.entities.enums.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(User.class);
        esTemplate.createIndex(User.class);
        esTemplate.putMapping(User.class);
        esTemplate.refresh(User.class);
    }

    @Test
    public void testSave(){
        User user = new User("1001", "Admin", "123", UserRole.ADMIN);

        User testUser = userService.save(user);

        assertNotNull(testUser.getId());
        assertEquals(user.getLogin(), testUser.getLogin());
        assertEquals(user.getPassword(), testUser.getPassword());
        assertEquals(user.getRole(), testUser.getRole());
    }
}
