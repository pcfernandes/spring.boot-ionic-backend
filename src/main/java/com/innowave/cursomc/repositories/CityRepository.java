package com.innowave.cursomc.repositories;

import com.innowave.cursomc.domain.Category;
import com.innowave.cursomc.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
	
}
