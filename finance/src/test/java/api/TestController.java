package api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/test")
public class TestController {

	private static final Log logger = LogFactory.getLog(TestController.class);

	@RequestMapping("/get")
	@ResponseBody
	public ResponseEntity<String> get(@RequestParam("name") String name) {
		Map map = new HashMap();
		map.put("name", "fsdsafsd");
		logger.debug("name:" + name);
		String json = new JSONSerializer().serialize(map);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return new ResponseEntity<String>(json,headers, HttpStatus.OK);
	}
	
	
}
