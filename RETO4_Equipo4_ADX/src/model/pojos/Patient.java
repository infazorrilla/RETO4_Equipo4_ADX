package model.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Patient extends User implements Serializable {

	private static final long serialVersionUID = -6651744658859466141L;
	
	private String phoneNumber;
	private String address;
	private ArrayList<Appointment> appointments = null;
	
	
	
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}
	// GETTERS AND SETTERS //
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
	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	// HASCODE //
	@Override
	public int hashCode() {
		return Objects.hash(address, appointments, phoneNumber);
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
		Patient other = (Patient) obj;
		return Objects.equals(address, other.address) && Objects.equals(appointments, other.appointments)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}
	@Override
	public String toString() {
		return "Patient [phoneNumber=" + phoneNumber + ", address=" + address + ", appointments=" + appointments
				+ ", dni=" + dni + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", birthDate="
				+ birthDate + ", password=" + password + "]";
	}
	
	// TO STRING //
	
	

}
