package com.xmy.blog.web;

import com.xmy.blog.NotFounfExcetion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//代表它指的是控制器
public class IndexController {
    @GetMapping("/")
    public String Index() {
        //这样会出现500错误
//        int i = 9 / 0;
        String blog = null;
        if (blog == null) {
            throw new NotFounfExcetion("博客不存在");
        }
        return "index";
    }
}
