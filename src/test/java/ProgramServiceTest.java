import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.uas.dao.ApplicantDaoImpl;
import com.cg.uas.exceptions.AdminException;
import com.cg.uas.exceptions.StoreException;
import com.cg.uas.model.Program;
import com.cg.uas.services.ProgramServices;
import com.cg.uas.services.ProgramServicesImpl;

public class ProgramServiceTest {

	static ProgramServices proService;
	static Program pro;
	static int id;
	static Program temp;

	@Before
	public void setUp() throws Exception {
		proService = new ProgramServicesImpl();
		pro = new Program("BE", LocalDate.now(), LocalDate.now().plusMonths(6));
		temp = proService.addProgram(pro);
		id = pro.getProgramId();
	}

	@Test
	public void testAddProgram() throws StoreException {

		assertEquals(temp, pro);
	}

	@Test
	public void testFindByID() {
		assertEquals(proService.searchProgram(id), temp);
	}

	@Test
	public void testUpdate() throws StoreException, AdminException {
		Program updatedPro = proService.updateProgram(id, LocalDate.now(), LocalDate.now().plusMonths(2));
		assertEquals(pro.getEndDate(), updatedPro.getEndDate());
	}

	@Test
	public void testDelete() throws StoreException, AdminException {
		proService.deleteProgram(id);
		assertNull(proService.searchProgram(id));
	}

	@After
	public void tearDown() throws Exception {
		proService = null;
		pro = null;
		id = 0;
		temp = null;
	}

}
