package com.springboot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.ecommerce.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer>{
	

}
