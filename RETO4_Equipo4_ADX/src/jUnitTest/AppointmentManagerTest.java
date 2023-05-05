package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import manager.AppointmentManager;
import model.pojos.Ambulatory;
import model.pojos.Appointment;
import model.pojos.Doctor;
import model.pojos.Patient;
import model.pojos.Sanitarian;


class AppointmentManagerTest {

	private final int COLUMN_NUMBER = 3;
	AppointmentManager appointmentManager = new AppointmentManager();

	@Test
	void testSelectedOne() {
		Appointment appointment = new Appointment();
		try {
			appointment = appointmentManager.select(2);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Appointment expected = new Appointment();
		expected.setId(2);
		
		
		assertEquals(appointment.getId(), expected.getId());
	}

	@Test
	void testSelectArray() {
		ArrayList<Appointment> appointment = new ArrayList<Appointment>();
		try {
			appointment = (ArrayList<Appointment>) appointmentManager.select();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == appointment) {
			fail("El metodo no devuelve ninguna jornada");
		} else {
			assertEquals(COLUMN_NUMBER, appointment.size());
		}
	}
	@Test
	void testInsert() {
		Appointment expected = new Appointment();
		expected.setId(9);
		
		Ambulatory ambulatory = new Ambulatory();
		Sanitarian sanitarian = new Doctor();
		Patient patient = new Patient();
		patient.setDni("00000000A");
		sanitarian.setDni("11111111A");
		ambulatory.setId(1);
		expected.setPatient(patient);
		expected.setSanitarian(sanitarian);
		expected.setAmbulatory(ambulatory);
		
		
		try {
			appointmentManager.insert(expected);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Appointment actual = null;
		try {
			actual = appointmentManager.select(expected.getId());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(expected.getId(), actual.getId());
	}
	
//	@Test
//	void testUpdate() {
//		Appointment newAppointment = new Appointment();
//		
//		newAppointment.setId(9);
//		newAppointment.setPatient(null);
//		newAppointment.setSanitarian(null);
//		newAppointment.setAmbulatory(null);
//		try {
//			appointmentManager.insert(newAppointment);
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//		Appointment appointment = new Appointment();
//		appointment.setId(9);
//		try {
//			appointmentManager.update(appointment);
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//		Appointment expected = new Appointment();
//		expected.setId(10);
//		expected.setPatient(null);
//		expected.setSanitarian(null);
//		expected.setAmbulatory(null);
//		
//		Appointment result = new Appointment();
//		try {
//			result = appointmentManager.select(appointment.getId());
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		assertEquals(expected, result);
//	}
	
	@Test
	void testDelete() {
		Appointment appointment = new Appointment();

		appointment.setId(11);
		
		
		try {
			appointmentManager.insert(appointment);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			appointmentManager.delete(appointment.getId());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			appointment= appointmentManager.select(appointment.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(appointment, null);
	}
}
