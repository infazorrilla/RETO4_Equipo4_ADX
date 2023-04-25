package model.pojos;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -406540892233438477L;

	private int id;
	private String type;

	private ArrayList<Sanitarian> sanitarians;
	private Patient patient;
	private TimeSlot timeSlot;

	public Appointment() {

	}

	public Appointment(int id, String type, ArrayList<Sanitarian> sanitarians, Patient patient, TimeSlot timeSlot) {
		super();
		this.id = id;
		this.type = type;
		this.sanitarians = sanitarians;
		this.patient = patient;
		this.timeSlot = timeSlot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, patient, sanitarians, timeSlot, type);
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
		return id == other.id && Objects.equals(patient, other.patient)
				&& Objects.equals(sanitarians, other.sanitarians) && Objects.equals(timeSlot, other.timeSlot)
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", type=" + type + ", sanitarians=" + sanitarians + ", patient=" + patient
				+ ", timeSlot=" + timeSlot + "]";
	}

}
