package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.pojos.Ambulatory;
import model.pojos.Doctor;
import manager.DoctorManager;

class DoctorManagerTest {

	DoctorManager doctorManager = new DoctorManager();

	@Test
	void testSelectInt() {
		// Instance of the Doctor class
		Doctor doctor = new Doctor();

		// We set the value of the query
		doctor.setDni("00000000D");
		try {
			// We select the the same value of the query
			doctor = doctorManager.select("00000000D");
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
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

		// User data
		expected.setDni("22222222D");
		expected.setName("Paloma");
		expected.setSurname("BORRAR");
		expected.setGender("mujer");
		Date date = null;
		String sDate = "2023-04-02";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		expected.setBirthDate(date);
		expected.setPassword("123");

		// Doctor data
		expected.setDni("22222222D");
		expected.setStaffNum(100);
		expected.setSalary(100);

		Ambulatory ambulatory = new Ambulatory();
		ambulatory.setId(1);
		expected.setAmbulatory(ambulatory);

		expected.setSpeciality("Pediatria");
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
		try {
			// We insert the comparative values
			actual = doctorManager.select("22222222D");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// We compares between the expected and comparative values
		assertEquals(expected.getDni(), actual.getDni());
	}

	@Test
	void testUpdateDoctor() {

		// We set the initial values
		Doctor doctor = new Doctor();
		// User data
		doctor.setDni("33333333D");
		doctor.setName("Paco");
		doctor.setSurname("BORRAR");
		doctor.setGender("hombre");
		Date date = null;
		String sDate = "2023-04-02";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		doctor.setBirthDate(date);
		doctor.setPassword("123");

		// Doctor data
		doctor.setDni("33333333D");
		doctor.setStaffNum(200);
		doctor.setSalary(100);

		Ambulatory ambulatory = new Ambulatory();
		ambulatory.setId(1);
		doctor.setAmbulatory(ambulatory);

		doctor.setSpeciality("Cardiología");
		doctor.setMir(true);

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
		doctor.setDni("33333333D");
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
		expected.setDni("33333333D");
		expected.setName("Paco");
		expected.setSurname("BORRAR");
		expected.setGender("hombre");
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		expected.setBirthDate(date);
		expected.setPassword("123");
		expected.setDni("33333333D");
		expected.setStaffNum(100);
		expected.setSalary(100);
		ambulatory.setId(1);
		expected.setAmbulatory(ambulatory);
		expected.setSpeciality("Pediatra");
		expected.setMir(true);

		assertEquals(doctor.getSpeciality(), expected.getSpeciality());

	}

	@Test
	void testDelete() {
		// Instance of the Doctor class
		Doctor doctor = new Doctor();

		// We set an int value in the instance
		doctor.setDni("22222222D");

		// We get the previous value and delete it
		try {
			doctorManager.delete(doctor.getDni());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// We get the previous value and select it
		// It doesn't get anything because we've eliminated it previously.
		try {
			doctor = doctorManager.select(doctor.getDni());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// We use AssertEquals to compare the state of the object with null
		assertEquals(doctor, null);
	}

}
