package com.intergalacticcallcenter.attentionbuckect;

import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.stereotype.Component;

import com.intergalacticcallcenter.dto.Call;

/*
Escenario: ICC-0008 Orden de llamadas en bolsa de atención
Dado que: que hay llamadas en estado pendientes
Cuando: entre una llamada
Entonces: el orden de atención se organiza descendentemente según el tiempo capturado de inicio en estado pendiente
*/
@Component
public class AttentionBucket extends PriorityBlockingQueue<Call> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1399192359691283316L;
	
	public AttentionBucket() {
		super(10);
	}

}
