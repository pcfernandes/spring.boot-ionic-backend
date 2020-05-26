package com.innowave.cursomc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

    @Email(message = "Invalid email")
    @NotEmpty(message = "Mandatory field")
    private String email;
}
