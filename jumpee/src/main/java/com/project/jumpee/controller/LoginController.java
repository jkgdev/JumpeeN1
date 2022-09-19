package com.project.jumpee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.jumpee.model.Customer;
import com.project.jumpee.response.CustomerResponse;
import com.project.jumpee.service.CustomerService;

@RestController
@RequestMapping ("/jumpee")
public class LoginController {

	@Autowired
	private CustomerService customerservice;
		
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
// LOG IN --------------------------------------------------------------------------------------------
	
	//LOGIN USING EMAIL AND PASSWORD - working
	@PostMapping("/login")
	public ResponseEntity <CustomerResponse> login (@RequestBody Customer customer) {
		
		String dbemailexist = customerservice.getdbemail(customer.getEmail());	
		String dbpassword= customerservice.getPassword(customer.getEmail());
		
		String role = customerservice.getRole(customer.getEmail());
		
		boolean samepasswords = encoder.matches(customer.getPassword(), dbpassword);
						
		if(dbemailexist != null && samepasswords == true && role.equals("REGISTERED CUSTOMER")) {
			
			//CHANGE LOGIN STATUS FROM OUT TO IN
			Customer user = customerservice.getCustomerByEmail(customer.getEmail());	
			user.setStatus("IN");		    
			customerservice.changestatus(user);
			return ResponseEntity.ok()
	                .body(new CustomerResponse( "You are successfully logged in, "
	                							+ user.getFirstname() + " " + user.getLastname()));
		}
		//EMAIL EXISTENCE
		else if(dbemailexist == null) {
			return ResponseEntity.badRequest()
				     .body(new CustomerResponse("Your provided email address is not registered..."));	
		}
		//WRONG PASSWORD
		else if(samepasswords == false) {			
			return ResponseEntity.badRequest()
		            .body(new CustomerResponse("Your provided password is wrong..."));	
		}
		else {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Customer login failed, please try again"));
		}		
	}

// RESET PASSWORD --------------------------------------------------------------------------------------------	
	
	//RESET PASSWORD - working
	@PutMapping("/resetpassword")
	public ResponseEntity <CustomerResponse> resetpassword (@RequestBody Customer customer) {
		
		String dbemailexist = customerservice.getdbemail(customer.getEmail());
		
		//SUCCESSFUL RESET PASSWORD - working			
		if(dbemailexist != null && customer.getNewpassword().equals(customer.getConfirmnewpassword())) {
						
			Customer user = customerservice.getCustomerByEmail(customer.getEmail());	
			user.setPassword(customer.getNewpassword());		    
			customerservice.save(user);
						
			return ResponseEntity.ok()
		            .body(new CustomerResponse("You have reset your password, " + user.getFirstname() + " "
		            							+ user.getLastname()));	
		}
		//EMAIL EXISTENCE - working
		else if(dbemailexist == null) {
			return ResponseEntity.badRequest()
		            .body(new CustomerResponse("Your provided email address didn't exist in the record..."));	
		}
		//PASSWORD LENGTH - working 
		else if (customer.getNewpassword().length() < 8) {
			return ResponseEntity.badRequest()
					 .body(new CustomerResponse("Your new password must be at least 8 characters"));			
				}					
		//PASSWORD ALPHANUMERIC FORMAT - working
		else if (!customerservice.isAlphaNumeric(customer.getPassword())) {
			return ResponseEntity.badRequest()
			          .body(new CustomerResponse("Your new password must be alphanumeric (letters and numbers)"));			
				}
		//NEW PASSWORD AND CONFIRM NEW PASSWORD
		else if (!(customer.getNewpassword().equals(customer.getConfirmnewpassword()))) {
			return ResponseEntity.badRequest()
		            .body(new CustomerResponse("Your new password and confirm password doesn't match..."));
		}		
		else {
			return ResponseEntity.badRequest()
            .body(new CustomerResponse("Reset password failed"));
		}			
	}
	
//CHANGE ACCOUNT PASSWORD (MYACCOUNT)--------------------------------------------------------------------------------------------
	
	//CHANGE PASSWORD (MYACCOUNT) - working
		@PutMapping("/change-password/{id}")
			public ResponseEntity <CustomerResponse> changepassword (	@PathVariable Integer id,
																		@RequestBody Customer customer) {							
			String dbpassword= customerservice.getPasswordById(id);
			String loggedin = customerservice.getStatus(id);			
			boolean samepasswords = encoder.matches(customer.getCurrentpassword(), dbpassword);
			boolean samecurrentnewpassword = customer.getCurrentpassword().equals(customer.getNewpassword());
				
			//CHANGE PASSWORD - working
			if(samepasswords == true && customer.getNewpassword().equals(customer.getConfirmnewpassword())
					&& !(samecurrentnewpassword)) {
								
				Customer user = customerservice.getCustomerById(id);	
					
				if (loggedin.equals("IN")) { 
					user.setPassword(customer.getNewpassword());		    
					customerservice.save(user);
								
					return ResponseEntity.ok()
				           .body(new CustomerResponse("You have changed your password, " 
				        		   						+ user.getFirstname() + " " + user.getLastname()));	
				}
				else {
					return ResponseEntity.badRequest()
					       .body(new CustomerResponse("Change password failed. Please log in to continue..."));
				}
			}
			//CURRENT PASSWORD CHECK - working
			else if (samepasswords == false) {					
					return ResponseEntity.badRequest()
				            .body(new CustomerResponse("Your provided password doesn't match with your current password..."));
				}
			//SAME CURRENT AND NEW PASSWORDS - working
			else if (samecurrentnewpassword && samepasswords == true) {
					return ResponseEntity.badRequest()
					         .body(new CustomerResponse("Your current password and new password are same. Try other new password..."));
				}
			//NEW PASSWORD and CONFIRM PASSWORD - working
			else if (!(customer.getNewpassword().equals(customer.getConfirmnewpassword()))) {
					return ResponseEntity.badRequest()
				            .body(new CustomerResponse("Your new password and confirm password doesn't match..."));
				}	
			//CHANGE PASSWORD FAILED
			else {
					return ResponseEntity.badRequest()
		            .body(new CustomerResponse("Change password failed"));
				}			
			}		
}

