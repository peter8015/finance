package com.daiyida.api.web.bankcard;


import java.util.Date;



import com.daiyida.api.domain.bankcard.BankCard;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/bankcards")
@Controller
@RooWebScaffold(path = "bankcards", formBackingObject = BankCard.class)
@RooWebJson(jsonObject = BankCard.class)
public class BankCardController {

	

	
	  @RequestMapping(value="/saveBankCard", method = RequestMethod.POST, headers = "Accept=application/json")
	    public ResponseEntity<String> saveBankCard(@RequestBody String json) {
	        BankCard bankCard = BankCard.fromJsonToBankCard(json);
	        bankCard.setBindTime(new Date());
	        bankCardService.saveBankCard(bankCard);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json");
	        return new ResponseEntity<String>(headers, HttpStatus.OK);
	    }
	


	
	

/*	@RequestMapping(value="/check/{accountId}",method=RequestMethod.POST,headers="Accept=application/json")

	public ResponseEntity<String> check(@PathVariable("accountId") String accountId,
			@RequestBody String json){
        HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
        try {
            JSONObject result = new JSONObject();
            
            //是否用户是否绑定银行卡
	        BankCard bankCard = bankCardService.findBankCardByAccount(accountId);
	        if(bankCard==null){
	        	//未绑定银行卡
	        	result.put("result", "FAIL");
	        	result.put("errorcode", "待定");
	        	result.put("errmsg", "未绑定银行卡");
	        	return new ResponseEntity<String>( result.toString(),headers, HttpStatus.NOT_FOUND);
	        } 
	        
	        //验证数据库实名信息
			JSONObject jsonObject = new JSONObject(json);
	        String realName =jsonObject.getString("realName");
	        String idCard = jsonObject.getString("idCardNum");
	        String cardNo = jsonObject.getString("bankCardNum"); 	        
	        if(!realName.equals(bankCard.getRealName())
	        		||!idCard.equals(bankCard.getIdCardNum())
	        		||!cardNo.equals(bankCard.getBankCardNum())){	        	
	        	//数据库实名认证错误
	        	result.put("result", "FAIL");
	        	result.put("errorcode", "待定");
	        	result.put("errmsg", "数据库实名认证错误");
	        	return new ResponseEntity<String>( result.toString(),headers, HttpStatus.BAD_REQUEST);
	        }
	    	result.put("result", "SUCCESS");
	    	result.put("bankCard", bankCard.getId());
	    	return new ResponseEntity<String>(result.toString(),headers, HttpStatus.OK);  
			
		} catch (JSONException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(headers, HttpStatus.BAD_REQUEST);
		}		
	}*/

}
