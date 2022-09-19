package com.project.jumpee.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.jumpee.model.Checkout;
import com.project.jumpee.model.Order;
import com.project.jumpee.repository.CheckoutRepository;

@Service
public class OrderTransactService {

	@Autowired
	CheckoutRepository repository;
	
	Date currenttime = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
    String strtime = formatter.format(currenttime); 
	
	public void ordercheckout (	Checkout transact, int orderid, int customerid,
								float amount, String orderstatus) {
		transact.setOrder_id(orderid);
		transact.setCustomerid(customerid);
		transact.setCheckoutamount(amount);
		transact.setStatus(orderstatus);
		transact.setDate(strtime);
		repository.save(transact);
	}
	
	//GET LIST OF CUSTOMERS 	
	public List <Checkout> listAll() {
		return repository.findAll();
	}
	
}
