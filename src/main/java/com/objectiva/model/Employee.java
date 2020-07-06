package com.objectiva.model;

public class Employee {
	private String name;
	private String type;
	private String birthday;
	private String workingHours;
	private String amount;
	private String month;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", type=" + type + ", birthday=" + birthday + ", workingHours=" + workingHours
				+ ", amount=" + amount + ", month=" + month + "]";
	}

}
