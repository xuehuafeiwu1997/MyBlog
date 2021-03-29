package com.xmy.blog.dao;import com.xmy.blog.entity.Blog;import com.xmy.blog.vo.BlogQuery;import com.xmy.blog.vo.FirstPageBlog;import com.xmy.blog.vo.SearchBlog;import com.xmy.blog.vo.ShowBlog;import org.apache.ibatis.annotations.Mapper;import java.util.List;/** * @author xmy * @date 2021/3/23 5:58 下午 */@Mapperpublic interface BlogDao {    Blog getBlog(Long id);    ShowBlog getBlogById(Long id);    List<Blog> getAllBlog();    List<BlogQuery> getAllBlogQuery();    int saveBlog(Blog blog);    int updateBlog(ShowBlog showBlog);    void deleteBlog(Long id);    List<FirstPageBlog> getFirstPageBlog();    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);}