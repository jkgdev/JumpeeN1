package com.project.jumpee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.jumpee.model.Product;
import com.project.jumpee.model.WalletTransactions;

@Repository
public interface WalletTransactRepository extends JpaRepository<WalletTransactions, Integer> {

	Page<WalletTransactions> findByCustomerid(Integer customerid, Pageable pageable);
	
	
}
