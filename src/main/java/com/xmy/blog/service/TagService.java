package com.xmy.blog.service;import com.xmy.blog.entity.Tag;import com.xmy.blog.entity.Type;import java.util.List;/** * @author xmy * @date 2021/3/17 10:19 上午 */public interface TagService {    int saveTag(Tag tag);    Tag getTag(Long id);    List<Tag>getAllTag();    List<Tag>getAllTagAndBlog();    Tag getTagByName(String name);    int updateTag(Tag tag);    void deleteTag(Long id);}