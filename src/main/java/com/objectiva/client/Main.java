package com.objectiva.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.objectiva.common.XMLUtils;
import com.objectiva.exception.InvalidParameterException;
import com.objectiva.handler.AbstractCalculateHandler;
import com.objectiva.handler.SimpleCalculateFactory;
import com.objectiva.model.Employee;

public class Main {
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws InvalidParameterException {
		Map<String, List<Employee>> map = XMLUtils.parseXML();
		if (MapUtils.isEmpty(map)) {
			LOGGER.error("No employee's profile found.");
			return;
		}
		for (Map.Entry<String, List<Employee>> entry : map.entrySet()) {
			String month = entry.getKey();
			List<Employee> empList = entry.getValue();
			if (CollectionUtils.isEmpty(empList)) {
				LOGGER.error("No employee's profile found for the month [{}].", month);
				continue;
			}
			
			// calculate all the expense
			BigDecimal sum = BigDecimal.valueOf(0);
			for (Employee emp : empList) {
				String empType = StringUtils.trimToEmpty(emp.getType());
				AbstractCalculateHandler handler = SimpleCalculateFactory.getHandler(empType);
				double eachEmpExpense = handler.calculate(emp);
				// sum all kinds of the employee's salary
				LOGGER.info("The employee [{}, {}] get the salary [{}] on {}.", emp.getName(), emp.getType(), eachEmpExpense, month);
				sum = sum.add(BigDecimal.valueOf(eachEmpExpense));
			}
			LOGGER.info("The total salary is {} on the month [{}].", sum.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), month);
			
			
		}
	}
	
}
