package com.xmy.blog.vo;/** * @author xmy * @date 2021/3/23 7:12 下午 */public class SearchBlog {    private String title;    private Long typeId;    public SearchBlog() {    }    public String getTitle() {        return title;    }    public void setTitle(String title) {        this.title = title;    }    public Long getTypeId() {        return typeId;    }    public void setTypeId(Long typeId) {        this.typeId = typeId;    }    @Override    public String toString() {        return "SearchBlog{" +                "title='" + title + '\'' +                ", typeId=" + typeId +                '}';    }}