package com.daiyida.api.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



import com.daiyida.api.domain.account.Account;
import com.daiyida.api.domain.role.Role;


public class UserdetailsServiceImpl implements UserDetailsService {

	
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		
		List<Account> users = Account.findUsersByLoginName(username);
		if(users==null || users.size()<1){
			throw new UsernameNotFoundException("no such this user !");
		}
		Account user =users.get(0);
		boolean enabled = true;               
        boolean accountNonExpired = true;       
        boolean credentialsNonExpired = true;   
        boolean accountNonLocked = true;  
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		
		
		Set<Role> roles = user.getRoles();
		for(Role role : roles){
			GrantedAuthority ga = new SimpleGrantedAuthority(role.getName());
			authorities.add(ga);	
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUserName(),
				user.getPassword(), 
				enabled, 
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked, 
				authorities);
		
	}
	/**
	 * @param userService the userService to set
	 */
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}

}