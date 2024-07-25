package com.springboot.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.ecommerce.entity.Login;
import com.springboot.ecommerce.entity.Registration;
import com.springboot.ecommerce.service.RegistrationService;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {
    
    private final RegistrationService registrationService;
    
    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmer);
    }
   
    // Display the registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        Registration registration = new Registration();
        model.addAttribute("registration", registration);
        return "register";
    }
    
    // Display the login form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        Login login = new Login();
        model.addAttribute("login", login);
        return "login";
    }
    
    
    
  
    // Process the registration form
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registration") Registration registration, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            return "register";
        } else {
            registrationService.save(registration);
            return "redirect:/login";
        }
    }
}
