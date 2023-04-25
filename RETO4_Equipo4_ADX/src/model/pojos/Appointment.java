package model.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -406540892233438477L;

	private int id;

	private ArrayList<Sanitarian> sanitarians;
	private Patient patient;
	private TimeSlot timeSlot;
	private ArrayList<Ambulatory> ambulatory;
	
	
	
	public Appointment(int id, ArrayList<Sanitarian> sanitarians, Patient patient, TimeSlot timeSlot,
			ArrayList<Ambulatory> ambulatory) {
		super();
		this.id = id;
		this.sanitarians = sanitarians;
		this.patient = patient;
		this.timeSlot = timeSlot;
		this.ambulatory = ambulatory;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Sanitarian> getSanitarians() {
		return sanitarians;
	}
	public void setSanitarians(ArrayList<Sanitarian> sanitarians) {
		this.sanitarians = sanitarians;
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
	public ArrayList<Ambulatory> getAmbulatory() {
		return ambulatory;
	}
	public void setAmbulatory(ArrayList<Ambulatory> ambulatory) {
		this.ambulatory = ambulatory;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ambulatory, id, patient, sanitarians, timeSlot);
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
		return Objects.equals(ambulatory, other.ambulatory) && id == other.id && Objects.equals(patient, other.patient)
				&& Objects.equals(sanitarians, other.sanitarians) && Objects.equals(timeSlot, other.timeSlot);
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", sanitarians=" + sanitarians + ", patient=" + patient + ", timeSlot="
				+ timeSlot + ", ambulatory=" + ambulatory + "]";
	}

	
}
