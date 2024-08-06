package com.thymeleafBlog.dao;

import com.thymeleafBlog.entity.Blog;

import java.util.ArrayList;
import java.util.List;

public interface BlogDao {

    Blog findBlogByTitle(String title);

    Blog findBlogById(int blogId);

    void save(Blog blog);

    List<Blog> allBlog();

    void deleteBlogById(int blogId);

}
