package com.intergalacticcallcenter.attentionbuckect;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.Employee;
import com.intergalacticcallcenter.dto.abc.CallFactory;
import com.intergalacticcallcenter.dto.abc.EmployeeType;
import com.intergalacticcallcenter.employee.AllocationAreaEmployee;
import com.intergalacticcallcenter.employee.AllocationAreaEmployeeImpl;
import com.intergalacticcallcenter.employee.EmployeeStorage;
import com.intergalacticcallcenter.employee.EmployeeStorageController;
import com.intergalacticcallcenter.employee.EmployeeStorageControllerImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:springContext.xml" })
public class AllocationAreaEmployeeTest {
	
	private AllocationAreaEmployee allocationAreaEmployee;
	
	private EmployeeStorageController employeeStorageController;
	
	private EmployeeStorage employeeStorage;
	
	@Autowired
	private CallFactory callFactory;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetEmployeeToCallOperator() {
		employeeStorage = new EmployeeStorage();
		employeeStorageController = new EmployeeStorageControllerImpl(employeeStorage); 
		allocationAreaEmployee = new AllocationAreaEmployeeImpl(employeeStorageController, callFactory);
		
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		Call call = this.allocationAreaEmployee.getEmployeeToCall(new Call(System.nanoTime()));
		assertNotNull(call);
		assertNotNull(call.getEmployee());
		assertEquals(EmployeeType.OPERATOR, call.getEmployee().getEmployeeType());
	}
	
	@Test
	public void testGetEmployeeToCallSupervisor() {
		employeeStorage = new EmployeeStorage();
		employeeStorageController = new EmployeeStorageControllerImpl(employeeStorage); 
		allocationAreaEmployee = new AllocationAreaEmployeeImpl(employeeStorageController, callFactory);
		
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		this.allocationAreaEmployee.getEmployeeToCall(new Call(System.nanoTime()));
		Call call = this.allocationAreaEmployee.getEmployeeToCall(new Call(System.nanoTime()));
		assertNotNull(call);
		assertNotNull(call.getEmployee());
		assertEquals(EmployeeType.SURPERVISOR, call.getEmployee().getEmployeeType());
	}
	
	@Test
	public void testGetEmployeeToCallDirector() {
		employeeStorage = new EmployeeStorage();
		employeeStorageController = new EmployeeStorageControllerImpl(employeeStorage); 
		allocationAreaEmployee = new AllocationAreaEmployeeImpl(employeeStorageController, callFactory);
		
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		this.allocationAreaEmployee.getEmployeeToCall(new Call(System.nanoTime()));
		this.allocationAreaEmployee.getEmployeeToCall(new Call(System.nanoTime()));
		Call call = this.allocationAreaEmployee.getEmployeeToCall(new Call(System.nanoTime()));
		assertNotNull(call);
		assertNotNull(call.getEmployee());
		assertEquals(EmployeeType.DIRECTOR, call.getEmployee().getEmployeeType());
	}
	
	@Test
	public void testGetEmployeeToCallNoEmployeesAvalible() {
		employeeStorage = new EmployeeStorage();
		employeeStorageController = new EmployeeStorageControllerImpl(employeeStorage); 
		allocationAreaEmployee = new AllocationAreaEmployeeImpl(employeeStorageController, callFactory);
		
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		this.allocationAreaEmployee.getEmployeeToCall(new Call(System.nanoTime()));
		this.allocationAreaEmployee.getEmployeeToCall(new Call(System.nanoTime()));
		this.allocationAreaEmployee.getEmployeeToCall(new Call(System.nanoTime()));
		Call call = this.allocationAreaEmployee.getEmployeeToCall(new Call(System.nanoTime()));
		assertNull(call);
	}

}
