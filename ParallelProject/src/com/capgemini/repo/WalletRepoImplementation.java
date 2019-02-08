package com.capgemini.repo;

import java.math.BigDecimal;
import java.util.Map;

import com.capgemini.bean.Customer;
import com.capgemini.bean.Wallet;

public class WalletRepoImplementation implements WalletRepo {
	Map<String, Customer> cMap;
	 public WalletRepoImplementation() {
		cMap= DBStore.createCollection();
		cMap.put("9450766173", new Customer("Sudhir", "9450766173", new Wallet(BigDecimal.valueOf(4000))));
		cMap.put("9731803567", new Customer("Shashank", "9731803567", new Wallet(BigDecimal.valueOf(12000))));
	}
	
	@Override
	public boolean save(Customer customer) {
		
		if(customer!=null){
		cMap.put(customer.getMobileNumber(), customer);
		return true;
		}
		
		return false;
	}
	@Override
	public Customer findOne(String mobileNumber) {
		if(cMap.containsKey(mobileNumber)){
		Customer customer =cMap.get(mobileNumber);
		return customer;
		}
		
		return null;
	}
		
}

