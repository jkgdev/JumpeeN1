package com.project.jumpee.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.project.jumpee.View;
import com.project.jumpee.model.Customer;
import com.project.jumpee.service.CustomerService;
import com.project.jumpee.response.CustomerResponse;

@RestController
@RequestMapping ("/jumpee")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
		
	public CustomerController(CustomerService service) {
		this.service = service;
	}


// REGISTER A CUSTOMER --------------------------------------------------------------------------------------------	
	
	//REGISTER A CUSTOMER - working
	@PostMapping ("/register")
	public ResponseEntity <CustomerResponse> register (@RequestBody Customer customer) {
					
		//FULLNAME NOT EMPTY - working	
		if (customer.getFirstname().isEmpty() || customer.getLastname().isEmpty()) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Please provide your full name (both first name and last name)"));			
		}		
		//FULL NAME CONSISTS OF LETTERS ONLY - working
		else if (!service.checkNameAllLetters(customer.getFirstname()) || !service.checkNameAllLetters(customer.getLastname())) {
			return ResponseEntity.badRequest()
			         .body(new CustomerResponse("Your first name and last name must only consist of alphabet letters"));			
				}		
		//VALID PH PHONE FORMAT - working
		else if (!service.isValidPHPhone(customer.getContact())) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Your provided phone number is not valid (use +639XXXXXXXXX or 09XXXXXXXXX formats)"));			
		}		
		//UNIQUE EMAIL - working		
		else if (!(service.getdbemail(customer.getEmail()) ==null)) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Your provided email address is already existed."));			
		}				
		//VALID EMAIL FORMAT - working
		else if (!service.isValidEmail(customer.getEmail())) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Your provided e-mail address has an invalid format"));			
		}		
		//PASSWORD LENGTH - working 
		else if (customer.getPassword().length() < 8) {
			return ResponseEntity.badRequest()
			        .body(new CustomerResponse("Your password must be at least 8 characters"));			
		}					
		//PASSWORD ALPHANUMERIC FORMAT - working
		else if (!service.isAlphaNumeric(customer.getPassword())) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Your provided password must be alphanumeric (letters and numbers)"));			
		}	
		//CONFIRM PASSWORD - working
		else if (!(customer.getPassword().equals(customer.getConfirmpassword()))) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Your password and confirm password don't match"));			
		}
		//CREATE RECORD - working
		else {
		customer.setRole("REGISTERED CUSTOMER");
		customer.setStatus("OUT");
		service.save(customer);
		return ResponseEntity.ok()
                .body(new CustomerResponse("You are now a registered customer, "
                							+ customer.getFirstname() + " " + customer.getLastname()));	
		}
	}

// UPDATE CUSTOMER DETAILS --------------------------------------------------------------------------------------------		
	
	//UPDATE CUSTOMER INFORMATION (WITHOUT INPUT VALIDATION) - working
	@PutMapping("/customer/{id}")
	public ResponseEntity <CustomerResponse> updateCustomerDetails(@PathVariable Integer id,
	  @RequestBody Customer customer) {
		try {
			Customer upcustomer = service.getCustomerById(id);
			upcustomer.setFirstname(customer.getFirstname());
			upcustomer.setLastname(customer.getLastname());	
			upcustomer.setContact(customer.getContact());
			upcustomer.setEmail(customer.getEmail());
		    upcustomer.setPassword(customer.getPassword());		    
			service.save(upcustomer);
			
			return ResponseEntity.ok()
	                .body(new CustomerResponse("Record updated for " + customer.getFirstname() + " " + customer.getLastname()));	
			
		} 
		catch (NoSuchElementException e) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Update failed..."));
		}
	}

// DELETE CUSTOMER ACCOUNT --------------------------------------------------------------------------------------------		
	
	//DELETE CUSTOMER BY ITS ID - working
	@DeleteMapping ("/customer/{id}")
		public ResponseEntity <CustomerResponse> deletecustomerrecord(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok()
                .body(new CustomerResponse("Record Deleted..."));
	}

// VIEW ALL REGISTERED CUSTOMERS --------------------------------------------------------------------------------------------		
	
	//VIEW ALL REGISTERED CUSTOMER - working
	@GetMapping ("/registeredcustomers") @JsonView(View.Base.class)
		public List <Customer> list() {
			return service.listAll();
	}	
}