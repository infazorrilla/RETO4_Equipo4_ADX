package model.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Sanitarian extends User implements Serializable  {

	private static final long serialVersionUID = -6651744658859466141L;

	private int staffNum;
	private float salary;
	private String type;
	private ArrayList<Appointment> appointments;
	private ArrayList<Ambulatory> ambulatory;
	
	
	
	
	public Sanitarian(int staffNum, float salary, String type, ArrayList<Appointment> appointments,
			ArrayList<Ambulatory> ambulatory) {
		super();
		this.staffNum = staffNum;
		this.salary = salary;
		this.type = type;
		this.appointments = appointments;
		this.ambulatory = ambulatory;
	}
	public Sanitarian() {
		// TODO Auto-generated constructor stub
	}
	// GETTERS AND SETTERS //
	public int getStaffNum() {
		return staffNum;
	}
	public void setStaffNum(int staffNum) {
		this.staffNum = staffNum;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public ArrayList<Ambulatory> getAmbulatory() {
		return ambulatory;
	}
	public void setAmbulatory(ArrayList<Ambulatory> ambulatory) {
		this.ambulatory = ambulatory;
	}
	
	// HASHCODE //
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(ambulatory, appointments, salary, staffNum, type);
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
		Sanitarian other = (Sanitarian) obj;
		return Objects.equals(ambulatory, other.ambulatory) && Objects.equals(appointments, other.appointments)
				&& Float.floatToIntBits(salary) == Float.floatToIntBits(other.salary) && staffNum == other.staffNum
				&& Objects.equals(type, other.type);
	}
	// TO STRING //
	@Override
	public String toString() {
		return "Sanitarian [staffNum=" + staffNum + ", salary=" + salary + ", type=" + type + ", appointments="
				+ appointments + ", ambulatory=" + ambulatory + ", dni=" + dni + ", name=" + name + ", surname="
				+ surname + ", gender=" + gender + ", birthDate=" + birthDate + ", password=" + password + "]";
	}

}
	

