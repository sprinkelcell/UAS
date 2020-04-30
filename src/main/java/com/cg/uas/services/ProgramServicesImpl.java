package com.cg.uas.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.cg.uas.dao.ProgramDaoImpl;
import com.cg.uas.exceptions.AdminException;
import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Applicant;
import com.cg.uas.model.Program;

public class ProgramServicesImpl implements ProgramServices {
	private ProgramDaoImpl dao;

	public ProgramServicesImpl() throws StoreException {
		super();
		dao = new ProgramDaoImpl();

		/*
		 * LocalDate startDate = LocalDate.now(); LocalDate endDate =
		 * LocalDate.now().plusMonths(6); addProgram(new Program("BsC", startDate,
		 * endDate)); addProgram(new Program("BE", startDate, endDate)); addProgram(new
		 * Program("BCom", startDate, endDate));
		 */

	}

	private final int generateProgrammId() {
		if (dao.getProgram().size() == 0) {
			System.out.println(1001);
			return 1001;
		} else {
			int id = dao.getProgram().stream().map(a -> a.getProgramId()).max(Integer::compare).get();
			return ++id;
		}

	}

	@Override
	public Program addProgram(Program pro) throws StoreException {
		// TODO Auto-generated method stub
		int programId = generateProgrammId();
		pro.setProgramId(programId);
		dao.addProgram(programId, pro);
		return pro;
	}

	@Override
	public Program updateProgram(int programId, LocalDate startDate, LocalDate endDate)
			throws StoreException, AdminException {
		// TODO Auto-generated method stub
		Program pro = dao.searchProgram(programId);
		pro.setStartDate(startDate);
		pro.setEndDate(endDate);
		dao.updateProgram(programId, pro);
		return pro;
	}

	@Override
	public void deleteProgram(int programId) throws AdminException, StoreException {
		// TODO Auto-generated method stub
		dao.deleteProgram(programId);
	}

	@Override
	public List<Program> getProgram() {
		// TODO Auto-generated method stub
		// dao.getProgram();
		return dao.getProgram().stream().collect(Collectors.toList());

	}

	@Override
	public Program searchProgram(int key) {
		// TODO Auto-generated method stub
		return dao.searchProgram(key);
	}

	@Override
	public Set<Applicant> getApplicantList(int programId) {
		// TODO Auto-generated method stub
		return dao.searchProgram(programId).getApplicants();
	}

	@Override
	public boolean dateValidation(LocalDate startDate, LocalDate endDate) {
		if (startDate.isBefore(endDate))
			return true;
		return false;
	}
}
