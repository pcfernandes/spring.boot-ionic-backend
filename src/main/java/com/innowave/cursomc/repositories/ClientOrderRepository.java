package com.innowave.cursomc.repositories;

import com.innowave.cursomc.domain.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder,Integer> {
	
}
