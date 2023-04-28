package model.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Doctor extends Sanitarian implements Serializable {

	private static final long serialVersionUID = -6651744658859466141L;

	private String speciality;
	private boolean mir;

	// CONSTRUCTOR | USING FIELD
	public Doctor(int staffNum, float salary, String type, ArrayList<Appointment> appointments,
			ArrayList<WorkingDaySanitarian> workingDay, Ambulatory ambulatory, String speciality, boolean mir) {
		super(staffNum, salary, type, appointments, workingDay, ambulatory);
		this.speciality = speciality;
		this.mir = mir;
	}

	// CONSTRUCTOR | FROM SUPERCLASS
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(int staffNum, float salary, String type, ArrayList<Appointment> appointments,
			ArrayList<WorkingDaySanitarian> workingDay, Ambulatory ambulatory) {
		super(staffNum, salary, type, appointments, workingDay, ambulatory);
		// TODO Auto-generated constructor stub
	}

	// GETTERS AND SETTERS //
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public boolean isMir() {
		return mir;
	}

	public void setMir(boolean mir) {
		this.mir = mir;
	}

	// HASHCODE //
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(mir, speciality);
		return result;
	}

	// EQUALS //
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return mir == other.mir && Objects.equals(speciality, other.speciality);
	}

	// TO STRING //
	@Override
	public String toString() {
		return "Doctor [speciality=" + speciality + ", mir=" + mir + "]";
	}

}
