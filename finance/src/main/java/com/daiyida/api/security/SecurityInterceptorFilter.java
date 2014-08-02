package com.daiyida.api.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/***
 * @description filter， 
 * authenticationManager,accessDecisionManager,securityMetadataSource
 * @author kpchen
 *
 */
public class SecurityInterceptorFilter extends AbstractSecurityInterceptor
		implements Filter {
	
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	public void invoke(FilterInvocation fi) throws IOException, ServletException{
		//核心方法，它会去调用securityMetadataSource的getAttributes方法和accessDecisionManager的decide方法
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try{
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(!principal.equals("anonymousUser")){
				HttpSession session = fi.getRequest().getSession(true);
				
					if(session.getMaxInactiveInterval() != 8*60*60)
						session.setMaxInactiveInterval(8*60*60);
				
			}
		}
		finally{
			super.afterInvocation(token, null);
		}
	}

	@Override
	public Class<?> getSecureObjectClass() {
		// TODO Auto-generated method stub
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		// TODO Auto-generated method stub
		return securityMetadataSource;
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}
}
