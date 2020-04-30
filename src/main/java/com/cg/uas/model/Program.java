package com.cg.uas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Program implements Serializable {
	private String programName;
	private LocalDate startDate;
	private LocalDate endDate;
	private int programId;
	private Set<Applicant> applicants;

	public Program() {
		super();
		applicants = new HashSet<Applicant>();
	}

	public Program(String programName, LocalDate startDate, LocalDate endDate) {
		super();
		this.programName = programName;
		this.startDate = startDate;
		this.endDate = endDate;
		applicants = new HashSet<Applicant>();
	}

	public Set<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(Set<Applicant> applicants) {
		this.applicants = applicants;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	@Override
	public String toString() {
		return " ProgramName = " + programName + "\n startDate = " + startDate + "\n endDate = " + endDate
				+ "\n ProgramId = " + programId + "\n-----------\n";
	};
}
