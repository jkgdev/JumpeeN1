package com.project.jumpee.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.jumpee.model.WalletTransactions;
import com.project.jumpee.repository.WalletTransactRepository;


@Service
public class WalletTransactService {

		@Autowired
		private WalletTransactRepository repository;
	
		public void addTransaction(WalletTransactions transact, int id, float addamount, float newamount) {
			
			Date currenttime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
		    String strtime = formatter.format(currenttime);  
		    
			transact.setCustomer_id(id);
			transact.setAddedamount(addamount);
			transact.setCurrentamount(newamount);
			transact.setDate(strtime);
			repository.save(transact);
		}	
		
		public List <WalletTransactions> listAll() {
			return repository.findAll();
		}
}
