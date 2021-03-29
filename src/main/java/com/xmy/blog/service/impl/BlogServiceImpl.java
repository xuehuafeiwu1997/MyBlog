package com.xmy.blog.service.impl;import com.xmy.blog.NotFounfExcetion;import com.xmy.blog.dao.BlogDao;import com.xmy.blog.entity.Blog;import com.xmy.blog.service.BlogService;import com.xmy.blog.vo.BlogQuery;import com.xmy.blog.vo.FirstPageBlog;import com.xmy.blog.vo.SearchBlog;import com.xmy.blog.vo.ShowBlog;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.Date;import java.util.List;/** * @author xmy * @date 2021/3/17 5:04 下午 */@Servicepublic class BlogServiceImpl implements BlogService {    @Autowired    private BlogDao blogDao;    @Override    public Blog getBlog(Long id) {        return blogDao.getBlog(id);    }    @Override    public ShowBlog getBlogById(Long id) {        return blogDao.getBlogById(id);    }    @Override    public List<BlogQuery> getAllBlog() {        return blogDao.getAllBlogQuery();    }    @Override    public List<BlogQuery> getAllBlogQuery() {        return null;    }    @Override    public int saveBlog(Blog blog) {        blog.setCreateTime(new Date());        blog.setUpdateTime(new Date());        blog.setViews(0);        return blogDao.saveBlog(blog);    }    @Override    public int updateBlog(ShowBlog showBlog) {        showBlog.setUpdateTime(new Date());        return blogDao.updateBlog(showBlog);    }    @Override    public void deleteBlog(Long id) {        blogDao.deleteBlog(id);    }    @Override    public List<FirstPageBlog> getFirstPageBlog() {        return blogDao.getFirstPageBlog();    }    @Override    public List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog) {        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);    }    //    @Autowired//    private BlogRepository blogRepository;////    @Transactional//    @Override//    public Blog getBlog(Long id) {//        return blogRepository.getOne(id);//    }////    @Transactional//    @Override//    public Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery) {//        //需要实现条件的动态组合//        return blogRepository.findAll(new Specification<Blog>() {//            @Override//            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {//                List<Predicate> predicate = new ArrayList<>();//                if (blogQuery.getTitle() != null && !blogQuery.getTitle().equals("")) {//                    predicate.add(criteriaBuilder.like(root.<String>get("title"),"%" + blogQuery.getTitle()));//                }//                if (blogQuery.getTypeId() != null) {//                    predicate.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blogQuery.getTypeId()));//                }//                if (blogQuery.isRecommend()) {//                    predicate.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blogQuery.isRecommend()));//                }//                criteriaQuery.where(predicate.toArray(new Predicate[predicate.size()]));//                return null;//            }//        },pageable);//    }////    @Transactional//    @Override//    public Page<Blog> listBlog(Pageable pageable, String quary) {//        return blogRepository.findByQuary(quary,pageable);//    }////    @Transactional//    @Override//    public Page<Blog> listBlog(Pageable pageable) {//        return blogRepository.findAll(pageable);//    }////    @Transactional//    @Override//    public List<Blog> listRecommendBlogTop(Integer size) {//        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");//        Pageable pageable = PageRequest.of(0,size,sort);//        return blogRepository.findTop(pageable);//    }////    @Transactional//    @Override//    public Blog save(Blog blog) {//        if (blog.getId() == null) {//            //新增的时候//            blog.setCreateTime(new Date());//            blog.setUpdateTime(new Date());//            blog.setViews(0);//        } else {//            //更新的时候//            blog.setUpdateTime(new Date());//        }//        return blogRepository.save(blog);//    }////    @Transactional//    @Override//    public Blog updateBlog(Long id, Blog blog) {//        Blog blog1 = blogRepository.getOne(id);//        if (blog1 == null) {//            throw new NotFounfExcetion("该博客不存在");//        }//        BeanUtils.copyProperties(blog,blog1, MyBeanUtils.getNullPropertyNames(blog));//        blog1.setUpdateTime(new Date());//        return blogRepository.save(blog1);//    }//    @Transactional//    @Override//    public void deleteBlog(Long id) {//        blogRepository.deleteById(id);//    }}