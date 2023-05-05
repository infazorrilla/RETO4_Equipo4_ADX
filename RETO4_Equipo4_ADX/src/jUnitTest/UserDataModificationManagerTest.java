package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import manager.DoctorManager;
import manager.NurseManager;
import manager.PatientManager;
import manager.UserDataModificationManager;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import model.pojos.User;

class UserDataModificationManagerTest {

	UserDataModificationManager manager = new UserDataModificationManager();

	@Test
	void testidentifyUserType() {
		// Case 1
		User testOne = null;
		try {
			testOne = manager.identifyUserType("00000000A");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Patient expectedOne = new Patient();

		assertEquals(testOne.getClass(), expectedOne.getClass());

		// Case 2
		User testTwo = null;
		try {
			testTwo = manager.identifyUserType("11111111A");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Doctor expectedTwo = new Doctor();

		assertEquals(testTwo.getClass(), expectedTwo.getClass());

		// Case 3
		User testThree = null;
		try {
			testThree = manager.identifyUserType("22222222A");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Nurse expectedThree = new Nurse();

		assertEquals(testThree.getClass(), expectedThree.getClass());

	}

	@Test
	void testEnableModifyPatientOkButton() {
		// Case 1
		String passwordOne = "";
		String addressOne = "";
		String phoneNumberOne = "";

		assertEquals(false, manager.enableModifyPatientOkButton(passwordOne, addressOne, phoneNumberOne));

		// Case 2
		String passwordTwo = "true expected";
		String addressTwo = "";
		String phoneNumberTwo = "";

		assertEquals(true, manager.enableModifyPatientOkButton(passwordTwo, addressTwo, phoneNumberTwo));

	}

	@Test
	void testEnableModifySanitarianOkButton() {
		// Case 1
		String passwordOne = "";
		assertEquals(false, manager.enableModifySanitarianOkButton(passwordOne));

		// Case 2
		String passwordTwo = "true expected";

		assertEquals(true, manager.enableModifySanitarianOkButton(passwordTwo));
	}

	@Test
	void testIsPhoneNumber() {
		// Case 1
		String phoneOne = "1";
		assertEquals(false, manager.isPhoneNumber(phoneOne));

		// Case 2
		String phoneTwo = "999999999";
		assertEquals(true, manager.isPhoneNumber(phoneTwo));
	}

	@Test
	void testUpdatePatientAddress() {
		Patient patient = new Patient();
		patient.setDni("00000000C");
		patient.setPhoneNumber("111111111");
		patient.setName("test");
		patient.setSurname("test");
		patient.setBirthDate(new Date());
		patient.setPassword("test");
		patient.setGender("mujer");
		patient.setAddress("test");

		PatientManager patientManager = new PatientManager();
		Patient actual = new Patient();
		try {
			patientManager.insert(patient);
			manager.updatePatientAddress(patient, "updated");
			actual = patientManager.select("00000000C");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("updated", actual.getAddress());
	}
	
	@Test
	void testUpdatePatientPhoneNumber() {
		Patient patient = new Patient();
		patient.setDni("11111111C");
		patient.setPhoneNumber("111111111");
		patient.setName("test");
		patient.setSurname("test");
		patient.setBirthDate(new Date());
		patient.setPassword("test");
		patient.setGender("mujer");
		patient.setAddress("test");

		PatientManager patientManager = new PatientManager();
		Patient actual = new Patient();
		try {
			patientManager.insert(patient);
			manager.updatePatientPhoneNumber(patient, "999999999");
			actual = patientManager.select("11111111C");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("999999999", actual.getPhoneNumber());
	}
	
	
	@Test
	void testUpdatePatientPassword() {
		Patient patient = new Patient();
		patient.setDni("11111111C");
		patient.setPhoneNumber("111111111");
		patient.setName("test");
		patient.setSurname("test");
		patient.setBirthDate(new Date());
		patient.setPassword("test");
		patient.setGender("mujer");
		patient.setAddress("test");

		PatientManager patientManager = new PatientManager();
		Patient actual = new Patient();
		try {
			patientManager.insert(patient);
			manager.updatePatientPassword(patient, "updated");
			actual = patientManager.select("11111111C");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("updated", actual.getPassword());
	}
	
	//A partir de aquí son gestores de POJOs que estaban mal hechos, así que no he hecho JUnit
	
	@Test
	//No funciona doctorManager.insert(String dni)
	void testUpdateDoctorPassword() {
		Doctor doctor= new Doctor();
		doctor.setDni("11111111D");
		doctor.setName("test");
		doctor.setSurname("test");
		doctor.setBirthDate(new Date());
		doctor.setPassword("test");
		doctor.setGender("mujer");
		doctor.setSalary(21313);
		doctor.setStaffNum(21);
		doctor.setType("Medicina");
		
		Doctor actual = new Doctor();
		
		DoctorManager doctorManager = new DoctorManager();
		try {
			//No funciona
			doctorManager.insert(doctor);
			manager.updateDoctorPassword(doctor, "updated");
			actual = manager.selectDoctor("11111111D");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("updated", actual.getPassword());
	}
	
	@Test
	//No funciona nurseManager.insert(String dni)
	void testUpdateNursePassword() {
		Nurse nurse= new Nurse();
		nurse.setDni("11111111E");
		nurse.setName("test");
		nurse.setSurname("test");
		nurse.setBirthDate(new Date());
		nurse.setPassword("test");
		nurse.setGender("mujer");
		nurse.setSalary(21313);
		nurse.setStaffNum(21);
		nurse.setType("Medicina");
		
		Nurse actual = new Nurse();
		
		NurseManager nurseManager = new NurseManager();
		try {
			//No funciona
			nurseManager.insert(nurse);
			manager.updateNursePassword(nurse, "updated");
			actual = manager.selectNurse("11111111E");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("updated", actual.getPassword());
	}
	
	
	
	

	
}
