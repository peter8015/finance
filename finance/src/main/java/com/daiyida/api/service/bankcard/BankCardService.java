package com.daiyida.api.service.bankcard;
import org.springframework.roo.addon.layers.service.RooService;

import com.daiyida.api.domain.bankcard.BankCard;

@RooService(domainTypes = { com.daiyida.api.domain.bankcard.BankCard.class })
public interface BankCardService {
	public abstract BankCard findBankCardByAccount(String accountId);
}
