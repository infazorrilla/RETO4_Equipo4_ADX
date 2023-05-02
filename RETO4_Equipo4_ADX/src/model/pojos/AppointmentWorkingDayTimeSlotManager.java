package model.pojos;

import java.util.ArrayList;
import java.util.Objects;

public class AppointmentWorkingDayTimeSlotManager {

	private ArrayList<Appointment> appointment = null;
	private WorkingDay workingDay;
	private TimeSlot timeSlot;

	public AppointmentWorkingDayTimeSlotManager(ArrayList<Appointment> appointment, WorkingDay workingDay, TimeSlot timeSlot) {
		super();
		this.appointment = appointment;
		this.workingDay = workingDay;
		this.timeSlot = timeSlot;
	}

	public ArrayList<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(ArrayList<Appointment> appointment) {
		this.appointment = appointment;
	}

	public WorkingDay getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(WorkingDay workingDay) {
		this.workingDay = workingDay;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appointment, timeSlot, workingDay);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppointmentWorkingDayTimeSlotManager other = (AppointmentWorkingDayTimeSlotManager) obj;
		return Objects.equals(appointment, other.appointment) && Objects.equals(timeSlot, other.timeSlot)
				&& Objects.equals(workingDay, other.workingDay);
	}

	@Override
	public String toString() {
		return "AppointmentWorkingDayTimeSlot [appointment=" + appointment + ", workingDay=" + workingDay
				+ ", timeSlot=" + timeSlot + "]";
	}

}
