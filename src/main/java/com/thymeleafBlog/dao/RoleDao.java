package com.thymeleafBlog.dao;

import com.thymeleafBlog.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);

}
