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

@Entity
@Table(name="leaves")
public class Leave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	private Integer annualTaken;
	private Integer annualRest;

	private Integer specificTaken;
	private Integer specificRest;

	private Integer sickTaken;
	private Integer sickRest;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	 @OneToMany(mappedBy="leave", fetch = FetchType.LAZY)
	 private List<Employee> employees;

	public Leave() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getAnnualTaken() {
		return annualTaken;
	}

	public void setAnnualTaken(Integer annualTaken) {
		this.annualTaken = annualTaken;
	}

	public Integer getAnnualRest() {
		return annualRest;
	}

	public void setAnnualRest(Integer annualRest) {
		this.annualRest = annualRest;
	}

	public Integer getSpecificTaken() {
		return specificTaken;
	}

	public void setSpecificTaken(Integer specificTaken) {
		this.specificTaken = specificTaken;
	}

	public Integer getSpecificRest() {
		return specificRest;
	}

	public void setSpecificRest(Integer specificRest) {
		this.specificRest = specificRest;
	}

	public Integer getSickTaken() {
		return sickTaken;
	}

	public void setSickTaken(Integer sickTaken) {
		this.sickTaken = sickTaken;
	}

	public Integer getSickRest() {
		return sickRest;
	}

	public void setSickRest(Integer sickRest) {
		this.sickRest = sickRest;
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
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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
