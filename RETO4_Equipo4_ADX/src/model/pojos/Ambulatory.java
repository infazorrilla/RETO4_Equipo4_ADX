package model.pojos;

import java.util.Objects;

public class Ambulatory {

	private int id;
	private String name;
	private String phoneNumber;
	private String address;

	// CONSTRUCTOR //
	public Ambulatory(int id, String name, String phoneNumber, String address) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public Ambulatory() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// HASHCODE //
	@Override
	public int hashCode() {
		return Objects.hash(address, id, name, phoneNumber);
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
		return Objects.equals(address, other.address) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

	// TO STRING //
	@Override
	public String toString() {
		return "Ambulatory [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ "]";
	}

}
