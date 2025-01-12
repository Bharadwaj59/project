package com.springboot.ecommerce.entity;

import jakarta.persistence.Table;


@Table(name = "login")
public class Login {

	private String email;
	
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}

	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public Login() {
		
	}
}
