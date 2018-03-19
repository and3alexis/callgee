package com.intergalacticcallcenter.dto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.Employee;
import com.intergalacticcallcenter.dto.abc.EmployeeType;
import com.intergalacticcallcenter.dto.abc.Status;

public class CallTest {
	
	private Employee employee;

	@Before
	public void setUp() throws Exception {
		employee = new Employee(EmployeeType.OPERATOR);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCallWithNoId() {
		long time = System.nanoTime();
		Call call = new Call();
		call.setEndTime(time);
		call.setStartTime(time);
		call.setStatus(Status.PENDING);
		call.setEmployee(employee);
		
		assertNotNull(call);
		assertEquals(0, call.getId());
		assertEquals(EmployeeType.OPERATOR, call.getEmployee().getEmployeeType());
		assertEquals(time, call.getEndTime());
		assertEquals(time, call.getStartTime());
		assertEquals(Status.PENDING, call.getStatus());
		assertEquals("Call [employee=Employee [employeeType=OPERATOR], status=PENDING, time=0 secs ]", call.toString());
	}
	
	@Test
	public void testCallWithId() {
		long time = System.nanoTime();
		Call call = new Call(time);
		call.setEndTime(time);
		call.setStartTime(time);
		call.setStatus(Status.PENDING);
		call.setEmployee(employee);
		
		assertNotNull(call);
		assertEquals(time, call.getId());
		assertEquals(EmployeeType.OPERATOR, call.getEmployee().getEmployeeType());
		assertEquals(time, call.getEndTime());
		assertEquals(time, call.getStartTime());
		assertEquals(Status.PENDING, call.getStatus());
	}
	
	@Test
	public void testCallCopy() {
		long time = System.nanoTime();
		Call call = new Call(time);
		call.setEndTime(time);
		call.setStartTime(time);
		call.setStatus(Status.PENDING);
		call.setEmployee(employee);
		
		Call call2 = new Call();
		call2.copy(call);
		
		assertNotNull(call2);
		assertEquals(0, call2.getId());
		assertEquals(EmployeeType.OPERATOR, call2.getEmployee().getEmployeeType());
		assertEquals(time, call2.getEndTime());
		assertEquals(time, call2.getStartTime());
		assertEquals(Status.PENDING, call2.getStatus());
	}
	
	@Test
	public void testCallCompare() {
		Call call = new Call(System.nanoTime());
		Call call2 = new Call(System.nanoTime());
		
		int result1 = call.compareTo(call2);
		int result2 = call2.compareTo(call);
		
		assertEquals(1, result1);
		assertEquals(-1, result2);
	}

}
