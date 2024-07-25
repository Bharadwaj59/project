package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
