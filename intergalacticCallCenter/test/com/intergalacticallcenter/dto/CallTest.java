package com.intergalacticallcenter.dto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CallTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Employee employee = new Employee(EmployeeType.OPERATOR);
		
		Call call = new Call();
		call.setEmployee(employee);
		
		assertNotNull(call);
		assertEquals(EmployeeType.OPERATOR, call.getEmployee().getEmployeeType());
	}

}
