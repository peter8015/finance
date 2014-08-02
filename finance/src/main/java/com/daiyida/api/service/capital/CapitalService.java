package com.daiyida.api.service.capital;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.daiyida.api.domain.capital.Capital;

@RooService(domainTypes = { com.daiyida.api.domain.capital.Capital.class })
public interface CapitalService {
	 public abstract List<Capital> findCapitalByUserId(String id);  
}
