package com.alansantos.apiproduct.controllers;

import com.alansantos.apiproduct.model.dto.ProductDTO;
import com.alansantos.apiproduct.model.entity.Product;
import com.alansantos.apiproduct.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> body =
                productService.findAll()
                        .stream().map(entity -> modelMapper.map(entity, ProductDTO.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(body);
    }

    @GetMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id){
        Product product = productService.findById(id);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save ( @Valid @RequestBody ProductDTO productDTO){
        var product = productService.save(modelMapper.map(productDTO, Product.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(product, ProductDTO.class));

    }

    @PutMapping("{id}")
    public ResponseEntity <ProductDTO> update(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id){
        productDTO.setId(id);
        var product = productService.update(modelMapper.map(productDTO, Product.class));

        return ResponseEntity.ok().body(modelMapper.map(product, ProductDTO.class));

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
