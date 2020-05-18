package com.innowave.cursomc;

import com.innowave.cursomc.domain.*;
import com.innowave.cursomc.domain.enums.ClientType;
import com.innowave.cursomc.domain.enums.PaymentStatus;
import com.innowave.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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

	@Autowired
	private ClientOrderRepository clientOrderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ItemOrderRepository itemOrderRepository;


	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1  = new Category(null,"Informatics");
		Category cat2  = new Category(null,"Office");
		Category cat3  = new Category(null,"Bed, Table, bath");
		Category cat4  = new Category(null,"Electronics");
		Category cat5  = new Category(null,"Garden");
		Category cat6  = new Category(null,"Decor");
		Category cat7  = new Category(null,"Perfume");
		
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		State est1 = new State(null, "Centro");
		State est2 = new State(null, "Algarve");

		City c1 = new City(null, "Lisboa", est1);
		City c2 = new City(null, "Tavira", est2);
		City c3 = new City(null, "Faro", est2);

		Client cli1 = new Client(null, "Pedro Fernandes", "pedro@gmail.com","2838475393", ClientType.PHYSICAL_PERSON);

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
		
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		stateRepository.saveAll(Arrays.asList(est1,est2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(e1,e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ClientOrder ped1 = new ClientOrder(null, sdf.parse("30/09/2017 10:32"),cli1,e1);
		ClientOrder ped2 = new ClientOrder(null,sdf.parse("10/10/2017 19:35"), cli1,e2);

		Payment pagto1 = new CardPayment(null, PaymentStatus.PAYED, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new PaymentWireTransfer(null, PaymentStatus.PENDING,ped2,sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getClientOrders().addAll(Arrays.asList(ped1,ped2));

		clientOrderRepository.saveAll(Arrays.asList(ped1,ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1,pagto2));

		ItemOrder ip1 = new ItemOrder(p1,ped1,0.00,1,2000.00);
		ItemOrder ip2 = new ItemOrder(p3,ped1,0.00,2,80.00);
		ItemOrder ip3 = new ItemOrder(p2,ped2,100.00,1,800.00);


		p1.getItemOrders().addAll(Arrays.asList(ip1));
		p2.getItemOrders().addAll(Arrays.asList(ip3));
		p3.getItemOrders().addAll(Arrays.asList(ip2));

		ped2.getItemOrders().addAll(Arrays.asList(ip3));
		ped1.getItemOrders().addAll(Arrays.asList(ip1,ip2));

		itemOrderRepository.saveAll(Arrays.asList(ip1,ip2,ip3));


	}

}
