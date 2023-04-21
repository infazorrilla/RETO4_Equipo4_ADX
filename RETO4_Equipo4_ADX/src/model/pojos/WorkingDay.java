package model.pojos;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class WorkingDay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 345790915290901941L;

	private int id = 0;
	
	private Date date = null;
	private LocalTime startTime = null;
	private LocalTime endTime = null;
	private ArrayList<TimeSlot> timeSlots;
	private Sanitarian sanitarian;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
		return Objects.hash(date, endTime, id, sanitarian, startTime, timeSlots);
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
		return Objects.equals(date, other.date) && Objects.equals(endTime, other.endTime) && id == other.id
				&& Objects.equals(sanitarian, other.sanitarian) && Objects.equals(startTime, other.startTime)
				&& Objects.equals(timeSlots, other.timeSlots);
	}
	@Override
	public String toString() {
		return "WorkingDay [id=" + id + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", timeSlots=" + timeSlots + ", sanitarian=" + sanitarian + "]";
	}
	
	
}
