package com.sbi.atm.iservice;

public interface IAccountService {
	public boolean deposit(Integer amount);
	public boolean withDraw(Integer amount);
	public Integer checkBalance();
}
