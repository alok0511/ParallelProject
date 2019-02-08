package com.capgemini.repo;

import java.util.HashMap;
import java.util.Map;

import com.capgemini.bean.Customer;

public class DBStore {

	private static Map<String, Customer> customers;	
	public static Map<String,Customer> createCollection(){
		
		if(customers==null)
			customers=new HashMap<>();
		return customers;
	
	}		
}