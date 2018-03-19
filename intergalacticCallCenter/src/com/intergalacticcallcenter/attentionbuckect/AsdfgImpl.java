package com.intergalacticcallcenter.attentionbuckect;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.employee.AllocationAreaEmployee;

public class AsdfgImpl extends Thread implements Asdfg{
	
	private AllocationAreaEmployee allocationAreaEmployee;
	
	private AttentionBucket attentionBucket;
	
	private Dispatcher dispatcher;

	public AsdfgImpl(AllocationAreaEmployee allocationAreaEmployee, AttentionBucket attentionBucket,
			Dispatcher dispatcher) {
		this.allocationAreaEmployee = allocationAreaEmployee;
		this.attentionBucket = attentionBucket;
		this.dispatcher = dispatcher;
	}

	@Override
	public void run() {
		while(true){
			Call call = this.attentionBucket.poll();
			if(call != null){
				Call call2 = this.allocationAreaEmployee.getEmployeeToCall(call);
				if(call2 == null){
					this.attentionBucket.add(call);
				}else{
					this.dispatcher.dispatchCall(call2);
				}
			}
		}
	}

}
