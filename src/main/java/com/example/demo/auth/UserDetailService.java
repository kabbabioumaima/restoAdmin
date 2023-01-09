package com.example.demo.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserDetail;
import com.example.demo.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
	UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByEmail(username);
		
		if(user==null) {
			System.out.println("not found");
			throw new UsernameNotFoundException("Not found");
			
		}
		
		UserDetail userDetail = new UserDetail();
		
		userDetail.setUsername(user.getEmail());
		userDetail.setAccountNonExpired(true);
		userDetail.setPassword(user.getPassword());
		userDetail.setEnabled(true);
		userDetail.setAccountNonLocked(true);
		userDetail.setCredentialsNonExpired(true);
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		for(Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		userDetail.setAuthorities(authorities);
		return userDetail;
	}

	
}
