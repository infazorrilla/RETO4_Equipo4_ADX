package model.pojos;

import java.util.Objects;

public class Ambulatory {

	private int id;
	private String name;
	
	// GETTERS AND SETTERS //
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// HASHCODE //
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
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
		Ambulatory other = (Ambulatory) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
	
	// TO STRING //
	@Override
	public String toString() {
		return "Ambulatory [id=" + id + ", name=" + name + "]";
	}
	
	
	

}
