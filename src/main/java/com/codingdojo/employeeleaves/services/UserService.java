package com.codingdojo.employeeleaves.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.employeeleaves.models.LoginUser;
import com.codingdojo.employeeleaves.models.User;
import com.codingdojo.employeeleaves.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User register(User newUser, BindingResult result) {

		// Reject if Email is taken.
		Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());
		if (potentialUser.isPresent()) {
			result.rejectValue("email", "registerErrors", "Email is taken");
		}

		// Does the entered password match the confirmation passaword?
		if (!newUser.getPassword().equals(newUser.getConfirmPW())) {
			result.rejectValue("password", "registerErrors", "passwords does not match");
		}

		// If we have any Error.
		if (result.hasErrors()) {
			return null;
		} else {
			// Hash and Set password then Save the User in the Database.
			String hashPW = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashPW);
			return userRepository.save(newUser);
		}

	}


	public User login(LoginUser newLoginObject, BindingResult result) {
		// Does the User with that email exist in the Database.
		Optional<User> potentialUser = userRepository.findByEmail(newLoginObject.getEmail());

		if (!potentialUser.isPresent()) {
			result.rejectValue("email", "loginErrors", "User not found!");
			return null;
		}

		User user = potentialUser.get();

		if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
			result.rejectValue("password", "loginErrors", "Invalid Password!");
		}

		if (result.hasErrors()) {
			return null;
		}

		return user;
	}
}
