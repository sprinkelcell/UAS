package com.cg.uas.exceptions;

public class AdminException extends Exception {
	private String str;

	public AdminException(String str) {
		// TODO Auto-generated constructor stub

		this.str = str;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return str;
	}

}
