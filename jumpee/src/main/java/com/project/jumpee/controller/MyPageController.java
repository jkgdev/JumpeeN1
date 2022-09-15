package com.project.jumpee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.project.jumpee.model.Address;
import com.project.jumpee.model.Customer;
import com.project.jumpee.model.WalletBalance;
import com.project.jumpee.model.WalletTransactions;
import com.project.jumpee.response.CustomerResponse;
import com.project.jumpee.service.AddressService;
import com.project.jumpee.service.CustomerService;
import com.project.jumpee.service.WalletBalanceService;
import com.project.jumpee.service.WalletTransactService;

@RestController
@RequestMapping ("/jumpee")
public class MyPageController {

	@Autowired
	private AddressService addressservice;
		
	@Autowired
	private CustomerService customerservice;
	
	@Autowired
	private WalletBalanceService walletservice;
	
	@Autowired
	private WalletTransactService transactservice;

//VIEW CUSTOMER DETAILS (LOGGED IN)--------------------------------------------------------------------------------------------		
	
	//VIEW CUSTOMER BY ID (MYACCOUNT CUSTOMER DETAILS) - working
	@GetMapping ("/customer/{id}")
	@JsonView(View.Base.class)
	public ResponseEntity <?> get(@PathVariable Integer id) {
	
			String loggedin = customerservice.getStatus(id);
						
			if (loggedin.equals("IN"))
			{
			Customer customer = customerservice.getCustomerById(id);
			return new ResponseEntity<>(customer, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
			} 		
	}	
	
//VIEW ALL RECORD OF ADDRESSES--------------------------------------------------------------------------------------------		
	
	//VIEW CUSTOMER ADDRESSES - working
	@GetMapping ("/viewcustomeraddress")
	@JsonView(View.Base.class)
	public List <Address> list() {
		return addressservice.listAll();
	}

//ADD OR CREATE ADDRESS--------------------------------------------------------------------------------------------		
	
	//REGISTER AN ADDRESS - working
	@PostMapping ("/address/add/{id}")
	public ResponseEntity <CustomerResponse> addaddress (@PathVariable Integer id, @RequestBody Address address) {					
		
		String loggedin = customerservice.getStatus(id);		
		
		//FULLNAME NOT EMPTY - working	
		if (address.getAddress().isEmpty()) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Please provide your address"));			
		}
		//CONTACT PERSON NOT EMPTY - working
		else if (address.getContactperson().isEmpty()) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Please provide your contact person"));			
		}
		//CONTACT NUMBER NOT EMPTY - working
		else if (address.getContactnumber().isEmpty()) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Please provide the phone of your contact person"));			
		}
		//FULL NAME CONSISTS OF LETTERS ONLY - working
		else if (!addressservice.checkNameAllLetters(address.getContactperson())) {
			return ResponseEntity.badRequest()
			         .body(new CustomerResponse("Your contact person must only consists of alphabet letters"));			
				}		
		//VALID PH PHONE FORMAT - working
		else if (!addressservice.isValidPHPhone(address.getContactnumber())) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Your provided phone number is not valid (use +639XXXXXXXXX or 09XXXXXXXXX formats)"));			
		}
		//CREATE ADDRESS RECORD - working
		else {
			if (loggedin.equals("IN"))
				{	
				Customer user = customerservice.getCustomerByStatus("IN");				
				Integer customer_id = user.getCustomer_id();								
				addressservice.save(address,customer_id);
				return ResponseEntity.ok()
						.body(new CustomerResponse("Your address details are saved"));				
				}
			else {
				return ResponseEntity.badRequest()
		                .body(new CustomerResponse("Record not saved. Please log in to continue..."));		
			}
		}
	}

//UPDATE ADDRESS DETAILS--------------------------------------------------------------------------------------------		
	
	//UPDATE ADDRESS BY ID - working
	@PutMapping("/address/update/customer/{id}/{addressid}")
	public ResponseEntity <CustomerResponse> updateaddress(@PathVariable Integer id, @PathVariable Integer addressid,
															@RequestBody Address address) {

		String loggedin = customerservice.getStatus(id);
		
		//FULLNAME NOT EMPTY - working	
		if (address.getAddress().isEmpty()) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Please provide your address"));			
		}
		//CONTACT PERSON NOT EMPTY - working
		else if (address.getContactperson().isEmpty()) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Please provide your contact person"));			
		}
		//CONTACT NUMBER NOT EMPTY - working
		else if (address.getContactnumber().isEmpty()) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Please provide the phone of your contact person"));			
		}
		//FULL NAME CONSISTS OF LETTERS ONLY - working
		else if (!addressservice.checkNameAllLetters(address.getContactperson())) {
			return ResponseEntity.badRequest()
			         .body(new CustomerResponse("Your contact person must only consists of alphabet letters"));			
				}		
		//VALID PH PHONE FORMAT - working
		else if (!addressservice.isValidPHPhone(address.getContactnumber())) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Your provided phone number is not valid (use +639XXXXXXXXX or 09XXXXXXXXX formats)"));			
		}
		//SUCCESSFUL UPDATE ADDRESS DETAILS -working
		else {
			
			if (loggedin.equals("IN")){
						
			Address newdetails = addressservice.getAddressById(addressid);
			newdetails.setAddress(address.getAddress());
			newdetails.setContactperson(address.getContactperson());	
			newdetails.setContactnumber(address.getContactnumber());	    
			addressservice.saveup(newdetails);
			
				return ResponseEntity.ok()
	                .body(new CustomerResponse("Record updated..."));
			}		
			else {			
				return ResponseEntity.badRequest()
		            .body(new CustomerResponse("Record not updated. Please log in to continue..."));				
			}			
		}		
	}	

