package com.cg.uas.services;

public interface LoginService {
	boolean validateCandidate(String username, String password, String role);
}
