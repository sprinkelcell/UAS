package com.cg.uas.uasui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.uas.exceptions.MacException;
import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.ApplicationStatus;
import com.cg.uas.services.ApplicantService;
import com.cg.uas.services.ApplicantServiceImpl;
import com.cg.uas.services.LoginService;
import com.cg.uas.services.LoginServiceImpl;
import com.cg.uas.services.ProgramServices;
import com.cg.uas.services.ProgramServicesImpl;

public class MacUI {

	private static ApplicantService service;
	private static ProgramServices pro;
	private static LoginService login = new LoginServiceImpl();

	public static void show() throws StoreException  {
		// TODO Auto-generated method stub
		service = new ApplicantServiceImpl();
		pro = new ProgramServicesImpl();
		System.out.print("Enter Username:");
		String username = ClientUI.console.next();
		System.out.print("Enter Password:");
		String password = ClientUI.console.next();

		if (login.validateCandidate(username, password, "mac")) {
			try {
				int choice;
				do {
					System.out.println("1.View Applications for program\n2.Accept Reject application\n3.Back");
					choice = ClientUI.console.nextInt();
					switch (choice) {
					case 1:
						viewApplicantList();
						break;

					case 2:
						setStatus();
						break;

					case 3:
						System.out.println("Logging Off");
						break;

					default:
						break;

					}

				} while (choice != 3);
			} catch (InputMismatchException inputEx) {
				// TODO Auto-generated catch block
				System.out.println("Enter Valid Key");
				ClientUI.console.next();
			}  catch (StoreException exp) {
				ClientUI.logger.error(exp);
				System.out.println(exp);
			} catch (MacException e) {
				// TODO Auto-generated catch block
				
			}
		} else {
			System.out.println("Invalid Id or Password");
		}
	}

	private static void viewApplicantList() throws MacException {
		// TODO Auto-generated method stub
		System.out.println("Enter ProgramID");
		try {
			pro.getApplicantList(ClientUI.console.nextInt()).forEach(System.out::println);
		} catch (Exception exp) {
			// TODO Auto-generated catch block
			ClientUI.logger.error(exp);
			throw  new MacException("Enter valid Application ID");
		}
	}

	private static void setStatus() throws StoreException {
		// TODO Auto-generated method stub
		System.out.print("Enter Application Id:");

		int id = ClientUI.console.nextInt();

		System.out.println(service.search(id).toString());
		System.out.println("Choose Status:");
		System.out.println("1.Confirmed\n2.Rejected");
		int i = ClientUI.console.nextInt();
		if (i == 1)
			service.addStatus(id, ApplicationStatus.Confirmed);
		else if (i == 2)
			service.addStatus(id, ApplicationStatus.Rejected);
		else
			System.out.println("Invalid Nos");
	}
}
