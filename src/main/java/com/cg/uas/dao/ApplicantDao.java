package com.cg.uas.dao;

import java.util.Collection;

import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.*;

import com.cg.uas.model.Applicant;

public interface ApplicantDao {
	void persist(int applicationId, Applicant st) throws StoreException;

	Applicant search(int applicationId);

	boolean delete(int applicationId) throws StoreException;

	void addStatus(int applicationId, ApplicationStatus status) throws StoreException;

	public Collection<Applicant> getApplicant();
}
