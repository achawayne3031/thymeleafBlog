package com.thymeleafBlog.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public class UserValidation {

    private int id;

    @NotNull(message = "full name is required")
    @Size(min = 1, message = "full name must be more than one character")
    private String fullName;

    @NotNull(message = "email is required")
    @Size(min = 1, message = "email must be more than one character")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    @NotNull(message = "password is required")
    @Size(min = 1, message = "password must be more than one character")
    private String password;

    public UserValidation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "full name is required") @Size(min = 1, message = "full name must be more than one character") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotNull(message = "full name is required") @Size(min = 1, message = "full name must be more than one character") String fullName) {
        this.fullName = fullName;
    }

    public @NotNull(message = "email is required") @Size(min = 1, message = "email must be more than one character") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "email is required") @Size(min = 1, message = "email must be more than one character") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") String email) {
        this.email = email;
    }

    public @NotNull(message = "password is required") @Size(min = 1, message = "password must be more than one character") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "password is required") @Size(min = 1, message = "password must be more than one character") String password) {
        this.password = password;
    }
}
