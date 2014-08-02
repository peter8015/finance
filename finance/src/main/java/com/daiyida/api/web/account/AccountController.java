package com.daiyida.api.web.account;


import java.util.Date;
import java.util.HashMap;
import java.util.List;





import com.daiyida.api.domain.account.Account;
import com.daiyida.api.service.bankcard.BankCardService;
import com.daiyida.api.service.capital.CapitalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.daiyida.api.domain.bankcard.BankCard;
import com.daiyida.api.domain.capital.Capital;
import com.daiyida.api.security.PasswordEncoder;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@RequestMapping("/accounts")
@Controller
@RooWebScaffold(path = "accounts", formBackingObject = Account.class)
@RooWebJson(jsonObject = Account.class)
public class AccountController {
	@Autowired
	private CapitalService CapitalService;
	
	@Autowired
	private BankCardService bankCardService;
	
	/*
	 * 验证用户名是否存在
	 */
	 @RequestMapping(value = "/findAccount", method = RequestMethod.POST, headers = "Accept=application/json")
	 public ResponseEntity<String> findUserByName(@RequestParam String name) {
		 List<Account> result  = accountService.findUserByName(name);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json; charset=utf-8");
	        if (result != null) {
	            return new ResponseEntity<String>("false",headers, HttpStatus.NOT_FOUND);
	        }
	      
	        return new ResponseEntity<String>("OK",headers, HttpStatus.OK);
	    }
	 
