package com.thymeleafBlog.controller;


import com.thymeleafBlog.entity.Blog;
import com.thymeleafBlog.entity.User;
import com.thymeleafBlog.service.BlogService;
import com.thymeleafBlog.validation.BlogValidation;
import com.thymeleafBlog.validation.UserValidation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/blog")
public class BlogController {


    private Logger logger = Logger.getLogger(getClass().getName());

    private BlogService blogService;


    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/add-blog-page")
    public String showAddBlogForm(Model theModel){

        theModel.addAttribute("blogData", new BlogValidation());
        return "blog/add-blog";
    }


    @PostMapping("/process-add-blog")
    public String processAddBlog(
            @Valid @ModelAttribute("blogData") BlogValidation theBlog,
            BindingResult theBindingResult,
            Model theModel,
            HttpSession session
    ){

        logger.info("Processing add blog form...... ");

        // form validation ////
        if (theBindingResult.hasErrors()){
            return "blog/add-blog";
        }

        String title = theBlog.getTitle();

        // check the database if title already exists
        Blog existing = blogService.findBlogByTitle(title);

        if (existing != null){
            theModel.addAttribute("blogData", new BlogValidation());
            theModel.addAttribute("addBlogError", "Title already exists.");

            logger.warning("Title already exists.");
            return "blog/add-blog";
        }

        // create blog and store in the databse
        blogService.save(theBlog, session);


        return "redirect:/";
    }



    @GetMapping("/view")
    public String viewBlog(@RequestParam("blogId") int blogId, Model theModel){

        Blog blog = blogService.findBlogById(blogId);
        theModel.addAttribute("blog", blog);

        return "blog/view-blog";
    }


    @GetMapping("/delete")
    public String deleteBlog(
            @RequestParam("blogId") int blogId
    ){

        blogService.deleteBlogById(blogId);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateBlogForm(
            @RequestParam("blogId") int blogId,
            Model theModel
    ){
        Blog blog = blogService.findBlogById(blogId);

        theModel.addAttribute("blogData", blog);

        return "blog/add-blog";

    }



}
