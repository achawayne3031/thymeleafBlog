package com.thymeleafBlog.service.impl;

import com.thymeleafBlog.dao.RoleDao;
import com.thymeleafBlog.dao.UserDao;
import com.thymeleafBlog.entity.Role;
import com.thymeleafBlog.entity.User;
import com.thymeleafBlog.service.UserService;
import com.thymeleafBlog.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleDao roleDao;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
    }


    @Override
    public User findByFullName(String fullName) {
        return userDao.findUserByFullName(fullName);
    }

    @Override
    public User findByEmail(String userName) {
        return userDao.findUserByEmail(userName);
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findUserByEmail(userName);
    }

    @Override
    public void save(UserValidation validUser) {
        User user = new User();

        // assign user details to the user object
        user.setPassword(passwordEncoder.encode(validUser.getPassword()));
        user.setFullName(validUser.getFullName());
        user.setEmail(validUser.getEmail());
        user.setEnabled(true);

        // give user default role of "employee"
        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));

        // save user in the database
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
