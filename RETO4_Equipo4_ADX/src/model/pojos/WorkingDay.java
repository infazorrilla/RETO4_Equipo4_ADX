package model.pojos;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class WorkingDay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 345790915290901941L;

	private int id = 0;

	private String weekDay = null;
	private LocalTime startTime = null;
	private LocalTime endTime = null;
	private ArrayList<TimeSlot> timeSlots;
	private Sanitarian sanitarian;

	public WorkingDay() {

	}

	public WorkingDay(int id, String weekDay, LocalTime startTime, LocalTime endTime, ArrayList<TimeSlot> timeSlots,
			Sanitarian sanitarian) {
		super();
		this.id = id;
		this.weekDay = weekDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeSlots = timeSlots;
		this.sanitarian = sanitarian;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public ArrayList<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(ArrayList<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public Sanitarian getSanitarian() {
		return sanitarian;
	}

	public void setSanitarian(Sanitarian sanitarian) {
		this.sanitarian = sanitarian;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endTime, id, sanitarian, startTime, timeSlots, weekDay);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkingDay other = (WorkingDay) obj;
		return Objects.equals(endTime, other.endTime) && id == other.id && Objects.equals(sanitarian, other.sanitarian)
				&& Objects.equals(startTime, other.startTime) && Objects.equals(timeSlots, other.timeSlots)
				&& Objects.equals(weekDay, other.weekDay);
	}

	@Override
	public String toString() {
		return "WorkingDay [id=" + id + ", weekDay=" + weekDay + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", timeSlots=" + timeSlots + ", sanitarian=" + sanitarian + "]";
	}

}
