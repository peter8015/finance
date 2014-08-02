package com.daiyida.api.web.capital;

import java.util.HashMap;
import java.util.List;
import com.daiyida.api.domain.account.Account;
import com.daiyida.api.domain.bankcard.BankCard;
import com.daiyida.api.domain.capital.Capital;
import com.daiyida.api.security.PasswordEncoder;
import com.daiyida.api.service.account.AccountService;
import com.daiyida.api.service.bankcard.BankCardService;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;



@RequestMapping("/capital")
@Controller
@RooWebScaffold(path = "capital", formBackingObject = Capital.class)
@RooWebJson(jsonObject = Capital.class)
public class CapitalController {

	@Autowired
	private BankCardService bankCardService;
	
	@Autowired
	private AccountService accountService;

	/*

	 * ����û�id������Ӧ��¼

	 */

	 @RequestMapping(value = "/findCapByUserId", method = RequestMethod.POST, headers = "Accept=application/json")
	    @ResponseBody
	    public ResponseEntity<String> findCapByUserId(@PathVariable("id") String id) {
		 List<Capital> result = capitalService.findCapitalByUserId(id);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json; charset=utf-8");
	        if (result == null) {
	            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
	        } 
	       
	        return new ResponseEntity<String>(Capital.toJsonArray(result), headers, HttpStatus.OK);
	    }
	 /*
	  * �޸Ľ�������
	  */
	 @RequestMapping(value = "/updateCapByUserId", method = RequestMethod.POST, headers = "Accept=application/json")
   public ResponseEntity<String> updateCapByUserId(@PathVariable("id") String id) {
		 HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json; charset=utf-8");
		 
		  return new ResponseEntity<String>( headers, HttpStatus.OK);
	 }

	/*@RequestMapping(value = "/findCapByUserId", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> findCapByUserId(@PathVariable("id") String id) {
		List<Capital> result = capitalService.findCapitalByUserId(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		if (result == null) {
			return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>(Capital.toJsonArray(result), headers,
				HttpStatus.OK);
	}*/

	/*
	 * �޸Ľ�������
	 
	@RequestMapping(value = "/updateCapByUserId", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> updateCapByUserId(@PathVariable("id") String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}
*/
/*	@RequestMapping(value = "/updatePass/{accoundId}", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> updatePass(@PathVariable String accountId,
			@RequestBody String json) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		try {

			JSONObject result = new JSONObject();
			List<Capital> capitals = capitalService
					.findCapitalByUserId(accountId);
			Capital capital = capitals.isEmpty() ? null : capitals.get(0);
			if (capital == null) {
				result.put("result", "FAIL");
				result.put("errorcode", "待定");
				result.put("errmsg", "未找到资金账号");
				return new ResponseEntity<String>(result.toString(), headers,
						HttpStatus.NOT_FOUND);
			}

			JSONObject jsonObject = new JSONObject(json);
			String newPassword = jsonObject.getString("capitalPassword");
			capital.setPassword(PasswordEncoder.md5(newPassword));
			capitalService.updateCapital(capital);

			result.put("result", "SUCCESS");
			result.put("captialId", capital.getId());
			return new ResponseEntity<String>(result.toString(), headers,
					HttpStatus.OK);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<String>(headers, HttpStatus.BAD_REQUEST);
	}*/
	
	
	
	
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

		// 更新资金密码
		List<Capital> capitals = capitalService.findCapitalByUserId(user
				.getId());
		Capital capital = capitals.isEmpty() ? null : capitals.get(0);
		if (capital == null) {
			result.put("result", "FAIL");
			result.put("errorcode", "待定");
			result.put("errmsg", "未找到资金账号");
			return new ResponseEntity<String>(new JSONSerializer().exclude(
					"*.class").serialize(result), headers, HttpStatus.NOT_FOUND);
		}

		String newPassword = jsonMap.get("capitalPassword");
		capital.setPassword(PasswordEncoder.md5(newPassword));
		capitalService.updateCapital(capital);

		result.put("result", "SUCCESS");
		result.put("captialId", capital.getId());
		return new ResponseEntity<String>(new JSONSerializer().exclude(
				"*.class").serialize(result), headers, HttpStatus.OK);
		

	}	
	

	 
	 
	 
	   @RequestMapping( value="/saveCapital",method = RequestMethod.POST, headers = "Accept=application/json")
	    public ResponseEntity<String> saveCapital(@RequestBody String json) {
	        Capital capital = Capital.fromJsonToCapital(json);
	        capitalService.saveCapital(capital);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json");
	        return new ResponseEntity<String>(headers, HttpStatus.OK);
	    }
	 
	 
	 
	 
}
