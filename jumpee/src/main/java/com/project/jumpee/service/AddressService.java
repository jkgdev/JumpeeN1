package com.project.jumpee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.jumpee.model.Address;
import com.project.jumpee.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository repository;
	
//REQUEST DATA FROM REPOSITORY---------------------------------------------------
	
	public List <Address> listAll() {
		return repository.findAll();
	}
	//GET ADDRESS BY ID
	public Address getAddressById(Integer id) {
		return repository.findById(id).get();
	}
	//SAVE ADDRESS ADD
	public void save(Address address, int id) {
		address.setCustomer_id(id);
		repository.save(address);
	}
	//SAVE ADDRESS UPDATE
	public void saveup(Address address) {
		repository.save(address);
	}
	
	//DELETE ADDRESS
	public void delete(Integer id) {
		repository.deleteById(id);
	}
		
//INPUT VALIDATION---------------------------------------------------		
	
	//NAME VALIDATION (ALL LETTERS)
	public boolean checkNameAllLetters(String name){
	    String pattern="^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$";
	    return name.matches(pattern);
	}
	//PHONE NUMBER VALIDATION
	public boolean isValidPHPhone(String phone){
	    String pattern="^(09|\\+639)\\d{9}$";
	    return phone.matches(pattern);
	}	
	
	//GET ADDRESS BY ID
	public Integer findAddIdbyCustomerId(Integer id) {
		return repository.findAddIdbyCustomerId (id);
	}
	
}
