package com.project.jumpee.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jumpee.model.Order;
import com.project.jumpee.model.WalletTransactions;
import com.project.jumpee.repository.WalletTransactRepository;


@Service
public class WalletTransactService {

		@Autowired
		private WalletTransactRepository repository;
	
		public void addTransaction(WalletTransactions transact, int id, String transacttype,
									float amount, float newamount) {
			
			Date currenttime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
		    String strtime = formatter.format(currenttime);  
		    
			transact.setCustomerid(id);
			transact.setTransaction_type(transacttype);
			transact.setTransact_amount(amount);
			transact.setCurrentamount(newamount);
			transact.setDate(strtime);
			repository.save(transact);
		}	
		
		public List <WalletTransactions> listAll() {
			return repository.findAll();
		}
		
}
