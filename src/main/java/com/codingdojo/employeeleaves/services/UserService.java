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

	// Create a new User with role "USER" while creating a new Employee
	public User createUser(User newUser, BindingResult result) {

		// Reject if Email is taken.
		Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());
		if (potentialUser.isPresent()) {
			result.rejectValue("email", "registerErrors", "Email is taken");
		}

		// Does the entered password match the confirmation password?
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

	// Update Password for User of role "USER"
	public User updateUserPW(User user, BindingResult result) {

		// Does the entered password match the confirmation password?
		if (!user.getPassword().equals(user.getConfirmPW())) {
			result.rejectValue("password", "updateErrors", "passwords does not match");
		}

		// If we have any Error.
		if (result.hasErrors()) {
			return null;
		} else {
		    User existingUser = userRepository.findById(user.getId()).orElse(null);
		    if (existingUser != null) {
		    	// Hash and Set password then Save the User in the Database.
				String hashPW = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
				existingUser.setPassword(hashPW);
		    }
		    return userRepository.save(existingUser);
		}
	}

	// Get a book by Id
	public User findUser(Long id) {
		Optional<User> maybeUser = userRepository.findById(id);
		if (maybeUser.isPresent()) {
			return maybeUser.get();
		} else {
			return null;
		}
	}

	// Login User
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
