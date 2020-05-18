package com.innowave.cursomc.DTO;

import com.innowave.cursomc.domain.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor

public class CategoryDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Mandatory field")
    @Length(min=5, max = 80, message = "The name must have more than 5 characters and less than 80")
    private String name;

    public CategoryDTO(Category obj){
        this.id = obj.getId();

        this.name = obj.getName();
    }
}

