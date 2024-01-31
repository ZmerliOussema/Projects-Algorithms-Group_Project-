package com.Employees_Leaves.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Employees_Leaves.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findAll();
	Optional<Employee> findByEmail(String email);
	Employee findByIdIs(Long id);
	
	@Modifying
	@Query("UPDATE Employee e SET e.firstName = :firstName, e.lastName = :lastName, e.email = :email, e.address = :address, e.title = :title, e.rangeEmployee = :rangeEmployee, e.category = :category, e.phoneNumber = :phoneNumber, e.firstNameAr = :firstNameAr, e.lastNameAr = :lastNameAr, e.addressAr = :addressAr, e.titleAr = :titleAr, e.rangeAr = :rangeAr, e.categoryAr = :categoryAr WHERE e.id = :id")
	int updateFirstName(@Param("firstName") String firstName,
						@Param("lastName") String lastName,
						@Param("email") String email,
						@Param("address") String address,
						@Param("title") String title,
						@Param("rangeEmployee") String rangeEmployee,
						@Param("category") String category,
						@Param("phoneNumber") int phoneNumber,
						@Param("firstNameAr") String firstNameAr,
						@Param("lastNameAr") String lastNameAr,
						@Param("addressAr") String addressAr,
						@Param("titleAr") String titleAr,
						@Param("rangeAr") String rangeAr,
						@Param("categoryAr") String categoryAr,
						@Param("id") Long id);
	
	@Modifying
	@Query("UPDATE Employee e SET e.password = :password WHERE e.id = :id")
	int chEmpPassword(@Param("password") String password,
					  @Param("id") Long id);
}






























































