package com.alansantos.apiproduct.service;

import com.alansantos.apiproduct.model.entity.Product;
import com.alansantos.apiproduct.model.dto.ProductDTO;
import com.alansantos.apiproduct.repository.ProductRepository;
import com.alansantos.apiproduct.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {

        return repository.findAll();
    }

    public Product findById (Long id){
        Optional<Product> product =  repository.findById(id);

        return product.orElseThrow(() -> new ObjectNotFoundException(
                "Produto com o ID " + id  + " não encontrado!"
        ));
    }

    @Transactional
    public Product save (Product product){

        return repository.save(product);
    }

    @Transactional
    public Product update (Product product){
        product.setId(product.getId());
        product = repository.save(product);

        return repository.save(product);
    }

    @Transactional
    public Product delete (Long id){
        Product product =  findById(id);
        repository.deleteById(product.getId());

        return product;

    }

    //converter para DTO
    public Product fromDTO(ProductDTO productDTO){
        Product product = Product
                .builder()
                .id(productDTO.getId())
                .description(productDTO.getDescription())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
        return product;
    }

    @Transactional(readOnly = true)
    public List<Product> findByFilter (Product productFilter){
        Example example = Example.of(productFilter, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));


        return repository.findAll(example);

    }


}
