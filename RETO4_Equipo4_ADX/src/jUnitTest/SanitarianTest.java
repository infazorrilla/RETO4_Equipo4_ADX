package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import model.pojos.Appointment;
import model.pojos.Sanitarian;

class SanitarianTest {

	private Sanitarian sanitarian = null;

	public SanitarianTest() {
		sanitarian = new Sanitarian();
	}

	@Test
	void testGettersAndSetters() {

		// STAFF NUMBER //
		sanitarian.setStaffNum(0);
		assertEquals(sanitarian.getStaffNum(), 0);

		// SALARY //
		sanitarian.setSalary(0);
		assertEquals(sanitarian.getSalary(), 0);

		// TYPE //
		sanitarian.setType("Type");
		assertEquals(sanitarian.getType(), "Type");

		// APPOINTMENT //
		Appointment apointment = new Appointment();
		apointment.setId(2);
		apointment.setSanitarians(null);
		apointment.setPatient(null);
		assertNotNull(sanitarian.getAppointments());
	}

	@Test
	void testHashCode() {
		sanitarian.setStaffNum(0);
		sanitarian.setSalary(0);
		sanitarian.setType(null);
		sanitarian.setAppointments(null);

		assertNotNull(sanitarian.hashCode());
	}

	@Test
	void testEquals() {
		Sanitarian sanitarianOne = new Sanitarian();
		sanitarianOne.setStaffNum(0);
		sanitarianOne.setSalary(0);
		sanitarianOne.setType(null);
		sanitarianOne.setAppointments(null);

		Sanitarian sanitarianTwo = new Sanitarian();
		sanitarianTwo.setStaffNum(0);
		sanitarianTwo.setSalary(0);
		sanitarianTwo.setType(null);
		sanitarianTwo.setAppointments(null);

		assertTrue(sanitarianOne.equals(sanitarianTwo));
	}

	@Test
	void testToString() {
		sanitarian.setStaffNum(0);
		sanitarian.setSalary(0);
		sanitarian.setType(null);
		sanitarian.setAppointments(null);

		String sentence = "Sanitarian [staffNum=" + 0 + ", salary=" + 0 + ", type=" + null + ", appointments=" + null
				+ "]";

		assertEquals(sentence, sanitarian.toString());
	}

}
