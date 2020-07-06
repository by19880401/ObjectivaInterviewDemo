package com.objectiva.InterviewDemo.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.objectiva.handler.impl.SaleCalculateHandler;
import com.objectiva.model.Employee;

public class SaleCalculateHandlerTest {

	@Test
	public void NoAmount_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-04-01");
		emp.setMonth("5");
		emp.setType("sale");
		emp.setAmount(null);
		emp.setWorkingHours(null);
		SaleCalculateHandler handler = new SaleCalculateHandler();
		assertEquals(3000, handler.calculate(emp), 0);
	}
	
	@Test
	public void Birthday_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-05-01");
		emp.setMonth("5");
		emp.setType("sale");
		emp.setAmount("16000");
		emp.setWorkingHours(null);
		SaleCalculateHandler handler = new SaleCalculateHandler();
		assertEquals(3100, handler.calculate(emp), 0);
	}
	
	@Test
	public void NotBirthday_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-04-01");
		emp.setMonth("5");
		emp.setType("sale");
		emp.setAmount("16000");
		emp.setWorkingHours(null);
		SaleCalculateHandler handler = new SaleCalculateHandler();
		assertEquals(3000, handler.calculate(emp), 0);
	}
	
	@Test
	public void AmountMoreThan2000_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-04-01");
		emp.setMonth("5");
		emp.setType("sale");
		emp.setAmount("26000");
		emp.setWorkingHours(null);
		SaleCalculateHandler handler = new SaleCalculateHandler();
		assertEquals(3000 + (26000-20000)*0.05, handler.calculate(emp), 0);
	}
	
	@Test
	public void AmountMoreThan3000_Test() {
		Employee emp = new Employee();
		emp.setName("Willis Bai");
		emp.setBirthday("1988-04-01");
		emp.setMonth("5");
		emp.setType("sale");
		emp.setAmount("31000");
		emp.setWorkingHours(null);
		SaleCalculateHandler handler = new SaleCalculateHandler();
		assertEquals(3000 + (31000-20000)*0.08, handler.calculate(emp), 0);
	}

}
