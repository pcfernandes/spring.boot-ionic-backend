package com.innowave.cursomc.services.validations;

import com.innowave.cursomc.DTO.ClientDTO;
import com.innowave.cursomc.domain.Client;
import com.innowave.cursomc.domain.enums.ClientType;
import com.innowave.cursomc.repositories.ClientRepository;
import com.innowave.cursomc.resources.exceptions.FieldMessage;
import com.innowave.cursomc.services.validations.utils.PT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void initialize(ClientUpdate ann) {
    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Map<String,String> map = ( Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriID = Integer.parseInt(map.get("id"));

        Client client = clientRepository.findByEmail(objDto.getEmail());

        if(client != null && !client.getId().equals(uriID)){
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
