package com.intergalacticcallcenter.employee;

import com.intergalacticcallcenter.dto.Employee;

public interface EmployeeStorageController {

	EmployeeCollection addEmployee(Employee employee);

	Employee getEmployee();

}
