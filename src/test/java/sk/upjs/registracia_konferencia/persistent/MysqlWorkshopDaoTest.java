package sk.upjs.registracia_konferencia.persistent;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.registracia_konferencia.entities.Workshop;

class MysqlWorkshopDaoTest {

	@Test
	void testGetAll() {
		List<Workshop> workshops = DaoFactory.INSTANCE.getWorkshopDao().getAll();
		assertNotNull(workshops);
		assertTrue(workshops.size() > 0);
	}
	
	@Test
	void testSave() {
		Workshop cidmWorkshop = new Workshop();
		cidmWorkshop.setName("CIDM");
		cidmWorkshop.setStart(LocalDate.of(2018, 10, 5));
		cidmWorkshop.setEnd(LocalDate.of(2018, 11, 2));
		cidmWorkshop.setPriceFull(355.0);
		cidmWorkshop.setPriceStudent(295.0);
		cidmWorkshop.setPriceFullLate(380.0);
		cidmWorkshop.setPriceStudentLate(320.0);
		DaoFactory.INSTANCE.getWorkshopDao().save(cidmWorkshop);
		assertNotNull(cidmWorkshop.getId());
		cidmWorkshop.setName("cidm-nove");
		DaoFactory.INSTANCE.getWorkshopDao().save(cidmWorkshop);
		List<Workshop> workshops = DaoFactory.INSTANCE.getWorkshopDao().getAll();
		for (Workshop w: workshops) {
			if (w.getId() == cidmWorkshop.getId()) {
				assertEquals("cidm-nove",w.getName());
				DaoFactory.INSTANCE.getWorkshopDao().delete(w.getId());
				return;
			}
		}
		assertTrue(false, "update je na nic.. learn to code bro");
		
	}
	
	@Test
	void testDelete() {
		Workshop cidmWorkshop = new Workshop();
		cidmWorkshop.setName("CIDM");
		cidmWorkshop.setStart(LocalDate.of(2018, 10, 5));
		cidmWorkshop.setEnd(LocalDate.of(2018, 11, 2));
		cidmWorkshop.setPriceFull(355.0);
		cidmWorkshop.setPriceStudent(295.0);
		cidmWorkshop.setPriceFullLate(380.0);
		cidmWorkshop.setPriceStudentLate(320.0);
		DaoFactory.INSTANCE.getWorkshopDao().save(cidmWorkshop);
		Long id = cidmWorkshop.getId();
		DaoFactory.INSTANCE.getWorkshopDao().delete(id);
		List<Workshop> workshops = DaoFactory.INSTANCE.getWorkshopDao().getAll();
		for (Workshop w : workshops) {
			assertNotEquals(id, w.getId());
		}
	}

}
