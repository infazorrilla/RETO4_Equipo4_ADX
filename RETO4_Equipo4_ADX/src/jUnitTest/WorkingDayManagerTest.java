package jUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import manager.WorkingDayManager;
import model.pojos.WorkingDay;

class WorkingDayManagerTest {

	private final int COLUMN_NUMBER = 4;
	WorkingDayManager workingDayManager = new WorkingDayManager();

	@Test
	void testSelectOne() {
		WorkingDay workingDay = new WorkingDay();
		try {
			workingDay = workingDayManager.select(1);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		WorkingDay expected = new WorkingDay();
		expected.setId(1);
		Date date = null;
		String sDate = "2023-04-05";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		expected.setDate(date);
		expected.setStartTime(LocalTime.parse("08:00"));
		expected.setEndTime(LocalTime.parse("15:00"));

		assertEquals(workingDay, expected);
	}
	

	@Test
	void testSelectArray() {
		ArrayList<WorkingDay> workingDays = new ArrayList<WorkingDay>();
		try {
			workingDays = (ArrayList<WorkingDay>) workingDayManager.select();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == workingDays) {
			fail("El metodo no devuelve ninguna jornada");
		} else {
			assertEquals(COLUMN_NUMBER, workingDays.size());
		}
	}

	@Test
	void testInsert() {
		WorkingDay expected = new WorkingDay();
		expected.setId(999);
		Date date = null;
		String sDate = "2023-07-07";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		expected.setDate(date);
		expected.setStartTime(LocalTime.parse("08:00:00"));
		expected.setEndTime(LocalTime.parse("15:00:00"));
		try {
			workingDayManager.insert(expected);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		WorkingDay actual = null;
		try {
			actual = workingDayManager.select(expected.getId());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(expected, actual);
	}
	
	@Test
	void testUpdate() {
		WorkingDay newWorkingDay = new WorkingDay();
		newWorkingDay.setId(666);
		Date date = null;
		String sDate = "2023-07-07";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newWorkingDay.setDate(date);
		newWorkingDay.setStartTime(LocalTime.parse("08:00:00"));
		newWorkingDay.setEndTime(LocalTime.parse("15:00:00"));
		try {
			workingDayManager.insert(newWorkingDay);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		WorkingDay workingDay = new WorkingDay();
		workingDay.setId(666);
		try {
			workingDayManager.update(workingDay);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//Expected
		WorkingDay expected = new WorkingDay();
		expected.setId(666);
		Date dateExpected = null;
		String sDateExpected = "2023-07-07";
		try {
			dateExpected = new SimpleDateFormat("yyyy-MM-dd").parse(sDateExpected);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		expected.setDate(dateExpected);
		expected.setStartTime(LocalTime.parse("11:00:00"));
		expected.setEndTime(LocalTime.parse("15:00:00"));
		expected.setSanitarians(null);
		expected.setTimeSlots(null);
		
		WorkingDay result = new WorkingDay();
		try {
			result = workingDayManager.select(workingDay.getId());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long milliseconds= result.getDate().getTime();
        Date dateResult = new Date(milliseconds);
        result.setDate(dateResult);		
		
		assertEquals(expected, result);
	}
	



	
	@Test
	void testDelete() {
		WorkingDay workingDay = new WorkingDay();
		workingDay.setId(777);
		Date date = null;
		String sDate = "2023-07-07";
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		workingDay.setDate(date);
		workingDay.setStartTime(LocalTime.parse("08:00:00"));
		workingDay.setEndTime(LocalTime.parse("15:00:00"));
		workingDay.setSanitarians(null);
		workingDay.setTimeSlots(null);
		try {
			workingDayManager.insert(workingDay);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			workingDayManager.delete(workingDay.getId());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			workingDay= workingDayManager.select(workingDay.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(workingDay, null);
	}

}
