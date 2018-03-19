package com.intergalacticcallcenter.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.Employee;
import com.intergalacticcallcenter.dto.abc.CallFactory;

@Component
public class AllocationAreaEmployeeImpl implements AllocationAreaEmployee {
	
	private EmployeeStorageController employeeStorageController;
	
	private CallFactory callFactory;
	
	@Autowired
	public AllocationAreaEmployeeImpl(EmployeeStorageController employeeStorageController, CallFactory callFactory) {
		this.employeeStorageController = employeeStorageController;
		this.callFactory = callFactory;
	}

	@Override
	public Call getEmployeeToCall(Call call){
		Employee employee = this.employeeStorageController.getEmployee();
		if(employee != null){
			Call call2 = callFactory.getCallWithEmployee(call, employee);
			return call2;
		}
		return null;
	}

}
