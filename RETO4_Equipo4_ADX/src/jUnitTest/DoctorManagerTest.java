package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.pojos.Appointment;
import model.pojos.Doctor;
import model.pojos.Patient;
import manager.DoctorManager;

class DoctorManagerTest {

	DoctorManager doctorManager = new DoctorManager();

	@Test
	void testDelete() {
		Doctor doctor = new Doctor();

		doctor.setStaffNum(11);

		try {
			doctorManager.insert(doctor);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			doctorManager.delete(doctor.getStaffNum());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			doctor = doctorManager.select(doctor.getStaffNum());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(doctor, null);
	}

	@Test
	void testSelectInt() {
		Doctor doctor = new Doctor();

		try {
			doctor = doctorManager.select(1);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Doctor expected = new Doctor();
		expected.setStaffNum(0);

		assertEquals(doctor.getStaffNum(), expected.getStaffNum());
	}

	@Test
	void testSelect() {
		// Instantiate the ArrayList
		ArrayList<Doctor> appointment = new ArrayList<Doctor>();

		try {
			appointment = (ArrayList<Doctor>) doctorManager.select();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == appointment) {
			fail("El metodo no devuelve ninguna jornada");
		} else {
			assertEquals(4, appointment.size());
		}
	}

	@Test
	void testInsertDoctor() {
		Doctor expected = new Doctor();
		expected.setDni("00000000D");
		expected.setStaffNum(0);
		expected.setSalary(0);
		expected.setType(null);
		expected.setSpeciality(null);
		expected.setMir(false);

		try {
			doctorManager.insert(expected);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Doctor actual = new Doctor();
		actual.setDni("00000000D");
		actual.setStaffNum(0);
		actual.setSalary(0);
		actual.setType(null);
		actual.setSpeciality(null);
		actual.setMir(false);
		try {
			doctorManager.insert(actual);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(expected.getDni(), actual.getDni());
	}

	@Test
	void testUpdateDoctor() {

	void testUpdate() {
		Doctor newDoctor = new Doctor();

		newDoctor.setSpeciality(null);
		try {
			doctorManager.insert(newDoctor);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Doctor doctor = new Doctor();
		doctor.setSpeciality(null);
//			try {
//				appointmentManager.update(appointment);
//			} catch (SQLException sqle) {
//				sqle.printStackTrace();
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
		//
//			Appointment expected = new Appointment();
//			expected.setId(10);
//			expected.setPatient(null);
//			expected.setSanitarian(null);
//			expected.setAmbulatory(null);
//			
//			Appointment result = new Appointment();
//			try {
//				result = appointmentManager.select(appointment.getId());
//			} catch (SQLException sqle) {
//				sqle.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		//
//			assertEquals(expected, result);

	}

}
