package com.cg.uas.services;

import java.util.List;

import com.cg.uas.exceptions.AdminException;
import com.cg.uas.exceptions.MacException;
import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Applicant;
import com.cg.uas.model.ApplicationStatus;

public interface ApplicantService {

	Applicant addStudent(Applicant std) throws StoreException, AdminException;

	Applicant search(int applicationId);

	void delete(int applicationId) throws StoreException, AdminException, MacException;

	void addStatus(int applicationId, ApplicationStatus status) throws StoreException;

	public List<Applicant> getStudent(ApplicationStatus status);

	List<Applicant> getProgramList(int programId);

	boolean validateName(String name);

	boolean validateMobile(String mobile);

	boolean validateEmail(String email);

	boolean validateAge(int age);

}
