package com.thymeleafBlog.repository;

import com.thymeleafBlog.entity.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Integer> {


   // List<Product> findAllByPrice(double price, Pageable pageable);



}
