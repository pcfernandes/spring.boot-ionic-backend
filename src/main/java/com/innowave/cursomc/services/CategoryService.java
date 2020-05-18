package com.innowave.cursomc.services;

import com.innowave.cursomc.domain.Category;
import com.innowave.cursomc.repositories.CategoryRepository;
import com.innowave.cursomc.services.exceptions.DataIntegrityException;
import com.innowave.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Category.class.getName()));
	}

	public Category insert(Category obj){

		obj.setId(null);
		return repo.save(obj);
	}

	public Category update(Category obj){

		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id){
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e ){
			throw new DataIntegrityException("Not possible to delete a category that contains products.");
		}
	}

	public List<Category> findAll(){
		return repo.findAll();
	}
}
