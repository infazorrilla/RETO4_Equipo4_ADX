package model.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Nurse extends Sanitarian implements Serializable {

	private static final long serialVersionUID = -6651744658859466141L;

	private String speciality;
	private boolean mir;

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
		return Objects.hash(mir, speciality);
	}

	// EQUALS //
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nurse other = (Nurse) obj;
		return mir == other.mir && Objects.equals(speciality, other.speciality);
	}

	// TOSTRING //
	@Override
	public String toString() {
		return "Nurse [speciality=" + speciality + ", mir=" + mir + "]";
	}

}
