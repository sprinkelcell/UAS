package com.cg.uas.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.net.ssl.SSLEngineResult.Status;

import com.cg.uas.dao.ApplicantDaoImpl;
import com.cg.uas.dao.ProgramDaoImpl;
import com.cg.uas.exceptions.AdminException;
import com.cg.uas.exceptions.ApplicantException;
import com.cg.uas.exceptions.MacException;
import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Applicant;
import com.cg.uas.model.Program;
import com.cg.uas.uasui.ClientUI;
import com.cg.uas.model.ApplicationStatus;

public class ApplicantServiceImpl implements ApplicantService {
	private ApplicantDaoImpl dao;
	private ProgramDaoImpl proDao;

	private static String namePattern = "[A-Za-z]{4,}";
	private static String mobilePattern = "[7-9][0-9]{9}";
	private static String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public ApplicantServiceImpl() throws StoreException {

		dao = new ApplicantDaoImpl();
		proDao = new ProgramDaoImpl();

	}

	public boolean validateName(String name) {
		return name.matches(namePattern);
	}

	public boolean validateMobile(String mobile) {
		return mobile.matches(mobilePattern);
	}

	public boolean validateEmail(String email) {
		return email.matches(emailPattern);
	}

	public boolean validateAge(int age) {
		return age <= 25 && age >= 17;
	}

	private final int generateRollNo() {
		if (dao.getApplicant().size() == 0)
			return 1000001;
		else {
			int id = dao.getApplicant().stream().map(a -> a.getApplicationId()).max(Integer::compare).get();
			return ++id;
		}
	}

	@Override
	public Applicant addStudent(Applicant std) throws StoreException, AdminException {

		int applicationId = generateRollNo();
		std.setApplicationId(applicationId);
		dao.persist(applicationId, std);
		Program key = std.getProgram();
		Set<Applicant> set = key.getApplicants();
		set.add(std);
		proDao.updateProgram(key.getProgramId(), key);
		return std;

	}

	@Override
	public Applicant search(int applicationId) {

		return dao.search(applicationId);

	}

	@Override
	public void delete(int applicationId) throws StoreException, AdminException, MacException {
		//
		Applicant std = dao.search(applicationId);
		Program key = std.getProgram();
		Set<Applicant> set = key.getApplicants();
		set.remove(std);
		proDao.updateProgram(key.getProgramId(), key);
		if (!dao.delete(applicationId)) {
			throw new MacException("Process not completed");
		}

	}

	@Override
	public List<Applicant> getStudent(ApplicationStatus status) {
		// TODO Auto-generated method stub

		if (status == null)
			return dao.getApplicant().stream().collect(Collectors.toList());
		else
			return dao.getApplicant().stream().filter(a -> a.getStatus().equals(status)).collect(Collectors.toList());

	}

	@Override
	public void addStatus(int applicationId, ApplicationStatus status) throws StoreException {
		// TODO Auto-generated method stub
		dao.addStatus(applicationId, status);
	}

	@Override
	public List<Applicant> getProgramList(int programId) {
		// TODO Auto-generated method stub
		return dao.getApplicant().stream().filter(
				a -> a.getProgram().getProgramId() == programId && a.getStatus().equals(ApplicationStatus.Pending))
				.collect(Collectors.toList());
	}

}
