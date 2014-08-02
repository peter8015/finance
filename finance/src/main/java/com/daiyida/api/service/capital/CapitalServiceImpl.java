package com.daiyida.api.service.capital;

import java.util.List;

import com.daiyida.api.domain.capital.Capital;

public class CapitalServiceImpl implements CapitalService {
	
	@Override
	 public  List<Capital> findCapitalByUserId(String id){		 
		 return Capital.findCapitalByUserId(id);
	 }  
}
