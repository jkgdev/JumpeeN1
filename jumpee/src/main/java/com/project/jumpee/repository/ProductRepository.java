package com.project.jumpee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
//import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.jumpee.model.Order;
import com.project.jumpee.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Page<Product> findByBrand(String brand, Pageable pageable);
	Page<Product> findByPrice(Float price, Pageable pageable);
	
	//Get product by name
	@Query (value ="select * from product where productname=:productname", nativeQuery = true)
	Product getProductByName (@Param ("productname") String productname);

	//Get product by id
	@Query (value ="select * from product where product_id=:product_id", nativeQuery = true)
	Product getProductById (@Param ("product_id") Integer product_id);
	
}
