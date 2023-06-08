package com.imi.tax.cal.taxcaliculator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imi.tax.cal.taxcaliculator.domain.Employee;
import com.imi.tax.cal.taxcaliculator.domain.EmployeeVo;
import com.imi.tax.cal.taxcaliculator.repo.TaxPayersRepo;
import com.imi.tax.cal.taxcaliculator.util.TaxCalUtility;


@Service
public class TaxServiceImpl implements TaxService {
	
	

	@Autowired
	TaxPayersRepo repo;
	
	@Autowired
	TaxCalUtility taxCalUtil;

	@Override
	public int addEmployee(Employee emp) {

		return repo.save(emp).getId();
	}

	@Override
	public List<EmployeeVo> getEmpData(String fromYear) {

		List<EmployeeVo> employVoList = new ArrayList<EmployeeVo>();

		String toYear = String.valueOf(Integer.parseInt(fromYear) + 1);
		Optional<List<Employee>> employee = repo.getEmployeeData(fromYear + "-04-01", toYear + "-03-31");
		if (employee.isPresent()) {
			List<Employee> empList = employee.get();
			for (Employee emp : empList) {
				EmployeeVo employVo = new EmployeeVo();
				employVo.setFirstName(emp.getFirstName());
				employVo.setEmployeeCode(emp.getId());
				employVo.setLastName(emp.getLastName());
				employVo.setYearlSalary(emp.getSal() * 12);
				employVo.setTax(taxCalUtil.taxApplicable(emp.getSal(), emp.getDoj(),fromYear + "-04-01"));
				employVo.setCess(taxCalUtil.cessApplied(emp.getSal() * 12));
				employVoList.add(employVo);
			}

		}

		return employVoList;
	}



}
