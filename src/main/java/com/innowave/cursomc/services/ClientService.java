package com.innowave.cursomc.services;

import com.innowave.cursomc.DTO.ClientDTO;
import com.innowave.cursomc.domain.Client;
import com.innowave.cursomc.domain.Client;
import com.innowave.cursomc.repositories.ClientRepository;
import com.innowave.cursomc.services.exceptions.DataIntegrityException;
import com.innowave.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}
	public Client update(Client obj){

		Client newObj = find(obj.getId());
		updateDate(newObj,obj);
		return repo.save(newObj);
	}

	public void delete(Integer id){
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e ){
			throw new DataIntegrityException("Not possible to delete a client that contains client orders.");
		}
	}

	public List<Client> findAll(){
		return repo.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO objDTO){
		return  new Client(objDTO.getId(),objDTO.getName(),objDTO.getEmail(),null,null);
	}

	private void updateDate(Client newObj, Client obj){
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
