package sk.upjs.registracia_konferencia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class CompanionTest {

	@Test
	void testGetPrice1() {
		Companion comp = new Companion();
		comp.setCategory(CompanionCategory.ADULT);
		comp.setStart(LocalDateTime.of(2019, 9, 21, 17, 0));
		comp.setEnd(LocalDateTime.of(2019, 9, 23, 10, 0));
		comp.setTshirt(null);
		assertEquals(110, comp.getPrice());
	}

	@Test
	void testGetPrice2() {
		Companion comp = new Companion();
		comp.setCategory(CompanionCategory.ADULT);
		comp.setStart(LocalDateTime.of(2019, 9, 22, 17, 0));
		comp.setEnd(LocalDateTime.of(2019, 9, 24, 10, 0));
		comp.setTshirt(Tshirt.M);
		assertEquals(135, comp.getPrice());
	}
}
