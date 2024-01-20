package com.Employees_Leaves.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {
	// MEMBER VARIABLES
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotEmpty(message = "First Name is required!")
		@Size(min = 2, message = "First Name must be at least 2 characters")
		private String firstName;
		
		@NotEmpty(message = "Email is required!")
		@Email(message = "Please enter a valid email!")
		private String email;

		@NotEmpty(message = "Password is required!")
		@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
		private String password;

		@Transient
		@NotEmpty(message = "Confirm Password is required!")
		@Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
		private String confirm;

	// Zero-args Constructor
		public Employee() {
			
		}

	// All-args Constructor
		public Employee(String firstName, String email, String password) {
			this.firstName = firstName;
			this.email = email;
			this.password = password;
		}

	// GETTERS & SETTERS
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getConfirm() {
			return confirm;
		}

		public void setConfirm(String confirm) {
			this.confirm = confirm;
		}		
}













































