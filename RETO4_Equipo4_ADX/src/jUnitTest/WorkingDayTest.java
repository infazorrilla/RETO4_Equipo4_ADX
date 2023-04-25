package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import model.pojos.WorkingDay;



class WorkingDayTest {

	@Test
	void testGettersAndSetters() {
		WorkingDay workingDay = new WorkingDay();

		workingDay.setId(1);
		assertEquals(workingDay.getId(), 1);
		
		workingDay.setWeekDay("Lunes");
		assertEquals(workingDay.getWeekDay(), "Lunes");

		workingDay.setStartTime(LocalTime.parse("15:30"));
		LocalTime expectedStartTime = LocalTime.parse("15:30");
		assertEquals(workingDay.getStartTime(), expectedStartTime);
		
		workingDay.setEndTime(LocalTime.parse("18:30"));
		LocalTime expectedEndTime = LocalTime.parse("18:30");
		assertEquals(workingDay.getEndTime(), expectedEndTime);
		
		workingDay.setTimeSlots(null);
		assertEquals(workingDay.getTimeSlots(), null);
		
		workingDay.setSanitarian(null);
		assertEquals(workingDay.getSanitarian(), null);
	}
	
	@Test
	public void testAppointmentEquals() {
		WorkingDay workingDayOne = new WorkingDay();
		workingDayOne.setId(1);
		workingDayOne.setWeekDay("Lunes");
		workingDayOne.setStartTime(LocalTime.parse("15:30"));
		workingDayOne.setEndTime(LocalTime.parse("18:30"));
		workingDayOne.setTimeSlots(null);
		workingDayOne.setSanitarian(null);

		WorkingDay workingDayTwo = new WorkingDay();
		workingDayTwo.setId(1);
		workingDayTwo.setWeekDay("Lunes");
		workingDayTwo.setStartTime(LocalTime.parse("15:30"));
		workingDayTwo.setEndTime(LocalTime.parse("18:30"));
		workingDayTwo.setTimeSlots(null);
		workingDayTwo.setSanitarian(null);
		
		assertTrue(workingDayOne.equals(workingDayTwo));
	}
	
	@Test
	public void testWorkingDayToString() {
		WorkingDay workingDay = new WorkingDay();
		workingDay.setId(1);
		workingDay.setWeekDay("Lunes");
		workingDay.setStartTime(LocalTime.parse("15:30"));
		workingDay.setEndTime(LocalTime.parse("18:30"));
		workingDay.setTimeSlots(null);
		workingDay.setSanitarian(null);
		
		String sentence = "WorkingDay [id=" + 1 + ", weekDay=" + "Lunes" + ", startTime=" + "15:30" + ", endTime=" + "18:30"
				+ ", timeSlots=" + null + ", sanitarian=" + null + "]";
		
		assertEquals(sentence, workingDay.toString());		
	}

	@Test
	public void testHasCode() {
		WorkingDay workingDay = new WorkingDay();
		workingDay.setId(1);
		workingDay.setWeekDay("Lunes");
		workingDay.setStartTime(LocalTime.parse("15:30"));
		workingDay.setEndTime(LocalTime.parse("18:30"));
		workingDay.setTimeSlots(null);
		workingDay.setSanitarian(null);
		
		assertNotNull(workingDay.hashCode());
	}
}
