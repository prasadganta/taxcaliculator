package com.imi.tax.cal.taxcaliculator.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imi.tax.cal.taxcaliculator.domain.Employee;

@Repository
public interface TaxPayersRepo extends JpaRepository<Employee, Integer>{


	@Query(value = "select * from employee where to_char(doj,'yyyy-mm')  between :fromYear and :toYear", nativeQuery = true)
	public Optional<List<Employee>> getEmployeeData(@Param("fromYear") String fromYear, @Param("toYear") String toYear);


}
