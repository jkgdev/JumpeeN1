package com.project.jumpee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.jumpee.model.Customer;
import com.project.jumpee.repository.CustomerRepository;

@Service
public class CustomerService {

		@Autowired
		private CustomerRepository repository;
		
		private PasswordEncoder passwordencoder;
				
		public CustomerService(CustomerRepository repository) {
			this.repository = repository;
			this.passwordencoder = new BCryptPasswordEncoder();
		}
		
//REQUEST DATA FROM REPOSITORY---------------------------------------------------		
		
		//SAVE CUSTOMER RECORD
		public void save(Customer user) {										
			String encodedpassword = this.passwordencoder.encode(user.getPassword());
			user.setPassword(encodedpassword);				
			repository.save(user);
		}
		
		public void changestatus(Customer user) {													
			repository.save(user);
		}
		//DELETE CUSTOMER FROM ID
		public void delete(Integer id) {
			repository.deleteById(id);
		}			
		//GET LIST OF CUSTOMERS 	
		public List <Customer> listAll() {
			return repository.findAll();
		}
		//GET CUSTOMER BY ID
		public Customer getCustomerById(Integer id) {
			return repository.findById(id).get();
		}

		//GET CUSTOMER BY EMAIL
		public Customer getCustomerByEmail(String email) {
			return repository.getCustomerByEmail(email);
		}		
		
		//GET EMAIL FROM DB using email
		public String getdbemail(String email) {
			return repository.findByEmail(email);
		}	
		//GET PASSWORD FROM DB using email
		public String getPassword (String password) {
			return repository.getPassword(password);
		}
		//GET STATUS FROM DB using email
		public String getStatus (Integer id) {
			return repository.getStatus(id);
		}
		
		//GET STATUS FROM DB using email
		public String getRole (String email) {
			return repository.getRoleByEmail(email);
		}
		
		//GET Customer FROM DB using email
		public Customer getCustomerByStatus (String status) {
			return repository.getCustomerByStatus(status);
		}		
		//GET PASSWORD FROM DB using id
		public String getPasswordById (Integer id) {
			return repository.getPasswordById(id);
		}
		
//INPUT VALIDATION---------------------------------------------------		
		
		//CHECK ALL IF LETTERS
		public boolean checkNameAllLetters(String name){
		    String pattern="^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$";
		    return name.matches(pattern);
		}
		//VALID PHONE FORMAT
		public boolean isValidPHPhone(String phone){
		    String pattern="^(09|\\+639)\\d{9}$";
		    return phone.matches(pattern);
		}		
		//VALID PHONE FORMAT
		public boolean isValidEmail(String email){
		    String pattern="^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
		    return email.matches(pattern);
		}
		//ALPHANUMERIC
		public boolean isAlphaNumeric(String password){
		    String pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
		    return password.matches(pattern);
		}

}