package com.intergalacticcallcenter.dto;

import com.intergalacticcallcenter.dto.abc.Status;
import com.intergalacticcallcenter.dto.abc.Zone;

public class CallResponse {
	
	private Status status;
	
	private Zone zone;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

}
