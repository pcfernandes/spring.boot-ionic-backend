package com.innowave.cursomc.services;

import com.innowave.cursomc.domain.*;
import com.innowave.cursomc.domain.enums.ClientType;
import com.innowave.cursomc.domain.enums.PaymentStatus;
import com.innowave.cursomc.domain.enums.Profile;
import com.innowave.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instantiateTestDatabase() throws ParseException {
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
        Product p4 = new Product(null, "Mesa de escritório", 300.00);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Colcha", 200.00);
        Product p7 = new Product(null, "TV true color", 1200.00);
        Product p8 = new Product(null, "Roçadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 100.00);
        Product p10 = new Product(null, "Pendente", 180.00);
        Product p11 = new Product(null, "Shampoo", 90.00);


        State est1 = new State(null, "Centro");
        State est2 = new State(null, "Algarve");

        City c1 = new City(null, "Lisboa", est1);
        City c2 = new City(null, "Tavira", est2);
        City c3 = new City(null, "Faro", est2);

        Client cli1 = new Client(null, "Pedro Fernandes", "pcruzfernandes12@gmail.com","2838475393", ClientType.PHYSICAL_PERSON,bCryptPasswordEncoder.encode("123"));
        Client cli2 = new Client(null, "Pedro Fernandes Segundo", "pcruzfernandes13@gmail.com","2838475393", ClientType.PHYSICAL_PERSON,bCryptPasswordEncoder.encode("123"));
        cli2.addProfile(Profile.ADMIN);

        Address e1 = new Address(null, "Rua Benfica", "300", "Apt 40", "Garden", "1500-434", cli1,c1);
        Address e2 = new Address(null, "Avenida alvalade", "23", "Apt 443", "Downtown", "1500-461", cli1,c2);
        Address e3 = new Address(null, "Avenida bla", "2", "Apt 3", "Down", "1500-4361", cli1,c2);

        cli1.getPhones().addAll(Arrays.asList("93939393939","914575449439"));
        cli1.getAddresses().addAll(Arrays.asList(e1,e2));
        cli2.getPhones().addAll(Arrays.asList("93555393939","914573349439"));
        cli2.getAddresses().addAll(Arrays.asList(e3));

        est1.getCities().addAll(Arrays.asList(c1));
        est2.getCities().addAll(Arrays.asList(c2,c3));

        cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));

        categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
        //productRepository.saveAll(Arrays.asList(p1,p2,p3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
        stateRepository.saveAll(Arrays.asList(est1,est2));
        cityRepository.saveAll(Arrays.asList(c1,c2,c3));
        clientRepository.saveAll(Arrays.asList(cli1,cli2));
        addressRepository.saveAll(Arrays.asList(e1,e2,e3));

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
