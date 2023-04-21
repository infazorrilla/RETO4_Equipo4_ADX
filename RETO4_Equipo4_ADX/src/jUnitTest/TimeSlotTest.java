package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.pojos.TimeSlot;

class TimeSlotTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGettersAndSetters() {
		TimeSlot timeSlot = new TimeSlot();

		timeSlot.setId(1);
		assertEquals(timeSlot.getId(), 1);

		timeSlot.setStartTime(LocalTime.parse("11:00"));
		assertEquals(timeSlot.getStartTime(), "11:00");

		timeSlot.setStartTime(LocalTime.parse("11:30"));
		assertEquals(timeSlot.getEndTime(), "11:30");

		timeSlot.setAvailable(true);
		assertEquals(timeSlot.isAvailable(), true);

		timeSlot.setWorkingDay(null);
		assertEquals(timeSlot.getWorkingDay(), null);
	}

	@Test
	public void testTimeSlotEquals() {
		TimeSlot timeSlotOne = new TimeSlot();
		timeSlotOne.setId(1);
		timeSlotOne.setStartTime(LocalTime.parse("11:00"));
		timeSlotOne.setStartTime(LocalTime.parse("11:30"));
		timeSlotOne.setAvailable(true);
		timeSlotOne.setWorkingDay(null);
		
		TimeSlot timeSlotTwo = new TimeSlot();
		timeSlotTwo.setId(1);
		timeSlotTwo.setStartTime(LocalTime.parse("11:00"));
		timeSlotTwo.setStartTime(LocalTime.parse("11:30"));
		timeSlotTwo.setAvailable(true);
		timeSlotTwo.setWorkingDay(null);

		assertTrue(timeSlotOne.equals(timeSlotTwo));
	}

	@Test
	public void testTimeSlotToString() {
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setId(1);
		timeSlot.setStartTime(LocalTime.parse("11:00"));
		timeSlot.setEndTime(LocalTime.parse("11:30"));
		timeSlot.setAvailable(true);
		timeSlot.setWorkingDay(null);

		String sentence = "TimeSlot [id=" + 1 + ", startTime=" + "11:00" + ", endTime=" + "11:30" + ", available=" + true
				+ ", workingDay=" + null + "]";

		assertEquals(sentence, timeSlot.toString());
	}

	@Test
	public void testHasCode() {
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setId(1);
		timeSlot.setStartTime(LocalTime.parse("11:00"));
		timeSlot.setStartTime(LocalTime.parse("11:30"));
		timeSlot.setAvailable(true);
		timeSlot.setWorkingDay(null);

		assertNotNull(timeSlot.hashCode());
	}

}
