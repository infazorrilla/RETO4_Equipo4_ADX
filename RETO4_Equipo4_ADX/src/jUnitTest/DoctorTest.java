package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.pojos.Doctor;

class DoctorTest {

	private Doctor doctor = null;

	public DoctorTest() {
		doctor = new Doctor();
	}

	@Test
	void testGettersAndSetters() {
		// SPECIALITY //
		doctor.setSpeciality(null);
		assertEquals(doctor.getSpeciality(), null);

		// MIR //
		doctor.setMir(false);
		assertEquals(doctor.isMir(), true);
	}

	@Test
	void testHashCode() {
		doctor.setSpeciality(null);
		doctor.setMir(false);

		assertNotNull(doctor.hashCode());

	}

	@Test
	void testEquals() {

		Doctor doctorOne = new Doctor();
		doctorOne.setSpeciality(null);
		doctorOne.setMir(false);

		Doctor doctorTwo = new Doctor();
		doctorTwo.setSpeciality(null);
		doctorTwo.setMir(false);

		assertTrue(doctorOne.equals(doctorTwo));

	}

	@Test
	void testToString() {
		doctor.setSpeciality(null);
		doctor.setMir(false);

		String sentence = "Doctor [speciality=" + null + ", mir=" + false + "]";

		assertEquals(sentence, doctor.toString());
	}

}
