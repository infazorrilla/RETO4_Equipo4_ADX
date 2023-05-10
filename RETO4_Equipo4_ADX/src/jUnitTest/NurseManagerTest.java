package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import manager.NurseManager;
import model.pojos.Ambulatory;
import model.pojos.Nurse;

class NurseManagerTest {

	NurseManager nurseManager = new NurseManager();

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

		// User data
		expected.setDni("22222222E");
		expected.setName("Raquel");
		expected.setSurname("BORRAR");
		expected.setGender("mujer");
		Date date = null;
		String sDate = "2023-04-02";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		expected.setBirthDate(date);
		expected.setPassword("123");

		// Doctor data
		expected.setDni("22222222E");
		expected.setStaffNum(300);
		expected.setSalary(100);

		Ambulatory ambulatory = new Ambulatory();
		ambulatory.setId(1);
		expected.setAmbulatory(ambulatory);

		expected.setCategory("Familiar");
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
		try {
			// We insert the comparative values
			actual = nurseManager.select("22222222E");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// We compares between the expected and comparative values
		assertEquals(expected.getDni(), actual.getDni());
	}

	@Test
	void testUpdateNurse() {
		// We set the initial values
		Nurse nurse = new Nurse();
		// User data
		nurse.setDni("33333333E");
		nurse.setName("Roberto");
		nurse.setSurname("BORRAR");
		nurse.setGender("hombre");
		Date date = null;
		String sDate = "2023-04-02";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		nurse.setBirthDate(date);
		nurse.setPassword("123");

		// Doctor data
		nurse.setDni("33333333E");
		nurse.setStaffNum(400);
		nurse.setSalary(100);

		Ambulatory ambulatory = new Ambulatory();
		ambulatory.setId(1);
		nurse.setAmbulatory(ambulatory);

		nurse.setCategory("Cardiología");
		nurse.setEir(true);

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
		nurse.setDni("33333333E");
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
		expected.setDni("33333333E");
		expected.setName("Roberto");
		expected.setSurname("BORRAR");
		expected.setGender("hombre");
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		expected.setBirthDate(date);
		expected.setPassword("123");
		expected.setDni("33333333E");
		expected.setStaffNum(400);
		expected.setSalary(100);
		ambulatory.setId(1);
		expected.setAmbulatory(ambulatory);
		expected.setCategory("Familiar");
		expected.setEir(true);

		assertEquals(nurse.getCategory(), expected.getCategory());

	}

	@Test
	void testDelete() {
		// Instance of the Nurse class
		Nurse nurse = new Nurse();

		// We set an int value in the instance
		nurse.setDni("22222222E");

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

}
