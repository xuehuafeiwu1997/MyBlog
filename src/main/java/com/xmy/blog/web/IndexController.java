package com.xmy.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//代表它指的是控制器
public class IndexController {
    @GetMapping("/")
    public String Index() {
        //这样会出现500错误
        int i = 9 / 0;
        return "index";
    }
}
