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
	
	// HASHCODE //
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(appointments, salary, staffNum, type);
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
		Sanitarian other = (Sanitarian) obj;
		return Objects.equals(appointments, other.appointments)
				&& Float.floatToIntBits(salary) == Float.floatToIntBits(other.salary) && staffNum == other.staffNum
				&& Objects.equals(type, other.type);
	}
	@Override
	public String toString() {
		return "Sanitarian [staffNum=" + staffNum + ", salary=" + salary + ", type=" + type + ", appointments="
				+ appointments + "]";
	}

	
	

	
	
	
	
	// TO STRING //
	
	
}
