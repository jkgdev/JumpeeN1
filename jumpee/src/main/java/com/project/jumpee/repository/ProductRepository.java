package com.project.jumpee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
//import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.jumpee.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Page<Product> findByBrand(String brand, Pageable pageable);
	Page<Product> findByPrice(Float price, Pageable pageable);

/*	
	@Query("FROM product ORDER BY brand ASC")
    List<Product> findAllOrderByBrandAsc();
	
	@Query("FROM product")
    List<Product> findAllOrderByNameAsc(Sort byName);
*/
}
