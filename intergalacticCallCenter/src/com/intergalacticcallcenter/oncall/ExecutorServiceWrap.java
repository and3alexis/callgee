package com.intergalacticcallcenter.oncall;

import java.util.concurrent.Future;

import com.intergalacticcallcenter.dto.Call;

public interface ExecutorServiceWrap {

	Future<Call> getTaskSubmited();

	Future<Call> submit(CallMomentum task) throws InterruptedException;

	void putTaskSubmited(Future<Call> future) throws InterruptedException;

}
