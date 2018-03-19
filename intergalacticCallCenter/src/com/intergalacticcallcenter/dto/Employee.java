package com.intergalacticcallcenter.dto;

import com.intergalacticcallcenter.dto.abc.EmployeeType;

public class Employee{
	
	private EmployeeType employeeType;
	
	public Employee(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [");
		if (employeeType != null)
			builder.append("employeeType=").append(employeeType);
		builder.append("]");
		return builder.toString();
	}



}
