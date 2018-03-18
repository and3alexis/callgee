package com.intergalacticallcenter.attentionbucket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.intergalacticallcenter.dto.Call;
import com.intergalacticallcenter.dto.Employee;

public class DispatcherImpl implements Dispatcher {

	private ExecutorService executor;

	private AttentionBucketCollection attentionBucketCollection;

	private BlockingQueue<Employee> employeeCollection;
	
	private Lock dispatcherlock = new ReentrantLock();

	public DispatcherImpl(ExecutorService executor, AttentionBucketCollection attentionBucketCollection,
			BlockingQueue<Employee> employeeCollection) {
		super();
		this.executor = executor;
		this.attentionBucketCollection = attentionBucketCollection;
		this.employeeCollection = employeeCollection;
		// TODO eliminar la siguiente linea:
		this.executor = Executors.newFixedThreadPool(10);
	}

	@Override
	public Call dispatchCall(Call call) {
		try {
			dispatcherlock.lock();
			Call call2 = copyCallAndSetPendingStatus(call);
			Call call3 = copyCallAndSetPendingStatus(call);
			Bucket bucket = new Bucket(call2);
			bucket.setEmployeeCollection(employeeCollection);
			Future<Call> callFuture = executor.submit(bucket);
			attentionBucketCollection.add(callFuture);
			return call3;
		} finally {
			dispatcherlock.unlock();
		}
		
	}
	
	private Call copyCallAndSetPendingStatus(Call call){
		Call call2 = new Call();
		call2.copy(call);
		call2.setStatus(Status.PENDING);
		return call2;
	}

}
