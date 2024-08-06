package com.thymeleafBlog.dao.impl;

import com.thymeleafBlog.dao.UserDao;
import com.thymeleafBlog.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    public UserDaoImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public User findUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);

        User theUser = null;
        try{
            theUser = query.getSingleResult();
        }catch(Exception e){
            theUser = null;
        }

        return theUser;
    }

    @Override
    public User findUserByFullName(String fullName) {
        TypedQuery<User> query = entityManager.createQuery("from User where full_name = :fullName", User.class);
        query.setParameter("fullName", fullName);

        User theUser = null;
        try{
            theUser = query.getSingleResult();
        }catch(Exception e){
            theUser = null;
        }

        return theUser;
    }

    @Override
    @Transactional
    public void save(User theUser) {
        entityManager.merge(theUser);
    }
}
