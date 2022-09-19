package com.project.jumpee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.jumpee.model.Address;
import com.project.jumpee.model.Checkout;

@Repository
public interface AddressRepository extends JpaRepository <Address, Integer> {

	Page<Address> findByCustomerid(Integer customerid, Pageable pageable);
	
	//Get address id in the DB using customer_id
	@Query (value ="select addressid from address where customer=:customer_id", nativeQuery = true)
	Integer findAddIdbyCustomerId (@Param ("customer_id") Integer customer_id);
	
	
}
