package sk.upjs.registracia_konferencia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParticipantTest {
	
	Workshop cidmWorkshop;
	Workshop slonlpWorkshop;
	
	@BeforeEach
	public void setBefore() {
		cidmWorkshop = new Workshop();
		cidmWorkshop.setPriceFull(355.0);
		cidmWorkshop.setPriceStudent(295.0);
		cidmWorkshop.setPriceFullLate(380.0);
		cidmWorkshop.setPriceStudentLate(320.0);
		slonlpWorkshop = new Workshop();
		slonlpWorkshop.setPriceFull(245.0);
		slonlpWorkshop.setPriceStudent(195.0);
		slonlpWorkshop.setPriceFullLate(265.0);
		slonlpWorkshop.setPriceStudentLate(210.0);
	}

	@Test
	void testFinalPrice1() {
		Participant participant = new Participant();
		participant.setEarly(true);
		participant.setWorkshop(cidmWorkshop);
		participant.setStudent(false);
		participant.setSingleRoom(false);
		participant.setCash(false);
		assertEquals(355.0,participant.finalPrice());
	}
	
	@Test
	void testFinalPrice2() {
		Participant participant = new Participant();
		participant.setEarly(false);
		participant.setWorkshop(cidmWorkshop);
		participant.setStudent(false);
		participant.setSingleRoom(false);
		participant.setCash(false);
		assertEquals(380.0,participant.finalPrice());
	}
	
	@Test
	void testFinalPrice3() {
		Participant participant = new Participant();
		participant.setEarly(true);
		participant.setWorkshop(slonlpWorkshop);
		participant.setStudent(false);
		participant.setSingleRoom(false);
		participant.setCash(false);
		List<Companion> companions = new ArrayList<>();
		Companion c1 = new Companion();
		c1.setCategory(CompanionCategory.ADULT);
		c1.setStart(LocalDateTime.of(2019, 9, 21, 17, 0));
		c1.setEnd(LocalDateTime.of(2019, 9, 23, 10, 0));
		c1.setTshirt(null);
		companions.add(c1);
		participant.setCompanions(companions);
		assertEquals(245+110,participant.finalPrice());
	}
	
	@Test
	void testFinalPrice4() {
		Participant participant = new Participant();
		participant.setEarly(false);
		participant.setWorkshop(cidmWorkshop);
		participant.setStudent(false);
		participant.setSingleRoom(true);
		participant.setCash(false);
		assertEquals(380.0+75.0,participant.finalPrice());
	}

}
