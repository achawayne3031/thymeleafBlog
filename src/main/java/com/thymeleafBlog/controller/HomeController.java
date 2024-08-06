package com.thymeleafBlog.controller;

import com.thymeleafBlog.entity.Blog;
import com.thymeleafBlog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    private BlogService blogService;

    @Autowired
    public HomeController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/")
    public String home(Model theModel){

        Page<Blog> pagerBlog = blogService.paginatedBlog(0, 3);

       // List<Blog> blogs = blogService.allBlog();

        theModel.addAttribute("blogs", pagerBlog);

        return "home";
    }
}
