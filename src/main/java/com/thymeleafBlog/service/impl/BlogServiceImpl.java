package com.thymeleafBlog.service.impl;

import com.thymeleafBlog.dao.BlogDao;
import com.thymeleafBlog.dao.UserDao;
import com.thymeleafBlog.entity.Blog;
import com.thymeleafBlog.entity.User;
import com.thymeleafBlog.repository.BlogRepository;
import com.thymeleafBlog.security.SecurityConfig;
import com.thymeleafBlog.service.BlogService;
import com.thymeleafBlog.validation.BlogValidation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {


    private BlogDao blogDao;
    private UserDao userDao;
    private BlogRepository blogRepository;


    @Autowired
    public BlogServiceImpl(BlogDao blogDao, UserDao userDao, BlogRepository blogRepository) {
        this.blogDao = blogDao;
        this.userDao = userDao;
        this.blogRepository = blogRepository;

    }


    @Override
    public Blog findBlogByTitle(String title) {
        return blogDao.findBlogByTitle(title);
    }

    @Override
    public void save(BlogValidation blog, HttpSession session) {
        Blog blog1 = new Blog();
        blog1.setTitle(blog.getTitle());
        blog1.setContent(blog.getContent());
        blog1.setEnabled(true);


        String userEmail = SecurityConfig.getAuthenticatedUserEmail();
        User user = userDao.findUserByEmail(userEmail);

        blog1.setUser(user);

        blogDao.save(blog1);

    }

    @Override
    public Blog findBlogById(int blogId) {
        return blogDao.findBlogById(blogId);
    }

    @Override
    public List<Blog> allBlog() {
        return blogDao.allBlog();
    }

    @Override
    public void deleteBlogById(int blogId) {
        blogDao.deleteBlogById(blogId);
    }

    @Override
    public Page<Blog> paginatedBlog(int page, int size) {
        Pageable pager = PageRequest.of(page, size);

        return blogRepository.findAll(pager);
    }


}
