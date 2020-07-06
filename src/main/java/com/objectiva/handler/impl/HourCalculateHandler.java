package com.objectiva.handler.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.objectiva.common.FileUtils;
import com.objectiva.handler.AbstractCalculateHandler;
import com.objectiva.model.Employee;

public class HourCalculateHandler extends AbstractCalculateHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(HourCalculateHandler.class);
	private static final String BASIC_WORKING_HOURS = "160";
	private static final String SALARY_LEVEL = "1.3";

	@Override
	public double calculate(Employee employee) {
		BigDecimal result = null;
		String empName = employee.getName();
		String empWorkingHours = employee.getWorkingHours();
		if (StringUtils.isBlank(empWorkingHours)) {
			LOGGER.warn("No working hours found for {}.", empName);
			return 0;
		}

		BigDecimal workingHours = new BigDecimal(empWorkingHours);
		BigDecimal extraHours = workingHours.subtract(new BigDecimal(BASIC_WORKING_HOURS));
		String oneHourSal = FileUtils.getOneHourSalary();
		if (extraHours.doubleValue() > 0) {
			// extralSal = extraHours*1.3*35
			BigDecimal extraSal = extraHours.multiply(new BigDecimal(SALARY_LEVEL)).multiply(new BigDecimal(oneHourSal));
			// basicSal = 160*35
			BigDecimal basicSal = new BigDecimal(BASIC_WORKING_HOURS).multiply(new BigDecimal(oneHourSal));
			result = basicSal.add(extraSal);
		} else {
			// pay attention to one case: the working hours <=160
			result = workingHours.multiply(new BigDecimal(oneHourSal));
		}
		
		BigDecimal birthdayBonus = new BigDecimal(BIRTHDAY_BONUS);
		String birthday = employee.getBirthday();
		if (isBirthday(birthday)) {
			result = result.add(birthdayBonus);
		}

		return result.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
