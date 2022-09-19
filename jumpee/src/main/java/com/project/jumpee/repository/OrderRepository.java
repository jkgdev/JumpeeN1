package com.project.jumpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.jumpee.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query (value ="select * from ordercart where order_id=:order_id", nativeQuery = true)
	Order findOrderbyId (@Param ("order_id") Integer order_id);
	
}
