package com.example.demo.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(User user) {
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findByName("ROLE_OWNER"));
		User userAccount = new User(user.getEmail(),user.getFname(),user.getLname(),passwordEncoder.encode(user.getPassword()),roles);
		
		return userRepository.save(userAccount);
	}

	@Override
	public User getByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}
	
	

}
