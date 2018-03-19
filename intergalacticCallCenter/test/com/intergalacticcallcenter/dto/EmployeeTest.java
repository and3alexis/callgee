package com.intergalacticcallcenter.dto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.intergalacticcallcenter.dto.Employee;
import com.intergalacticcallcenter.dto.abc.EmployeeType;

public class EmployeeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Employee employee = new Employee(EmployeeType.OPERATOR);
		assertNotNull(employee);
		assertEquals(EmployeeType.OPERATOR, employee.getEmployeeType());
		assertEquals("Employee [employeeType=OPERATOR]", employee.toString());
	}
	
	@Test
	public void testEmployeeTypeSurperviser() {
		Employee employee = new Employee(EmployeeType.SURPERVISOR);
		assertNotNull(employee);
		assertEquals(EmployeeType.SURPERVISOR, employee.getEmployeeType());
	}
	
	@Test
	public void testEmployeeTypeDirector() {
		Employee employee = new Employee(EmployeeType.DIRECTOR);
		assertNotNull(employee);
		assertEquals(EmployeeType.DIRECTOR, employee.getEmployeeType());
	}

}
