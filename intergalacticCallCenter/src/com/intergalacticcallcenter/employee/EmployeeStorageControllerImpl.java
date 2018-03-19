package com.intergalacticcallcenter.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.intergalacticcallcenter.dto.Employee;
import com.intergalacticcallcenter.dto.abc.EmployeeType;

@Controller
public class EmployeeStorageControllerImpl implements EmployeeStorageController{
	
	private EmployeeStorage employeeStorage;
	
	@Autowired
	public EmployeeStorageControllerImpl(EmployeeStorage employeeStorage) {
		this.employeeStorage = employeeStorage;
	}
	
	@Override
	public EmployeeCollection addEmployee(Employee employee){
		EmployeeCollection employeeCollection = employeeStorage.get(employee.getEmployeeType());
		if(employeeCollection == null){
			employeeCollection = new EmployeeCollection();
			employeeCollection.add(employee);
			employeeStorage.put(employee.getEmployeeType(), employeeCollection);
		}else{
			employeeCollection.add(employee);
		}
		return employeeCollection;
	}
	
	@Override
	public Employee getEmployee(){
		Employee employee = getEmployeeByEmployeeType(EmployeeType.OPERATOR);
		if(employee == null){
			employee = getEmployeeByEmployeeType(EmployeeType.SURPERVISOR);
			if(employee == null){
				employee = getEmployeeByEmployeeType(EmployeeType.DIRECTOR);
			}
		}
		return employee;
	}
	
	private Employee getEmployeeByEmployeeType(EmployeeType employeeType){
		Employee employee = null;
		EmployeeCollection employeeCollection = employeeStorage.get(employeeType);
		if(employeeCollection != null){
			employee = employeeCollection.poll();
		}
		return employee;
	}

}
