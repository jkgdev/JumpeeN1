package com.project.jumpee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.project.jumpee.View;
import com.project.jumpee.model.Address;
import com.project.jumpee.model.Checkout;
import com.project.jumpee.model.Customer;
import com.project.jumpee.model.Product;
import com.project.jumpee.model.WalletBalance;
import com.project.jumpee.model.WalletTransactions;
import com.project.jumpee.repository.AddressRepository;
import com.project.jumpee.repository.CheckoutRepository;
import com.project.jumpee.repository.WalletTransactRepository;
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
	private WalletTransactService wallettransactservice;
	
	@Autowired
	private WalletTransactRepository wallettransactrepo;
	
	@Autowired
	private CheckoutRepository checkoutrepo;
	
	@Autowired
	private AddressRepository addressrepo;

//VIEW CUSTOMER DETAILS (LOGGED IN)--------------------------------------------------------------------------------------------		
	
	//VIEW CUSTOMER BY ID (MYACCOUNT CUSTOMER DETAILS) - working
	@GetMapping ("/customer/{id}") @JsonView(View.Base.class)
	public ResponseEntity <?> viewCustomerById (@PathVariable Integer id) {
	
			String loggedin = customerservice.getStatus(id);
						
			if (loggedin.equals("IN"))
			{
			Customer customer = customerservice.getCustomerById(id);
			return new ResponseEntity<>(customer, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
			} 		
	}	
 
//VIEW CUSTOMER ADDRESSES--------------------------------------------------------------------------------------------		
	
    @GetMapping("view-all-addresses/{id}")
    public ResponseEntity<?> getAddressList( @PathVariable Integer id,
          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

    	String loggedin = customerservice.getStatus(id);
    	
    	if (loggedin.equals("IN")) {
    	
        Pageable pageall = PageRequest.of(page, size);
        
        Page<Address> pagecontent;	        
        pagecontent = addressrepo.findByCustomerid(id,pageall);

        List<Address> addresslist = new ArrayList<Address>();
        addresslist = pagecontent.getContent();

        Map<String, Object> display = new HashMap<>();	        
        display.put("Addresses", addresslist);

        return new ResponseEntity<>(display, HttpStatus.OK);
    	}
    	else{
    		return ResponseEntity.badRequest()
		            .body(new CustomerResponse("Please log in to continue..."));
    	}
    }		
	
	
//ADD OR CREATE ADDRESS--------------------------------------------------------------------------------------------		
	
	//REGISTER AN ADDRESS - working
	@PostMapping ("/add-address/{id}")
	public ResponseEntity <CustomerResponse> addaddress (	@PathVariable Integer id,
															@RequestBody Address address ) {					
		
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
	@PutMapping("/update-address/customer/{id}")
	public ResponseEntity <CustomerResponse> updateaddress(	@PathVariable Integer id,
															@RequestBody Address address) {

		try {
		String loggedin = customerservice.getStatus(id);
		int addressid = address.getAddressid();		
		
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
		}catch (NullPointerException e) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Update failed, address not found..."));
		}
	}	

//DELETE ADDRESS RECORD--------------------------------------------------------------------------------------------	
	
	//DELETE ADDRESS BY ID - working
	@DeleteMapping ("/delete-address/{id}")
		public ResponseEntity <CustomerResponse> deleteaddress( @PathVariable Integer id,
																@RequestBody Address address) {
		try {
			String loggedin = customerservice.getStatus(id);
			int addressid = address.getAddressid();
		
			if (loggedin.equals("IN")) {								
				addressservice.delete(addressid);
				return ResponseEntity.ok()
	                .body(new CustomerResponse("Your address has been deleted...")); 
			}		
			else {
				return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Record not deleted. Please log in to continue..."));
			}
		}catch (NullPointerException e) {
			return ResponseEntity.badRequest()
	                .body(new CustomerResponse("Delete failed, address not found..."));
		}
	}		

	
// ADD/REGISTER A WALLET--------------------------------------------------------------------------------------------
	
	@PostMapping ("/add-wallet/{id}")
	public ResponseEntity <CustomerResponse> addwallet (@PathVariable Integer id,
														@RequestBody WalletBalance balance,
														WalletTransactions transact) {					
			
		String loggedin = customerservice.getStatus(id);		
			
			if (loggedin.equals("IN")) {	
				Customer user = customerservice.getCustomerByStatus("IN");				
				int customer_id = user.getCustomer_id();
				String transacttype = "ADD WALLET"; 
				walletservice.addAmount(balance,customer_id);
				wallettransactservice.addTransaction(transact, id, transacttype, balance.getAmount(), balance.getAmount());
					
				return ResponseEntity.ok()
						.body(new CustomerResponse("New wallet created. " + balance.getAmount() + " is added."));				
			}
			else {
				return ResponseEntity.badRequest()
			            .body(new CustomerResponse("New wallet not created. Please log in to continue..."));		
			}
	}

// UPDATE WALLET BALANCE (ADD AMOUNT)--------------------------------------------------------------------------------------------		
		
	@PutMapping("/update-balance/{id}")
	public ResponseEntity <CustomerResponse> updatewallet(	@PathVariable Integer id,															
															@RequestBody WalletBalance balance,
															WalletTransactions transact) {
		try {
		String loggedin = customerservice.getStatus(id);		
			
		int wallet_id = balance.getWallet_id();				
			
			if (loggedin.equals("IN")){
				
				float dbamount = walletservice.getAmountbyWalletId(wallet_id);	
				float inputamount = balance.getAmount();			
				float totalamount = walletservice.addAmounts(dbamount, inputamount);
				
				WalletBalance newamount = walletservice.getWalletById(wallet_id);
				newamount.setAmount(totalamount);
			
				String transacttype = "UPDATE BALANCE"; 			
			
			walletservice.saveup(newamount);
			wallettransactservice.addTransaction(transact, id, transacttype, inputamount, totalamount);
			
				return ResponseEntity.ok() 
						.body(new CustomerResponse("Added: " + balance.getAmount() + "   " + "New amount: " + totalamount));
			}		
			else {			
				return ResponseEntity.badRequest()
			            .body(new CustomerResponse("Record not updated. Please log in to continue..."));				
			}
		}catch (NullPointerException e) {
		return ResponseEntity.badRequest()
                .body(new CustomerResponse("Add balance failed, wallet not found..."));
	}
	}

//VIEW WALLET TRANSACTIONS--------------------------------------------------------------------------------------------	
	
    @GetMapping("view-wallet-transactions/{id}")
    public ResponseEntity<?> getWalletTransactions( @PathVariable Integer id,
          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

    	String loggedin = customerservice.getStatus(id);
    	
    	if (loggedin.equals("IN")) {
    	
        Pageable pageall = PageRequest.of(page, size);
        
        Page<WalletTransactions> pagecontent;	        
        pagecontent = wallettransactrepo.findByCustomerid(id,pageall);

        List<WalletTransactions> wallettransactions = new ArrayList<WalletTransactions>();
        wallettransactions = pagecontent.getContent();

        Map<String, Object> display = new HashMap<>();	        
        display.put("Wallet Transactions", wallettransactions);

        return new ResponseEntity<>(display, HttpStatus.OK);
        
    	}else{
    		return ResponseEntity.badRequest()
		            .body(new CustomerResponse("Please log in to continue..."));
    	}
      }	 

//VIEW ORDER HISTORY OF CUSTOMER--------------------------------------------------------------------------------------------    
    
    @GetMapping("view-order-history/{id}")
    public ResponseEntity<?> getOrderHistory( @PathVariable Integer id,
          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

    	String loggedin = customerservice.getStatus(id);
    	
    	if (loggedin.equals("IN")) {
    	
        Pageable pageall = PageRequest.of(page, size);
        
        Page<Checkout> pagecontent;	        
        pagecontent = checkoutrepo.findByCustomerid(id,pageall);

        List<Checkout> orderhistory = new ArrayList<Checkout>();
        orderhistory = pagecontent.getContent();

        Map<String, Object> display = new HashMap<>();	        
        display.put("Order History", orderhistory);

        return new ResponseEntity<>(display, HttpStatus.OK);
    	}else{
    		return ResponseEntity.badRequest()
		            .body(new CustomerResponse("Please log in to continue..."));
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
			customerservice.changestatus(user);
				
			return ResponseEntity.ok()
			         .body(new CustomerResponse("You are logged out from your account,"
			            							+ user.getFirstname() + " " + user.getLastname()));			
		}
		else {
			return ResponseEntity.badRequest()
			         .body(new CustomerResponse("Your account is already logged out..."));			
			}
	}	
	
	
//VIEW ALL RECORD OF CUSTOMER BALANCES--------------------------------------------------------------------------------------------		
		
		@GetMapping ("/view-customer-balances") @JsonView(View.Base.class)
		public List <WalletBalance> listBalances() {
			return walletservice.listAll();
		}	

//VIEW ALL WALLET TRANSACTIONS--------------------------------------------------------------------------------------------		
		
		@GetMapping ("/view-all-wallet-transactions") @JsonView(View.Base.class)
		public List <WalletTransactions> listWalletTransaction() {
			return wallettransactservice.listAll();
				}				


//VIEW ALL RECORD OF ADDRESSES--------------------------------------------------------------------------------------------		

	//VIEW CUSTOMER ADDRESSES - working
		@GetMapping ("/view-all-customer-address") @JsonView(View.Base.class)
		public List <Address> list() {
			return addressservice.listAll();
	}
}			