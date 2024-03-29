package com.Employees_Leaves.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "Leaves")
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start_date;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end_date;

	private int annual = 0;

	private int specificLeave = 0;

	private int sick = 0;

	private String status;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

// 	M:1	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee owner;

//	ZERO ARGS CONSTRUCTOR	
	public Leave() {
	}

// 	ALL ARGS CONSTRUCTOR 
	public Leave(Date start_date, Date end_date, int annual, int specificLeave, int sick, String status) {
		this.start_date = start_date;
		this.end_date = end_date;
		this.annual = annual;
		this.specificLeave = specificLeave;
		this.sick = sick;
		this.status = status;
	}

//	GETTERS & SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getAnnual() {
		return annual;
	}

	public void setAnnual(int annual) {
		this.annual = annual;
	}

	public int getSpecificLeave() {
		return specificLeave;
	}

	public void setSpecificLeave(int specificLeave) {
		this.specificLeave = specificLeave;
	}

	public int getSick() {
		return sick;
	}

	public void setSick(int sick) {
		this.sick = sick;
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

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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