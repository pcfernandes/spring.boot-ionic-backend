package com.innowave.cursomc;

import com.innowave.cursomc.domain.*;
import com.innowave.cursomc.domain.enums.ClientType;
import com.innowave.cursomc.repositories.*;
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

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

	
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

		Client cli1 = new Client(null, "Pedro Fernandes", "pedro@gmail.com","2838475393", ClientType.PHYSICALPERSON);

		Address e1 = new Address(null, "Rua Benfica", "300", "Apt 40", "Garden", "1500-434", cli1,c1);
		Address e2 = new Address(null, "Avenida alvalade", "23", "Apt 443", "Downtown", "1500-461", cli1,c2);

		cli1.getPhones().addAll(Arrays.asList("93939393939","914575449439"));
		cli1.getAddresses().addAll(Arrays.asList(e1,e2));


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
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(e1,e2));
	}

}
