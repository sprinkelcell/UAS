/*
 * package com.cg.uas.cli;
 * 
 * import java.time.LocalDate; import java.util.InputMismatchException; import
 * java.util.Scanner;
 * 
 * import javax.net.ssl.SSLEngineResult.Status;
 * 
 * import com.cg.uas.exceptions.InvalidIDException; import
 * com.cg.uas.exceptions.InvalidUserException; import
 * com.cg.uas.exceptions.StoreException; import com.cg.uas.login.LoginService;
 * import com.cg.uas.login.LoginServiceImpl; import com.cg.uas.model.Applicant;
 * import com.cg.uas.model.Program; import com.cg.uas.model.Stat; import
 * com.cg.uas.services.ApplicantService; import
 * com.cg.uas.services.ApplicantServiceImpl; import
 * com.cg.uas.services.ProgramServices; import
 * com.cg.uas.services.ProgramServicesImpl;
 * 
 * public class Client { static Scanner sc = new Scanner(System.in); private
 * static ApplicantService service; private static ProgramServices pro; private
 * static LoginService login = new LoginServiceImpl();
 * 
 * public static void main(String[] args) { int choice;
 * 
 * try { service = new ApplicantServiceImpl(); pro = new ProgramServicesImpl();
 * } catch (StoreException exp) { // TODO Auto-generated catch block
 * System.out.println(exp);
 * 
 * } do {
 * 
 * try { System.out.println("  Welcome to university admission system\n\n ");
 * 
 * System.out.
 * println("1. Applicant Login \n2. Mac Login\n3. Admin Login\n4. Exit"); choice
 * = sc.nextInt(); switch (choice) { case 1: applicant(); break; case 2: mac();
 * break; case 3: admin(); break; case 4: System.out.println("Exit.....");
 * System.exit(0); break; default: System.out.println("Invalid choice"); break;
 * } } catch (InputMismatchException inputEx) { // TODO Auto-generated catch
 * block System.out.println("Enter Valid Key"); sc.next(); } } while (true); }
 * 
 * private static void mac() { // TODO Auto-generated method stub
 * System.out.print("Enter Username:"); String username = sc.next();
 * System.out.print("Enter Password:"); String password = sc.next(); try { if
 * (login.validateCandidate(username, password, "mac")) {
 * 
 * int choice; do { System.out.
 * println("1.View Applications for program\n2.Accept Reject application\n3.Back"
 * ); choice = sc.nextInt(); switch (choice) { case 1:
 * System.out.println("Enter ProgramID");
 * pro.getApplicantList(sc.nextInt()).forEach(System.out::println); break;
 * 
 * case 2: System.out.print("Enter Application Id:");
 * 
 * int id = sc.nextInt();
 * 
 * if (service.search(id) == null)
 * 
 * throw new InvalidIDException();
 * 
 * else { System.out.println("1.Confirmed\n2.Rejected");
 * System.out.println(service.search(id).toString());
 * System.out.println("Choose Status:");
 * System.out.println("1.Confirmed\n2.Rejected"); int i = sc.nextInt(); if (i ==
 * 1) service.addStatus(id, Stat.Confirmed); else if (i == 2)
 * service.addStatus(id, Stat.Rejected); else System.out.println("Invalid Nos");
 * } break;
 * 
 * case 3: System.out.println("Logging Off"); break;
 * 
 * default: break;
 * 
 * }
 * 
 * } while (choice != 3); } else { throw new InvalidUserException(); } } catch
 * (InvalidIDException exp) { // TODO Auto-generated catch block
 * System.out.println(exp); } catch (InputMismatchException inputEx) { // TODO
 * Auto-generated catch block System.out.println("Enter Valid Key"); sc.next();
 * } catch (InvalidUserException exp) { // TODO Auto-generated catch block
 * System.out.println(exp); } }
 * 
 * private static void admin() { // TODO Auto-generated method stub
 * System.out.print("Enter Username:"); String username = sc.next();
 * System.out.print("Enter Password:"); String password = sc.next(); int choice;
 * try { if (login.validateCandidate(username, password, "admin")) { do {
 * System.out.println(
 * "1. Add new Programm\n2. Update Programm\n3. Delete Programm \n4. View Applicant List \n5. Log Out"
 * ); choice = sc.nextInt(); LocalDate startDate; LocalDate endDate; switch
 * (choice) { case 1: System.out.print("Enter Program Name"); String proName =
 * sc.next(); startDate = getDate(); endDate = getDate(); pro.addProgram(new
 * Program(proName, startDate, endDate));
 * 
 * break; case 2: System.out.print("Enter Program id:"); int id = sc.nextInt();
 * if (pro.searchProgram(id) == null) System.out.println("Not Found"); else {
 * startDate = getDate(); endDate = getDate(); pro.updateProgram(id, startDate,
 * endDate); } break; case 3: System.out.print("Enter Program id:"); id =
 * sc.nextInt(); if (pro.searchProgram(id) == null)
 * System.out.println("Not Found"); else { pro.deleteProgram(id); } break; case
 * 4: viewList(); break; case 5: System.out.println("Exit Application "); break;
 * default: System.out.println("Invalid choice"); break; } } while (choice !=
 * 5); } else { throw new InvalidIDException(); } } catch (InvalidIDException
 * exp) { // TODO Auto-generated catch block System.out.println(exp); } catch
 * (InputMismatchException exp) { // TODO: handle exception
 * System.out.println(exp); sc.next(); }
 * 
 * }
 * 
 * private static void viewList() { // TODO Auto-generated method stub
 * System.out.println("1.Confirmed\n2.Rejected\n3.Pending\n4.All");
 * 
 * switch (sc.nextInt()) { case 1:
 * service.getStudent(Stat.Confirmed).forEach(System.out::println); break; case
 * 2: service.getStudent(Stat.Rejected).forEach(System.out::println); break;
 * case 3: service.getStudent(Stat.Pending).forEach(System.out::println); break;
 * case 4: service.getStudent(null).forEach(System.out::println); break;
 * default: break; } }
 * 
 * private static void applicant() { // TODO Auto-generated method stub
 * System.out.println("***Application system***"); int choice = 0; do { try {
 * System.out.
 * println("1. View Courses\n2. Apply\n3. Check Application Status \n4. Log Out"
 * ); choice = sc.nextInt(); int appId; switch (choice) { case 1:
 * pro.getProgram().forEach(System.out::println); break; case 2:
 * 
 * 
 * String fullName = "Kalpesh"; LocalDate doB = LocalDate.of(2017, 11, 17);
 * String highestQualification = "HSC"; int marksObtained = 91; String goals =
 * "Vadapav thela"; String emailId = "testy@testmail.com"; int programId =
 * sc.nextInt(); Program getPro = pro.searchProgram(programId); appId =
 * service.addStudent( new Applicant(fullName, doB, highestQualification,
 * marksObtained, goals, emailId, getPro)); System.out.println(appId);
 * 
 * addStudent(); break; case 3: System.out.println("Enter Application id:");
 * appId = sc.nextInt(); System.out.println(service.search(appId).getStatus());
 * break; case 4: System.out.println("Logging off "); break; default:
 * System.out.println("Invalid choice"); break; } } catch
 * (InputMismatchException exp) { // TODO Auto-generated catch block
 * System.out.println("Enter valid key"); sc.next(); } } while (choice != 4); }
 * 
 * private static LocalDate getDate() { // TODO Auto-generated method stub
 * System.out.print("Enter start date in dd mm yyyy format"); int dayOfMonth =
 * sc.nextInt(); int month = sc.nextInt(); int year = sc.nextInt();
 * 
 * return LocalDate.of(year, month, dayOfMonth);
 * 
 * }
 * 
 * private static void addStudent() { // TODO Auto-generated method stub String
 * name = null, mobile = null, emailId = null;
 * 
 * Program getPro; Scanner console = new Scanner(System.in);
 * 
 * do { System.out.println("Enter Student Name :"); name = console.nextLine();
 * 
 * if (!ApplicantService.validateName(name)) System.out.println("Invalid name");
 * else break; } while (true);
 * 
 * do { System.out.println("enter programId"); int programId = sc.nextInt();
 * getPro = pro.searchProgram(programId); if (getPro == null)
 * System.out.println("Invalid PogramID"); else break; } while (true); do {
 * System.out.println("Enter Student mobile :"); mobile = console.nextLine(); if
 * (!ApplicantService.validateMobile(mobile))
 * System.out.println("Invalid mobile"); else break; } while (true);
 * 
 * do { System.out.println("Enter Student email :"); emailId =
 * console.nextLine();
 * 
 * if (!ApplicantService.validateEmail(emailId))
 * System.out.println("Invalid email"); else break; } while (true);
 * 
 * LocalDate doB = getDate(); System.out.println("Enter Highest Qualification");
 * String highestQualification = sc.next();
 * System.out.println("Enter Previous Marks"); int marksObtained = sc.nextInt();
 * System.out.println("Enter your Goals"); String goals = sc.next();
 * 
 * int appId = service .addStudent(new Applicant(name, doB,
 * highestQualification, marksObtained, goals, emailId, getPro));
 * System.out.println("Application Id:" + appId);
 * 
 * } }
 */