package api;
import java.util.Map;

import flexjson.JSONDeserializer;
public class Main {

	public static void main(String[] args) {
		String str = "{\"aa\":\"bb\"}";
		JSONDeserializer<Map> serializer = new JSONDeserializer<Map>();
		Map map = serializer.deserialize(str);
		System.out.println(map.get("aa"));
	}

}
