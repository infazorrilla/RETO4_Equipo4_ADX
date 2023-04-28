package model.pojos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class TimeSlot {

	private int id;
	private LocalTime startTime;
	private LocalTime endTime;
	private boolean available;

	private ArrayList<AppointmentWorkingDayTimeSlot> workingDays;
	

	public TimeSlot() {

	}


	public TimeSlot(int id, LocalTime startTime, LocalTime endTime, boolean available,
			ArrayList<AppointmentWorkingDayTimeSlot> workingDays) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.available = available;
		this.workingDays = workingDays;
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


	public ArrayList<AppointmentWorkingDayTimeSlot> getWorkingDays() {
		return workingDays;
	}


	public void setWorkingDays(ArrayList<AppointmentWorkingDayTimeSlot> workingDays) {
		this.workingDays = workingDays;
	}


	@Override
	public int hashCode() {
		return Objects.hash(available, endTime, id, startTime, workingDays);
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
				&& Objects.equals(startTime, other.startTime) && Objects.equals(workingDays, other.workingDays);
	}


	@Override
	public String toString() {
		return "TimeSlot [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", available=" + available
				+ ", workingDays=" + workingDays + "]";
	}


}
