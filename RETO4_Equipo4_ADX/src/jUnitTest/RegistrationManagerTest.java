package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.RegistrationManager;
import model.pojos.Ambulatory;

class RegistrationManagerTest {

	private final int COLUMN_NUMBER = 6;
	RegistrationManager registrationManager = new RegistrationManager();
	@Test
	void testSelectInt() {
		// Instance of the Doctor class
		Ambulatory ambulatory = new Ambulatory();

		// We set the value of the query
		ambulatory.setName("Cruces");
		try {
			// We select the the same value of the query
			ambulatory = registrationManager.select("Cruces");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// We check that the method has obtained values
		assertNotNull(ambulatory);
	}

	@Test
	void testSelectAll() {
		// Instantiate the ArrayList
		ArrayList<Ambulatory> ambulatory = new ArrayList<Ambulatory>();

		try {
			// We initialize the method that selects all
			ambulatory = (ArrayList<Ambulatory>) registrationManager.select();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == ambulatory) {
			fail("El metodo no nada - Null");
		} else {
			// Compares the amount that we expected with the given
			assertEquals(COLUMN_NUMBER, ambulatory.size());
		}
	}

}
