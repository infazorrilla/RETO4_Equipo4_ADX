package elorrieta.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class AppointmentTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGettersAndSetters() {
		Appointment apointment = new Appointment();

		apointment.setId(1);
		assertEquals(apointment.getId(), 1);
		
		apointment.setHour(LocalTime.parse("11:00"));
		assertEquals(apointment.getHour(), "11:00");

		apointment.setType("Cita");
		assertEquals(apointment.getType(), "Cita");
		
		apointment.setSanitarians(null);
		assertEquals(apointment.getSanitarians(), null);
		
		apointment.setPatient(null);
		assertEquals(apointment.getPatient(), null);
	}
	
	@Test
	public void testAppointmentEquals() {
		Appointment apointmentOne = new Appointment();
		apointmentOne.setId(1);
		apointmentOne.setHour(LocalTime.parse("11:00"));
		apointmentOne.setType("Cita");
		apointmentOne.setSanitarians(null);
		apointmentOne.setPatient(null);

		Appointment apointmentTwo = new Appointment();
		apointmentTwo.setId(1);
		apointmentTwo.setHour(LocalTime.parse("11:00"));
		apointmentTwo.setType("Cita");
		apointmentTwo.setSanitarians(null);
		apointmentTwo.setPatient(null);
		
		assertTrue(apointmentOne.equals(apointmentTwo));
	}
	
	@Test
	public void testAppointmentToString() {
		Appointment apointment = new Appointment();
		apointment.setId(1);
		apointment.setHour(LocalTime.parse("11:00"));
		apointment.setType("Cita");
		apointment.setSanitarians(null);
		apointment.setPatient(null);
		
		String sentence = "Appointment [id=" + 1 + ", hour=" + "11:00" + ", type=" + "Cita" + ", sanitarians=" + null
				+ ", patient=" + null + "]";
		
		assertEquals(sentence, apointment.toString());		
	}

	@Test
	public void testHasCode() {
		Appointment apointment = new Appointment();
		apointment.setId(1);
		apointment.setHour(LocalTime.parse("11:00"));
		apointment.setType("Cita");
		apointment.setSanitarians(null);
		apointment.setPatient(null);
		
		assertNotNull(apointment.hashCode());
	}

}
