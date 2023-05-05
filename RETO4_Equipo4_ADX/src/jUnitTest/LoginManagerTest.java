package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.pojos.User;

class LoginManagerTest {

	@Test
	void testGetUserByDniAndPassword() {
		String userDNI = "00000000D";
		String pass = "pass";
		
		User user= loginManager.getUserByDniAndPassword(userDNI, pass);
		assertNull(user);
	}

	@Test
	void testIdentifyUserType() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectPatient() {
		fail("Not yet implemented");
	}

}
