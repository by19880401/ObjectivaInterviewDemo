package com.objectiva.handler;

import com.objectiva.common.DateUtils;

public abstract class AbstractCalculateHandler implements CalculateHandler {

	protected static final String BIRTHDAY_BONUS = "100";

	protected boolean isBirthday(String birthday) {
		long birthdayMonth = DateUtils.getBirthdayMonth(birthday);
		long curMonth = DateUtils.getCurrentMonth();
		return (birthdayMonth == curMonth);
	}

}
