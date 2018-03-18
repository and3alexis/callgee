package com.intergalacticallcenter.attentionbucket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.intergalacticallcenter.dto.Call;
import com.intergalacticallcenter.dto.Employee;
import com.intergalacticallcenter.dto.EmployeeType;

public class BucketTest {
	
	private ExecutorService executor;

	@Before
	public void setUp() throws Exception {
		this.executor = Executors.newFixedThreadPool(10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCallWithOperator() throws Exception {
		BlockingQueue<Employee> employeeCollection = new EmployeeCollection();
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		employeeCollection.put(new Employee(EmployeeType.SURPERVISOR));
		employeeCollection.put(new Employee(EmployeeType.SURPERVISOR));
		employeeCollection.put(new Employee(EmployeeType.OPERATOR));
		employeeCollection.put(new Employee(EmployeeType.SURPERVISOR));
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		
		
		Bucket bucketOperator = new Bucket(new Call());
		bucketOperator.setEmployeeCollection(employeeCollection);
		
		Future<Call> futureCallOperator = this.executor.submit(bucketOperator);
		
		Call returnedCall = futureCallOperator.get();
		
		assertNotNull(returnedCall);
		assertNotNull(returnedCall.getEmployee());
		assertEquals(EmployeeType.OPERATOR, returnedCall.getEmployee().getEmployeeType());
	}
	
	@Test
	public void testCallWithSupervisor() throws Exception {
		BlockingQueue<Employee> employeeCollection = new EmployeeCollection();
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		employeeCollection.put(new Employee(EmployeeType.SURPERVISOR));
		employeeCollection.put(new Employee(EmployeeType.SURPERVISOR));
		employeeCollection.put(new Employee(EmployeeType.OPERATOR));
		employeeCollection.put(new Employee(EmployeeType.SURPERVISOR));
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		
		Bucket bucketOperator = new Bucket(new Call());
		bucketOperator.setEmployeeCollection(employeeCollection);
		Bucket bucketSupervisor = new Bucket(new Call());
		bucketSupervisor.setEmployeeCollection(employeeCollection);
		
		Future<Call> futureCallOperator = this.executor.submit(bucketOperator);
		Future<Call> futureCallSupervisor = this.executor.submit(bucketSupervisor);
		
		Call returnedCallOperator = futureCallOperator.get();
		Call returnedCallSupervisor = futureCallSupervisor.get();
		
		assertNotNull(returnedCallOperator);
		assertNotNull(returnedCallOperator.getEmployee());
		assertEquals(EmployeeType.OPERATOR, returnedCallOperator.getEmployee().getEmployeeType());
		assertNotNull(returnedCallSupervisor);
		assertNotNull(returnedCallSupervisor.getEmployee());
		assertEquals(EmployeeType.SURPERVISOR, returnedCallSupervisor.getEmployee().getEmployeeType());
	}
	
	@Test
	public void testCallWithDirector() throws Exception {
		BlockingQueue<Employee> employeeCollection = new EmployeeCollection();
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		employeeCollection.put(new Employee(EmployeeType.DIRECTOR));
		employeeCollection.put(new Employee(EmployeeType.OPERATOR));
		employeeCollection.put(new Employee(EmployeeType.SURPERVISOR));
		
		Bucket bucketOperator = new Bucket(new Call());
		bucketOperator.setEmployeeCollection(employeeCollection);
		Bucket bucketSupervisor = new Bucket(new Call());
		bucketSupervisor.setEmployeeCollection(employeeCollection);
		Bucket bucketDirector = new Bucket(new Call());
		bucketDirector.setEmployeeCollection(employeeCollection);
		
		Future<Call> futureCallOperator = this.executor.submit(bucketOperator);
		Future<Call> futureCallSupervisor = this.executor.submit(bucketSupervisor);
		Future<Call> futureCallDirector = this.executor.submit(bucketDirector);
		
		Call returnedCallOperator = futureCallOperator.get();
		Call returnedCallSupervisor = futureCallSupervisor.get();
		Call returnedCallDirector = futureCallDirector.get();
		
		assertNotNull(returnedCallOperator);
		assertNotNull(returnedCallOperator.getEmployee());
		assertEquals(EmployeeType.OPERATOR, returnedCallOperator.getEmployee().getEmployeeType());
		assertNotNull(returnedCallSupervisor);
		assertNotNull(returnedCallSupervisor.getEmployee());
		assertEquals(EmployeeType.SURPERVISOR, returnedCallSupervisor.getEmployee().getEmployeeType());
		assertNotNull(returnedCallDirector);
		assertNotNull(returnedCallDirector.getEmployee());
		assertEquals(EmployeeType.DIRECTOR, returnedCallDirector.getEmployee().getEmployeeType());
	}

}
