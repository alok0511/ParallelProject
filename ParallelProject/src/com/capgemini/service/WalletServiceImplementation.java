package com.capgemini.service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.bean.Customer;
import com.capgemini.bean.Wallet;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InvalidInputException;
import com.capgemini.repo.WalletRepo;
import com.capgemini.repo.WalletRepoImplementation;

public class WalletServiceImplementation implements WalletService{
	WalletRepo walletrepo;
	String[] deposit;
	public String[] getDeposit() {
		return deposit;
	}


	public void setDeposit(String[] deposit) {
		this.deposit = deposit;
	}

	//= new String[100];
	int i=0;
	
	public WalletServiceImplementation() {
		walletrepo=new WalletRepoImplementation();
	}
	
	
	@Override
	public Customer createAccount(String cName, String mobileNumber,BigDecimal balance) throws InvalidInputException{
		Wallet wallet=new Wallet(balance);
		Customer c=new Customer(cName, mobileNumber, wallet);
		if(walletrepo.save(c)){
			return c;
		}
		
		return null;
		
	}

	@Override
	public Customer showBalance(String mobileNumber) throws InvalidInputException {
		
		Customer customer=walletrepo.findOne(mobileNumber);
		if(customer==null)
			throw new InvalidInputException("Number not registered");
		else
		return customer;
	}

	@Override
	public Customer fundTransfer(String sourceMobileNumber, String targetMobileNumber,BigDecimal balance) throws InvalidInputException {
		
		Customer customer1=walletrepo.findOne(sourceMobileNumber);
		if(customer1==null)
			throw new InvalidInputException("Number not registered");
		
		Wallet wallet1=customer1.getWallet();
		BigDecimal currentBalance=wallet1.getBalance();
		Customer customer2=walletrepo.findOne(targetMobileNumber);
		if(customer2==null)
			throw new InvalidInputException("Number not registered");
		
		Wallet wallet2=customer2.getWallet();
		BigDecimal currentBalance2=wallet2.getBalance();
		
		if(currentBalance.compareTo(balance)<0)
			throw new InvalidInputException("Your account balance is less than amount you are transferring");
		
		currentBalance2= currentBalance2.add(balance);
		currentBalance= currentBalance.subtract(balance);
		wallet1.setBalance(currentBalance);
		customer1.setWallet(wallet1);
		walletrepo.save(customer1);
		
		wallet2.setBalance(currentBalance2);
		customer2.setWallet(wallet2);
		walletrepo.save(customer2);
		
		
		Customer customer3=walletrepo.findOne(sourceMobileNumber);
		
		return customer3;
	}

	@Override
	public Customer deposit(String mobileNumber, BigDecimal amount) throws InvalidInputException {
		
		Customer customer=walletrepo.findOne(mobileNumber);
		if(customer==null)
			throw new InvalidInputException("Number not registered");
		Wallet wallet=customer.getWallet();
		BigDecimal currentbal=wallet.getBalance();
		
		BigDecimal newbal= currentbal.add(amount);
		wallet.setBalance(newbal);
		customer.setWallet(wallet);
		walletrepo.save(customer);
		
		Customer customer1=walletrepo.findOne(mobileNumber);
		
		//deposit[0]= "Deposited amount "+amount;
		//i++;
		return customer1;
	}

	@Override
	public Customer withdraw(String mobileNumber, BigDecimal balance) throws InsufficientBalanceException, InvalidInputException {
		Customer customer=walletrepo.findOne(mobileNumber);
		if(customer==null)
			throw new InvalidInputException("Number not registered");
		
		Wallet wallet=customer.getWallet();
		BigDecimal currentbal=wallet.getBalance();
		if(currentbal.compareTo(balance)>0){
			BigDecimal newBalance= currentbal.subtract(balance);
			wallet.setBalance(newBalance);
			customer.setWallet(wallet);
			walletrepo.save(customer);
		
			Customer customer1=walletrepo.findOne(mobileNumber);
		
		return customer1;
		}
		else
			throw new InsufficientBalanceException("Current balance is lesser than Withdraw Ammount ");
	}

	@Override
	public boolean ValidateName(String cName) throws InvalidInputException {
		if(cName==null)
			throw new InvalidInputException("Name field cannot be empty");
		Pattern pattern=Pattern.compile("[A-Za-z ]{1,20}");
		Matcher mat=pattern.matcher(cName);
		return mat.matches();
	}

	@Override
	public boolean ValidateMobNo(String mobileNumber) throws InvalidInputException {
		if(mobileNumber==null)
			throw new InvalidInputException("Number connot be zero");
		Pattern pattern=Pattern.compile("[6-9]{1}[0-9]{2,9}");
		Matcher mat=pattern.matcher(mobileNumber);
		return mat.matches();
		
	}

	@Override
	public boolean ValidateAmount(BigDecimal balance) throws InvalidInputException {
		
		if(balance==null)
		throw new InvalidInputException("Number connot be zero");
	Pattern pattern=Pattern.compile("[1-9]{1}[0-9]{1,5}");
	Matcher mat=pattern.matcher(String.valueOf(balance));
	return mat.matches();
	}
}
