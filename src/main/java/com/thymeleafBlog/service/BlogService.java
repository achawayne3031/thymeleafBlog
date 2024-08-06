package com.thymeleafBlog.service;

import com.thymeleafBlog.entity.Blog;
import com.thymeleafBlog.validation.BlogValidation;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {


    Blog findBlogByTitle(String title);

    void save(BlogValidation blog, HttpSession session);

    Blog findBlogById(int blogId);

    List<Blog> allBlog();

    void deleteBlogById(int blogId);

    Page<Blog> paginatedBlog(int page, int size);
}
