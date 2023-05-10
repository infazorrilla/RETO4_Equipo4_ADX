package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.AmbulatoryManager;
import model.pojos.Ambulatory;

class AmbulatoryManagerTest {

	AmbulatoryManager ambulatoryManager = new AmbulatoryManager();

	@Test
	void testSelectInt() {
		// Instance of the Doctor class
		Ambulatory ambulatory = new Ambulatory();

		// We set the value of the query
		ambulatory.setId(2);
		try {
			// We select the the same value of the query
			ambulatory = ambulatoryManager.select(2);
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
			ambulatory = (ArrayList<Ambulatory>) ambulatoryManager.select();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == ambulatory) {
			fail("El metodo no nada - Null");
		} else {
			// Compares the amount that we expected with the given
			assertEquals(5, ambulatory.size());
		}
	}

	@Test
	void testInsertAmbulatory() {
		// We set expected values for later comparison
		Ambulatory expected = new Ambulatory();
		expected.setId(15);
		expected.setName("Cruces");
		expected.setAddress("Cruces");
		expected.setPhoneNumber("945555520");

		try {
			// We insert the expected values
			ambulatoryManager.insert(expected);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// We set the comparative values
		Ambulatory actual = new Ambulatory();

		try {

			// We insert the comparative values
			actual = ambulatoryManager.select(15);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// We compares between the expected and comparative values
		assertEquals(expected, actual);
	}

	@Test
	void testUpdateAmbulatory() {
		// We set the initial values
		Ambulatory ambulatory = new Ambulatory();
		ambulatory.setId(11);
		ambulatory.setName("Cruces");
		ambulatory.setPhoneNumber("945555520");
		ambulatory.setAddress("Cruces");
		try {
			// We insert the initial values
			ambulatoryManager.insert(ambulatory);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// We set a new value
		ambulatory.setName("Cruces");
		ambulatory.setPhoneNumber("944444444");
		ambulatory.setAddress("Cruces");
		try {
			// We update the values
			ambulatoryManager.update(ambulatory);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// We set a comparative expected result
		Ambulatory expected = new Ambulatory();
		try {
			// We select the previous object
			expected = ambulatoryManager.select(11);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		assertEquals(ambulatory, expected);
	}

	@Test
	void testDelete() {

		// Instance of the Ambulatory class
		Ambulatory ambulatory = new Ambulatory();

		// We set an int value in the instance
		ambulatory.setId(11);

		// We get the previous value and delete it
		try {
			ambulatoryManager.delete(ambulatory.getId());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// We get the previous value and select it
		// It doesn't get anything because we've eliminated it previously.
		try {
			ambulatory = ambulatoryManager.select(ambulatory.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// We use AssertEquals to compare the state of the object with null
		assertEquals(ambulatory, null);
	}

}
