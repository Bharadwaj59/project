package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
