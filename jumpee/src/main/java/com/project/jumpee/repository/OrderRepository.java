package com.project.jumpee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.jumpee.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
