package com.innowave.cursomc.services;

import com.innowave.cursomc.Security.UserSS;
import com.innowave.cursomc.domain.enums.Profile;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        }
        catch(Exception e){
            return null;
        }
    }

}
