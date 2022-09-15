package com.project.jumpee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.jumpee.model.Customer;
import com.project.jumpee.model.Order;
import com.project.jumpee.model.Product;
import com.project.jumpee.response.CustomerResponse;
import com.project.jumpee.service.CustomerService;
import com.project.jumpee.service.OrderService;
import com.project.jumpee.service.ProductService;

@RestController
@RequestMapping ("/jumpee")
public class OrderCheckoutController {
	
	@Autowired
	OrderService orderservice;
	
	@Autowired
	CustomerService customerservice;
	
	@Autowired
	ProductService productservice;
	
//MAKE A PRODUCT ORDER --------------------------------------------------------------------------------------------	
	
	@PostMapping ("/orderproduct/{id}")
	public ResponseEntity <CustomerResponse> orderproduct (@PathVariable Integer id, @RequestBody Order order,
															Product product, Customer customer) {
		
		String loggedin = customerservice.getStatus(id);
		
		if (loggedin.equals("IN")) {
			Product prod  = productservice.getProductByName(order.getProductname());
			int productid = prod.getProduct_id();
			float price = prod.getPrice();
			int qty = order.getQuantity();		
			float totalprice = orderservice.findtotalprice(price, qty);
		
		orderservice.saveOrder(order, productid, price, totalprice);
		
			return ResponseEntity.ok()
                .body(new CustomerResponse("Successfully ordered product... Total Amount: " + totalprice));
		}
		else {
			return ResponseEntity.ok()
	                .body(new CustomerResponse("order not successful "));
		}
	}		
}
