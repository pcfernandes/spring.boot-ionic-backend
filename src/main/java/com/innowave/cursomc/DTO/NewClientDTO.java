package com.innowave.cursomc.DTO;

import com.innowave.cursomc.services.validations.ClientInsert;
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
@ClientInsert
public class NewClientDTO  implements Serializable {

    //Client Info
    @NotEmpty(message = "Mandatory field")
    @Length(min=5, max = 120, message = "The name must have more than 5 characters and less than 120")
    private String name;
    @NotEmpty(message = "Mandatory field")
    @Email(message = "Invalid email")
    private String email;
    @NotEmpty(message = "Mandatory field")
    private String nif;
    private Integer type;

    //Address Info
    @NotEmpty(message = "Mandatory field")
    private String streetName;
    @NotEmpty(message = "Mandatory field")
    private String houseNumber;
    private String houseDoor;
    private String streetNeighbourhood;
    @NotEmpty(message = "Mandatory field")
    private String postalCode;
    //City Info
    private Integer cityId;

    //Phone Info
    @NotEmpty(message = "Mandatory field")
    private String phone1;
    private String phone2;
    private String phone3;



}
