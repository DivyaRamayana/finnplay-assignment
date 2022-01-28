package com.finnplay.assignmet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.finnplay.assignmet.exception.ResourceNotFoundException;
import com.finnplay.assignmet.model.User;
import com.finnplay.assignmet.repository.UserRepository;

@Controller
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepo;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "SignUp";
	}

	// create user
	@PostMapping("/process_register")
	public String processRegister(User user, BindingResult bindingResult) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepo.save(user);

		BCryptPasswordEncoder confirmpasswordEncoder = new BCryptPasswordEncoder();
		String encodedconfirmPassword = confirmpasswordEncoder.encode(user.getConfirmPassword());
		user.setConfirmPassword(encodedconfirmPassword);
		userRepo.save(user);
		LOGGER.info("user registered successfully");

		return "registration_success";
	}

	@GetMapping("/users")
	public String listUsers(Model model, Authentication authentication) throws ResourceNotFoundException  {
		User user = userRepo.findByEmail(authentication.getName());
		//System.out.println(authentication.getName());
		model.addAttribute("user", user);
		return "users";
	}

	// Update user
	@SuppressWarnings("unlikely-arg-type")
	@GetMapping("/edit/{id}")
	public String updateUser(@PathVariable("id") Long userId, Model model) throws ResourceNotFoundException {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user id :" + userId));
		model.addAttribute(user);
		userRepo.equals(user);
		return "update_user";
	}

	@PostMapping("/edit/{id}")
	public String update_User(User user, BindingResult bindingResult) throws ResourceNotFoundException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepo.save(user);

		BCryptPasswordEncoder confirmpasswordEncoder = new BCryptPasswordEncoder();
		String encodedconfirmPassword = confirmpasswordEncoder.encode(user.getConfirmPassword());
		user.setConfirmPassword(encodedconfirmPassword);
		userRepo.save(user);
		
		LOGGER.info("user updated successfully");

		return "update_success";
	}

}
