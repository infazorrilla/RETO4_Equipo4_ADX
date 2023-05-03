package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.Test;
import model.pojos.Patient;
import model.pojos.TimeSlot;
import manager.PatientManager;

class PatientManagerTest {

	private final int COLUMN_NUMBER = 1;
	PatientManager patientManager = new PatientManager();

	@Test
	void testSelectedOne() {
		Patient patient = new Patient();
		try {
			patient = patientManager.select("00000000A");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Patient expected = new Patient();
		expected.setDni("00000000A");
		expected.setPhoneNumber("333333333");
		expected.setAddress("1");
		Date date = null;
		String sDate = "2023-04-02";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		expected.setBirthDate(date);
		expected.setGender("mujer");
		expected.setName("a");
		expected.setPassword("beba");
		expected.setSurname("a");

		assertEquals(patient, expected);
	}

	@Test
	void testSelectArray() {
		ArrayList<Patient> patient = new ArrayList<Patient>();
		try {
			patient = (ArrayList<Patient>) patientManager.select();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == patient) {
			fail("El metodo no devuelve ninguna jornada");
		} else {
			assertEquals(COLUMN_NUMBER, patient.size());
		}
	}

	@Test
	void testInsert() {
		Patient expected = new Patient();
		expected.setDni("44444444A");
		expected.setAddress("1");
		expected.setPhoneNumber("123456789");

		try {
			patientManager.insert(expected);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Patient actual = null;

		try {
			actual = patientManager.select(expected.getDni());
		} catch (SQLException sqle) {

			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		assertEquals(expected.getDni(), actual.getDni());
	}

	@Test
	void testUpdate() {
		Patient newPatient = new Patient();
		newPatient.setDni("99999999A");
		newPatient.setPhoneNumber("888888888");
		newPatient.setAddress("2");
		Date date = null;
		String sDate = "2023-04-02";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newPatient.setBirthDate(date);
		newPatient.setGender("hombre");
		newPatient.setName("b");
		newPatient.setPassword("bebo");
		newPatient.setSurname("b");
		try {
			patientManager.insert(newPatient);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Patient patient = new Patient();
		patient.setDni("99999999A");
		try {
			patientManager.update(patient);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Patient expected = new Patient();
		expected.setDni("99999999A");
		expected.setPhoneNumber("777666555");
		expected.setAddress("2");
		Date dateExpected = null;
		String sDateExpected = "2023-04-02";
		try {
			dateExpected = new SimpleDateFormat("yyyy-MM-dd").parse(sDateExpected);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		expected.setBirthDate(dateExpected);
		expected.setGender("hombre");
		expected.setName("b");
		expected.setPassword("bebo");
		expected.setSurname("b");

		Patient result = new Patient();
		try {
			result = patientManager.select(patient.getDni());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(expected.getPhoneNumber(), result.getPhoneNumber());
	}

	@Test
	void testDelete() {
		Patient patient = new Patient();

		patient.setDni("77777777B");
		patient.setPhoneNumber("123456789");
		patient.setAddress("2");
		Date date = null;
		String sDate = "2023-04-02";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		patient.setBirthDate(date);
		patient.setGender("hombre");
		patient.setName("b");
		patient.setPassword("bebo");
		patient.setSurname("b");

		try {
			patientManager.insert(patient);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			patientManager.delete(patient.getDni());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			patient = patientManager.select(patient.getDni());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(patient, null);
	}

}
