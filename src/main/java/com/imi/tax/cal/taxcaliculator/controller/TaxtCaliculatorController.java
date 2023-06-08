package com.imi.tax.cal.taxcaliculator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.imi.tax.cal.taxcaliculator.domain.Employee;
import com.imi.tax.cal.taxcaliculator.domain.EmployeeVo;
import com.imi.tax.cal.taxcaliculator.service.TaxService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

/**
 * @author prasadganta
 *
 */
@RestController
@RequestMapping("api/taxcal/")
public class TaxtCaliculatorController {

	@Autowired
	TaxService taxServ;

	@PostMapping(value="taxpayers")
	public ResponseEntity<String> perssiteEmp(@Valid @RequestBody Employee emp) {
		int empId = taxServ.addEmployee(emp);
		return ResponseEntity.ok("Employee Created with ID :" + empId);
	}
	
	
	@GetMapping("taxpayers/{financialYear}")
	public ResponseEntity<List<EmployeeVo>> empData(@PathVariable("financialYear") @Pattern(regexp="\\d{4}") String financialYear) {
		List<EmployeeVo> employeeVoList = taxServ.getEmpData(financialYear);
		return ResponseEntity.ok(employeeVoList);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
	    Map<String, Object> responseBody = new HashMap<>();
	    responseBody.put("error", "Validation failed");
	    responseBody.put("details", ex.getBindingResult().getAllErrors());
	    return ResponseEntity.badRequest().body(responseBody);
	}


}
