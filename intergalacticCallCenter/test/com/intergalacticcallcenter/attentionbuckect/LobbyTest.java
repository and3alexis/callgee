package com.intergalacticcallcenter.attentionbuckect;

import static org.junit.Assert.*;

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
import com.intergalacticcallcenter.employee.Lobby;
import com.intergalacticcallcenter.employee.LobbyImpl;
import com.intergalacticcallcenter.employee.EmployeeStorage;
import com.intergalacticcallcenter.employee.EmployeeStorageController;
import com.intergalacticcallcenter.employee.EmployeeStorageControllerImpl;

/*
 * Para mas info ver la url:
 * https://s3-sa-east-1.amazonaws.com/intergalacticcallcenter/index.html
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:springContext.xml" })
public class LobbyTest {
	
	private Lobby lobby;
	
	private EmployeeStorageController employeeStorageController;
	
	private EmployeeStorage employeeStorage;
	
	@Autowired
	private CallFactory callFactory;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*
	Escenario: ICC-0002 Asignar empleado operador a una llamada
	Dado que: un empleado operador se encuentra disponible
	Cuando: hay una llamada en la bolsa de atención
	Entonces: se asigna la llamada al empleado operador
	*/
	@Test
	public void testGetEmployeeToCallOperator() {
		employeeStorage = new EmployeeStorage();
		employeeStorageController = new EmployeeStorageControllerImpl(employeeStorage); 
		lobby = new LobbyImpl(employeeStorageController, callFactory);
		
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		Call call = this.lobby.getEmployeeToCall(new Call(System.nanoTime()));
		assertNotNull(call);
		assertNotNull(call.getEmployee());
		assertEquals(EmployeeType.OPERATOR, call.getEmployee().getEmployeeType());
	}
	
	/*
	Escenario: ICC-0003 Asignar empleado supervisor a una llamada
	Dado que: no hay empleados operador disponibles y hay un empleado supervisor disponible
	Cuando: hay una llamada en la bolsa de atención
	Entonces: se asigna la llamada al empleado supervisor
	*/
	@Test
	public void testGetEmployeeToCallSupervisor() {
		employeeStorage = new EmployeeStorage();
		employeeStorageController = new EmployeeStorageControllerImpl(employeeStorage); 
		lobby = new LobbyImpl(employeeStorageController, callFactory);
		
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		this.lobby.getEmployeeToCall(new Call(System.nanoTime()));
		Call call = this.lobby.getEmployeeToCall(new Call(System.nanoTime()));
		assertNotNull(call);
		assertNotNull(call.getEmployee());
		assertEquals(EmployeeType.SURPERVISOR, call.getEmployee().getEmployeeType());
	}
	
	/*
	Escenario: ICC-0004 Asignar empleado director a una llamada
	Dado que: no hay empleados operador y supervisor disponibles y hay un empleado director disponible
	Cuando: hay una llamada en la bolsa de atención
	Entonces: se asigna la llamada al empleado director
	*/
	@Test
	public void testGetEmployeeToCallDirector() {
		employeeStorage = new EmployeeStorage();
		employeeStorageController = new EmployeeStorageControllerImpl(employeeStorage); 
		lobby = new LobbyImpl(employeeStorageController, callFactory);
		
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		this.lobby.getEmployeeToCall(new Call(System.nanoTime()));
		this.lobby.getEmployeeToCall(new Call(System.nanoTime()));
		Call call = this.lobby.getEmployeeToCall(new Call(System.nanoTime()));
		assertNotNull(call);
		assertNotNull(call.getEmployee());
		assertEquals(EmployeeType.DIRECTOR, call.getEmployee().getEmployeeType());
	}
	
	/*
	Escenario: ICC-0007 No hay empleados disponibles
	Dado que: no hay empleados disponibles
	Cuando: hay una llamada en la bolsa de atención
	Entonces: se agrega nuevamente a la bolsa de atención
	*/
	@Test
	public void testGetEmployeeToCallNoEmployeesAvalible() {
		employeeStorage = new EmployeeStorage();
		employeeStorageController = new EmployeeStorageControllerImpl(employeeStorage); 
		lobby = new LobbyImpl(employeeStorageController, callFactory);
		
		employeeStorageController.addEmployee(new Employee(EmployeeType.OPERATOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.SURPERVISOR));
		employeeStorageController.addEmployee(new Employee(EmployeeType.DIRECTOR));
		
		this.lobby.getEmployeeToCall(new Call(System.nanoTime()));
		this.lobby.getEmployeeToCall(new Call(System.nanoTime()));
		this.lobby.getEmployeeToCall(new Call(System.nanoTime()));
		Call call = this.lobby.getEmployeeToCall(new Call(System.nanoTime()));
		assertNull(call);
	}

}
