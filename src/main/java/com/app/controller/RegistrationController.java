package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.UserRegistration;
import com.app.service.UserRegistrationService;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {
	
	@Autowired
	UserRegistrationService userRegistrationService;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@GetMapping("/registration")
	public ModelAndView registrationForm() {
		ModelAndView mav = new ModelAndView();
		UserRegistration user = new UserRegistration();
		mav.addObject("user", user);
		mav.setViewName("registration");
		return mav;
	}

	@PostMapping("/saveuser")
	public  ModelAndView saveUser(@Valid UserRegistration user, BindingResult result) {
		System.out.println(user);
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.addObject("user",user);
			mav.setViewName("registration");
			mav.addObject("failuremsg", "A problem occured in User registration.. Please try again");
			return mav;
		}
		boolean saveUser = userRegistrationService.saveUser(user);
		
		
		if(saveUser) {
			sendEmail(user.getEmail());
			UserRegistration newUser = new UserRegistration();
			mav.addObject("user", newUser);
			mav.addObject("successmsg", "User registration done and mail sent");
		}
		else {
			mav.addObject("failuremsg", "A problem occured in User registration.. Please try again");	
			mav.addObject("user", user);
		}
		mav.setViewName("registration");
		
		return mav;
		
	}
	public void sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registration Team");
        message.setText("User Registration done");

        javaMailSender.send(message);
    }
}
