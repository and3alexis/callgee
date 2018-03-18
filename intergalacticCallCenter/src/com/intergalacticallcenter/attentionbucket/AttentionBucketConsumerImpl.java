package com.intergalacticallcenter.attentionbucket;

import java.util.concurrent.Future;

import com.intergalacticallcenter.dto.Call;

public class AttentionBucketConsumerImpl implements AttentionBucketConsumer{
	
	private AttentionBucketCollection attentionBucketCollection;
	
	public AttentionBucketConsumerImpl(AttentionBucketCollection attentionBucketCollection) {
		this.attentionBucketCollection = attentionBucketCollection;
	}

	@Override
	public void processAttentionBucketCollection(){
		while(true){
			Future<Call> callFuture = attentionBucketCollection.poll();
			if(callFuture != null){
				//do something for me
			}
		}
	}

}
