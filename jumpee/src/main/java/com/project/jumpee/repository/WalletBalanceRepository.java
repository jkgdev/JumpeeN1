package com.project.jumpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.jumpee.model.WalletBalance;

@Repository
public interface WalletBalanceRepository extends JpaRepository<WalletBalance, Integer> {

	//Get address id in the DB using customer_id
	@Query (value ="select walletbalance_id from wallet where customer_id=:customer_id", nativeQuery = true)
	Integer getWalletIdbyCustomerId (@Param ("customer_id") Integer customer_id);
	
	//Get amount in the DB using customer_id
	@Query (value ="select amount from wallet where customer_id=:customer_id", nativeQuery = true)
	float getAmountbyCustomerId (@Param ("customer_id") Integer customer_id);
	
	//Get amount in the DB using wallet id
	@Query (value ="select amount from wallet where walletbalance_id=:walletbalance_id", nativeQuery = true)
	float getAmountbyWalletId (@Param ("walletbalance_id") Integer walletbalance_id);
	
}
