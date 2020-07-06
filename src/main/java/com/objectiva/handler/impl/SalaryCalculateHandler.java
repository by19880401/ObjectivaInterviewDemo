package com.objectiva.handler.impl;

import java.math.BigDecimal;

import com.objectiva.common.FileUtils;
import com.objectiva.handler.AbstractCalculateHandler;
import com.objectiva.model.Employee;

public class SalaryCalculateHandler extends AbstractCalculateHandler {
	

	@Override
	public double calculate(Employee employee) {
		BigDecimal result = null;
		BigDecimal fixedSalary = new BigDecimal(FileUtils.getFixedSalary());
		BigDecimal birthdayBonus = new BigDecimal(BIRTHDAY_BONUS);
		String birthday = employee.getBirthday();
		if (isBirthday(birthday)) {
			result = fixedSalary.add(birthdayBonus);
		} else {
			result = fixedSalary;
		}
		return result.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
