package com.alansantos.apiproduct.model.dto;

import com.alansantos.apiproduct.model.entity.Product;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotEmpty(message = "Campo obrigatório!")
    private String name;

    @NotEmpty(message = "Campo obrigatório.")
    private String description;

    @NotNull(message = "Campo Obrigtório.")
    private Double price;

}
