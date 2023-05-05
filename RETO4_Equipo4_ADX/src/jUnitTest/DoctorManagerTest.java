package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.pojos.Appointment;
import model.pojos.Doctor;
import manager.DoctorManager;

class DoctorManagerTest {

	DoctorManager doctorManager = new DoctorManager();
	
	@Test
	void testDelete() {
		fail("Not yet implemented");
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

		Doctor actual = null;
		try {
			actual = doctorManager.insert(actual);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(expected.getDni(), actual.getDni());
	}

	@Test
	void testUpdateDoctor() {
		fail("Not yet implemented");
	}

}
