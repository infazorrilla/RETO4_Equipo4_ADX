package model.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -406540892233438477L;

	private int id= 0;

	private Sanitarian sanitarian;
	private Patient patient;
	private TimeSlot timeSlot;
	private Ambulatory ambulatory;
	private AppointmentWorkingDayTimeSlot appointmentWorkingDayTimeSlot;
	
	
	public int getId() {
		return id;
	}
	public Appointment(int id, Sanitarian sanitarian, Patient patient, TimeSlot timeSlot, Ambulatory ambulatory,
			AppointmentWorkingDayTimeSlot appointmentWorkingDayTimeSlot) {
		super();
		this.id = id;
		this.sanitarian = sanitarian;
		this.patient = patient;
		this.timeSlot = timeSlot;
		this.ambulatory = ambulatory;
		this.appointmentWorkingDayTimeSlot = appointmentWorkingDayTimeSlot;
		
	}
	public void setId(int id) {
		this.id = id;
	}
	public Sanitarian getSanitarian() {
		return sanitarian;
	}
	public void setSanitarian(Sanitarian sanitarian) {
		this.sanitarian = sanitarian;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}
	public Ambulatory getAmbulatory() {
		return ambulatory;
	}
	public void setAmbulatory(Ambulatory ambulatory) {
		this.ambulatory = ambulatory;
	}
	public AppointmentWorkingDayTimeSlot getAppointmentWorkingDayTimeSlot() {
		return appointmentWorkingDayTimeSlot;
	}
	public void setAppointmentWorkingDayTimeSlot(AppointmentWorkingDayTimeSlot appointmentWorkingDayTimeSlot) {
		this.appointmentWorkingDayTimeSlot = appointmentWorkingDayTimeSlot;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ambulatory, appointmentWorkingDayTimeSlot, id, patient, sanitarian, timeSlot);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(ambulatory, other.ambulatory)
				&& Objects.equals(appointmentWorkingDayTimeSlot, other.appointmentWorkingDayTimeSlot) && id == other.id
				&& Objects.equals(patient, other.patient) && Objects.equals(sanitarian, other.sanitarian)
				&& Objects.equals(timeSlot, other.timeSlot);
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", sanitarian=" + sanitarian + ", patient=" + patient + ", timeSlot="
				+ timeSlot + ", ambulatory=" + ambulatory + ", appointmentWorkingDayTimeSlot="
				+ appointmentWorkingDayTimeSlot + "]";
	}
	
	
	
	
	
}
