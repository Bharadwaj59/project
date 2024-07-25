package com.springboot.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.ecommerce.entity.Category;
import com.springboot.ecommerce.entity.Product;
import com.springboot.ecommerce.service.CategoryService;
import com.springboot.ecommerce.service.ProductService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        return "dashboard";
    }

    @GetMapping("/addCategory")
    public String addCategoryPage(Model model) {
    	model.addAttribute("categories", categoryService.findAll());
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.save(category);
        return "redirect:/admin/viewProducts";
    }

    @PostMapping("/deleteCategory")
    public String deleteCategory(@RequestParam Long id) {
    	categoryService.delete(id);
    	return "redirect:/admin/addCategory";
    }
    
    
    @GetMapping("/addProduct")
    public String addProductPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name, 
                             @RequestParam double price, 
                             @RequestParam String description, 
                             @RequestParam Long categoryId, 
                             @RequestParam("image") MultipartFile file) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        Category category = categoryService.findById(categoryId).orElse(null);
        product.setCategory(category);

        // Save the image and get the image URL
        String imageUrl = saveImage(file);
        product.setImageUrl(imageUrl);

        productService.save(product);
        return "redirect:/admin/viewProducts";
    }

    @GetMapping("/viewProducts")
    public String viewProducts(Model model) {
        List<Product> products = productService.findAll();
        Map<Category, List<Product>> productsByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));
        model.addAttribute("productsByCategory", productsByCategory);
        return "viewProducts";
    }
    
    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam Long id) {
    	productService.delete(id);
    	return "redirect:/admin/viewProducts";
    }
    

    private String saveImage(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get("src/main/resources/static/images/" + fileName);
            Files.write(path, file.getBytes());
            return "/images/" + fileName;  // URL path to access the image
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
