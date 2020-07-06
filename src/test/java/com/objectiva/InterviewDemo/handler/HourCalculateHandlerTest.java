package com.objectiva.InterviewDemo.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.objectiva.handler.impl.HourCalculateHandler;
import com.objectiva.model.Employee;

public class HourCalculateHandlerTest {

	@Test
	public void NoWorkingHours_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-04-01");
		emp.setMonth("5");
		emp.setType("hour");
		emp.setAmount(null);
		emp.setWorkingHours(null);
		HourCalculateHandler handler = new HourCalculateHandler();
		assertEquals(0, handler.calculate(emp), 0);
	}

	@Test
	public void NotBirthday_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-04-01");
		emp.setMonth("5");
		emp.setType("hour");
		emp.setAmount(null);
		emp.setWorkingHours("160");
		HourCalculateHandler handler = new HourCalculateHandler();
		assertEquals(160 * 35, handler.calculate(emp), 0);
	}

	@Test
	public void Birthday_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-05-01");
		emp.setMonth("5");
		emp.setType("hour");
		emp.setAmount(null);
		emp.setWorkingHours("160");
		HourCalculateHandler handler = new HourCalculateHandler();
		assertEquals(160 * 35 + 100, handler.calculate(emp), 0);
	}

	@Test
	public void WorkingHoursLessThan160_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-04-01");
		emp.setMonth("5");
		emp.setType("hour");
		emp.setAmount(null);
		emp.setWorkingHours("150");
		HourCalculateHandler handler = new HourCalculateHandler();
		assertEquals(150 * 35, handler.calculate(emp), 0);
	}

	@Test
	public void ExtraWorkingHours_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-04-01");
		emp.setMonth("5");
		emp.setType("hour");
		emp.setAmount(null);
		emp.setWorkingHours("170");
		HourCalculateHandler handler = new HourCalculateHandler();
		assertEquals(160 * 35 + (170 - 160) * 35 * 1.3, handler.calculate(emp), 0);
	}

}
