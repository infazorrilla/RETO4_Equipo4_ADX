package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.pojos.Ambulatory;
import model.pojos.Appointment;
import model.pojos.Doctor;
import model.pojos.Patient;
import manager.DoctorManager;

class DoctorManagerTest {

	DoctorManager doctorManager = new DoctorManager();

	@Test
	void testDelete() {
		// Instance of the Doctor class
		Doctor doctor = new Doctor();

		// We set an int value in the instance
		doctor.setStaffNum(4);

		// We insert the object in the manager
		try {
			doctorManager.insert(doctor);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// We get the previous value and delete it
		try {
			doctorManager.delete(doctor.getStaffNum());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// We get the previous value and select it
		// It doesn't get anything because we've eliminated it previously.
		try {
			doctor = doctorManager.select(doctor.getStaffNum());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// We use AssertEquals to compare the state of the object with null
		assertEquals(doctor, null);
	}

	@Test
	void testSelectInt() {
		// Instance of the Doctor class
		Doctor doctor = new Doctor();

		// We set the value of the query
		doctor.setStaffNum(3);
		try {
			// We select the the same value of the query
			doctor = doctorManager.select(3);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// We check that the method has obtained values
		assertNotNull(doctor);
	}

	@Test
	void testSelectAll() {
		// Instantiate the ArrayList
		ArrayList<Doctor> doctor = new ArrayList<Doctor>();

		try {
			// We initialize the method that selects all
			doctor = (ArrayList<Doctor>) doctorManager.select();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == doctor) {
			fail("El metodo no nada - Null");
		} else {
			// Compares the amount that we expected with the given
			assertEquals(2, doctor.size());
		}
	}

	@Test
	void testInsertDoctor() {
		// We set expected values for later comparison
		Doctor expected = new Doctor();
		expected.setDni("00000000D");
		expected.setStaffNum(0);
		expected.setSalary(0);
		expected.setType(null);
		expected.setSpeciality(null);
		expected.setMir(false);

		try {
			// We insert the expected values
			doctorManager.insert(expected);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// We set the comparative values
		Doctor actual = new Doctor();
		actual.setDni("00000000D");
		actual.setStaffNum(0);
		actual.setSalary(0);
		actual.setType(null);
		actual.setSpeciality(null);
		actual.setMir(false);
		try {

			// We insert the comparative values
			doctorManager.insert(actual);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// We compares between the expected and comparative values
		assertEquals(expected, actual);
	}

	@Test
	void testUpdateDoctor() {

		// We set the initial values
		Doctor doctor = new Doctor();
		doctor.setDni("00000000D");
		doctor.setStaffNum(0);
		doctor.setSalary(0);
		doctor.setType(null);
		doctor.setSpeciality(null);
		doctor.setMir(false);

		try {
			// We insert the initial values
			doctorManager.insert(doctor);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// We set a new value
		doctor.setSpeciality("Pediatra");
		try {
			// We update the values
			doctorManager.update(doctor);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// We set a comparative expected result
		Doctor expected = new Doctor();
		expected.setDni("00000000D");
		expected.setStaffNum(0);
		expected.setSalary(0);
		expected.setType(null);
		expected.setSpeciality("Pediatra");
		expected.setMir(false);

		assertEquals(doctor, expected);

	}

}
