package com.intergalacticcallcenter.employee;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.intergalacticcallcenter.dto.abc.EmployeeType;

@Component
public class EmployeeStorage extends ConcurrentHashMap<EmployeeType, EmployeeCollection> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2884471986186191430L;

}
