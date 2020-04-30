package com.cg.uas.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.cg.uas.exceptions.AdminException;
import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Applicant;
import com.cg.uas.model.Program;

public interface ProgramServices {
	Program addProgram(Program pro) throws StoreException;

	Program updateProgram(int programId, LocalDate startDate, LocalDate endDate) throws StoreException, AdminException;

	void deleteProgram(int programId) throws StoreException, AdminException;

	List<Program> getProgram();

	Program searchProgram(int key);

	public Set<Applicant> getApplicantList(int programId);

	boolean dateValidation(LocalDate startDate, LocalDate endDate);
}
