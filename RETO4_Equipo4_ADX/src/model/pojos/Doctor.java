package model.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Doctor extends Sanitarian implements Serializable {

	private static final long serialVersionUID = -6651744658859466141L;

	private String category;
	private boolean eir;

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
		Doctor other = (Doctor) obj;
		return Objects.equals(category, other.category) && eir == other.eir;
	}

	// TO STRING //
	@Override
	public String toString() {
		return "Doctor [category=" + category + ", eir=" + eir + "]";
	}

}
