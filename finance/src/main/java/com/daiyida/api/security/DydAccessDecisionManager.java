package com.daiyida.api.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * According compare , decide the visitor that enter or deny
 * @author kpchen
 *
 */

public class DydAccessDecisionManager implements AccessDecisionManager {  
	  
    /** 
     * @description authentication compare 
     *  
     */  
    @Override  
    public void decide(Authentication authentication, Object obj,  
            Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,InsufficientAuthenticationException {  
        if(configAttributes==null) return;  
        Iterator<ConfigAttribute> it = configAttributes.iterator();  
        while(it.hasNext()){  
            String needRole = it.next().getAttribute();  
            for(GrantedAuthority ga:authentication.getAuthorities()){  
                if(needRole.equals(ga.getAuthority())){  
                    return;  
                }  
            }  
        }  
        throw new AccessDeniedException("AccessDeniedException");  
    }  
  
    /** 
     *  called by AbstractSecurityInterceptor 
     */  
    @Override  
    public boolean supports(ConfigAttribute configAttribute) {  
        return true;  
    }  
  
    /** 
     *  
     */  
    @Override  
    public boolean supports(Class<?> clazz) {  
        return true;  
    }  
  
}  
