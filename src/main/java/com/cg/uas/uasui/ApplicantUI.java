package com.cg.uas.uasui;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.uas.exceptions.AdminException;
import com.cg.uas.exceptions.ApplicantException;
import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Applicant;
import com.cg.uas.model.Program;
import com.cg.uas.services.ApplicantService;
import com.cg.uas.services.ApplicantServiceImpl;
import com.cg.uas.services.ProgramServices;
import com.cg.uas.services.ProgramServicesImpl;

public class ApplicantUI {

	private static ApplicantService service;
	private static ProgramServices pro;

	public static void show() throws  StoreException {
		service = new ApplicantServiceImpl();
		pro = new ProgramServicesImpl();
		System.out.println("***Application system***");
		int choice = 0;
		do {
			try {
				System.out.println("1. View Courses\n2. Apply\n3. Check Application Status \n4. Log Out");
				choice = ClientUI.console.nextInt();
				switch (choice) {
				case 1:
					// View All Programs
					pro.getProgram().forEach(System.out::println);
					break;

				case 2:
					addStudent();
					break;

				case 3:
					searchStudent();
					break;

				case 4:
					System.out.println("Logging off ");
					break;

				default:
					System.out.println("Invalid choice");
					break;
				}
			} catch (InputMismatchException exp) {
				// TODO Auto-generated catch block
				System.out.println("invalid key");
				ClientUI.console.next();
			}  catch (StoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ApplicantException e) {
				// TODO Auto-generated catch block
				
			} catch (AdminException e) {
				// TODO Auto-generated catch block
				
			}
		} while (choice != 4);
	}

	private static void searchStudent() throws ApplicantException {
		// TODO Auto-generated method stub
		System.out.println("Enter Application id:");
		int appId = ClientUI.console.nextInt();
		try {
			System.out.println(service.search(appId).getStatus());
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			throw new ApplicantException("Application ID Not Found");
		}
	}

	private static void addStudent() throws StoreException, AdminException {
		// TODO Auto-generated method stub
		String name = null, mobile = null, emailId = null;

		Program getPro;
		ClientUI.console.nextLine();

		do {
			System.out.print("Enter Student Name :");
			name = ClientUI.console.nextLine();

			if (!service.validateName(name))
				System.out.println("Invalid name");
			else
				break;
		} while (true);

		do {
			System.out.print("enter programId");
			int programId = ClientUI.console.nextInt();
			getPro = pro.searchProgram(programId);
			if (getPro == null)
				System.out.println("Invalid PogramID");
			else
				break;
		} while (true);
		ClientUI.console.nextLine();
		do {
			System.out.println("Enter Student mobile :");
			mobile = ClientUI.console.nextLine();
			if (!service.validateMobile(mobile))
				System.out.println("Invalid mobile");
			else
				break;
		} while (true);

		do {
			System.out.print("Enter Student email :");
			emailId = ClientUI.console.nextLine();

			if (!service.validateEmail(emailId))
				System.out.println("Invalid email");
			else
				break;
		} while (true);

		LocalDate doB = getDate("Date of birth");
		String highestQualification;
		System.out.print("Enter Highest Qualification : ");
		highestQualification = ClientUI.console.next();
		System.out.print("Enter Previous Marks : ");
		int marksObtained = ClientUI.console.nextInt();
		System.out.print("Enter your Goals : ");
		String goals = ClientUI.console.next();

		Applicant app = service
				.addStudent(new Applicant(name, doB, highestQualification, marksObtained, goals, emailId, getPro));
		System.out.println("Application Id:" + app.getApplicationId());

	}

	private static LocalDate getDate(String str) {
		// TODO Auto-generated method stub
		System.out.print("Enter "+str+" in dd mm yyyy format : ");
		int dayOfMonth = ClientUI.console.nextInt();
		int month = ClientUI.console.nextInt();
		int year = ClientUI.console.nextInt();

		return LocalDate.of(year, month, dayOfMonth);

	}
}
