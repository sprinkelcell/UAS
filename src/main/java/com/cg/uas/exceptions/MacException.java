package com.cg.uas.exceptions;

public class MacException extends Exception {
	String str = null;

	public MacException(String string) {
		// TODO Auto-generated constructor stub
		
		this.str = string;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return str;
	}
}
