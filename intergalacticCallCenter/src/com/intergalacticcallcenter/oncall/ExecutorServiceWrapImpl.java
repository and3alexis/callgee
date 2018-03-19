package com.intergalacticcallcenter.oncall;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import com.intergalacticcallcenter.dto.Call;

@Component
public class ExecutorServiceWrapImpl implements ExecutorServiceWrap{
	
	private ExecutorService executorService;
	
	private BlockingQueue<Future<Call>> blockingQueue;
	
	public ExecutorServiceWrapImpl() {
		this.executorService = Executors.newFixedThreadPool(10);
		this.blockingQueue = new LinkedBlockingQueue<>();
	}

	@Override
	public Future<Call> submit(CallMomentum task) throws InterruptedException{
		Future<Call> future = executorService.submit(task);
		putTaskSubmited(future);
		return future;
	}
	
	@Override
	public Future<Call> getTaskSubmited(){
		Future<Call> future = blockingQueue.poll();
		return future;
	}
	
	@Override
	public void putTaskSubmited(Future<Call> future) throws InterruptedException{
		blockingQueue.put(future);
	}
	
	

}
