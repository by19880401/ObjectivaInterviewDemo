package com.objectiva.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.objectiva.exception.InvalidParameterException;
import com.objectiva.handler.impl.HourCalculateHandler;
import com.objectiva.handler.impl.SalaryCalculateHandler;
import com.objectiva.handler.impl.SaleCalculateHandler;
import com.objectiva.model.EmployeeType;

public class SimpleCalculateFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCalculateFactory.class);

	public static AbstractCalculateHandler getHandler(String empType) throws InvalidParameterException {
		AbstractCalculateHandler calculateHandler = null;
		EmployeeType et = EmployeeType.getEmployeeType(empType);
		if (null == et) {
			LOGGER.error("Employee type is null.");
			throw new InvalidParameterException("Employee type is null");
		}
		switch (et) {
		case SALARY:
			calculateHandler = new SalaryCalculateHandler();
			break;
		case HOUR:
			calculateHandler = new HourCalculateHandler();
			break;
		case SALE:
			calculateHandler = new SaleCalculateHandler();
			break;
		default:
			LOGGER.error("Invalid employee type {}", empType);
			throw new InvalidParameterException("Invalid employee type");
		}
		return calculateHandler;
	}

}
