package com.sbi.atm.service;
import com.sbi.atm.iservice.IAccountService;
public class AccountServie implements IAccountService{
	private static Integer balance=1000;
	public boolean deposit(Integer amount) {
		boolean isDeposited=false;
		try {
			this.balance+=amount;
			isDeposited=true;
		}
		catch(Exception ex){
			System.out.print("Exception raised while deposit");
		}
		return isDeposited;
	}
	public boolean withDraw(Integer amount) {
		boolean isWithDraw=false;
		try {
			if(this.balance>=amount) {
				balance-=amount;
				isWithDraw=true;
			}
			else {
				System.out.println("Not enough balance");
			}
		}
		catch(Exception ex) {
			System.out.println("Exception raised while withdraw");
		}
		return isWithDraw;
	}
	public Integer checkBalance() {
		return this.balance;
	}

}
