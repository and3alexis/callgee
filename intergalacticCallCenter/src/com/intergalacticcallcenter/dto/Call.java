package com.intergalacticcallcenter.dto;

import java.util.concurrent.TimeUnit;

import com.intergalacticcallcenter.dto.abc.Status;
import com.intergalacticcallcenter.dto.abc.Zone;

public class Call implements Comparable<Call>{
	
	public Call() {
	}

	public Call(long id) {
		this.id = id;
	}

	private long id;
	
	private Employee employee;
	
	private Status status;
	
	private long startTime;
	
	private long endTime;
	
	private Zone zone;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public void copy(Call that){
		setStatus(that.getStatus());
		setEmployee(that.getEmployee());
		setEndTime(that.getEndTime());
		setStartTime(that.getStartTime());
		setZone(that.getZone());
	}

	@Override
	public int compareTo(Call o) {
		if(this.getId() < o.getId())
			return 1;
		return -1;
	}

	public long getId() {
		return id;
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
		builder.append("Call [id=").append(id).append(", ");
		if (employee != null)
			builder.append("employee=").append(employee).append(", ");
		if (status != null)
			builder.append("status=").append(status).append(", ");
		builder.append("time=").append(TimeUnit.SECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS)).append(" secs ").append("]");
		if (zone != null)
			builder.append("zone=").append(zone);
		builder.append("]");
		return builder.toString();
	}
	
	

}
