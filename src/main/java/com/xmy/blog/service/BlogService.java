package com.xmy.blog.service;import com.xmy.blog.po.Blog;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;/** * @author xmy * @date 2021/3/17 5:01 下午 */public interface BlogService {    Blog getBlog(Long id);    Page<Blog> listBlog(Pageable pageable,Blog blog);    Blog save(Blog blog);    Blog updateBlog(Long id,Blog blog);    void deleteBlog(Long id);}