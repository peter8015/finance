package com.daiyida.api.security;

import java.util.List;
import java.util.Map;

public class ResourceDetailInfo {
	
	public static Map<String, List<String>> resourceInfoMap = null;
	
	
	/**
	 * 根据资源名称获取资源的访问权限
	 * @param resourceName USER_ADD_BUTTON USER_ADD_PAGE 与RESOURCE_INFO表的NAME字段名称一致即可。
	 * @return String 对应按钮的可访问的角色信息
	 */
	public static String getRoleInfos(String resourceName) {
		StringBuilder builder = new StringBuilder();
		List<String> resources = ResourceDetailInfo.resourceInfoMap.get(resourceName);
		if(resources==null){
			return builder.toString();
		}
		for(String resourceInfo:resources){
			if(builder.capacity() == 0){
				builder.append(resourceInfo);
			}
			else{
				builder.append(",").append(resourceInfo);
			}
		}
		return builder.toString();
	}
}
