package com.innowave.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innowave.cursomc.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
	
}
