package com.cg.uas.exceptions;

public class StoreException extends Exception {
	String str="File Corrupts";
	public StoreException(String str) {
		// TODO Auto-generated constructor stub
		this.str=str;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "File Corrupts";
	}
}
