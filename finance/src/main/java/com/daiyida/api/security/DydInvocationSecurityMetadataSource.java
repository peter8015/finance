package com.daiyida.api.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.daiyida.api.domain.resource.Resource;
import com.daiyida.api.domain.role.Role;
import com.daiyida.api.service.role.RoleService;



@Component
public class DydInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{

	 @javax.annotation.Resource
	 private RoleService roleService;
	
	
	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
		loadResourcesDefine();
	}

	private Map<String,Collection<ConfigAttribute>> resourceMap = null; 
	private AntPathMatcher urlMatcher = new AntPathMatcher();
	


	private void loadResourcesDefine(){
		resourceMap = new HashMap<String,Collection<ConfigAttribute>>();
//		Collection<ConfigAttribute> configAttributes1 = new ArrayList<ConfigAttribute>() ;
//		ConfigAttribute configAttribute1 = new SecurityConfig("ROLE_ADMIN");
//		configAttributes1.add(configAttribute1);
//		resourceMap.put("/leftmenu.action", configAttributes1);
		
		
		List<Role> roless = roleService.findAllRoles();
		for(Role role : roless){
			Set<Resource> resources = role.getResources();
			for(Resource resource : resources){
				Collection<ConfigAttribute> configAttributes = null;
				ConfigAttribute configAttribute = new SecurityConfig(role.getName());
				if(resourceMap.containsKey(resource.getUrl())){
					configAttributes = resourceMap.get(resource.getUrl());
					configAttributes.add(configAttribute);
				}else{
					configAttributes = new ArrayList<ConfigAttribute>() ;
					configAttributes.add(configAttribute);
					resourceMap.put(resource.getUrl(), configAttributes);
				}
			}
		}
		if(resourceMap.size() == 0){
		//throw new AccessDeniedException("AccessDeniedException");
	}
	}
	/* 
	 * according resouce get grant
	 * 根据请求的资源地址获取它所拥有的权限
	 * 
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj)
			throws IllegalArgumentException {
		
		String url = ((FilterInvocation)obj).getRequestUrl();
		
		if(url.indexOf("?")!=-1){
			url = url.substring(0, url.indexOf("?"));
		}
		if(url.indexOf("/resources") != -1 || url.indexOf("/login") != -1 || url.equals("/")){
			return null;
		}
		
		if(resourceMap==null){
			loadResourcesDefine();
		}
		Iterator<String> it = resourceMap.keySet().iterator();
		while(it.hasNext()){
			String _url = it.next();
			if(_url.indexOf("?")!=-1){
				_url = _url.substring(0, _url.indexOf("?"));
			}
			if(urlMatcher.match(_url,url))
				return resourceMap.get(_url);
			
//			if(RegexUrlPathMatcher.pathMatchesUrl(_url, url)){
//				return resourceMap.get(_url);
//			} //for extend
		}
		throw new AccessDeniedException("The Resouce your visit that not been registed");
		//return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		System.out.println("MySecurityMetadataSource.supports()");
		return true;
	}
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
}