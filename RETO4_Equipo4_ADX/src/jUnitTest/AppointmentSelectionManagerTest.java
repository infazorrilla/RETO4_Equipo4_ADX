package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import manager.AppointmentSelectionManager;
import model.pojos.Ambulatory;
import model.pojos.Sanitarian;

class AppointmentSelectionManagerTest {

	AppointmentSelectionManager manager = new AppointmentSelectionManager();

	@Test
	void testSelectAmbulatoryNames() {
		ArrayList<String> ambulatories = null;
		try {
			ambulatories = manager.selectAmbulatoryNames();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(5, ambulatories.size());
	}

	@Test
	void testSelectAmbulatories() {
		Ambulatory ambulatory = null;
		try {
			ambulatory = manager.selectAmbulatory("a");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, ambulatory.getId());
	}

	@Test
	void testShowAvailableDates() {
		Ambulatory test = new Ambulatory();
		test.setId(1);
		ArrayList<Date> dates = new ArrayList<Date>();
		try {
			dates = manager.showAvailableDates("Enfermeria", test);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sDate = "2023-04-05";
		Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertEquals(dates.get(0), date);
	}

	@Test
	void testSelectAmbulatory() {
		Ambulatory test = new Ambulatory();
		try {
			test = manager.selectAmbulatory("a");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, test.getId());
	}

	@Test
	void testShowAvailableSanitarianByDate() {
		ArrayList<Sanitarian> test = new ArrayList<Sanitarian>();
		try {
			test = manager.showAvailableSanitarianByDate("Enfermeria", "2023-04-05");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(2, test.size());
	}

	// TODO
	// Arreglar
	@Test
	void testShowAvailableTimeSlots() {
		ArrayList<Integer> timeSlots = new ArrayList<Integer>();
		try {
			timeSlots = manager.showAvailableTimeSlots("11111111A", "2023-04-05");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(3);
		expected.add(4);
		expected.add(5);
		expected.add(6);

		assertEquals(expected.size(), timeSlots.size());
	}

}
