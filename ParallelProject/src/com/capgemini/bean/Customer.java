package com.capgemini.bean;

public class Customer {
	private String cName;
	private String mobileNumber;
	private Wallet wallet;
	public Customer(String cName, String mobileNumber, Wallet wallet) {
		super();
		this.cName = cName;
		this.mobileNumber = mobileNumber;
		this.wallet = wallet;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	@Override
	public String toString() {
		return "Customer [cName=" + cName + ", mobileNumber=" + mobileNumber + ", wallet=" + wallet + "]";
	}
	
}
