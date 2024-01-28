package com.sbi.atm.ui.service;

import com.sbi.atm.ui.iservice.*;
import com.sbi.atm.iservice.*;
import com.sbi.atm.service.*;
import java.util.Scanner;

public class SBIservice implements SBIUIService {
    
	public void showSBI() {
		
		while (true) {
			showMenu();
		}

	}

	private void showMenu() {
		System.out.print("\n1.Deposit\n2.withdraw\n3.Balance\n4.pinChange\n5.Exit");
		prompting();
	}

	private void prompting() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nenter choice: ");
		Integer choice = scanner.nextInt();
		performOperations(choice);
	}

	private void performOperations(Integer choice) {
		Scanner scanner = new Scanner(System.in);
		IAccountService accountService = new AccountServie();
		Integer amount =null;
		switch (choice) {
		case 1:
			amount = readAmount();
			if (validateAmount(amount)) {
				if (accountService.deposit(amount)) {
					System.out.println("Amount deposited.");
				} else {
					System.out.println("Failed to deposit the amount.");
				}
			} else {
				System.out.println("Invalid amount entered.");
			}
			break;
		case 2:
			if(authorizeUser()) {
				amount =readAmount();
			    if(validateAmount(amount)) {
			    	if(accountService.withDraw(amount)) {
			    		System.out.println("amount withdraw");
			    		System.out.println("your balance is:" +accountService.checkBalance());
			    	}
			    	else {
			    		System.out.println("discarded");
			    	}
			    }
			}
			break;
		case 3:
			if(authorizeUser()) {
				System.out.println(accountService.checkBalance());
			}
			break;
		case 4:
			if(authorizeUser()) {
				Integer newPin=readPin();
				if(validatePin(newPin)) {
					IAuthenticationService authenticationService = new AuthenticationService();
					authenticationService.resetpin(newPin);
					System.out.println("Pin changed");
				}
				else {
					System.out.println("Enter valid pin");
				}
			}
			break;
		case 5:
			System.exit(0);
		default:
			System.out.println("Invalid choice.");
			break;
		}
	}

	private boolean authorizeUser() {		
		IAuthenticationService authenticationService = new AuthenticationService();
		boolean isAuthorized = false;
		Integer count = 1;
		Integer pin = null;
		while(count <=3) {
			pin = readPin();
			if (validatePin(pin)) {
				if(authenticationService.authentication(pin)) {
					isAuthorized = true;
					break;
				}else {
					count++;
					if(count>3) {
						System.out.println("Unauthorized user. Terminating the program");
					System.exit(0);
					}
				}				
			} else {
				System.out.println("Invalid PIN entered.");
			}			
		}
		return isAuthorized;
	}

	private Integer readAmount() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("enter amount: ");
		return scanner.nextInt();
	}

	private Integer readPin() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("enter PIN: ");
		return scanner.nextInt();
	}

	private boolean validateAmount(Integer amount) {
		return ((amount > 0) && (amount % 100 == 0)) ? true : false;
	}

	private boolean validatePin(Integer pin) {
		return ((pin > 999) && (pin <= 9999)) ? true : false;
	}


}
