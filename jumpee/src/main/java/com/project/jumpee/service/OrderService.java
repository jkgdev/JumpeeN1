package com.project.jumpee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.jumpee.model.Order;
import com.project.jumpee.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;
	
	public void saveOrder (Order order, int id, float price, float totalprice) {
		order.setProduct_id(id);
		order.setPrice(price);
		order.setTotalprice(totalprice);
		repository.save(order);
	}
	
	public float findtotalprice(float price, int quantity) {
		return price * quantity;
	}
	
}
