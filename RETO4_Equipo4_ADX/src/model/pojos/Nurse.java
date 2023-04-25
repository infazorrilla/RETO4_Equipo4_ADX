package model.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Nurse extends Sanitarian implements Serializable {

	public Nurse(int staffNum, float salary, String type, ArrayList<Appointment> appointments,
			ArrayList<Ambulatory> ambulatory, String category, boolean eir) {
		super(staffNum, salary, type, appointments, ambulatory);
		// TODO Auto-generated constructor stub
	}

	public Nurse() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -6651744658859466141L;

	private String category;
	private boolean eir;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(category, eir);
		return result;
	}
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

	@Override
	public String toString() {
		return "Nurse [category=" + category + ", eir=" + eir + ", dni=" + dni + ", name=" + name + ", surname="
				+ surname + ", gender=" + gender + ", birthDate=" + birthDate + ", password=" + password + "]";
	}
	

	



}
