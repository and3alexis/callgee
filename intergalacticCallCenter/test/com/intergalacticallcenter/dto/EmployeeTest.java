package com.intergalacticallcenter.dto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		assertEquals(false, employee.isAvalible());
	}
	
	@Test
	public void testEmployeeTypeSurperviser() {
		Employee employee = new Employee(EmployeeType.SURPERVISOR);
		assertNotNull(employee);
		assertEquals(EmployeeType.SURPERVISOR, employee.getEmployeeType());
		assertEquals(false, employee.isAvalible());
	}
	
	@Test
	public void testEmployeeTypeDirector() {
		Employee employee = new Employee(EmployeeType.DIRECTOR);
		assertNotNull(employee);
		assertEquals(EmployeeType.DIRECTOR, employee.getEmployeeType());
		assertEquals(false, employee.isAvalible());
	}

}
