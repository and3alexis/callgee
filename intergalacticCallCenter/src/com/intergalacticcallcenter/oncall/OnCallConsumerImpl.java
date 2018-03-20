package com.intergalacticcallcenter.oncall;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.abc.CallFactory;
import com.intergalacticcallcenter.dto.abc.Zone;

public class OnCallConsumerImpl extends Thread implements OnCallConsumer {
	
	private ExecutorServiceWrap executorServiceWrap;
	
	private CallFactory callFactory;
	
	public OnCallConsumerImpl(ExecutorServiceWrap executorServiceWrap, CallFactory callFactory) {
		this.executorServiceWrap = executorServiceWrap;
		this.callFactory = callFactory;
	}

	@Override
	public void run() {
		while(true){
			getCall();
		}
	}
	
	@Override
	public Call getCall(){
		while(true){
			Call call = getCallFinnished();
			if(call != null)
				return call;
		}
	}
	
	private Call getCallFinnished(){
		
		Call call = null;
		Future<Call> future = executorServiceWrap.getTaskSubmited();
		if(future != null){
			if(!future.isDone()){
				setCallIsNotDone(future);
			}else{
				call = getCallDone(future);
			}
		}
		return call;
	}
	
	private void setCallIsNotDone(Future<Call> future){
		try {
			this.executorServiceWrap.putTaskSubmited(future);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private Call getCallDone(Future<Call> future){
		Call call = null;
		try {
			call = future.get();
			call = callFactory.getCallFinnished(call);
			long time = TimeUnit.SECONDS.convert((call.getEndTime() - call.getStartTime()), TimeUnit.NANOSECONDS);
			if(time < 5){
				call.setZone(Zone.CLOUDY);
			}else if(time > 10){
				call.setZone(Zone.RAINNING);
			}else{
				call.setZone(Zone.SUNNY);
			}
			System.out.println(call);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return call;
	}

}
