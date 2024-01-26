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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "First name is required!")
	private String firstName;

	@NotBlank(message = "Last name is required!")
	private String lastName;

	@Email(message = "Please enter a valid email!")
	private String email;

	@Transient
	@NotBlank(message = "Password is required!")
	private String password;

	@Transient
	@NotBlank(message = "Confirm Password is required!")
	private String confirmPassword;

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

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	 @OneToMany(mappedBy="employee", fetch = FetchType.LAZY)
	 private List<Leave> leaves;
	 
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
