package com.alansantos.apiproduct.controllers;

import com.alansantos.apiproduct.controllers.exception.ErrorGenericException;
import com.alansantos.apiproduct.model.entity.Product;
import com.alansantos.apiproduct.model.dto.ProductDTO;
import com.alansantos.apiproduct.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<Product> list = productService.findAll();

        List<ProductDTO> listDto = list.stream()
                .map(obj -> new ProductDTO(obj))
                .collect(Collectors.toList());

        return ResponseEntity.ok(listDto);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id){
        Product product = productService.findById(id);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity save ( @Valid @RequestBody ProductDTO productDTO){

        try{
            Product product = productService.fromDTO(productDTO);
            product = productService.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(product);

        }
        catch (ErrorGenericException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @PutMapping("{id}")
    public ResponseEntity <Product> update(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id){

        Product product = productService.fromDTO(productDTO);
        product.setId(id);
        product = productService.update(product);

        return ResponseEntity.ok(product);

    }

    @DeleteMapping("{id}")
    public ResponseEntity delete (@PathVariable Long id){
        Product product = productService.delete(id);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity findByFilter (
            @RequestParam(value = "name",  required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "min_price", required = false) Double min_price,
            @RequestParam(value = "max_price", required = false) Double max_price
            ){
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(min_price);
        product.setPrice(max_price);

        List<Product> productList = productService.findByFilter(product);



        return ResponseEntity.ok(productList);
    }
}
