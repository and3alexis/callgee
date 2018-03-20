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

/*
 * Para mas info ver la url:
 * https://s3-sa-east-1.amazonaws.com/intergalacticcallcenter/index.html
 */
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
	
	/*
	Escenario: ICC-0010 Llamada finalizada de menos de 5 segundos
	Dado que: una llamada está siendo atendida por un empleado
	Cuando: la llamada finaliza en menos de 5 segundos
	Entonces: cambia a estado finalizada
	Y se establece la zona cloudy
	
	La zona Cloudy es la categoría a la llamada que termina en menos de 5 segundos puede deberse
	a que tubo intermitencias ó mala comunicación por lo que intergalactic call puede tomar la llamada
	y volver a llamar al cliente
	
	*/
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
	
	/*
	Escenario: ICC-0012 Llamada finaliza entre una duración de 5 a 10 segundos
	Dado que: una llamada está siendo atendida por un empleado
	Cuando: la llamada finaliza en una duración entre 5 y 10 segundos
	Entonces: cambia a estado finalizada
	Y se establece la zona sunny
	
	La zona Sunny es la categoría a la llamada que es normal en el lapso de tiempo
	*/
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
	
	/*
	Escenario: ICC-0011 Llamada finalizada de más de 10 segundos
	Dado que: una llamada está siendo atendida por un empleado
	Cuando: la llamada supere los 10 segundos
	Entonces:   cambia a estado finalizada
	Y se establece la zona rainning
	
	La zona Rainning es la categoría a la llamada que se extiende en su duración
	por lo pronto intergalactic call center puede tomar la llamada y realizar procesos de auditoría
	o revisión del guion.
	*/
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
		assertEquals(Zone.RAINNING, call2.getZone());
	}

}
