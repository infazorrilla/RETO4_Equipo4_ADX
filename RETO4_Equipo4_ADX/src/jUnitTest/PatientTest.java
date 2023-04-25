package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import model.pojos.Appointment;
import model.pojos.Patient;

class PatientTest {

	private Patient patient = null;

	public PatientTest() {
		patient = new Patient();
	}

	@Test
	void testGettersAndSetters() {
		// PHONE NUMBER //
		patient.setPhoneNumber("11");
		assertEquals(patient.getPhoneNumber(), "11");

		// ADDRESS //
		patient.setAddress("11");
		assertEquals(patient.getAddress(), "11");

		// APOINTMENT //
		Appointment apointment = new Appointment();
		apointment.setId(2);
	
		apointment.setSanitarians(null);
		apointment.setPatient(null);
		assertNotNull(patient.getAppointments());
	}

	@Test
	void testHashCode() {
		patient.setPhoneNumber(null);
		patient.setAddress(null);
		patient.setAppointments(null);

		assertNotNull(patient.hashCode());
	}

	@Test
	void testEquals() {
		Patient patientOne = new Patient();
		patientOne.setPhoneNumber("666 666 666");
		patientOne.setAddress("Avenida Lendakari Aguirre");
		patientOne.setAppointments(null);

		Patient patientTwo = new Patient();
		patientTwo.setPhoneNumber("666 666 666");
		patientTwo.setAddress("Avenida Lendakari Aguirre");
		patientTwo.setAppointments(null);

		assertTrue(patientOne.equals(patientTwo));
	}

	@Test
	void testToString() {
		patient.setPhoneNumber("666 666 666");
		patient.setAddress("Avenida Lendakari Aguirre");
		patient.setAppointments(null);

		String sentence = "Patient [phoneNumber=" + "666 666 666" + ", address=" + "Avenida Lendakari Aguirre"
				+ ", appointments=" + null + "]";

		assertEquals(sentence, patient.toString());
	}
}
