package elorrieta.model;

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
	private LocalTime hour;
	private String type;

	private ArrayList<Sanitarian> sanitarians;
	private Patient patient;

	public Appointment() {

	}

	public Appointment(int id, LocalTime hour, String type, ArrayList<Sanitarian> sanitarians, Patient patient) {
		super();
		this.id = id;
		this.hour = hour;
		this.type = type;
		this.sanitarians = sanitarians;
		this.patient = patient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hour, id, patient, sanitarians, type);
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
		return Objects.equals(hour, other.hour) && id == other.id && Objects.equals(patient, other.patient)
				&& Objects.equals(sanitarians, other.sanitarians) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", hour=" + hour + ", type=" + type + ", sanitarians=" + sanitarians
				+ ", patient=" + patient + "]";
	}

}
