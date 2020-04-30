package com.cg.uas.uasui;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.cg.uas.exceptions.AdminException;
import com.cg.uas.exceptions.ApplicantException;
import com.cg.uas.exceptions.MacException;
import com.cg.uas.exceptions.StoreException;

public class ClientUI {
	public static Scanner console = new Scanner(System.in);
	public final static Logger logger = Logger.getLogger(ClientUI.class);

	public static void main(String[] args) throws ApplicantException, AdminException, MacException, StoreException {
		do {

			try {
				System.out.println("  Welcome to university admission system\n\n ");

				System.out.println("1. Applicant Login \n2. Mac Login\n3. Admin Login\n4. Exit");
				int choice = console.nextInt();
				switch (choice) {
				case 1:
					ApplicantUI.show();
					break;
				case 2:
					MacUI.show();
					break;
				case 3:
					AdminUI.show();
					break;
				case 4:
					System.out.println("Exit.....");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid choice");
					break;
				}
			} catch (InputMismatchException inputEx) {
				System.out.println("Enter Valid Key");
				logger.error(inputEx);
				console.next();
			}  catch (StoreException exp) {
				System.out.println(exp);
				logger.error(exp);
				
			}

		} while (true);
	}
}
