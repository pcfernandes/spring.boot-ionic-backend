package com.innowave.cursomc.DTO;

import com.innowave.cursomc.domain.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor

public class CategoryDTO implements Serializable {

    private Integer id;
    private String name;

    public CategoryDTO(Category obj){
        this.id = obj.getId();
        this.name = obj.getName();
    }
-
}

