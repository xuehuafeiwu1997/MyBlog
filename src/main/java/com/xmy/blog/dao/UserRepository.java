package com.xmy.blog.dao;import com.xmy.blog.po.User;import org.springframework.data.jpa.repository.JpaRepository;/** * @author xmy * @date 2021/3/9 11:33 下午 *///使用JPA操作数据库public interface UserRepository extends JpaRepository<User,Long> {    User findByUsernameAndPassword(String username, String password);}