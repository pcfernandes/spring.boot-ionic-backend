package com.innowave.cursomc.services.validations;

import com.innowave.cursomc.DTO.NewClientDTO;
import com.innowave.cursomc.domain.Client;
import com.innowave.cursomc.domain.enums.ClientType;
import com.innowave.cursomc.repositories.ClientRepository;
import com.innowave.cursomc.resources.exceptions.FieldMessage;
import com.innowave.cursomc.services.validations.utils.PT;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, NewClientDTO> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(NewClientDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getType().equals(ClientType.PHYSICAL_PERSON.getCod()) && !PT.isValidPersonalNif(objDto.getNif())){
            list.add(new FieldMessage("Nif","Invalid Personal NIF"));
        }
        if(objDto.getType().equals(ClientType.LEGAL_PERSON.getCod()) && !PT.isValidCorplNif(objDto.getNif())){
            list.add(new FieldMessage("Nif","Invalid Corporate NIF"));
        }

        Client client = clientRepository.findByEmail(objDto.getEmail());

        if(client != null){
            list.add(new FieldMessage("Email","This Email already exists"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
