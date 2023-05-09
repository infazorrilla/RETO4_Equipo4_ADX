package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import EDE.amaia.sprint2.DBUsers;

class DBUsersTest {

	@Test
	void testGetAllUsers() {
		DBUsers dBUsers = new DBUsers();
		ArrayList<String> users = dBUsers.getAllUsers();

		assertNotNull(users);
	}
}
