package com.thymeleafBlog.dao.impl;

import com.thymeleafBlog.dao.BlogDao;
import com.thymeleafBlog.entity.Blog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogDaoImpl implements BlogDao {

    private EntityManager entityManager;

    @Autowired
    public BlogDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public Blog findBlogByTitle(String title) {

        TypedQuery<Blog> query = entityManager.createQuery("from Blog where title = :title", Blog.class);
        query.setParameter("title", title);

        Blog blog = null;

        try{
            blog = query.getSingleResult();
        }catch (Exception e){
            blog = null;
        }

        return blog;
    }

    @Override
    public Blog findBlogById(int blogId) {
        TypedQuery<Blog> query = entityManager.createQuery("from Blog where id = :id", Blog.class);
        query.setParameter("id", blogId);

        Blog blog = null;

        try{
            blog = query.getSingleResult();
        }catch (Exception e){
            blog = null;
        }

        return blog;
    }

    @Override
    @Transactional
    public void save(Blog blog) {
        entityManager.merge(blog);
    }

    @Override
    public List<Blog> allBlog() {

        TypedQuery<Blog> query = entityManager.createQuery(
                "select b from Blog b " +
                        "JOIN FETCH b.user", Blog.class);
        List<Blog> blog = null;

        try{
            blog = query.getResultList();

        }catch (Exception e){
            blog = null;
        }

        return blog;
    }

    @Override
    @Transactional
    public void deleteBlogById(int blogId) {
        Blog blog = entityManager.find(Blog.class, blogId);

        entityManager.remove(blog);
    }


}
