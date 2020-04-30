import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.uas.exceptions.AdminException;
import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Applicant;
import com.cg.uas.model.ApplicationStatus;
import com.cg.uas.services.ApplicantService;
import com.cg.uas.services.ApplicantServiceImpl;
import com.cg.uas.services.ProgramServices;
import com.cg.uas.services.ProgramServicesImpl;

public class ApplicantServiceTest {
	static ApplicantService service;
	static ProgramServices proService;
	static Applicant applicant;
	static Applicant app;
	static int id;

	@Before
	public void setUpBeforeClass() throws StoreException, AdminException {

		service = new ApplicantServiceImpl();
		proService = new ProgramServicesImpl();
		LocalDate doB = LocalDate.of(1998, 11, 11);
		applicant = new Applicant("Rajesh", doB, "SSC", 91, "IT Engineer", " Rajesh@gmail.com",
				proService.searchProgram(1001));
		app = service.addStudent(applicant);
		id = applicant.getApplicationId();
	}

	@Test
	public void testAddApplicant() throws StoreException, AdminException {

		assertEquals(app, applicant);
	}

	@Test
	public void testFindByID() {
		assertEquals(applicant, service.search(id));
	}

	@Test
	public void testUpdateStatus() throws StoreException {
		service.addStatus(id, ApplicationStatus.Confirmed);
		assertEquals(applicant.getStatus(), ApplicationStatus.Confirmed);
	}

	@After
	public void tearDownAfterClass() throws Exception {
		service=null;
		proService=null;
		applicant=null;
		app=null;
		id=0;
	}

}
