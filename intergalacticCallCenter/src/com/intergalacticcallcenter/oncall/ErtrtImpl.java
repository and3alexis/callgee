package com.intergalacticcallcenter.oncall;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.abc.CallFactory;

public class ErtrtImpl extends Thread implements Ertrt {
	
	private ExecutorServiceWrap executorServiceWrap;
	
	private CallFactory callFactory;

	@Override
	public void run() {
		while(true){
			getCallFinnished();
		}
	}
	
	@Override
	public Call getCallFinnished(){
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
			System.out.println(call);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return call;
	}

}
