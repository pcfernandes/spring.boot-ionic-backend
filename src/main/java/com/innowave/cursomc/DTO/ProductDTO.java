package com.innowave.cursomc.DTO;

import com.innowave.cursomc.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private Integer id;
    private String name;
    private Double price;

    public ProductDTO(Product obj){
        this.id = obj.getId();
        this.name= obj.getName();
        this.price = obj.getPrice();
    }
}
