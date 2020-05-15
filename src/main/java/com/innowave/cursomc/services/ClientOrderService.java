package com.innowave.cursomc.services;

import com.innowave.cursomc.domain.ClientOrder;
import com.innowave.cursomc.repositories.ClientOrderRepository;
import com.innowave.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientOrderService {

	@Autowired
	private ClientOrderRepository repo;
	
	public ClientOrder find(Integer id) {
		Optional<ClientOrder> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + ClientOrder.class.getName()));
	}
	
}
