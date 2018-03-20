package com.intergalacticcallcenter.dispatcher;

import com.intergalacticcallcenter.attentionbuckect.AttentionBucket;
import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.employee.Lobby;

public class DispatcherConsumerImpl extends Thread implements DispatcherConsumer{
	
	private Lobby lobby;
	
	private AttentionBucket attentionBucket;
	
	private Dispatcher dispatcher;

	public DispatcherConsumerImpl(Lobby lobby, AttentionBucket attentionBucket,
			Dispatcher dispatcher) {
		this.lobby = lobby;
		this.attentionBucket = attentionBucket;
		this.dispatcher = dispatcher;
	}

	@Override
	public void run() {
		while(true){
			getCallToDispatch();
		}
	}
	
	@Override
	public void getCallToDispatch(){
		Call call = this.attentionBucket.poll();
		if(call != null){
			Call call2 = this.lobby.getEmployeeToCall(call);
			if(call2 == null){
				this.attentionBucket.add(call);
			}else{
				System.out.println(call2);
				this.dispatcher.dispatchCall(call2);
			}
		}
	}

}
