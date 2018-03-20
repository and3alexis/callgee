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
import com.intergalacticcallcenter.dto.CallResponse;
import com.intergalacticcallcenter.dto.abc.CallFactory;
import com.intergalacticcallcenter.dto.abc.Status;
import com.intergalacticcallcenter.dto.abc.Zone;

/*
 * Para mas info ver la url:
 * https://s3-sa-east-1.amazonaws.com/intergalacticcallcenter/index.html
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:springContext.xml" })
public class AttentionBucketControllerTest {

	private AttentionBucketController attentionBucketController;

	private AttentionBucket attentionBucket;

	@Autowired
	private CallFactory callFactory;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	Escenario: ICC-0009 Onceava llamada
	Dado que: hay 10 llamadas en la bolsa de atencion
	Cuando: entra una llamada
	Entonces: se establece la zona cloudy
	*/
	@Test
	public void testAddCallCloudy() {
		attentionBucket = new AttentionBucket();
		attentionBucketController = new AttentionBucketControllerImpl(attentionBucket, callFactory);

		attentionBucketController.addCall(new Call());
		attentionBucketController.addCall(new Call());
		attentionBucketController.addCall(new Call());
		attentionBucketController.addCall(new Call());
		attentionBucketController.addCall(new Call());
		attentionBucketController.addCall(new Call());
		attentionBucketController.addCall(new Call());
		attentionBucketController.addCall(new Call());
		attentionBucketController.addCall(new Call());
		attentionBucketController.addCall(new Call());
		CallResponse callResponse11 = attentionBucketController.addCall(new Call());

		assertNotNull(callResponse11);
		assertEquals(Status.FINNISHED, callResponse11.getStatus());
		assertEquals(Zone.CLOUDY, callResponse11.getZone());
	}

	/*
	 * Escenario: ICC-0001 Llamada entrante 
	 * Dado que: un cliente llama al call center 
	 * Cuando: entra la llamada 
	 * Entonces: se agrega la llamada a la bolsa de atención con estado pendiente Y se captura el tiempo de inicio de
	 * estado pendiente
	 */
	@Test
	public void testAddCallSunny() {
		attentionBucket = new AttentionBucket();
		attentionBucketController = new AttentionBucketControllerImpl(attentionBucket, callFactory);

		CallResponse callResponse = attentionBucketController.addCall(new Call());
		assertNotNull(callResponse);
		assertEquals(Status.PENDING, callResponse.getStatus());
		assertEquals(Zone.SUNNY, callResponse.getZone());
	}

}
