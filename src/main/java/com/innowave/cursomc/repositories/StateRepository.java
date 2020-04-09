package com.innowave.cursomc.repositories;

import com.innowave.cursomc.domain.City;
import com.innowave.cursomc.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State,Integer> {
	
}
