package com.intergalacticallcenter.attentionbucket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.Employee;
import com.intergalacticcallcenter.dto.abc.CallFactory;
import com.intergalacticcallcenter.dto.abc.CallFactoryImpl;
import com.intergalacticcallcenter.dto.abc.EmployeeType;
import com.intergalacticcallcenter.dto.abc.Status;
import com.intergalacticcallcenter.employee.EmployeeStorage;
import com.intergalacticcallcenter.employee.EmployeeStorageController;
import com.intergalacticcallcenter.employee.EmployeeStorageControllerImpl;
import com.intergalacticcallcenter.oncall.CallMomentum;

public class CallMomentumTest {
	
	private ExecutorService executor;

	@Before
	public void setUp() throws Exception {
		this.executor = Executors.newFixedThreadPool(10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCall() throws Exception {
		EmployeeStorageController employeeStorageController = new EmployeeStorageControllerImpl(new EmployeeStorage());
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		CallFactory callFactory = new CallFactoryImpl();
		Call call = new Call(System.nanoTime());
		call.setEmployee(new Employee(EmployeeType.OPERATOR));
		CallMomentum callMomentum = new CallMomentum(call, 5);
		callMomentum.setCallFactory(callFactory);
		callMomentum.setEmployeeStorageController(employeeStorageController);
		Future<Call> futureCall = this.executor.submit(callMomentum);
		Call returnedCall = futureCall.get();
		
		assertNotNull(returnedCall);
		assertEquals(Status.FINNISHED, returnedCall.getStatus());
	}
	
}
