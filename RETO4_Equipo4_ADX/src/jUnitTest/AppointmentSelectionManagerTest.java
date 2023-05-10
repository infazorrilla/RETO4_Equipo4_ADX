package jUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.AppointmentSelectionManager;
import model.pojos.Ambulatory;
import model.pojos.Appointment;
import model.pojos.Doctor;
import model.pojos.Patient;
import model.pojos.Sanitarian;
import model.pojos.TimeSlot;
import model.pojos.WorkingDay;

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
	void testShowAvailableDates() {
		Ambulatory test = new Ambulatory();
		test.setId(1);
		ArrayList<String> dates = new ArrayList<String>();
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

		assertEquals(dates.get(0), sDate);
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
	void testShowAvailableTimeSlots() {
		Sanitarian sanitarian = new Doctor();
		sanitarian.setDni("11111111A");

		ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		try {
			timeSlots = manager.showAvailableTimeSlots(sanitarian, "2023-04-05");
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

	@Test
	void testSelectSanitarian() {
		Sanitarian test = new Doctor();
		test.setName("b");
		test.setSurname("b");
		try {
			test = manager.selectSanitarian(test.getName(), test.getSurname());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("11111111A", test.getDni());
	}

	@Test
	void testSelectAppointment() {
		Patient patient = new Patient();
		patient.setDni("00000000A");
		Sanitarian sanitarian = new Doctor();
		sanitarian.setDni("11111111A");
		Ambulatory ambulatory = new Ambulatory();
		ambulatory.setId(1);

		Appointment test = new Appointment();
		try {
			test = manager.selectAppointment(patient, sanitarian, ambulatory);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(4, test.getId());
	}
	
	@Test
	void testWorkingDay() {
		WorkingDay test = new WorkingDay();
		try {
			test = manager.selectWorkingDay("2023-04-05");
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
	void testTimeSlot() {
		TimeSlot test = new TimeSlot();
		
		try {
			test = manager.selectTimeSlot("08:00");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(1, test.getId());
	}

}
