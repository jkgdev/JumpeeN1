package com.project.jumpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.jumpee.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	//Get email in the DB using email
	@Query (value ="select email from customer where email=:email", nativeQuery = true)
	String findByEmail (@Param ("email") String email);
	
	//Get password in the DB using email
	@Query (value = "select password from customer where email=:email", nativeQuery = true)
	String getPassword (@Param ("email") String email);
	
	//Get passwor in the DB using email
	@Query (value = "select role from customer where email=:email", nativeQuery = true)
	String getRoleByEmail (@Param ("email") String email);
	
	//Get status(log in/out) in the DB using customer_id
	@Query (value = "select status from customer where customer_id=:customer_id", nativeQuery = true)
	String getStatus (@Param ("customer_id") Integer id);
	
	
	//Get customer in the DB using status (log in/out)
	@Query (value = "select * from customer where status=:status", nativeQuery = true)
	Customer getCustomerByStatus (@Param ("status") String status);
	
	//Get customer in the DB using email	
	@Query (value ="select * from customer where email=:email", nativeQuery = true)
	Customer getCustomerByEmail (@Param ("email") String email);
	
	//Get password in the DB using customer_id
	@Query (value = "select password from customer where customer_id=:customer_id", nativeQuery = true)
	String getPasswordById (@Param ("customer_id") Integer id);
}