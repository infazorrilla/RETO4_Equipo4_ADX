package jUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.pojos.Appointment;


class AppointmentTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGettersAndSetters() {
		Appointment apointment = new Appointment();

		apointment.setId(1);
		assertEquals(apointment.getId(), 1);
		
		apointment.setSanitarians(null);
		assertEquals(apointment.getSanitarians(), null);
		
		apointment.setPatient(null);
		assertEquals(apointment.getPatient(), null);
		
		apointment.setTimeSlot(null);
		assertEquals(apointment.getTimeSlot(), null);
	}
	
	@Test
	public void testAppointmentEquals() {
		Appointment apointmentOne = new Appointment();
		apointmentOne.setId(1);
		apointmentOne.setSanitarians(null);
		apointmentOne.setPatient(null);
		apointmentOne.setTimeSlot(null);


		Appointment apointmentTwo = new Appointment();
		apointmentTwo.setId(1);
		apointmentTwo.setSanitarians(null);
		apointmentTwo.setPatient(null);
		apointmentTwo.setTimeSlot(null);

		
		assertTrue(apointmentOne.equals(apointmentTwo));
	}
	
	@Test
	public void testAppointmentToString() {
		Appointment apointment = new Appointment();
		apointment.setId(1);
		apointment.setSanitarians(null);
		apointment.setPatient(null);
		apointment.setTimeSlot(null);

		String sentence = "Appointment [id=" + 1 + ", sanitarians=" + null + ", patient=" + null + ", timeSlot="
				+ null + ", ambulatory=" + null + "]";
		
		assertEquals(sentence, apointment.toString());		
	}

	@Test
	public void testHasCode() {
		Appointment apointment = new Appointment();
		apointment.setId(1);
		apointment.setSanitarians(null);
		apointment.setPatient(null);
		apointment.setTimeSlot(null);
		
		assertNotNull(apointment.hashCode());
	}

}
