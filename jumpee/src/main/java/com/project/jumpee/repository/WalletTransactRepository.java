package com.project.jumpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.jumpee.model.WalletTransactions;

@Repository
public interface WalletTransactRepository extends JpaRepository<WalletTransactions, Integer> {

}
