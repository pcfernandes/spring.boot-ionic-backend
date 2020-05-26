package com.innowave.cursomc.services;

import com.innowave.cursomc.Security.UserSS;
import com.innowave.cursomc.domain.Client;
import com.innowave.cursomc.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsSecurityImpl implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Client client = clientRepository.findByEmail(email);

        if(client == null){
            throw new UsernameNotFoundException(email);
        }

        return new UserSS(client.getId(), client.getEmail(), client.getPassword(), client.getProfiles());
    }
}
