package com.intergalacticcallcenter.dto.abc;

import org.springframework.stereotype.Component;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.Employee;

@Component
public class CallFactoryImpl implements CallFactory{
	
	@Override
	public Call getCallWithId(Call call){
		Call call2 = new Call(System.nanoTime());
		call2.copy(call);
		call2.setStatus(Status.PENDING);
		return call2;
	}
	
	@Override
	public Call getCallWithEmployee(Call call, Employee employee){
		Call call2 = new Call(call.getId());
		call2.copy(call);
		call2.setEmployee(employee);
		return call2;
	}
	
	@Override
	public Call getCallFinnished(Call call){
		Call call2 = new Call(call.getId());
		call2.copy(call);
		call2.setStatus(Status.FINNISHED);
		call2.setEndTime(System.nanoTime());
		return call2;
	}
	
	@Override
	public Call getCallStarted(Call call){
		Call call2 = new Call(call.getId());
		call2.copy(call);
		call2.setStatus(Status.STARTED);
		call2.setStartTime(System.nanoTime());
		return call2;
	}

}
