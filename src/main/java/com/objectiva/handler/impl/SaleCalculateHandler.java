package com.objectiva.handler.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.objectiva.common.FileUtils;
import com.objectiva.handler.AbstractCalculateHandler;
import com.objectiva.model.Employee;

public class SaleCalculateHandler extends AbstractCalculateHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(SaleCalculateHandler.class);
	private static final String BASIC_SALE_AMOUNT = "20000";
	private static final String EXTRA_SALE_AMOUNT = "30000";

	@Override
	public double calculate(Employee employee) {
		BigDecimal result = new BigDecimal(0);
		BigDecimal birthdayBonus = new BigDecimal(BIRTHDAY_BONUS);
		String empName = employee.getName();
		String birthday = employee.getBirthday();
		if (isBirthday(birthday)) {
			result = result.add(birthdayBonus);
		}
		String amount = employee.getAmount();
		String saleBasicSalary = FileUtils.getSaleBasicSalary();
		// FIXME not sure this case: if the amount is 0, the employee can only get the basic salary
		if (StringUtils.isBlank(amount)) {
			LOGGER.info("No amount found for {}.", empName);
			result = result.add(new BigDecimal(saleBasicSalary));
			return result.doubleValue();
		}

		BigDecimal basicAmount = new BigDecimal(amount).subtract(new BigDecimal(BASIC_SALE_AMOUNT));
		// amount <=20000
		if (basicAmount.doubleValue() <= 0) {
			result = result.add(new BigDecimal(saleBasicSalary));
			return result.doubleValue();
		}

		BigDecimal extraAmount = new BigDecimal(amount).subtract(new BigDecimal(EXTRA_SALE_AMOUNT));
		// 2000<amount<=30000, salary = basic salary(3000) + (amount-20000)*5%
		if (basicAmount.doubleValue() > 0 && extraAmount.doubleValue() <= 0) {
			String leve1Rate = FileUtils.getLevelOneCommissionRate();
			BigDecimal level1Commission = basicAmount.multiply(new BigDecimal(leve1Rate));
			result = new BigDecimal(saleBasicSalary).add(level1Commission).add(result);
			return result.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}

		// 30000<amount, salary = basic salary(3000) + (amount-20000)*8%
		if (extraAmount.doubleValue() > 0) {
			String level2Rate = FileUtils.getLevelTwoCommissionRate();
			BigDecimal level2Commission = basicAmount.multiply(new BigDecimal(level2Rate));
			result = new BigDecimal(saleBasicSalary).add(level2Commission).add(result);
			return result.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return result.doubleValue();
	}

}
