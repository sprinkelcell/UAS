package com.cg.uas.dao;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import com.cg.uas.exceptions.AdminException;
import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Program;
import com.cg.uas.uasui.ClientUI;

public class ProgramDaoImpl implements ProgramDao {
	private HashMap<Integer, Program> programs;
	static Store store = null;

	public ProgramDaoImpl() throws StoreException {

		try {
			store = Store.getInstance();
			programs = (HashMap<Integer, Program>) store.getProgram();
		} catch (ClassNotFoundException | IOException exp) {
			// TODO Auto-generated catch block
			ClientUI.logger.error(exp);
			throw new StoreException("Please Restart the program");
		}

	}

	@Override
	public void addProgram(int programId, Program pro) throws StoreException {
		// TODO Auto-generated method stub
		programs.put(programId, pro);
		save();
	}

	@Override
	public void updateProgram(int programId, Program program) throws StoreException, AdminException {
		// TODO Auto-generated method stub
		// if (programs.get(programId) == null)

		programs.replace(programId, program);

		save();
	}

	@Override
	public boolean deleteProgram(int programId) throws AdminException, StoreException {
		// TODO Auto-generated method stub
		Program pro = null;

		pro = programs.remove(programId);
		save();

		if (pro == null)
			throw new AdminException("Invalid Key");
		return pro != null ? true : false;
	}

	@Override
	public Program searchProgram(int key) {
		// TODO Auto-generated method stub
		return programs.get(key);
	}

	@Override
	public Collection<Program> getProgram() {
		Collection<Program> list = programs.values();
		return list;
	}

	private void save() throws StoreException {
		// TODO Auto-generated method stub
		try {
			store.setProgram(programs);
			store.save();
		} catch (IOException exp) {
			// TODO Auto-generated catch block
			ClientUI.logger.error(exp);
			throw new StoreException("Process not completed");
		}
	}

}
