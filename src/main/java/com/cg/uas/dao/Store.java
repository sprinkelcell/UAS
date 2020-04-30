package com.cg.uas.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Applicant;
import com.cg.uas.model.Program;

public class Store implements Serializable {

	private Map<Integer, Applicant> applicants;
	private Map<Integer, Program> programs;
	private static Store store;
	public static final String STORE_PATH = "University.txt";

	private Store() {
		this.applicants = new HashMap<Integer, Applicant>();
		this.programs = new HashMap<Integer, Program>();
	}

	public static Store getInstance() throws IOException, ClassNotFoundException {
		if (null == store) {
			File file = new File(STORE_PATH);
			if (file.exists()) {

				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STORE_PATH));
				Object obj = ois.readObject();
				if (obj instanceof Store) {
					store = (Store) obj;
				}

				ois.close();
			} else {

				store = new Store();

			}
		}
		return store;
	}

	public void save() throws IOException, StoreException {
		// write your store into a ObjectOutputStream
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(STORE_PATH));
			oos.writeObject(store);
			//oos.flush();
			//oos.close();
		} catch (IOException exp) {
			// TODO Auto-generated catch block
			throw new StoreException("Data not stored");
		}

	}

	public Map<Integer, Applicant> getApplicant() {
		return applicants;
	}

	public void setApplicant(Map<Integer, Applicant> applicant) {
		this.applicants = applicant;
	}

	public Map<Integer, Program> getProgram() {
		return programs;
	}

	public void setProgram(Map<Integer, Program> program) {
		this.programs = program;
	}

}
