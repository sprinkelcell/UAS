package com.cg.uas.dao;

import java.util.Collection;

import com.cg.uas.exceptions.AdminException;
import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Program;

public interface ProgramDao {
	void addProgram(int programId, Program pro) throws StoreException;

	void updateProgram(int programId, Program program) throws StoreException, AdminException;

	boolean deleteProgram(int programId) throws  AdminException, StoreException;

	public Program searchProgram(int key);

	public Collection<Program> getProgram();

}
