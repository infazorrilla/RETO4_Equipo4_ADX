package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.pojos.Nurse;

class NurseTest {

	private Nurse nurse = null;

	public NurseTest() {
		nurse = new Nurse();
	}

	@Test
	void testGettersAndSetters() {
		// CATEGORY //
		nurse.setCategory(null);
		assertEquals(nurse.getCategory(), null);

		// EIR //
		nurse.setEir(false);
		assertEquals(nurse.isEir(), false);
	}

	@Test
	void testHashCode() {
		nurse.setCategory(null);
		nurse.setEir(false);

		assertNull(nurse.hashCode());
	}

	@Test
	void testEquals() {
		Nurse nurseOne = new Nurse();
		nurseOne.setCategory(null);
		nurseOne.setEir(false);

		Nurse nurseTwo = new Nurse();
		nurseTwo.setCategory(null);
		nurseTwo.setEir(false);

		assertTrue(nurseOne.equals(nurseTwo));
	}

	@Test
	void testToString() {
		nurse.setCategory(null);
		nurse.setEir(false);

		String sentence = "Nurse [category=" + null + ", eir=" + false + "]";
		assertEquals(sentence, nurse.toString());
	}

}
