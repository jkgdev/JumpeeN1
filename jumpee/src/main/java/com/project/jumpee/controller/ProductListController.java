package com.project.jumpee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.jumpee.model.Product;
import com.project.jumpee.repository.ProductRepository;
import com.project.jumpee.service.ProductService;

@RestController
@RequestMapping("/jumpee")
public class ProductListController {
	    
	    @Autowired
	    ProductRepository repository;
	    
	    @Autowired
		ProductService productservice;	

// DEFAULT PRODUCT LISTING --------------------------------------------------------------------------------------------	    
	    
	    @GetMapping("/productlist")
	    public ResponseEntity<?> defaultProductList(
	          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

	        Pageable pageall = PageRequest.of(page, size);
	        
	        Page<Product> pagecontent;	        
	        pagecontent = repository.findAll(pageall);

	        List<Product> products = new ArrayList<Product>();
	        products = pagecontent.getContent();

	        Map<String, Object> display = new HashMap<>();
	        display.put("totalPagesCreated", pagecontent.getTotalPages());
	        display.put("currentPageIndex", pagecontent.getNumber());
	        
	        display.put("totalProductFound", pagecontent.getTotalElements());	        
	        display.put("products", products);

	        return new ResponseEntity<>(display, HttpStatus.OK);
	      }	    

// SORT BY PRODUCT NAME (ASCENDING / DESCENDING) --------------------------------------------------------------------------------------------		    
	    
	    @GetMapping("/productlist/SortByNameASC")
	    public Page <Product> nameASC (@PageableDefault(sort="productname", direction = Direction.ASC,size = 9) Pageable page){
	    	return productservice.getProducts(page);
	    }
	    
	    @GetMapping("/productlist/SortByNameDESC")
	    public Page <Product> nameDESC (@PageableDefault(sort="productname", direction = Direction.DESC,size = 9) Pageable page){
	    	return productservice.getProducts(page);
	    }	    

// SORT BY PRODUCT PRICE (ASCENDING / DESCENDING) --------------------------------------------------------------------------------------------	    
	    
	    @GetMapping("/productlist/SortByPriceASC")
	    public Page <Product> priceASC (@PageableDefault(sort="price", direction = Direction.ASC,size = 9) Pageable page){
	    	return productservice.getProducts(page);
	    }
	    
	    @GetMapping("/productlist/SortByPriceDESC")
	    public Page <Product> priceDESC (@PageableDefault(sort="price", direction = Direction.DESC,size = 9) Pageable page){
	    	return productservice.getProducts(page);
	    }	

// SORT BY PRODUCT BRAND (ASCENDING / DESCENDING) --------------------------------------------------------------------------------------------	    
	    
	    @GetMapping("/productlist/SortByBrandASC")
	    public Page <Product> brandASC (@PageableDefault(sort="brand", direction = Direction.ASC,size = 9) Pageable page){
	    	return productservice.getProducts(page);
	    }
	   
	    @GetMapping("/productlist/SortByBrandDESC")
	    public Page <Product> brandDESC (@PageableDefault(sort="brand", direction = Direction.DESC,size = 9) Pageable page){
	    	return productservice.getProducts(page);
	    }	

// FILTER BY PRODUCT BRAND --------------------------------------------------------------------------------------------	    
    
	    @GetMapping("/productlist/filterByBrand")
	    public ResponseEntity<?> filterProductsByBrand( @RequestParam(required = false) String brand,
	          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

	        Pageable pageall = PageRequest.of(page, size);
	        
	        Page<Product> pagecontent;	        
	        pagecontent = repository.findByBrand(brand,pageall);

	        List<Product> products = new ArrayList<Product>();
	        products = pagecontent.getContent();

	        Map<String, Object> display = new HashMap<>();
	        display.put("totalPagesCreated", pagecontent.getTotalPages());
	        display.put("currentPageIndex", pagecontent.getNumber());
	        
	        display.put("totalProductFound", pagecontent.getTotalElements());	        
	        display.put("products", products);

	        return new ResponseEntity<>(display, HttpStatus.OK);
	      }	    

// FILTER BY PRODUCT PRICE --------------------------------------------------------------------------------------------	    
	    
	    @GetMapping("/productlist/filterByPrice")
	    public ResponseEntity<?> filterProductsByPrice( @RequestParam(required = false) Float price,
	          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

	        Pageable pageall = PageRequest.of(page, size);
	        
	        Page<Product> pagecontent;	        
	        pagecontent = repository.findByPrice(price,pageall);

	        List<Product> products = new ArrayList<Product>();
	        products = pagecontent.getContent();

	        Map<String, Object> display = new HashMap<>();
	        display.put("totalPagesCreated", pagecontent.getTotalPages());
	        display.put("currentPageIndex", pagecontent.getNumber());
	        
	        display.put("totalProductFound", pagecontent.getTotalElements());	        
	        display.put("products", products);

	        return new ResponseEntity<>(display, HttpStatus.OK);
	      }	        	    	    	    
}
