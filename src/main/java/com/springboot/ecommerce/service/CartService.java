package com.springboot.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.ecommerce.entity.Product;

@Service
public class CartService {
    private List<Product> cart = new ArrayList<>();

    public void addToCart(Product product) {
        cart.add(product);
    }

    public List<Product> getCartProducts() {
        return new ArrayList<>(cart);
    }
}