package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.UserRegistration;
import com.app.repository.UserRegistrationRepo;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{
	
	@Autowired
	UserRegistrationRepo userRegistrationRepo;
	
	@Override
	public boolean saveUser(UserRegistration user) {
		UserRegistration savedUser = userRegistrationRepo.save(user);
		return savedUser.getId()!= null?  true:  false;
	}


}
