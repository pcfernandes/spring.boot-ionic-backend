package com.innowave.cursomc.services;

import com.innowave.cursomc.domain.ClientOrder;
import com.innowave.cursomc.domain.ItemOrder;
import com.innowave.cursomc.domain.PaymentWireTransfer;
import com.innowave.cursomc.domain.Product;
import com.innowave.cursomc.domain.enums.PaymentStatus;
import com.innowave.cursomc.repositories.*;
import com.innowave.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class ClientOrderService {

	@Autowired
	private ClientOrderRepository clientOrderRepository;

	@Autowired
	private PaymentWireTransferService paymentWireTransferService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ItemOrderRepository itemOrderRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private EmailService emailService;
	
	public ClientOrder find(Integer id) {
		Optional<ClientOrder> obj = clientOrderRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + ClientOrder.class.getName()));
	}

	@Transactional
	public ClientOrder insert(ClientOrder obj){
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientRepository.findById(obj.getClient().getId()).get());
		obj.getPayment().setPaymentStatus(PaymentStatus.PENDING);
		obj.getPayment().setClientOrder(obj);
		if(obj.getPayment() instanceof PaymentWireTransfer){
			PaymentWireTransfer paymentWireTransfer = (PaymentWireTransfer) obj.getPayment();
			paymentWireTransferService.fillPaymentWithTransfer(paymentWireTransfer,obj.getInstant());
		}
		obj = clientOrderRepository.save(obj);
		paymentRepository.save(obj.getPayment());
		for(ItemOrder io : obj.getItemOrders()){
			io.setDiscount(0.0);
			io.setProduct(productRepository.findById(io.getProduct().getId()).get());
			io.setPrice( io.getProduct().getPrice());
			io.setClientOrder(obj);
		}
		itemOrderRepository.saveAll(obj.getItemOrders());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
}
