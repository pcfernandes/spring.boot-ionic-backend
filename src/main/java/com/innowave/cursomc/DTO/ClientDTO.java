package com.innowave.cursomc.DTO;

import com.innowave.cursomc.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Mandatory field")
    @Length(min=5, max = 120, message = "The name must have more than 5 characters and less than 120")
    private String name;
    @NotEmpty(message = "Mandatory field")
    @Email(message = "Invalid email")
    private String email;

    public ClientDTO (Client obj){
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
    }
}
