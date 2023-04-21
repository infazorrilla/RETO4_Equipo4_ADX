package elorrieta.model;

import java.time.LocalTime;
import java.util.Objects;

public class TimeSlot {

	private int id;
	private LocalTime startTime;
	private LocalTime endTime;
	private boolean available;

	private WorkingDay workingDay;

	public TimeSlot() {

	}

	public TimeSlot(int id, LocalTime startTime, LocalTime endTime, boolean available, WorkingDay workingDay) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.available = available;
		this.workingDay = workingDay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public WorkingDay getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(WorkingDay workingDay) {
		this.workingDay = workingDay;
	}

	@Override
	public int hashCode() {
		return Objects.hash(available, endTime, id, startTime, workingDay);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeSlot other = (TimeSlot) obj;
		return available == other.available && Objects.equals(endTime, other.endTime) && id == other.id
				&& Objects.equals(startTime, other.startTime) && Objects.equals(workingDay, other.workingDay);
	}

	@Override
	public String toString() {
		return "TimeSlot [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", available=" + available
				+ ", workingDay=" + workingDay + "]";
	}

}