//DELETE ADDRESS RECORD--------------------------------------------------------------------------------------------	
	
	//DELETE ADDRESS BY ID - working
	@DeleteMapping ("/address/delete/{id}/{addressid}")
		public ResponseEntity <CustomerResponse> deleteaddress(@PathVariable Integer id, @PathVariable Integer addressid) {
		
		String loggedin = customerservice.getStatus(id);
				
		if (loggedin.equals("IN")) {								
			addressservice.delete(addressid);
			return ResponseEntity.ok()
	                .body(new CustomerResponse("Your address has been deleted...")); 
		}		
		else {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Record not deleted. Please log in to continue..."));
		}			
	}		

//LOGOUT CUSTOMER ACCOUNT--------------------------------------------------------------------------------------------		
		
	//LOGOUT ACCOUNT - working
	@PutMapping("/logout/{id}")
		public ResponseEntity <CustomerResponse> logout (@PathVariable Integer id) {
		
		String loggedin = customerservice.getStatus(id);
		
		if (loggedin.equals("IN")) {
			
			Customer user = customerservice.getCustomerById(id);
			user.setStatus("OUT");
			customerservice.save(user);
			
			return ResponseEntity.ok()
		            .body(new CustomerResponse("You are logged out from your account," + user.getFirstname() + " " + user.getLastname()));			
		}
		else {
			return ResponseEntity.badRequest()
		            .body(new CustomerResponse("Your account is already logged out..."));			
		}
	}
	
// ADD/REGISTER A WALLET--------------------------------------------------------------------------------------------
	
	@PostMapping ("/add/wallet/{id}")
	public ResponseEntity <CustomerResponse> addwallet (@PathVariable Integer id, @RequestBody WalletBalance balance,
														WalletTransactions transact) {					
			
		String loggedin = customerservice.getStatus(id);		
			
			if (loggedin.equals("IN")) {	
				Customer user = customerservice.getCustomerByStatus("IN");				
				int customer_id = user.getCustomer_id();
				walletservice.addAmount(balance,customer_id);
				transactservice.addTransaction(transact, id, balance.getAmount(), balance.getAmount());
					
				return ResponseEntity.ok()
						.body(new CustomerResponse("New wallet created. " + balance.getAmount() + " is added."));				
			}
			else {
				return ResponseEntity.badRequest()
			            .body(new CustomerResponse("New wallet not saved. Please log in to continue..."));		
			}
	}

// UPDATE WALLET BALANCE (ADD AMOUNT)--------------------------------------------------------------------------------------------		
		
	@PutMapping("/update/wallet/{id}/{walletid}")
	public ResponseEntity <CustomerResponse> updatewallet(@PathVariable Integer id, @PathVariable Integer walletid,
																@RequestBody WalletBalance balance,
																 WalletTransactions transact) {
		String loggedin = customerservice.getStatus(id);		
				
			if (loggedin.equals("IN")){
				
				float dbamount = walletservice.getAmountbyWalletId(walletid);	
				float inputamount = balance.getAmount();			
				float totalamount = walletservice.addAmounts(dbamount, inputamount);
			
			WalletBalance newamount = walletservice.getWalletById(walletid);
			newamount.setAmount(totalamount);
			walletservice.saveup(newamount);
			transactservice.addTransaction(transact, id, inputamount, totalamount);
			
				return ResponseEntity.ok() 
						.body(new CustomerResponse("Added: " + balance.getAmount() + "   " + "New amount: " + totalamount));
			}		
			else {			
				return ResponseEntity.badRequest()
			            .body(new CustomerResponse("Record not updated. Please log in to continue..."));				
			}			
	}
	
//VIEW ALL RECORD OF BALANCES--------------------------------------------------------------------------------------------		
		
		@GetMapping ("/viewcustomerbalances")
		@JsonView(View.Base.class)
		public List <WalletBalance> listBalances() {
			return walletservice.listAll();
		}	

//VIEW ALL WALLET TRANSACTIONS--------------------------------------------------------------------------------------------		
		
		@GetMapping ("/viewwallettransactions")
		@JsonView(View.Base.class)
		public List <WalletTransactions> listWalletTransaction() {
			return transactservice.listAll();
				}		
		
}			
			