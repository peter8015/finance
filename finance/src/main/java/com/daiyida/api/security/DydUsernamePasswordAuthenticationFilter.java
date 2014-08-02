package com.daiyida.api.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class DydUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	 public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
	        if (!request.getMethod().equals("POST")) {
	            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
	        }

	        String username = obtainUsername(request);
	        String password = obtainPassword(request);

	        if (username == null) {
	            username = "";
	        }

	        if (password == null) {
	            password = "";
	        }
            
	        username = username.trim();
	        request.getSession().setAttribute("loginid",username);
	        
	        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

	        // Allow subclasses to set the "details" property
	        setDetails(request, authRequest);

	        return this.getAuthenticationManager().authenticate(authRequest);
	    }
	}

	

