package com.daiyida.api.service.account;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.daiyida.api.domain.account.Account;

@RooService(domainTypes = { com.daiyida.api.domain.account.Account.class })
public interface AccountService {
	public abstract List<Account> findUserByName(String name);
	public abstract List<Account> login(String name,String pwd);
	public abstract long checkUser(String name);
	public abstract long checkPhone(String name);
	public abstract long checkUserPhone(String name,String phone);

	public abstract List<Account> findAccountByName(String name);

	public abstract Account findAccountByName_Phone(String userName,String phoneNum);

}
