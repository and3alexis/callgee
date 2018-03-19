package com.intergalacticcallcenter.attentionbuckect;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.CallResponse;
import com.intergalacticcallcenter.dto.abc.CallFactory;
import com.intergalacticcallcenter.dto.abc.CallFactoryImpl;
import com.intergalacticcallcenter.dto.abc.Status;
import com.intergalacticcallcenter.dto.abc.Zone;

public class AttentionBucketControllerTest {
	
	private AttentionBucketController attentionBucketController;

	@Before
	public void setUp() throws Exception {
		CallFactory callFactory = new CallFactoryImpl();
		AttentionBucket attentionBucket = new AttentionBucket();
		attentionBucketController = new AttentionBucketControllerImpl(attentionBucket, callFactory);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddCallSunny() {
		CallResponse callResponse = attentionBucketController.addCall(new Call());
		assertNotNull(callResponse);
		assertEquals(Status.PENDING, callResponse.getStatus());
		assertEquals(Zone.SUNNY, callResponse.getZone());
	}
	
	@Test
	public void testAddCallCloudy() {
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

}
