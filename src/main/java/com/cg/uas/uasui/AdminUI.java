package com.cg.uas.uasui;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.uas.exceptions.AdminException;
import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Program;
import com.cg.uas.model.ApplicationStatus;
import com.cg.uas.services.ApplicantService;
import com.cg.uas.services.ApplicantServiceImpl;
import com.cg.uas.services.LoginService;
import com.cg.uas.services.LoginServiceImpl;
import com.cg.uas.services.ProgramServices;
import com.cg.uas.services.ProgramServicesImpl;

public class AdminUI {

	private static ApplicantService service;
	private static ProgramServices pro;
	private static LoginService login = new LoginServiceImpl();

	public static void show() throws StoreException {
		// TODO Auto-generated method stub
		service = new ApplicantServiceImpl();
		pro = new ProgramServicesImpl();
		System.out.print("Enter Username:");
		String username = ClientUI.console.next();
		System.out.print("Enter Password:");
		String password = ClientUI.console.next();
		int choice = 0;

		if (login.validateCandidate(username, password, "admin")) {

			do {
				try {

					int id;
					System.out.println(
							"1. Add new Programm\n2. Update Programm\n3. Delete Programm \n4. View Applicant List \n5. Log Out");
					choice = ClientUI.console.nextInt();

					switch (choice) {
					case 1:
						addProgram();
						break;
					case 2:
						updateProgram();
						break;
					case 3:
						deleteProgram();
						break;
					case 4:
						viewList();
						break;
					case 5:
						System.out.println("Exit Application ");
						break;
					default:
						System.out.println("Invalid choice");
						break;
					}

				} catch (InputMismatchException exp) {
					// TODO: handle exception
					System.out.println(exp);
					ClientUI.logger.error(exp);
					ClientUI.console.next();
				} catch (StoreException exp) {
					System.out.println(exp);
					ClientUI.logger.error(exp);
				} catch (AdminException exp) {
					System.out.println(exp);
					ClientUI.logger.error(exp);
				}
			} while (choice != 5);
		} else {
			System.out.println("Invlid Id or Password");
		}
	}

	private static void deleteProgram() throws AdminException, StoreException {
		// TODO Auto-generated method stub
		System.out.print("Enter Program id:");
		int id = ClientUI.console.nextInt();
		pro.deleteProgram(id);

	}

	private static void updateProgram() throws StoreException, AdminException {
		// TODO Auto-generated method stub
		System.out.print("Enter Program id:");
		int id = ClientUI.console.nextInt();

		LocalDate startDate = getDate();
		LocalDate endDate = getDate();
		if (pro.dateValidation(startDate, endDate)) {
			try {
				pro.updateProgram(id, startDate, endDate);
				System.out.println("Successfully Updated Program...");
			} catch (NullPointerException exp) {
				throw new AdminException("Enter valid id");
			}
		} else {
			System.out.println("Enter valid date");
		}

	}

	private static void addProgram() throws StoreException {
		// TODO Auto-generated method stub
		System.out.print("Enter Program Name:");
		String proName = ClientUI.console.next();
		LocalDate startDate = getDate();
		LocalDate endDate = getDate();
		if (pro.dateValidation(startDate, endDate)) {
			int id = pro.addProgram(new Program(proName, startDate, endDate)).getProgramId();
			System.out.println("Successfully Created Program...");
			System.out.println("Program Id : " + id);
		} else {
			System.out.println("Enter valid date");
		}

	}

	private static void viewList() {
		// TODO Auto-generated method stub
		System.out.println("1.Confirmed\n2.Rejected\n3.Pending\n4.All");

		switch (ClientUI.console.nextInt()) {
		case 1:
			service.getStudent(ApplicationStatus.Confirmed).forEach(System.out::println);
			break;
		case 2:
			service.getStudent(ApplicationStatus.Rejected).forEach(System.out::println);
			break;
		case 3:
			service.getStudent(ApplicationStatus.Pending).forEach(System.out::println);
			break;
		case 4:
			service.getStudent(null).forEach(System.out::println);
			break;
		default:
			break;
		}
	}

	private static LocalDate getDate() {
		// TODO Auto-generated method stub
		System.out.print("Enter start date in dd mm yyyy format");
		int dayOfMonth = ClientUI.console.nextInt();
		int month = ClientUI.console.nextInt();
		int year = ClientUI.console.nextInt();

		return LocalDate.of(year, month, dayOfMonth);

	}

}