package com.imi.tax.cal.taxcaliculator.service;

import java.util.List;

import com.imi.tax.cal.taxcaliculator.domain.Employee;
import com.imi.tax.cal.taxcaliculator.domain.EmployeeVo;


/**
 * @author prasadganta
 *
 */
public interface TaxService {
	
	
	
 public int addEmployee(Employee emp);
	 
	 public List<EmployeeVo> getEmpData(String financialYear);

}
