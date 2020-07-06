package com.objectiva.model;

import org.apache.commons.lang3.StringUtils;

public enum EmployeeType {
	SALARY(1, "salary"), HOUR(2, "hour"), SALE(3, "sale");

	private int typeId;
	private String typeVal;

	private EmployeeType(int typeId, String typeVal) {
		this.typeId = typeId;
		this.typeVal = typeVal;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeVal() {
		return typeVal;
	}

	public void setTypeVal(String typeVal) {
		this.typeVal = typeVal;
	}

	public static EmployeeType getEmployeeType(String employeeVal) {
		for (EmployeeType pT : EmployeeType.values()) {
			if (StringUtils.equals(pT.getTypeVal(), employeeVal)) {
				return pT;
			}
		}

		return null;
	}

}
