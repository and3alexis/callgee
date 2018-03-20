package com.intergalacticcallcenter.attentionbuckect;

import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.stereotype.Component;

import com.intergalacticcallcenter.dto.Call;

@Component
public class AttentionBucket extends PriorityBlockingQueue<Call> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1399192359691283316L;
	
	public AttentionBucket() {
		super(10);
	}

}
