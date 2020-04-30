package com.cg.uas.dao;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Applicant;
import com.cg.uas.model.ApplicationStatus;
import com.cg.uas.uasui.ClientUI;

public class ApplicantDaoImpl implements ApplicantDao {

	private Map<Integer, Applicant> applicants;
	static Store store = null;

	public ApplicantDaoImpl() throws StoreException {

		try {
			store = Store.getInstance();
			applicants = store.getApplicant();
		} catch (ClassNotFoundException | IOException exp) {
			// TODO Auto-generated catch block
			ClientUI.logger.error(exp);
			throw new StoreException("Please restart the program");
		}

	}

	@Override
	public void persist(int applicationId, Applicant st) throws StoreException {
		// TODO Auto-generated method stub
		applicants.put(applicationId, st);
		save();

	}

	@Override
	public Applicant search(int applicationId) {
		return applicants.get(applicationId);
	}

	@Override
	public boolean delete(int applicationId) throws StoreException {
		// TODO Auto-generated method stub

		Applicant student = applicants.remove(applicationId);
		save();
		return student != null ? true : false;
	}

	@Override
	public void addStatus(int applicationId, ApplicationStatus status) throws StoreException {
		// TODO Auto-generated method stub
		applicants.get(applicationId).setStatus(status);
		save();
	}

	@Override
	public Collection<Applicant> getApplicant() {
		// TODO Auto-generated method stub
		return applicants.values();
	}

	private void save() throws StoreException {
		// TODO Auto-generated method stub
		try {
			store.setApplicant(applicants);
			store.save();
		} catch (IOException exp) {
			// TODO Auto-generated catch block
			ClientUI.logger.error(exp);
			throw new StoreException("Process not completed");
		}
	}
}
