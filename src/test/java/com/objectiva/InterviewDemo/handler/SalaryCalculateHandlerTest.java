package com.objectiva.InterviewDemo.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.objectiva.handler.impl.SalaryCalculateHandler;
import com.objectiva.model.Employee;

public class SalaryCalculateHandlerTest {

	@Test
	public void NotBirthday_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-04-01");
		emp.setMonth("5");
		emp.setType("salary");
		emp.setAmount(null);
		emp.setWorkingHours(null);
		SalaryCalculateHandler handler = new SalaryCalculateHandler();
		assertEquals(6000.00, handler.calculate(emp), 0);
	}

	@Test
	public void Birthday_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-05-01");
		emp.setMonth("5");
		emp.setType("salary");
		emp.setAmount(null);
		emp.setWorkingHours(null);
		SalaryCalculateHandler handler = new SalaryCalculateHandler();
		assertEquals(6100.00, handler.calculate(emp), 0);
	}

}
