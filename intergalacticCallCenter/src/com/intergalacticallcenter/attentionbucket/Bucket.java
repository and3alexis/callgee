package com.intergalacticallcenter.attentionbucket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

import com.intergalacticallcenter.dto.Call;
import com.intergalacticallcenter.dto.Employee;

public class Bucket implements Callable<Call>{
	
	private Call call;
	
	private BlockingQueue<Employee> employeeCollection;
	
	public Bucket(Call call) {
		this.call = call;
	}

	@Override
	public Call call() throws Exception {
		Employee employee = getEmployee();
		this.call.setEmployee(employee);
		this.call.setStartTime(System.nanoTime());
		this.call.setStatus(Status.STARTED);
		onCall();
		return this.call;
	}

	private Employee getEmployee(){
		Employee employee = null;
		boolean checkEmployee = true;
		while (checkEmployee) {
			if((employee = employeeCollection.poll()) != null){
				checkEmployee = false;
			}
		}
		return employee;
	}
	
	private void onCall(){
		boolean onCall = true;
		int callDurability = ThreadLocalRandom.current().nextInt(0,20);
		long endTime = 0;
		while (onCall) {
			if((endTime = System.nanoTime()) - this.call.getStartTime() > (callDurability * 1000000000L)){
				employeeCollection.add(this.call.getEmployee());
				this.call.setStatus(Status.FINNISHED);
				this.call.setEndTime(endTime);
				onCall = false;
			}
		}
	}

	public void setEmployeeCollection(BlockingQueue<Employee> employeeCollection) {
		this.employeeCollection = employeeCollection;
	}

	
}
