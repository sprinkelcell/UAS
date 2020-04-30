package com.cg.uas.exceptions;

public class ApplicantException extends Exception {
	private String str;

	public ApplicantException(String string) {
		// TODO Auto-generated constructor stub
		
		this.str = string;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.str;
	}
}
