package com.cg.uas.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Applicant implements Serializable{
	 
	private String fullName;
	private int applicationId;
	private LocalDate dob;
	private String highestQualification;
	private int marksObtained;
	private String goals;
	private String emailId;
	private Program programs;
	private ApplicationStatus status;
	private LocalDate interviewDate;

	public Applicant() {
		super();
	}

	public Applicant(String fullName, LocalDate doB, String highestQualification, int marksObtained, String goals,
			String emailId, Program program) {
		super();
		this.fullName = fullName;
		this.dob = doB;
		this.highestQualification = highestQualification;
		this.marksObtained = marksObtained;
		this.goals = goals;
		this.emailId = emailId;
		this.programs = program;
		this.status = ApplicationStatus.Pending;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public LocalDate getDoB() {
		return dob;
	}

	public void setDoB(LocalDate doB) {
		this.dob = doB;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public int getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Program getProgram() {
		return programs;
	}

	public void setProgram(Program program) {
		this.programs = program;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public LocalDate getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(LocalDate interviewDate) {
		this.interviewDate = interviewDate;
	}

	@Override
	public String toString() {
		return " FullName = " + fullName + "\n ApplicationId = " + applicationId + "\n DoB = " + dob
				+ "\n HighestQualification = " + highestQualification + "\n MarksObtained = " + marksObtained
				+ "\n Goals = " + goals + "\n EmailId = " + emailId + "\n ProgramId = " + programs.getProgramId()
				+ "\n ProgramName = " + programs.getProgramName() + "\n Status = " + status+"\n-----------\n";
	}

}
