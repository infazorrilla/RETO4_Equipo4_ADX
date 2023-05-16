package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import manager.LoginManager;
import model.pojos.*;

class LoginManagerTest {

	@Test
	void testChekingLogin() {

		// We set the values
		String userDNI = "00000000D";
		String pass = "123";

		// Instantiate the manager class
		LoginManager loginManager = new LoginManager();
		// We get the values
		String user = loginManager.chekingLogin(userDNI, pass);

		// We check that the method is not empty
		assertNotNull(user);
	}

	@Test
	void testCheckUserType() {

		// We initialized the Pojos
		User doctor = null;
		User nurse = null;
		User patient = null;

		// Instantiated the Login Manager
		LoginManager loginManager = new LoginManager();

		try {
			// We set the values in the Method
			doctor = loginManager.checkUserType("00000000D");
			nurse = loginManager.checkUserType("00000000E");
			patient = loginManager.checkUserType("00000000P");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// We instantiated a comparative objects
		Doctor expectedDoctor = new Doctor();
		Nurse expectedNurse = new Nurse();
		Patient expectedPatient = new Patient();

		// We compare all the values
		assertEquals(doctor.getClass(), expectedDoctor.getClass());
		assertEquals(nurse.getClass(), expectedNurse.getClass());
		assertEquals(patient.getClass(), expectedPatient.getClass());
	}

}
