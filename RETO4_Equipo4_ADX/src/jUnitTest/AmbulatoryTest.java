package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.pojos.Ambulatory;
import model.pojos.Patient;

class AmbulatoryTest {

	private Ambulatory ambulatory = null;

	public AmbulatoryTest() {
		ambulatory = new Ambulatory();
	}

	@Test
	void testGettersAndSetters() {
		// ID //
		ambulatory.setId(2);
		assertEquals(ambulatory.getId(), 2);
		// NAME //
		ambulatory.setName("Cruces");
		assertEquals(ambulatory.getName(), "Cruces");

		// PHONE NUMBER //
		ambulatory.setPhoneNumber("666 666 666");
		assertEquals(ambulatory.getPhoneNumber(), "666 666 666");

		// ADDRESS //
		ambulatory.setAddress("Lendakari Aguirre");
		assertEquals(ambulatory.getAddress(), "Lendakari Aguirre");
	}

	@Test
	void testHashCode() {
		ambulatory.setId(2);
		ambulatory.setName("Cruces");
		ambulatory.setPhoneNumber("666 666 666");
		ambulatory.setAddress("Lendakari Aguirre");

		assertNotNull(ambulatory.hashCode());
	}

	@Test
	void testEquals() {
		Ambulatory ambulatoryOne = new Ambulatory();
		ambulatoryOne.setId(2);
		ambulatoryOne.setName("Cruces");
		ambulatory.setPhoneNumber("666 666 666");
		ambulatory.setAddress("Lendakari Aguirre");

		Ambulatory ambulatoryTwo = new Ambulatory();
		ambulatoryTwo.setId(2);
		ambulatoryTwo.setName("Cruces");
		ambulatory.setPhoneNumber("666 666 666");
		ambulatory.setAddress("Lendakari Aguirre");

		assertTrue(ambulatoryOne.equals(ambulatoryTwo));
	}

	@Test
	void testToString() {
		ambulatory.setId(2);
		ambulatory.setName("Cruces");
		ambulatory.setPhoneNumber("666 666 666");
		ambulatory.setAddress("Lendakari Aguirre");

		String sentence = "Ambulatory [id=" + 2 + ", name=" + "Cruces" + ", phoneNumber=" + "666 666 666" + ", address="
				+ "Lendakari Aguirre" + "]";

	}

}
