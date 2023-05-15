package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import manager.PatientManager;
import model.pojos.Patient;

class AccessDBArrayListTest {
	private PatientManager patientManager = null;

	@Test
	void test() {

		if (null == patientManager) {
			patientManager = new PatientManager();
		}

		ArrayList<Patient> patients = null;

		try {
			patients = (ArrayList<Patient>) patientManager.select();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<Patient> patientsExpected = new ArrayList<>();

		Patient patient1 = new Patient();

		// User setters
		patient1.setDni("00000000A");
		patient1.setName("Marta");
		patient1.setSurname("Gonzalez");
		patient1.setGender("mujer");
		Date date = null;
		String sDate = "2023-04-02";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		patient1.setBirthDate(date);
		patient1.setPassword("123");

		// Patient setters
		patient1.setPhoneNumber("111111111");
		patient1.setAddress("asada");
		patient1.setBlocked(false);

		patientsExpected.add(patient1);

		Patient patient2 = new Patient();

		// User setters
		patient2.setDni("11111111B");
		patient2.setName("Lorena");
		patient2.setSurname("Gomez");
		patient2.setGender("mujer");
		Date date2 = null;
		String sDate2 = "2023-05-01";
		try {
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		patient2.setBirthDate(date2);
		patient2.setPassword("dfsaf");

		// Patient setters
		patient2.setPhoneNumber("222211115");
		patient2.setAddress("adasfsd");
		patient2.setBlocked(false);

		patientsExpected.add(patient2);

		// Check
		assertEquals(patients, patientsExpected);

	}
}
