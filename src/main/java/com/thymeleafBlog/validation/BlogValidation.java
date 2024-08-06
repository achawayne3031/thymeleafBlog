package com.thymeleafBlog.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BlogValidation {

    private int id;

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    private String title;

    @NotNull(message = "Content is required")
    @NotEmpty(message = "Content is required")
    private String content;

    public BlogValidation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