	 /*
	  * 
	  * 登陆
	  */
	 @RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	 public ResponseEntity<String> login(@RequestBody String json) {
		  //HashMap<String, String> result = new HashMap<String, String>();
			HashMap<String, String> jsonMap = new JSONDeserializer<HashMap<String, String>>()
					.use(null, HashMap.class).deserialize(json);
			String userName = jsonMap.get("loginName");
			String pwd = jsonMap.get("loginPwd");
		    List<Account> result1 = accountService.login(userName.toLowerCase(), PasswordEncoder.md5(pwd));
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json; charset=utf-8");
	        if (result1 == null) {
	            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<String>(Account.toJsonArray(result1),headers, HttpStatus.OK);
	    }
	 
	 /*
	  * 验证用户名
	  */
	 @RequestMapping(value = "/checkUser", method = RequestMethod.POST, headers = "Accept=application/json")
	 public ResponseEntity<String> checkUser(@RequestParam("name") String name) {
		    Long l = accountService.checkUser(name);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json; charset=utf-8");
	        if (l >=1) {
	            return new ResponseEntity<String>("false",headers, HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<String>("true",headers, HttpStatus.OK);
	    }
	 
	 
	/*
	 * 
	 * 验证手机号
	 */
	 @RequestMapping(value = "/checkphone", method = RequestMethod.POST, headers = "Accept=application/json")
	 public ResponseEntity<String> checkPhone(@RequestParam("name") String name) {
		    Long l = accountService.checkPhone(name);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json; charset=utf-8");
	        if (l >=1) {
	            return new ResponseEntity<String>("false",headers, HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<String>("true",headers, HttpStatus.OK);
	    }
	 
	 /*
	  * 验证用户名手机号是否一致
	  */
	 @RequestMapping(value = "/checkUserPhone", method = RequestMethod.POST, headers = "Accept=application/json")
	 public ResponseEntity<String> checkUserPhone(@RequestParam("name") String name,@RequestParam("phone") String phone) {
		    Long l = accountService.checkUserPhone(name,phone);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json; charset=utf-8");
	        if (l == 0) {
	            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<String>(headers, HttpStatus.OK);
	    }
	 

	
	/*
	 * 修改资金交易密码
	 */
	
	@RequestMapping(value = "/updateCap", method = RequestMethod.POST, headers = "Accept=application/json")
	  public ResponseEntity<String> updateCap(@RequestBody() String json, @PathVariable("name") String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Account user = Account.fromJsonToAccount(json);
        List<Account> result  = accountService.findUserByName(name);
        if(result == null ){
        	 return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        String id=result.get(0).getId();
        String string=  CapitalService.findCapitalByUserId(id).toString();
        if (accountService.updateAccount(user) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
	
	

	
	 @RequestMapping(value = "/saveAccount", method = RequestMethod.POST, headers = "Accept=application/json")
	    public ResponseEntity<String> saveAccount(@RequestBody String json) {
	        Account account = Account.fromJsonToAccount(json);
	        account.setCreateTime(new Date());
	        accountService.saveAccount(account);
	       List<Account> result = accountService.findAccountByName(account.getUserName());
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json");
	        return new ResponseEntity<String>(result.get(0).getId(),headers, HttpStatus.OK);
	    }
	
	
	/*
	 * 
	 * 注册
	 */
	 @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
	    public ResponseEntity<String> register(@RequestBody String json) {
		 
		 
		  //  HashMap<String, String> result = new HashMap<String, String>();
			HashMap<String, String> jsonMap = new JSONDeserializer<HashMap<String, String>>()
					.use(null, HashMap.class).deserialize(json);
			String userName = jsonMap.get("userName");
			String pwd = jsonMap.get("pwd");
		    String phone=jsonMap.get("phone");
			String code=jsonMap.get("code");
		    String realName=jsonMap.get("realName");
			String cardNo=jsonMap.get("cardNo");
			String idCardNo=jsonMap.get("idCardNo");
			String capitalPwd=jsonMap.get("capitalPwd");
			Account account = new Account();
			account.setUserName(userName);
			account.setPassword(PasswordEncoder.md5(pwd));
			account.setPhoneNum(phone);
			account.setRealName(realName);
			account.setStatus(1);
			account.setCreateTime(new Date());
			 accountService.saveAccount(account);
			 List<Account> result1 = accountService.findAccountByName(account.getUserName());	      
             String accountId=result1.get(0).getId();
             BankCard bankCard=new BankCard();
             bankCard.setRealName(realName);
             bankCard.setBankCardNum(cardNo);
             bankCard.setIdCardNum(idCardNo);
             bankCard.setStatus(1);
             bankCard.setBindTime(new Date());
             bankCard.setUserId(accountId);
             bankCardService.saveBankCard(bankCard);
             Capital capital=new Capital();
             capital.setUserId(accountId);
             capital.setFund(0.00);
             capital.setStatus(1);
             capital.setPassword(PasswordEncoder.md5(capitalPwd));
             CapitalService.saveCapital(capital);
	         
	     
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json");
	        return new ResponseEntity<String>("OK",headers, HttpStatus.OK);
	    }
	
	
	
	
	
	

	////----------------------------------------------------------------
	
	@RequestMapping(value="/updatePass",method=RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<String> updateLoginPass(@RequestBody String json){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		// JSONObject result = new JSONObject();
		HashMap<String, String> result = new HashMap<String, String>();

		// JSONObject jsonObject = new JSONObject(json);
		HashMap<String, String> jsonMap = new JSONDeserializer<HashMap<String, String>>()
				.use(null, HashMap.class).deserialize(json);

		String userName = jsonMap.get("userName");
		String phoneNum = jsonMap.get("phoneNum");

		// 验证账号信息
		Account user = accountService.findAccountByName_Phone(userName,
				phoneNum);
		if (user == null) {
			// 用户不存在
			result.put("result", "FAIL");
			result.put("errorcode", "待定");
			result.put("errmsg", "用户不存在");

			return new ResponseEntity<String>(new JSONSerializer().exclude(
					"*.class").serialize(result), headers,
					HttpStatus.BAD_REQUEST);
		}

		// TODO: 验证手机验证码

		// TODO:验证U盾与实名信息是否匹配

		// 验证数据库实名信息
		String realName = jsonMap.get("realName");
		String idCard = jsonMap.get("idCardNum");
		String cardNo = jsonMap.get("bankCardNum");
		BankCard bankCard = bankCardService.findBankCardByAccount(user.getId());
		if (bankCard == null) {
			// 未绑定银行卡
			result.put("result", "FAIL");
			result.put("errorcode", "待定");
			result.put("errmsg", "未绑定银行卡");
			return new ResponseEntity<String>(new JSONSerializer().exclude(
					"*.class").serialize(result), headers,
					HttpStatus.BAD_REQUEST);
		}
		if (!realName.equals(bankCard.getRealName())
				|| !idCard.equals(bankCard.getIdCardNum())
				|| !cardNo.equals(bankCard.getBankCardNum())) {
			// 数据库实名认证错误
			result.put("result", "FAIL");
			result.put("errorcode", "待定");
			result.put("errmsg", "数据库实名认证错误");
			return new ResponseEntity<String>(new JSONSerializer().exclude(
					"*.class").serialize(result), headers,
					HttpStatus.BAD_REQUEST);
		}

		// 更新账户密码
		String newPassword = jsonMap.get("loginPassword");
		user.setPassword(PasswordEncoder.md5(newPassword));
		accountService.updateAccount(user);

		result.put("result", "SUCCESS");
		result.put("accountId", user.getId());
		result.put("bankCardId", bankCard.getId());
		return new ResponseEntity<String>(new JSONSerializer().exclude(
				"*.class").serialize(result), headers, HttpStatus.OK);

	}
/*	
	@RequestMapping(value="/check",method=RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<String> checkByNameAndPhone(@RequestBody String json){
        HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            JSONObject result = new JSONObject();
            
			JSONObject jsonObject = new JSONObject(json);
	        String userName = jsonObject.getString("userName");
	        String phoneNum = jsonObject.getString("phoneNum");			
			
	        //验证账号信息
	        Account user = accountService.findAccountByName_Phone(userName, phoneNum);
	        if(user==null){
	        	//用户不存在
	        	result.put("result", "FAIL");
	        	result.put("errorcode", "待定");
	        	result.put("errmsg", "用户不存在");
	        	return new ResponseEntity<String>( result.toString(),headers, HttpStatus.NOT_FOUND);
	        }	

	                
	    	result.put("result", "SUCCESS");
	    	result.put("accountId", user.getId());
	    	return new ResponseEntity<String>(result.toString(),headers, HttpStatus.OK);  
			
		} catch (JSONException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(headers, HttpStatus.BAD_REQUEST);
		}		
	}
	
	@RequestMapping(value="/updatePass/{accountId}",method=RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<String> updatePassWord(@PathVariable String accountId,
			@RequestBody String json){
        HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
        
        JSONObject result = new JSONObject();
        try {
	        Account user = accountService.findAccount(accountId);
	        if(user==null){
	        	//用户不存在
	        	result.put("result", "FAIL");
	        	result.put("errorcode", "待定");
	        	result.put("errmsg", "用户不存在");
	        	return new ResponseEntity<String>( result.toString(),headers, HttpStatus.NOT_FOUND);
	        }
	        
	        //更新账户密码            
			JSONObject jsonObject = new JSONObject(json);
	        String newPassword = jsonObject.getString("loginPassword");	        	        
	        user.setPassword(PasswordEncoder.md5(newPassword));
	        accountService.updateAccount(user);
	        
	    	result.put("result", "SUCCESS");
	    	return new ResponseEntity<String>(result.toString(),headers, HttpStatus.OK);
			
		} catch (JSONException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(headers, HttpStatus.BAD_REQUEST);
		}		
	}*/
	

}
