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

	private ArrayList<WorkingDaySanitarian> sanitarians;

	public WorkingDay() {

	}

	public WorkingDay(int id, Date date, LocalTime startTime, LocalTime endTime,
			ArrayList<WorkingDaySanitarian> sanitarians) {
		super();
		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.sanitarians = sanitarians;
	}

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

	public ArrayList<WorkingDaySanitarian> getSanitarians() {
		return sanitarians;
	}

	public void setSanitarians(ArrayList<WorkingDaySanitarian> sanitarians) {
		this.sanitarians = sanitarians;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, endTime, id, sanitarians, startTime);
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
				&& Objects.equals(sanitarians, other.sanitarians) && Objects.equals(startTime, other.startTime);
	}

	@Override
	public String toString() {
		return "WorkingDay [id=" + id + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", sanitarians=" + sanitarians + "]";
	}

}
