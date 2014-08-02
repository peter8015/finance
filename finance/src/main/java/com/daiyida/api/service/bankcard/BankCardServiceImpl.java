package com.daiyida.api.service.bankcard;

import com.daiyida.api.domain.bankcard.BankCard;

public class BankCardServiceImpl implements BankCardService {
	@Override
	public BankCard findBankCardByAccount(String accountId){
		return BankCard.findBankCardByAccount(accountId);
	};

}
