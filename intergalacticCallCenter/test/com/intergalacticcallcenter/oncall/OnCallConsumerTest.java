package com.intergalacticcallcenter.oncall;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

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
import com.intergalacticcallcenter.dto.abc.Status;
import com.intergalacticcallcenter.dto.abc.Zone;
import com.intergalacticcallcenter.employee.EmployeeStorageController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:springContext.xml" })
public class OnCallConsumerTest {
	
	private OnCallConsumer onCallConsumer;
	
	@Autowired
	private CallFactory callFactory;
	
	@Autowired
	private ExecutorServiceWrap executorServiceWrap;
	
	@Autowired
	private EmployeeStorageController employeeStorageController;

	@Before
	public void setUp() throws Exception {
		onCallConsumer = new OnCallConsumerImpl(executorServiceWrap, callFactory);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCallFinnished() throws InterruptedException, ExecutionException {
		Call call = new Call(System.nanoTime());
		call = callFactory.getCallWithEmployee(call, new Employee(EmployeeType.OPERATOR));
		CallMomentum callMomentum = new CallMomentum(call, 0);
		callMomentum.setCallFactory(callFactory);
		callMomentum.setEmployeeStorageController(employeeStorageController);
		executorServiceWrap.submit(callMomentum);
		
		Call call2 = onCallConsumer.getCall();
		assertEquals(Status.FINNISHED, call2.getStatus());
	}
	
	@Test
	public void testGetCallFinnishedCloudy() throws InterruptedException, ExecutionException {
		Call call = new Call(System.nanoTime());
		call = callFactory.getCallWithEmployee(call, new Employee(EmployeeType.OPERATOR));
		CallMomentum callMomentum = new CallMomentum(call, 2);
		callMomentum.setCallFactory(callFactory);
		callMomentum.setEmployeeStorageController(employeeStorageController);
		executorServiceWrap.submit(callMomentum);
		
		Call call2 = onCallConsumer.getCall();
		assertEquals(Status.FINNISHED, call2.getStatus());
		assertEquals(Zone.CLOUDY, call2.getZone());
	}
	
	@Test
	public void testGetCallFinnishedSunny() throws InterruptedException, ExecutionException {
		Call call = new Call(System.nanoTime());
		call = callFactory.getCallWithEmployee(call, new Employee(EmployeeType.OPERATOR));
		CallMomentum callMomentum = new CallMomentum(call, 6);
		callMomentum.setCallFactory(callFactory);
		callMomentum.setEmployeeStorageController(employeeStorageController);
		executorServiceWrap.submit(callMomentum);
		
		Call call2 = onCallConsumer.getCall();
		assertEquals(Status.FINNISHED, call2.getStatus());
		assertEquals(Zone.SUNNY, call2.getZone());
	}
	
	@Test
	public void testGetCallFinnishedRainnig() throws InterruptedException, ExecutionException {
		Call call = new Call(System.nanoTime());
		call = callFactory.getCallWithEmployee(call, new Employee(EmployeeType.OPERATOR));
		CallMomentum callMomentum = new CallMomentum(call, 12);
		callMomentum.setCallFactory(callFactory);
		callMomentum.setEmployeeStorageController(employeeStorageController);
		executorServiceWrap.submit(callMomentum);
		
		Call call2 = onCallConsumer.getCall();
		assertEquals(Status.FINNISHED, call2.getStatus());
		assertEquals(Zone.RAINNIG, call2.getZone());
	}

}
