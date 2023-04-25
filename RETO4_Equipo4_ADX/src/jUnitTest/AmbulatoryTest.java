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
		
	}
	
	@Test
	void testHashCode() {
		ambulatory.setId(2);
		ambulatory.setName("Cruces");
		
		assertNotNull(ambulatory.hashCode());
	}
	
	@Test
	void testEquals() {
		Ambulatory ambulatoryOne = new Ambulatory();
		ambulatoryOne.setId(2);
		ambulatoryOne.setName("Cruces");
		
		Ambulatory ambulatoryTwo = new Ambulatory();
		ambulatoryTwo.setId(2);
		ambulatoryTwo.setName("Cruces");
		
		assertTrue(ambulatoryOne.equals(ambulatoryTwo));
	}
	
	@Test
	void testToString() {
		ambulatory.setId(2);
		ambulatory.setName("Cruces");
		
		String sentence = "Ambulatory [id=" + 2 + ", name=" + "Cruces" + "]";
		assertEquals(sentence, ambulatory.toString());
		
	}

}
