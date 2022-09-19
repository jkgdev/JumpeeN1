package com.project.jumpee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.jumpee.model.Order;
import com.project.jumpee.model.Product;
import com.project.jumpee.model.WalletBalance;
import com.project.jumpee.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productrepository;
	
	 public Page <Product> getProducts (Pageable page) {
		 return productrepository.findAll(page);
	 }     
    
		public Product getProductByName(String name) {
			return productrepository.getProductByName(name);
		}		
		
		public Product getProductById(Integer id) {
			return productrepository.getProductById(id);
		}
  
		public int afterCheckoutQuatity (int dbquantity, int orderquantity) {
			int difference = dbquantity - orderquantity;
			return difference;
		}
		
		public void updateproductdetails(Product product) {
			productrepository.save(product);
		}
}
