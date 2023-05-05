package model.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Patient extends User implements Serializable {

	private static final long serialVersionUID = -6651744658859466141L;

	private String phoneNumber;
	private String address;
	private boolean blocked;
	private ArrayList<Appointment> appointments = null;

	public Patient(String phoneNumber, String address, boolean blocked, ArrayList<Appointment> appointments) {
		super();
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.blocked = blocked;
		this.appointments = appointments;
	}

	public Patient() {
		// TODO Auto-generated constructor stub
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

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(address, appointments, blocked, phoneNumber);
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
		Patient other = (Patient) obj;
		return Objects.equals(address, other.address) && Objects.equals(appointments, other.appointments)
				&& blocked == other.blocked && Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "Patient [phoneNumber=" + phoneNumber + ", address=" + address + ", blocked=" + blocked
				+ ", appointments=" + appointments + ", dni=" + dni + ", name=" + name + ", surname=" + surname
				+ ", gender=" + gender + ", birthDate=" + birthDate + ", password=" + password + "]";
	}

}