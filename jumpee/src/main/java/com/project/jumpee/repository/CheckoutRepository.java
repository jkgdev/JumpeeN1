package com.project.jumpee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.jumpee.model.Checkout;
import com.project.jumpee.model.WalletTransactions;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {

	Page<Checkout> findByCustomerid(Integer customerid, Pageable pageable);
}
