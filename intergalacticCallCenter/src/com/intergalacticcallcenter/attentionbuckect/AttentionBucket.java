package com.intergalacticcallcenter.attentionbuckect;

import java.util.concurrent.PriorityBlockingQueue;

import com.intergalacticcallcenter.dto.Call;

public class AttentionBucket extends PriorityBlockingQueue<Call> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1399192359691283316L;
	
	public AttentionBucket() {
		super(10);
	}

}
