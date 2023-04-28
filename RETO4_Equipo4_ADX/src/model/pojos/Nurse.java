package model.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Nurse extends Sanitarian implements Serializable {

	private static final long serialVersionUID = -6651744658859466141L;

	private String category;
	private boolean eir;

	// CONSTRUCTOR | USING FIELDS //
	public Nurse(int staffNum, float salary, String type, ArrayList<Appointment> appointments,
			ArrayList<WorkingDaySanitarian> workingDay, Ambulatory ambulatory, String category, boolean eir) {
		super(staffNum, salary, type, appointments, workingDay, ambulatory);
		this.category = category;
		this.eir = eir;
	}

	// CONSTRUCTOR | FROM SUPERCLASS //
	public Nurse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Nurse(int staffNum, float salary, String type, ArrayList<Appointment> appointments,
			ArrayList<WorkingDaySanitarian> workingDay, Ambulatory ambulatory) {
		super(staffNum, salary, type, appointments, workingDay, ambulatory);
		// TODO Auto-generated constructor stub
	}

	// GETTERS AND SETTERS //
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isEir() {
		return eir;
	}

	public void setEir(boolean eir) {
		this.eir = eir;
	}

	// HASHCODE //
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(category, eir);
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
		Nurse other = (Nurse) obj;
		return Objects.equals(category, other.category) && eir == other.eir;
	}

	// TO STRING //
	@Override
	public String toString() {
		return "Nurse [category=" + category + ", eir=" + eir + "]";
	}

}
