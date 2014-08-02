package com.daiyida.api.service.account;

import java.util.List;

import com.daiyida.api.domain.account.Account;

public class AccountServiceImpl implements AccountService {
	@Override
	public List<Account> findUserByName(String name) {
	
		return Account.findUserByName(name);
	}

	

	@Override
	public List<Account> login(String name, String pwd) {
		return Account.login(name,pwd);
	}
	@Override
	public  long checkUser(String name){
		
		return Account.checkUser(name);
	}
	
	@Override
	public  long checkPhone(String name){
		
		return Account.checkPhone(name);
	}
	@Override
	public  long checkUserPhone(String name,String phone){
		
		return Account.checkUserPhone(name,phone);
	}

	
	@Override
	public List<Account> findAccountByName(String name) {
	
		return Account.findAccountByName(name);
	}

	@Override 
	public Account findAccountByName_Phone(String userName,String phoneNum){
		return Account.findAccountByName_Phone(userName, phoneNum);
	}


	
	

}
