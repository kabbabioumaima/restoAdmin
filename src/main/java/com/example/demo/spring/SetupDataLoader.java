package com.example.demo.spring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Etat;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.EtatRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.EtatEnum;
import com.example.demo.utils.RoleEnum;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{

	/*
	 * this class will be useful to insert two users in database for test checks also for
	 * roles existence if not it will create them
	 */
	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private EtatRepository etatRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		
		createEtatIfNotFound(EtatEnum.PENDING.name());
		createEtatIfNotFound(EtatEnum.VALIDATED.name());
		createEtatIfNotFound(EtatEnum.NOT_VALIDATED.name());
		
		createRoleIfNotFound(RoleEnum.ROLE_ADMIN.name());
		createRoleIfNotFound(RoleEnum.ROLE_OWNER.name());

		Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN.name());
		Role ownerRole = roleRepository.findByName(RoleEnum.ROLE_OWNER.name());
		
		creatUserIfNotFound("owner@test.com", "test123", new Role[]{ownerRole});
		creatUserIfNotFound("admin@test.com", "test123", new Role[]{ownerRole,adminRole});
		
		alreadySetup = true;
		System.out.println("launched the app .............");
	}
	
	@Transactional
	void creatUserIfNotFound(String userName, String password,Role[] role) {
		if(userRepository.findByEmail(userName) == null) {
			User user = new User();
			user.setFname("TEST");
			user.setLname("TEST");
			user.setPassword(passwordEncoder.encode(password));
			user.setEmail(userName);
			Set<Role> roles = new HashSet<Role>(Arrays.asList(role));
			user.setRoles(roles);
			userRepository.save(user);
		}
	}

	@Transactional
	Role createRoleIfNotFound(String name) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			roleRepository.save(role);
		}
		return role;
	}
	
	@Transactional
	Etat createEtatIfNotFound(String name) {

		Etat etat = etatRepository.findByName(name);
		if (etat == null) {
			etat = new Etat();
			etat.setName(name);
			etatRepository.save(etat);
		}
		return etat;
	}
}

