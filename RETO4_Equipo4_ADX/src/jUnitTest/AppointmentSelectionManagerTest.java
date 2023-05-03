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
			ambulatory = manager.selectAmbulatory("Ambulatorio 1");
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
		Ambulatory test=new Ambulatory();
		test.setId(1);
		try {
			ArrayList<Date> dates = manager.showAvailableDates("Enfermeria", test);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Date> expected = new ArrayList<Date>();
		String sDate = "2023-04-05";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		expected.add(null);
		
		
		
	}

}
