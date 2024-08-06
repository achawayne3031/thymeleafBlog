package com.thymeleafBlog.controller;


import com.thymeleafBlog.entity.User;
import com.thymeleafBlog.service.UserService;
import com.thymeleafBlog.validation.UserValidation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
public class AuthController {

    private Logger logger = Logger.getLogger(getClass().getName());

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @GetMapping("/register/show-registration-form")
    public String showRegisterPage(Model theModel) {

        theModel.addAttribute("webUser", new UserValidation());
        return "auth/register";
    }

    @GetMapping("/show-login-form")
    public String showMyLoginPage(Model theModel) {

        theModel.addAttribute("webUser", new UserValidation());
        return "auth/login";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage(){
        return "auth/access-denied";
    }



    @PostMapping("/register/process-registration-form")
    public String processRegistrationForm(
            @Valid @ModelAttribute("webUser") UserValidation theWebUser,
            BindingResult theBindingResult,
            HttpSession session, Model theModel) {

        String email = theWebUser.getEmail();
        logger.info("Processing registration form for: " + email);

        // form validation
        if (theBindingResult.hasErrors()){
            return "auth/register";
        }

        // check the database if user already exists
        User existing = userService.findByEmail(email);
        if (existing != null){
            theModel.addAttribute("webUser", new UserValidation());
            theModel.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "auth/register";
        }

        // create user account and store in the databse
        userService.save(theWebUser);

        logger.info("Successfully created user: " + email);

        // place user in the web http session for later use
        session.setAttribute("user", theWebUser);

        return "auth/login";
    }


}
