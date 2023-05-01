package jUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.TimeSlotManager;
import model.pojos.TimeSlot;

class TimeSlotManagerTest {
	
	private final int COLUMN_NUMBER = 6;
	TimeSlotManager timeSlotManager = new TimeSlotManager();

	@Test
	void testSelectOne() {
		TimeSlot timeSlot = new TimeSlot();
		try {
			timeSlot = timeSlotManager.select(1);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		TimeSlot expected = new TimeSlot();
		expected.setId(1);
		expected.setStartTime(LocalTime.parse("08:00:00"));
		expected.setEndTime(LocalTime.parse("09:00:00"));

		assertEquals(timeSlot, expected);
	}
	
	@Test
	void testSelectArray() {
		ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
		try {
			timeSlots = (ArrayList<TimeSlot>) timeSlotManager.select();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == timeSlots) {
			fail("El metodo no devuelve ninguna franja");
		} else {
			assertEquals(COLUMN_NUMBER, timeSlots.size());
		}
	}
	
	@Test
	void testInsert() {
		TimeSlot expected = new TimeSlot();
		expected.setId(999);
		expected.setStartTime(LocalTime.parse("15:00:00"));
		expected.setEndTime(LocalTime.parse("16:00:00"));
		try {
			timeSlotManager.insert(expected);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		TimeSlot actual = null;
		try {
			actual = timeSlotManager.select(expected.getId());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(expected, actual);

	}
	
	@Test
	void testUpdate() {
		TimeSlot newTimeSlot = new TimeSlot();
		newTimeSlot.setId(666);
		newTimeSlot.setStartTime(LocalTime.parse("08:00:00"));
		newTimeSlot.setEndTime(LocalTime.parse("15:00:00"));
		try {
			timeSlotManager.insert(newTimeSlot);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setId(666);
		try {
			timeSlotManager.update(timeSlot);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//Expected
		TimeSlot expected = new TimeSlot();
		expected.setId(666);
		expected.setStartTime(LocalTime.parse("11:00:00"));
		expected.setEndTime(LocalTime.parse("15:00:00"));
		
		TimeSlot result = new TimeSlot();
		try {
			result = timeSlotManager.select(timeSlot.getId());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, result);
	}
	
	@Test
	void testDelete() {
		TimeSlot timeslot = new TimeSlot();
		timeslot.setId(777);
		timeslot.setStartTime(LocalTime.parse("15:00:00"));
		timeslot.setEndTime(LocalTime.parse("16:00:00"));
		try {
			timeSlotManager.insert(timeslot);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			timeSlotManager.delete(timeslot.getId());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			timeslot= timeSlotManager.select(timeslot.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(timeslot, null);
	}

}
