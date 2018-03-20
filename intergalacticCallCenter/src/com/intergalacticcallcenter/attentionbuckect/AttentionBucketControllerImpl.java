package com.intergalacticcallcenter.attentionbuckect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.CallResponse;
import com.intergalacticcallcenter.dto.abc.CallFactory;
import com.intergalacticcallcenter.dto.abc.Status;
import com.intergalacticcallcenter.dto.abc.Zone;

@Controller
public class AttentionBucketControllerImpl implements AttentionBucketController{
	
	private AttentionBucket attentionBucket;
	
	private CallFactory callFactory;
	
	@Autowired
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
