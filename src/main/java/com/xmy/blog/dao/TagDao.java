package com.xmy.blog.dao;import com.xmy.blog.entity.Tag;import com.xmy.blog.entity.Type;import org.apache.ibatis.annotations.Mapper;import java.util.List;/** * @author xmy * @date 2021/3/23 5:58 下午 */@Mapperpublic interface TagDao {    int saveTag(Tag tag);    Tag getTag(Long id);    List<Tag> getAllTag();    List<Tag> getAllTagAndBlog();    Tag getTagByName(String name);    int updateTag(Tag tag);    void deleteTag(Long id);}