package com.xmy.blog.service.impl;import com.xmy.blog.NotFounfExcetion;import com.xmy.blog.dao.BlogRepository;import com.xmy.blog.entity.Blog;import com.xmy.blog.entity.Type;import com.xmy.blog.service.BlogService;import com.xmy.blog.vo.BlogQuary;import org.springframework.beans.BeanUtils;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Pageable;import org.springframework.data.domain.Sort;import org.springframework.data.jpa.domain.Specification;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import util.MyBeanUtils;import javax.persistence.criteria.CriteriaBuilder;import javax.persistence.criteria.CriteriaQuery;import javax.persistence.criteria.Predicate;import javax.persistence.criteria.Root;import java.util.ArrayList;import java.util.Date;import java.util.List;/** * @author xmy * @date 2021/3/17 5:04 下午 */@Servicepublic class BlogServiceImpl implements BlogService {    @Autowired    private BlogRepository blogRepository;    @Transactional    @Override    public Blog getBlog(Long id) {        return blogRepository.getOne(id);    }    @Transactional    @Override    public Page<Blog> listBlog(Pageable pageable, BlogQuary blogQuary) {        //需要实现条件的动态组合        return blogRepository.findAll(new Specification<Blog>() {            @Override            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {                List<Predicate> predicate = new ArrayList<>();                if (blogQuary.getTitle() != null && !blogQuary.getTitle().equals("")) {                    predicate.add(criteriaBuilder.like(root.<String>get("title"),"%" + blogQuary.getTitle()));                }                if (blogQuary.getTypeId() != null) {                    predicate.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),blogQuary.getTypeId()));                }                if (blogQuary.isRecommend()) {                    predicate.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blogQuary.isRecommend()));                }                criteriaQuery.where(predicate.toArray(new Predicate[predicate.size()]));                return null;            }        },pageable);    }    @Transactional    @Override    public Page<Blog> listBlog(Pageable pageable, String quary) {        return blogRepository.findByQuary(quary,pageable);    }    @Transactional    @Override    public Page<Blog> listBlog(Pageable pageable) {        return blogRepository.findAll(pageable);    }    @Transactional    @Override    public List<Blog> listRecommendBlogTop(Integer size) {        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");        Pageable pageable = PageRequest.of(0,size,sort);        return blogRepository.findTop(pageable);    }    @Transactional    @Override    public Blog save(Blog blog) {        if (blog.getId() == null) {            //新增的时候            blog.setCreateTime(new Date());            blog.setUpdateTime(new Date());            blog.setViews(0);        } else {            //更新的时候            blog.setUpdateTime(new Date());        }        return blogRepository.save(blog);    }    @Transactional    @Override    public Blog updateBlog(Long id, Blog blog) {        Blog blog1 = blogRepository.getOne(id);        if (blog1 == null) {            throw new NotFounfExcetion("该博客不存在");        }        BeanUtils.copyProperties(blog,blog1, MyBeanUtils.getNullPropertyNames(blog));        blog1.setUpdateTime(new Date());        return blogRepository.save(blog1);    }    @Transactional    @Override    public void deleteBlog(Long id) {        blogRepository.deleteById(id);    }}