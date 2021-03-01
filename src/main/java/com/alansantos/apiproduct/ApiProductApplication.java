package com.alansantos.apiproduct;

import com.alansantos.apiproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApiProductApplication   {

	public static void main(String[] args) {
		SpringApplication.run(ApiProductApplication.class, args);
	}

	@Autowired
	private ProductRepository productRepository;


}
