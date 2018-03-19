package com.intergalacticcallcenter.attentionbuckect;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.CallResponse;
import com.intergalacticcallcenter.dto.abc.CallFactory;
import com.intergalacticcallcenter.dto.abc.Status;
import com.intergalacticcallcenter.dto.abc.Zone;

public class AttentionBucketControllerImpl implements AttentionBucketController{
	
	private AttentionBucket attentionBucket;
	
	private CallFactory callFactory;
	
	public AttentionBucketControllerImpl(AttentionBucket attentionBucket, CallFactory callFactory) {
		this.attentionBucket = attentionBucket;
		this.callFactory = callFactory;
	}

	@Override
	public CallResponse addCall(Call call) {
		int size = this.attentionBucket.size();
		if(size < 10){
			Call call2 = this.callFactory.getCallWithId(call);
			if(this.attentionBucket.add(call2)){
				CallResponse callResponse = new CallResponse();
				callResponse.setStatus(Status.PENDING);
				callResponse.setZone(Zone.SUNNY);
				return callResponse;
			}
		}
		
		CallResponse callResponse = new CallResponse();
		callResponse.setStatus(Status.FINNISHED);
		callResponse.setZone(Zone.CLOUDY);
		return callResponse;
	}

}
