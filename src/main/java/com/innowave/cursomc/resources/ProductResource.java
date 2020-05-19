package com.innowave.cursomc.resources;

import com.innowave.cursomc.DTO.ProductDTO;
import com.innowave.cursomc.domain.Product;
import com.innowave.cursomc.resources.utils.URL;
import com.innowave.cursomc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Product obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name")String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC")String direction )
	{
		List<Integer> categoriesIdList = URL.decodeIntList(categories);
		String nameDecoded = URL.decodeParam(name);
		Page<Product> list = service.search(nameDecoded, categoriesIdList, page,linesPerPage,orderBy,direction);
		Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDto);

	}
}
