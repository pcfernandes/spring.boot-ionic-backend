package com.innowave.cursomc.services;

import com.innowave.cursomc.DTO.ClientDTO;
import com.innowave.cursomc.DTO.NewClientDTO;
import com.innowave.cursomc.domain.Address;
import com.innowave.cursomc.domain.City;
import com.innowave.cursomc.domain.Client;
import com.innowave.cursomc.domain.enums.ClientType;
import com.innowave.cursomc.repositories.AddressRepository;
import com.innowave.cursomc.repositories.ClientRepository;
import com.innowave.cursomc.services.exceptions.DataIntegrityException;
import com.innowave.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;

	@Autowired
	private AddressRepository addressRepository;

	@Transactional
	public Client insert(Client obj){

		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	}


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

	public Client fromDTO(NewClientDTO objDTO){
		Client client =  new Client(null,objDTO.getName(),objDTO.getEmail(),objDTO.getNif(), ClientType.toEnum(objDTO.getType()));
		City city = new City(objDTO.getCityId(),null,null);
		Address address = new Address(null, objDTO.getStreetName(),objDTO.getHouseNumber(),objDTO.getHouseDoor(),objDTO.getStreetNeighbourhood(),objDTO.getPostalCode(),client,city);
		client.getAddresses().add(address);
		client.getPhones().add(objDTO.getPhone1());
		if(objDTO.getPhone2()!=null){
			client.getPhones().add(objDTO.getPhone2());
		}
		if(objDTO.getPhone3()!=null){
			client.getPhones().add(objDTO.getPhone3());
		}
		return client;
	}


	private void updateDate(Client newObj, Client obj){
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
