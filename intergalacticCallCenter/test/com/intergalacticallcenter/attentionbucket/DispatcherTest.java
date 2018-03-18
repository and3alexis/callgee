package com.intergalacticallcenter.attentionbucket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.intergalacticallcenter.dto.Call;
import com.intergalacticallcenter.dto.Employee;
import com.intergalacticallcenter.dto.EmployeeType;

public class DispatcherTest {
	
	private Dispatcher dispatcher;

	@Before
	public void setUp() throws Exception {
		BlockingQueue<Employee> employeeCollection = new EmployeeCollection();
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		employeeCollection.put(new Employee(EmployeeType.SURPERVISOR));
		employeeCollection.put(new Employee(EmployeeType.OPERATOR));
		employeeCollection.put(new Employee(EmployeeType.OPERATOR));
		employeeCollection.put(new Employee(EmployeeType.OPERATOR));
		employeeCollection.put(new Employee(EmployeeType.OPERATOR));
		employeeCollection.put(new Employee(EmployeeType.OPERATOR));
		employeeCollection.put(new Employee(EmployeeType.OPERATOR));
		employeeCollection.put(new Employee(EmployeeType.OPERATOR));
		employeeCollection.put(new Employee(EmployeeType.OPERATOR));
		
		dispatcher = new DispatcherImpl(null, new AttentionBucketCollection(), employeeCollection);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDispatchCall() throws InterruptedException, ExecutionException {
		Call callPending = dispatcher.dispatchCall(new Call());
		assertDispatchCall(callPending);
	}
	
	@Test
	public void testDispatchCall10calls() throws InterruptedException, ExecutionException {
		Call callFuture1 = dispatcher.dispatchCall(new Call());
		Call callFuture2 = dispatcher.dispatchCall(new Call());
		Call callFuture3 = dispatcher.dispatchCall(new Call());
		Call callFuture4 = dispatcher.dispatchCall(new Call());
		Call callFuture5 = dispatcher.dispatchCall(new Call());
		Call callFuture6 = dispatcher.dispatchCall(new Call());
		Call callFuture7 = dispatcher.dispatchCall(new Call());
		Call callFuture8 = dispatcher.dispatchCall(new Call());
		Call callFuture9 = dispatcher.dispatchCall(new Call());
		Call callFuture10 = dispatcher.dispatchCall(new Call());
		
		assertDispatchCall(callFuture1);
		assertDispatchCall(callFuture2);
		assertDispatchCall(callFuture3);
		assertDispatchCall(callFuture4);
		assertDispatchCall(callFuture5);
		assertDispatchCall(callFuture6);
		assertDispatchCall(callFuture7);
		assertDispatchCall(callFuture8);
		assertDispatchCall(callFuture9);
		assertDispatchCall(callFuture10);
		
	}
	
	@Test
	public void testDispatchCall11calls() throws InterruptedException, ExecutionException {
		Call callFuture1 = dispatcher.dispatchCall(new Call());
		Call callFuture2 = dispatcher.dispatchCall(new Call());
		Call callFuture3 = dispatcher.dispatchCall(new Call());
		Call callFuture4 = dispatcher.dispatchCall(new Call());
		Call callFuture5 = dispatcher.dispatchCall(new Call());
		Call callFuture6 = dispatcher.dispatchCall(new Call());
		Call callFuture7 = dispatcher.dispatchCall(new Call());
		Call callFuture8 = dispatcher.dispatchCall(new Call());
		Call callFuture9 = dispatcher.dispatchCall(new Call());
		Call callFuture10 = dispatcher.dispatchCall(new Call());
		Call callFuture11 = dispatcher.dispatchCall(new Call());
		
		assertDispatchCall(callFuture1);
		assertDispatchCall(callFuture2);
		assertDispatchCall(callFuture3);
		assertDispatchCall(callFuture4);
		assertDispatchCall(callFuture5);
		assertDispatchCall(callFuture6);
		assertDispatchCall(callFuture7);
		assertDispatchCall(callFuture8);
		assertDispatchCall(callFuture9);
		assertDispatchCall(callFuture10);
		assertDispatchCall(callFuture11);
		
	}
	
	private void assertDispatchCall(Call callFuture) throws InterruptedException, ExecutionException{
		assertNotNull(callFuture);
		assertEquals(Status.PENDING, callFuture.getStatus());
		System.out.println(callFuture);
	}

}
