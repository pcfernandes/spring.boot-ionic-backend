package com.innowave.cursomc.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CredentialsDTO implements Serializable {

    private String email;
    private String password;

}
