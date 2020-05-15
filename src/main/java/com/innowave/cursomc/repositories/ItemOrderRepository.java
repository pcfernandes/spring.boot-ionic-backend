package com.innowave.cursomc.repositories;

import com.innowave.cursomc.domain.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrder,Integer> {
	
}
