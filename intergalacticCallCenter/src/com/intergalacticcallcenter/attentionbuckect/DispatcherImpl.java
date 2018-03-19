package com.intergalacticcallcenter.attentionbuckect;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.abc.CallFactory;
import com.intergalacticcallcenter.employee.EmployeeStorageController;
import com.intergalacticcallcenter.oncall.CallMomentum;
import com.intergalacticcallcenter.oncall.ExecutorServiceWrap;

public class DispatcherImpl implements Dispatcher {
	
	private Lock dispatcherlock = new ReentrantLock();
	
	private ExecutorServiceWrap executorServiceWrap;

	private EmployeeStorageController employeeStorageController;
	
	private CallFactory callFactory;
	
	@Autowired
	public DispatcherImpl(ExecutorServiceWrap executorServiceWrap, EmployeeStorageController employeeStorageController,
			CallFactory callFactory) {
		this.executorServiceWrap = executorServiceWrap;
		this.employeeStorageController = employeeStorageController;
		this.callFactory = callFactory;
	}

	
	@Override
	public Call dispatchCall(Call call) {
		try {
			dispatcherlock.lock();
			int callDurability = ThreadLocalRandom.current().nextInt(0,20);
			Call call2 = callFactory.getCallStarted(call);
			CallMomentum callMomentum = getCallMomentum(call2, callDurability);
			executorServiceWrap.submit(callMomentum);
			return call2;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dispatcherlock.unlock();
		}
		return null;
		
	}
	
	private CallMomentum getCallMomentum(Call call2, int callDurability){
		CallMomentum callMomentum = new CallMomentum(call2, callDurability);
		callMomentum.setEmployeeStorageController(employeeStorageController);
		callMomentum.setCallFactory(callFactory);
		return callMomentum;
	}
	
}
