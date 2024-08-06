package com.thymeleafBlog.dao;

import com.thymeleafBlog.entity.User;

public interface UserDao {


    User findUserByEmail(String email);

    User findUserByFullName(String fullName);

    void save(User theUser);
}
