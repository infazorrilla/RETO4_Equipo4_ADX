package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

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
}
