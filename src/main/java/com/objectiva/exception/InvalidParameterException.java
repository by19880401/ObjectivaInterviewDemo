package com.objectiva.exception;

public class InvalidParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4417387955970061467L;
	private String errorCode;
	private String tid;

	public InvalidParameterException() {
	}

	public InvalidParameterException(String message) {
		super(message);
	}

	public InvalidParameterException(String tid, String code, String message) {
		super(message);
		this.errorCode = code;
		this.tid = tid;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getTid() {
		return tid;
	}
}
