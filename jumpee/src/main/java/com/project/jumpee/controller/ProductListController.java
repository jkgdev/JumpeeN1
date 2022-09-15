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
	    
	    @GetMapping("/productlist/SortByPriceASC")
	    public ResponseEntity<?> getProductsSortByPriceASC(
	          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

	        Pageable priceASC = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,"price"));
	        
	        Page<Product> pagecontent;	        
	        pagecontent = repository.findAll(priceASC);

	        List<Product> products = new ArrayList<Product>();
	        products = pagecontent.getContent();

	        Map<String, Object> display = new HashMap<>();
	        display.put("totalPagesCreated", pagecontent.getTotalPages());
	        display.put("currentPageIndex", pagecontent.getNumber());
	        
	        display.put("totalProductFound", pagecontent.getTotalElements());	        
	        display.put("products", products);

	        return new ResponseEntity<>(display, HttpStatus.OK);
	      }

	    @GetMapping("/productlist/SortByPriceDESC")
	    public ResponseEntity<?> getProductsSortByPriceDESC(
	          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

	        Pageable priceDESC = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"price"));
	        
	        Page<Product> pagecontent;	        
	        pagecontent = repository.findAll(priceDESC);

	        List<Product> products = new ArrayList<Product>();
	        products = pagecontent.getContent();

	        Map<String, Object> display = new HashMap<>();
	        display.put("totalPagesCreated", pagecontent.getTotalPages());
	        display.put("currentPageIndex", pagecontent.getNumber());
	        
	        display.put("totalProductFound", pagecontent.getTotalElements());	        
	        display.put("products", products);

	        return new ResponseEntity<>(display, HttpStatus.OK);
	      }	    

	    @GetMapping("/productlist/SortByBrandASC")
	    public ResponseEntity<?> getProductsSortByBrandASC(
	          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

	        Pageable priceDESC = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,"brand"));
	        
	        Page<Product> pagecontent;	        
	        pagecontent = repository.findAll(priceDESC);

	        List<Product> products = new ArrayList<Product>();
	        products = pagecontent.getContent();

	        Map<String, Object> display = new HashMap<>();
	        display.put("totalPagesCreated", pagecontent.getTotalPages());
	        display.put("currentPageIndex", pagecontent.getNumber());
	        
	        display.put("totalProductFound", pagecontent.getTotalElements());	        
	        display.put("products", products);

	        return new ResponseEntity<>(display, HttpStatus.OK);
	      }	    

	    @GetMapping("/productlist/SortByBrandDESC")
	    public ResponseEntity<?> getProductsSortByBrandDESC(
	          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

	        Pageable brandDESC = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"brand"));
	        
	        Page<Product> pagecontent;	        
	        pagecontent = repository.findAll(brandDESC);

	        List<Product> products = new ArrayList<Product>();
	        products = pagecontent.getContent();

	        Map<String, Object> display = new HashMap<>();
	        display.put("totalPagesCreated", pagecontent.getTotalPages());
	        display.put("currentPageIndex", pagecontent.getNumber());	        
	        display.put("totalProductFound", pagecontent.getTotalElements());	        
	        display.put("products", products);

	        return new ResponseEntity<>(display, HttpStatus.OK);
	      }	 	    
/*
	    @GetMapping("/productlist/SortByNameASC")
	    public ResponseEntity<?> getProductsSortByNameASC(
	          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

	        Pageable nameASC = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,"product_name"));
	        
	        Page<Product> pagecontent;	        
	        pagecontent = repository.findAll(nameASC);

	        List<Product> products = new ArrayList<Product>();
	        products = pagecontent.getContent();

	        Map<String, Object> display = new HashMap<>();
	        display.put("totalPagesCreated", pagecontent.getTotalPages());
	        display.put("currentPageIndex", pagecontent.getNumber());
	        
	        display.put("totalProductFound", pagecontent.getTotalElements());	        
	        display.put("products", products);

	        return new ResponseEntity<>(display, HttpStatus.OK);
	      }	    

	    @GetMapping("/productlist/SortByNameDESC")
	    public ResponseEntity<?> getProductsSortByNameDESC(
	          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {

	        Pageable nameDESC = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"product_name"));
	        
	        Page<Product> pagecontent;	        
	        pagecontent = repository.findAll(nameDESC);

	        List<Product> products = new ArrayList<Product>();
	        products = pagecontent.getContent();

	        Map<String, Object> display = new HashMap<>();
	        display.put("totalPagesCreated", pagecontent.getTotalPages());
	        display.put("currentPageIndex", pagecontent.getNumber());
	        
	        display.put("totalProductFound", pagecontent.getTotalElements());	        
	        display.put("products", products);

	        return new ResponseEntity<>(display, HttpStatus.OK);
	      }	 	    	    
*/
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
	    
	    /*
	    @GetMapping("/productlist/SortByPriceASC")
	    public ResponseEntity<?> getProducts(String sort, @RequestParam(required = false) String brand,
	    	  @RequestParam(required = false) Float price,
	          @RequestParam(defaultValue = "0") int page,@RequestParam (defaultValue = "9") int size) {
	        
	    	Pageable pageall = PageRequest.of(page, size);
	    	
	        Pageable brandASC = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,"brand"));
	        Pageable brandDESC = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"brand"));
	        
	        Pageable priceASC = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,"price"));
	        Pageable priceDESC = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"price"));
	        
	        Page<Product> pagecontent;	        
	        if (sort == "BrandASC")
	        	pagecontent = repository.findAll(brandASC);	         
	        else if (sort =="BrandDESC") 
	        	pagecontent = repository.findAll(brandDESC);
	        else if (sort == "PriceASC")
	        	pagecontent = repository.findAll(priceASC);	         
	        else if (sort =="PriceDESC") 
	        	pagecontent = repository.findAll(priceDESC);
	        else
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
*/	     	    
	    
	    
}
