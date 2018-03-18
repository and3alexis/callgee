package com.intergalacticallcenter.dto;

public class Employee implements Comparable<Employee>{
	
	private EmployeeType employeeType;
	
	private boolean isAvalible;

	public Employee(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public synchronized boolean isAvalible() {
		return isAvalible;
	}

	public synchronized void setAvalible(boolean isAvalible) {
		this.isAvalible = isAvalible;
	}

	@Override
	public int compareTo(Employee o) {
		if(this.employeeType.ordinal() > o.getEmployeeType().ordinal()){
			return 1;
		}else if(this.employeeType.ordinal() < o.getEmployeeType().ordinal()){
			return -1;
		}
		return 0;
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
