package com.springboot.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.entity.Registration;
import com.springboot.ecommerce.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	private RegistrationRepository registrationRepository;
	
	@Autowired
	public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
		this.registrationRepository = registrationRepository;
	}
	
	@Override
	public Registration save(Registration registration) {
		return registrationRepository.save(registration);
		
	}

}
