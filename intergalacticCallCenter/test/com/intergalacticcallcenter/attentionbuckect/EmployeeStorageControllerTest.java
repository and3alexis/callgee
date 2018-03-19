package com.intergalacticcallcenter.attentionbuckect;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.intergalacticcallcenter.dto.Employee;
import com.intergalacticcallcenter.dto.abc.EmployeeType;
import com.intergalacticcallcenter.employee.EmployeeCollection;
import com.intergalacticcallcenter.employee.EmployeeStorage;
import com.intergalacticcallcenter.employee.EmployeeStorageController;
import com.intergalacticcallcenter.employee.EmployeeStorageControllerImpl;

public class EmployeeStorageControllerTest {
	
	private EmployeeStorageController employeeStorageController;

	@Before
	public void setUp() throws Exception {
		EmployeeStorage employeeStorage = new EmployeeStorage();
		this.employeeStorageController = new EmployeeStorageControllerImpl(employeeStorage);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddEmployeeOperator() {
		EmployeeCollection employeeCollection = this.employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		assertNotNull(employeeCollection);
		assertEquals(1, employeeCollection.size());
		Employee employee = employeeCollection.poll();
		assertNotNull(employee);
		assertEquals(EmployeeType.OPERATOR, employee.getEmployeeType());
	}
	
	@Test
	public void testAddEmployeeSupervisor() {
		EmployeeCollection employeeCollection = this.employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		assertNotNull(employeeCollection);
		assertEquals(1, employeeCollection.size());
		Employee employee = employeeCollection.poll();
		assertNotNull(employee);
		assertEquals(EmployeeType.SURPERVISOR, employee.getEmployeeType());
	}
	
	@Test
	public void testAddEmployeeDirector() {
		EmployeeCollection employeeCollection = this.employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		assertNotNull(employeeCollection);
		assertEquals(1, employeeCollection.size());
		Employee employee = employeeCollection.poll();
		assertNotNull(employee);
		assertEquals(EmployeeType.DIRECTOR, employee.getEmployeeType());
	}
	
	@Test
	public void testAddEmployees() {
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		EmployeeCollection employeeCollectionOperators = this.employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		EmployeeCollection employeeCollectionSupervisor = this.employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		EmployeeCollection employeeCollectionDirector = this.employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		
		assertNotNull(employeeCollectionOperators);
		assertEquals(3, employeeCollectionOperators.size());
		Employee employeeOperator = employeeCollectionOperators.poll();
		assertNotNull(employeeOperator);
		assertEquals(EmployeeType.OPERATOR, employeeOperator.getEmployeeType());
		
		assertNotNull(employeeCollectionSupervisor);
		assertEquals(3, employeeCollectionSupervisor.size());
		Employee employeeSupervisor = employeeCollectionSupervisor.poll();
		assertNotNull(employeeSupervisor);
		assertEquals(EmployeeType.SURPERVISOR, employeeSupervisor.getEmployeeType());
		
		assertNotNull(employeeCollectionDirector);
		assertEquals(3, employeeCollectionDirector.size());
		Employee employeeDirector = employeeCollectionDirector.poll();
		assertNotNull(employeeDirector);
		assertEquals(EmployeeType.DIRECTOR, employeeDirector.getEmployeeType());
	}

	@Test
	public void testGetEmployeeOperator() {
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		Employee employeeOperator = this.employeeStorageController.getEmployee();
		assertNotNull(employeeOperator);
		assertEquals(EmployeeType.OPERATOR, employeeOperator.getEmployeeType());
	}
	
	@Test
	public void testGetEmployeeSupervisor() {
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		this.employeeStorageController.getEmployee();
		Employee employee = this.employeeStorageController.getEmployee();
		assertNotNull(employee);
		assertEquals(EmployeeType.SURPERVISOR, employee.getEmployeeType());
	}
	
	@Test
	public void testGetEmployeeDirector() {
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		this.employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		this.employeeStorageController.getEmployee();
		this.employeeStorageController.getEmployee();
		Employee employee = this.employeeStorageController.getEmployee();
		assertNotNull(employee);
		assertEquals(EmployeeType.DIRECTOR, employee.getEmployeeType());
	}

}
