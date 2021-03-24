package com.xmy.blog.web;

import com.xmy.blog.NotFounfExcetion;
import com.xmy.blog.service.BlogService;
import com.xmy.blog.service.TagService;
import com.xmy.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//代表它指的是控制器
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    //indexController中的方法还未进行修改，先将这两个方法注释掉

//    @GetMapping("/")
//    public String index(@PageableDefault(size=8,sort={"updateTime"},direction = Sort.Direction.DESC)Pageable pageable, Model model) {
//        model.addAttribute("page", blogService.listBlog(pageable));
//        model.addAttribute("types",typeService.listTypeTop(6));
//        model.addAttribute("tags",typeService.listTypeTop(10));
//        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
//        return "index";
//    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id) {
        System.out.println("当前博客的id为"+id);
        return "blog";
    }

//    @PostMapping("/search")
//    public String search(@PageableDefault(size=8,sort={"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,String query, Model model) {
//        model.addAttribute("page",blogService.listBlog(pageable,"%" + query + "%"));
//        model.addAttribute("query",query);
//        return "search";
//    }
}
