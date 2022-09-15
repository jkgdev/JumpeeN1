package com.project.jumpee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.jumpee.model.WalletBalance;
import com.project.jumpee.repository.WalletBalanceRepository;

@Service
public class WalletBalanceService {

		@Autowired
		private WalletBalanceRepository repository;
			
		
		public List <WalletBalance> listAll() {
			return repository.findAll();
		}
		
		//GET WALLET BALANCE BY ID
		public WalletBalance getWalletById(Integer id) {
			return repository.findById(id).get();
		}
		
		//GET WALLET ID by Customer ID
		public int getWalletIdbyCustomerId (int id) {
			return repository.getWalletIdbyCustomerId(id);
		}
		
		//GET Amount by Customer ID
		public float getAmountbyCustomerId (int id) {
			return repository.getAmountbyCustomerId(id);
		}
		
		//GET Amount by Wallet ID
		public float getAmountbyWalletId (int id) {
			return repository.getAmountbyWalletId(id);
		}
		
		//addition of DB amount and input amount
		public float addAmounts (float dbamount, float insertamount) {
			float sum = dbamount + insertamount;
			return sum;
		}
		
		//Create wallet account
		public void addAmount(WalletBalance wallet, int id) {
			wallet.setCustomer_id(id);
			repository.save(wallet);
		}	
		
		public void saveup(WalletBalance balance) {
			repository.save(balance);
		}
}
