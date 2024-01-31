package com.Employees_Leaves.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {

// 	MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "First Name is required!")
	@Size(min = 3, max = 30, message = "First Name must be between 3 and 30 characters")
	private String firstName;

	@NotEmpty(message = "Last Name is required!")
	@Size(min = 3, max = 30, message = "Last Name must be between 3 and 30 characters")
	private String lastName;

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

	private String role = "user"; // Default value is "user"

	@NotBlank(message = "Address is required!")
	private String address;

	@NotBlank(message = "Title is required!")
	private String title;

	@NotBlank(message = "Range is required!")
	private String rangeEmployee;

	@NotBlank(message = "Category is required!")
	private String category;

	@NotNull(message = "Phone Number is required!")
	private int phoneNumber;

	@NotBlank(message = "First name is required/translate")
	private String firstNameAr;

	@NotBlank(message = "Last name is required/translate")
	private String lastNameAr;

	@NotBlank(message = "Address is required/translate")
	private String addressAr;

	@NotBlank(message = "Title is required/translate")
	private String titleAr;

	@NotBlank(message = "Range is required/translate")
	private String rangeAr;

	@NotBlank(message = "Category is required/translate")
	private String categoryAr;

// 	This will not allow the createdAt column to be updated after creation	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

//	1:M	
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private List<Leave> leaves;

//	Zero-args Constructor	
	public Employee() {
	}

//	All-args Constructor
	public Employee(String firstName, String lastName, String email, String password, String address, String title,
			String rangeEmployee, String category, int phoneNumber, String firstNameAr, String lastNameAr,
			String addressAr, String titleAr, String rangeAr, String categoryAr, List<Leave> leaves) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.title = title;
		this.rangeEmployee = rangeEmployee;
		this.category = category;
		this.phoneNumber = phoneNumber;
		this.firstNameAr = firstNameAr;
		this.lastNameAr = lastNameAr;
		this.addressAr = addressAr;
		this.titleAr = titleAr;
		this.rangeAr = rangeAr;
		this.categoryAr = categoryAr;
		this.leaves = leaves;
	}

//	GETTERS & SETTERS
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRangeEmployee() {
		return rangeEmployee;
	}

	public void setRangeEmployee(String rangeEmployee) {
		this.rangeEmployee = rangeEmployee;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstNameAr() {
		return firstNameAr;
	}

	public void setFirstNameAr(String firstNameAr) {
		this.firstNameAr = firstNameAr;
	}

	public String getLastNameAr() {
		return lastNameAr;
	}

	public void setLastNameAr(String lastNameAr) {
		this.lastNameAr = lastNameAr;
	}

	public String getAddressAr() {
		return addressAr;
	}

	public void setAddressAr(String addressAr) {
		this.addressAr = addressAr;
	}

	public String getTitleAr() {
		return titleAr;
	}

	public void setTitleAr(String titleAr) {
		this.titleAr = titleAr;
	}

	public String getRangeAr() {
		return rangeAr;
	}

	public void setRangeAr(String rangeAr) {
		this.rangeAr = rangeAr;
	}

	public String getCategoryAr() {
		return categoryAr;
	}

	public void setCategoryAr(String categoryAr) {
		this.categoryAr = categoryAr;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}

	// Save the date before the object is created
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	// Save the date on every update
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
