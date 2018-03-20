package com.intergalacticallcenter.attentionbucket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intergalacticcallcenter.dispatcher.Dispatcher;
import com.intergalacticcallcenter.dispatcher.DispatcherImpl;
import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.Employee;
import com.intergalacticcallcenter.dto.abc.CallFactory;
import com.intergalacticcallcenter.dto.abc.EmployeeType;
import com.intergalacticcallcenter.dto.abc.Status;
import com.intergalacticcallcenter.employee.EmployeeStorage;
import com.intergalacticcallcenter.employee.EmployeeStorageController;
import com.intergalacticcallcenter.employee.EmployeeStorageControllerImpl;
import com.intergalacticcallcenter.oncall.ExecutorServiceWrapImpl;

/*
 * Para mas info ver la url:
 * https://s3-sa-east-1.amazonaws.com/intergalacticcallcenter/index.html
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:springContext.xml" })
public class DispatcherTest {
	
	private Dispatcher dispatcher;
	
	@Autowired
	private CallFactory callFactory;
	
	@Before
	public void setUp() throws Exception {
		EmployeeStorage employeeStorage = new EmployeeStorage();
		EmployeeStorageController employeeStorageController = new EmployeeStorageControllerImpl(employeeStorage);
		employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		dispatcher = new DispatcherImpl(new ExecutorServiceWrapImpl(), employeeStorageController, callFactory);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*
	Escenario: ICC-0005 Llamada iniciada
	Dado que: una llamada ya se asignó un empleado
	Cuando: se envía al dispatcher
	Entonces: el estado de la llamada cambia el estado a iniciada
	Y se captura el tiempo fin de estado pendiente
	Y se captura el tiempo de comienzo
	*/
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
	
	private void assertDispatchCall(Call callFuture) throws InterruptedException, ExecutionException{
		assertNotNull(callFuture);
		assertEquals(Status.STARTED, callFuture.getStatus());
		System.out.println(callFuture);
	}

}
