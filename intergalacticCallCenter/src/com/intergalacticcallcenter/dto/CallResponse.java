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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CallResponse [");
		if (status != null)
			builder.append("status=").append(status).append(", ");
		if (zone != null)
			builder.append("zone=").append(zone);
		builder.append("]");
		return builder.toString();
	}
	
	

}
