package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.NurseManager;
import model.pojos.Nurse;

class NurseManagerTest {

	NurseManager nurseManager = new NurseManager();

	@Test
	void testDelete() {
		// Instance of the Nurse class
		Nurse nurse = new Nurse();

		// We set an int value in the instance
		nurse.setDni("22222222E");

		// We insert the object in the manager
		try {
			nurseManager.insert(nurse);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// We get the previous value and delete it
		try {
			nurseManager.delete(nurse.getDni());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// We get the previous value and select it
		// It doesn't get anything because we've eliminated it previously.
		try {
			nurse = nurseManager.select(nurse.getDni());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// We use AssertEquals to compare the state of the object with null
		assertEquals(nurse, null);
	}

	@Test
	void testSelectInt() {
		// Instance of the Doctor class
		Nurse nurse = new Nurse();

		// We set the value of the query
		nurse.setDni("00000000E");
		try {
			// We select the the same value of the query
			nurse = nurseManager.select("00000000E");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// We check that the method has obtained values
		assertNotNull(nurse);
	}

	@Test
	void testSelectAll() {
		// Instantiate the ArrayList
		ArrayList<Nurse> nurse = new ArrayList<Nurse>();

		try {
			// We initialize the method that selects all
			nurse = (ArrayList<Nurse>) nurseManager.select();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == nurse) {
			fail("El metodo no nada - Null");
		} else {
			// Compares the amount that we expected with the given
			assertEquals(2, nurse.size());
		}
	}

	@Test
	void testInsertNurse() {
		// We set expected values for later comparison
		Nurse expected = new Nurse();
		expected.setDni("00000000D");
		expected.setStaffNum(0);
		expected.setSalary(0);
		expected.setType(null);
		expected.setCategory(null);
		expected.setEir(false);

		try {
			// We insert the expected values
			nurseManager.insert(expected);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// We set the comparative values
		Nurse actual = new Nurse();
		actual.setDni("00000000D");
		actual.setStaffNum(0);
		actual.setSalary(0);
		actual.setType(null);
		actual.setCategory(null);
		actual.setEir(false);
		try {

			// We insert the comparative values
			nurseManager.insert(actual);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// We compares between the expected and comparative values
		assertEquals(expected, actual);
	}

	@Test
	void testUpdateNurse() {
		// We set the initial values
		Nurse nurse = new Nurse();
		nurse.setDni("00000000D");
		nurse.setStaffNum(0);
		nurse.setSalary(0);
		nurse.setType(null);
		nurse.setCategory(null);
		nurse.setEir(false);

		try {
			// We insert the initial values
			nurseManager.insert(nurse);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// We set a new value
		nurse.setCategory("Familiar");
		try {
			// We update the values
			nurseManager.update(nurse);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// We set a comparative expected result
		Nurse expected = new Nurse();
		expected.setDni("00000000D");
		expected.setStaffNum(0);
		expected.setSalary(0);
		expected.setType(null);
		expected.setCategory("Familiar");
		expected.setEir(false);

		assertEquals(nurse, expected);

	}

}
