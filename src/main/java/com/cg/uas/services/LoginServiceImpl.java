package com.cg.uas.services;

public class LoginServiceImpl implements LoginService {

	@Override
	public boolean validateCandidate(String username, String password, String role) {

		if ( "admin".equals(role)) {
			if ("admin".equals(username) && "root".equals(password))
				return true;
			else
				return false;
		} else if ("mac".equals(role)) {
			if ("mac".equals(username) && "root".equals(password))
				return true;
			else
				return false;
		}
		else 
			return false;
	}

}
