package com.intergalacticcallcenter.oncall;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.abc.CallFactory;
import com.intergalacticcallcenter.employee.EmployeeStorageController;

public class CallMomentum implements Callable<Call>{
	
	private int callDuraability;
	
	private EmployeeStorageController employeeStorageController;
	
	private CallFactory callFactory;
	
	private Call call;
	
	public CallMomentum(Call call, int callDurability) {
		this.call = call;
		this.callDuraability = callDurability;
	}

	@Override
	public Call call() throws Exception {
		Call call = onCall();
		return call;
	}

	private Call onCall(){
		boolean onCall = true;
		Call call = callFactory.getCallStarted(this.call);
		int callDurability = ThreadLocalRandom.current().nextInt(0,20);
		while (onCall) {
			if(System.nanoTime() - call.getStartTime() > (this.callDuraability * 1000000000L)){
				this.employeeStorageController.addEmployee(call.getEmployee());
				call = callFactory.getCallFinnished(call);
				onCall = false;
			}
		}
		return call;
	}

	public void setEmployeeStorageController(EmployeeStorageController employeeStorageController) {
		this.employeeStorageController = employeeStorageController;
	}

	public void setCallFactory(CallFactory callFactory) {
		this.callFactory = callFactory;
	}
}
