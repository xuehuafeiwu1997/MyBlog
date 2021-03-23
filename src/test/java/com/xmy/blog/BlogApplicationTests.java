package com.xmy.blog;

import com.xmy.blog.entity.User;
import com.xmy.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }


    @Test
    void testUser() {
        System.out.println("成功执行该方法");
        User user = userService.checkUser("xmy","123456");
        System.out.println(user.getNickname()+user.getPassword());
    }
}
