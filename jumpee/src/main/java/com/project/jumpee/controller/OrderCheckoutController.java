package com.project.jumpee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.jumpee.View;
import com.project.jumpee.model.Checkout;
import com.project.jumpee.model.Customer;
import com.project.jumpee.model.Order;
import com.project.jumpee.model.Product;
import com.project.jumpee.model.WalletBalance;
import com.project.jumpee.model.WalletTransactions;
import com.project.jumpee.response.CustomerResponse;
import com.project.jumpee.service.CustomerService;
import com.project.jumpee.service.OrderService;
import com.project.jumpee.service.OrderTransactService;
import com.project.jumpee.service.ProductService;
import com.project.jumpee.service.WalletBalanceService;
import com.project.jumpee.service.WalletTransactService;

@RestController
@RequestMapping ("/jumpee")
public class OrderCheckoutController {
		
	@Autowired
	CustomerService customerservice;
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	WalletBalanceService walletservice;
	@Autowired
	WalletTransactService wallettransactservice;
	
	@Autowired
	OrderService orderservice;	
	@Autowired
	OrderTransactService ordertransactservice;
	
		
//MAKE A PRODUCT ORDER --------------------------------------------------------------------------------------------	
	
	@PostMapping ("/order-product/{id}")
	public ResponseEntity <?> orderproduct (	@PathVariable Integer id,
												@RequestBody Order order,
												Product product,
												Customer customer) {
		
		String loggedin = customerservice.getStatus(id);
		
		if (loggedin.equals("IN")) {
			product  = productservice.getProductByName(order.getProductname());
			int productid = product.getProduct_id();
			float price = product.getPrice();
			int productstock = product.getQuantity();
			int orderquantity = order.getQuantity();
			String cartstatus = "PENDING";
			
			if (productstock > orderquantity) {
				float totalprice = orderservice.findtotalprice(price, orderquantity);		
				orderservice.saveOrder(order, productid, price, totalprice, cartstatus);
		
				return ResponseEntity.ok()
						.body(new CustomerResponse("Successfully added to cart... Total Order Amount: "																					+ totalprice));			
			}
			else {
				return ResponseEntity.badRequest()
						.body(new CustomerResponse("Insufficient stock for your order... current Stock for "
													+ order.getProductname() + " is " + productstock));	
			}		
		}
		else {
				return ResponseEntity.ok()
						.body(new CustomerResponse("Order not successful. Please log in to continue... "));
		}
	}
	
	
	
	@PostMapping ("/order-checkout/{id}")
	public ResponseEntity <CustomerResponse> checkoutorder (	@PathVariable Integer id,
																@RequestBody Checkout checkout,
																Customer customer,
																Order order,
																WalletBalance wallet,
																WalletTransactions transact,
																Product product) {		
		String loggedin = customerservice.getStatus(id);
		
		if (loggedin.equals("IN")) {
			
			order  = orderservice.getOrderById(checkout.getOrder_id());
			int orderid = order.getOrder_id();			
			float checkoutamount = order.getTotalprice();
			String cartstatus = order.getStatus();
			
			product = productservice.getProductById(order.getProduct_id());
			int dbquantity = product.getQuantity();
			int orderquantity = order.getQuantity();
			
			float dbwallet = walletservice.getAmountbyWalletId(checkout.getWallet_id());
			int customerid = id;
									
			if (cartstatus.equals("CHECKOUT")) {
				return ResponseEntity.badRequest()
		                .body(new CustomerResponse("This order is already checked out"));
			}
			
			else if (dbwallet >= checkoutamount && cartstatus.equals("PENDING")){
				float newwalletamount = walletservice.afterCheckoutAmount(dbwallet,checkoutamount);
				
				int newquantity = productservice.afterCheckoutQuatity(dbquantity, orderquantity);				
				product.setQuantity(newquantity);			
				
				wallet = walletservice.getWalletById(checkout.getWallet_id());
				wallet.setAmount(newwalletamount);

				
				String orderstatus = "SUCESS";
				String transacttype = "PURCHASE(CHECKOUT)";
				cartstatus = "CHECKOUT";
				
				orderservice.changeCartStatus(order, cartstatus);
				productservice.updateproductdetails(product);
				ordertransactservice.ordercheckout (checkout, orderid, customerid, checkoutamount, orderstatus);
				wallettransactservice.addTransaction(transact, id, transacttype, -checkoutamount, newwalletamount);
			
				return ResponseEntity.ok()
						.body(new CustomerResponse("Order checkout successful. Wallet Balance: " + newwalletamount));
			}			
			else {
				String orderstatus = "FAILED";	
				ordertransactservice.ordercheckout (checkout, orderid, customerid, checkoutamount, orderstatus);
				
				return ResponseEntity.badRequest()
		                .body(new CustomerResponse("Order checkout failed. Insufficient wallet balance..."));	
			}					
		}
		else {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Order checkout not successful. Please log in to continue... "));
		}
	}			

//VIEW ALL ORDER CARTS--------------------------------------------------------------------------------------------		
	
	@GetMapping ("/view-all-order-cart") @JsonView(View.Base.class)
		public List <Order> listWalletTransaction() {
			return orderservice.listAll();
	}					

//VIEW ALL WALLET TRANSACTIONS--------------------------------------------------------------------------------------------		
		
	@GetMapping ("/view-all-order-history") @JsonView(View.Base.class)
		public List <Checkout> listOrderTransaction() {
			return ordertransactservice.listAll();
	}		
	
}
