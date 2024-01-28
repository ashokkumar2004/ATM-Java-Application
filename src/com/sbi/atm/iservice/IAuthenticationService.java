package com.sbi.atm.iservice;

public interface IAuthenticationService {
	public boolean authentication(Integer pin);
	public boolean resetpin(Integer newpin);
}