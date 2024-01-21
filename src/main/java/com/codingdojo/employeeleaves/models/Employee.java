package com.codingdojo.employeeleaves.models;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message= "First name is required!")
	private String firstName;
	
	@NotBlank(message= "Last name is required!")
	private String lastName;
	
	@Email(message = "Please enter a valid email!")
	private String email;
	
	@NotBlank(message = "User name is required!")
	private String userName;
	
	@NotBlank(message = "Password is required!")
	private String password;
	
	@NotBlank(message = "Address is required!")
	private String address;
	
	@NotBlank(message = "Title is required!")
	private String title;
	
	@NotBlank(message = "Range is required!")
	private String rangeEmployee;
	
	@NotBlank(message = "Category is required!")
	private String category;
	
	@NotBlank(message = "Phone Number is required!")
	private Integer phoneNumber;
	
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
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	private List<Leave> leaves;

	public Employee() {
		
	}

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}
