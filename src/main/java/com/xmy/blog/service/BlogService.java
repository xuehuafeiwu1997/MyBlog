package com.xmy.blog.service;import com.xmy.blog.entity.Blog;import com.xmy.blog.vo.BlogQuery;import com.xmy.blog.vo.FirstPageBlog;import com.xmy.blog.vo.SearchBlog;import com.xmy.blog.vo.ShowBlog;import java.util.List;/** * @author xmy * @date 2021/3/17 5:01 下午 */public interface BlogService {    ShowBlog getBlogById(Long id);    List<BlogQuery> getAllBlog();    List<BlogQuery> getAllBlogQuery();    int saveBlog(Blog blog);    int updateBlog(ShowBlog showBlog);    void deleteBlog(Long id);    List<FirstPageBlog> getFirstPageBlog();    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);}