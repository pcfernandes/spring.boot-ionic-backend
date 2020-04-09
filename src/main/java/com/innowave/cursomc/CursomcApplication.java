package com.innowave.cursomc;

import com.innowave.cursomc.domain.Category;
import com.innowave.cursomc.domain.City;
import com.innowave.cursomc.domain.Product;
import com.innowave.cursomc.domain.State;
import com.innowave.cursomc.repositories.CategoryRepository;
import com.innowave.cursomc.repositories.CityRepository;
import com.innowave.cursomc.repositories.ProductRepository;
import com.innowave.cursomc.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1  = new Category(null,"Informatics");
		Category cat2  = new Category(null,"Office");
		
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		State est1 = new State(null, "Centro");
		State est2 = new State(null, "Algarve");

		City c1 = new City(null, "Lisboa", est1);
		City c2 = new City(null, "Tavira", est2);
		City c3 = new City(null, "Faro", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2,c3));

		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		stateRepository.saveAll(Arrays.asList(est1,est2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
	}

}
