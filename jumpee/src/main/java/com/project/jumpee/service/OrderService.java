package com.project.jumpee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jumpee.model.Address;
import com.project.jumpee.model.Customer;
import com.project.jumpee.model.Order;
import com.project.jumpee.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;
	
	//GET LIST OF CUSTOMERS 	
	public List <Order> listAll() {
		return repository.findAll();
	}
	
	public void saveOrder (Order order, int id, float price, float totalprice, String status) {
		order.setProduct_id(id);
		order.setPrice(price);
		order.setTotalprice(totalprice);
		order.setStatus(status);
		repository.save(order);
	}
	
	public void changeCartStatus (Order order, String status) {
		order.setStatus(status);
		repository.save(order);
	}
	
	//Compute total price
	public float findtotalprice(float price, int quantity) {
		return price * quantity;
	}
	
	//GET Order BY ID
	public Order getOrderById(Integer id) {
		return repository.findOrderbyId(id);
	}
}
