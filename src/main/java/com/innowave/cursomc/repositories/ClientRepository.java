package com.innowave.cursomc.repositories;

import com.innowave.cursomc.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    @Transactional(readOnly = true)
    Client findByEmail(String email);
	
}
