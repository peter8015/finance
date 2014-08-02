package com.daiyida.api.security;

import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

public class ResourceSecurityTag extends TagSupport {
	
	/**
	 * 资源名称
	 */
	private String resourceName;

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Override
	public int doStartTag() throws JspException {
//		String roleNames = ResourceDetailInfo.getRoleInfos(resourceName);
//		Authentication authn = SecurityContextHolder.getContext().getAuthentication();
//		UserDetailInfo user = (UserDetailInfo)authn.getPrincipal();
//		Collection<GrantedAuthority> authorites = user.getAuthorities();
//		for(GrantedAuthority authority : authorites){
//			String auth = authority.getAuthority();
//			if(roleNames.indexOf(auth) != -1){
//				return EVAL_BODY_INCLUDE;
//			}
//		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
