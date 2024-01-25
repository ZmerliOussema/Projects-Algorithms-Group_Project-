package com.codingdojo.employeeleaves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.employeeleaves.models.Leave;
import com.codingdojo.employeeleaves.repositories.LeaveRepository;

@Service
public class LeaveService {

	@Autowired
	private LeaveRepository leaveRepo;

	public List<Leave> all(){
		return leaveRepo.findAll();
	}
	
	public Leave update(Leave leave) {
		return leaveRepo.save(leave);
	}
	

	public Leave addLeave(Leave leave) {
		return leaveRepo.save(leave);
	}
	
	public void deleteLeave(Leave leave) {
		leaveRepo.delete(leave);
	}
	
	public Leave findById(Long id) {
		Optional<Leave> optionalLeave = leaveRepo.findById(id);
		if(optionalLeave.isPresent()) {
			return optionalLeave.get();
		}else {
			return null;
		}
	}
	
	 public List<Leave> getAnnualLeavesByEmployeeId(Long employeeId) {
			return leaveRepo.findByEmployee_IdAndAnnualGreaterThan(employeeId, 0);
		}

		public List<Leave> getSickLeavesByEmployeeId(Long employeeId) {
			return leaveRepo.findByEmployee_IdAndSickGreaterThan(employeeId, 0);
		}

		public List<Leave> getSpecificLeavesByEmployeeId(Long employeeId) {
			return leaveRepo.findByEmployee_IdAndSpecificLeaveGreaterThan(employeeId, 0);
		}

		public List<Leave> getLeavesByEmployeeId(Long employeeId) {
			return leaveRepo.findByEmployee_Id(employeeId);
	    }
}
