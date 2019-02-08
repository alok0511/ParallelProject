package com.capgemini.repo;

import com.capgemini.bean.Customer;

public interface WalletRepo {
	public boolean save(Customer customer);
	public Customer findOne(String mobileNumber);

}
