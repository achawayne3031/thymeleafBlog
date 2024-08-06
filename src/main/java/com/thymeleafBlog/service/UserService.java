package com.thymeleafBlog.service;

import com.thymeleafBlog.entity.User;
import com.thymeleafBlog.validation.UserValidation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByFullName(String fullName);

    public User findByEmail(String userName);

    public User findByUserName(String userName);

    void save(UserValidation validUser);


}
