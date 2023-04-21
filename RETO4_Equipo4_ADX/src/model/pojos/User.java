package model.pojos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class User implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6466887570169801863L;

	protected String dni = null;
	
	protected String name= null;
	protected String surname= null;
	protected String gender= null;
	protected Date birthDate= null;
	protected String password= null;
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(birthDate, dni, gender, name, password, surname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(dni, other.dni)
				&& Objects.equals(gender, other.gender) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname);
	}
	@Override
	public String toString() {
		return "User [dni=" + dni + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", birthDate="
				+ birthDate + ", password=" + password + "]";
	}
	
	
	
}
