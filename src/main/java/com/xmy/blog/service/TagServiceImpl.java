package com.xmy.blog.service;import com.xmy.blog.NotFounfExcetion;import com.xmy.blog.dao.TagRepository;import com.xmy.blog.po.Tag;import org.springframework.beans.BeanUtils;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.ArrayList;import java.util.List;/** * @author xmy * @date 2021/3/17 10:28 上午 */@Servicepublic class TagServiceImpl implements TagService {    @Autowired    private TagRepository tagRepository;    //将这些操作放入事务中    @Transactional    @Override    public Tag saveTag(Tag tag) {        return tagRepository.save(tag);    }    @Transactional    @Override    public Tag getTag(Long id) {        return tagRepository.getOne(id);    }    @Transactional    @Override    public Tag getTagByName(String name) {        return tagRepository.findByName(name);    }    @Transactional    @Override    public Page<Tag> listTag(Pageable pageable) {        return tagRepository.findAll(pageable);    }    @Transactional    @Override    public List<Tag> listTag() {        return tagRepository.findAll();    }    @Transactional    @Override    public List<Tag> listTag(String ids) {        return tagRepository.findAllById(convertToList(ids));    }    //将字符串转变为数组，供上面的方法使用    private  List<Long> convertToList(String ids) {        List<Long> list = new ArrayList<>();        if (!"".equals(ids) && ids != null) {            String[] idarray = ids.split(",");            for (int i = 0; i < idarray.length; i++) {                list.add(new Long(idarray[i]));            }        }        return list;    }    @Transactional    @Override    public Tag updateType(Long id, Tag tag) {        Tag tag1 = tagRepository.getOne(id);        if (tag1 == null) {            throw new NotFounfExcetion("不存在该类型");        }        BeanUtils.copyProperties(tag,tag1);        return tagRepository.save(tag1);    }    @Transactional    @Override    public void deleteTag(Long id) {        tagRepository.deleteById(id);    }}