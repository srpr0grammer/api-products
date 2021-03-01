package com.alansantos.apiproduct;

import com.alansantos.apiproduct.model.entity.Product;
import com.alansantos.apiproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class ApiProductApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiProductApplication.class, args);
	}

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		Product p1 = new Product(null, "Video Gamer", "Playstion 5", (5500.00));
		Product p2 = new Product(null, "Celular", "Iphone 8", (2000.00));
		Product p3 = new Product(null, "Monitor", "Monitor LG 48 PL", (3500.00));
		Product p4 = new Product(null, "Geladeira", "Geladeira Samsung", (1500.00));

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
	}
}
