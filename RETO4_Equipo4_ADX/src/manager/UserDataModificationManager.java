package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.pojos.Ambulatory;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import model.pojos.User;
import model.utils.BBDDUtils;

public class UserDataModificationManager {

	/**
	 * Identifies which kind of user enters the application
	 * 
	 * @param userDNI, String with user's id
	 * @return doctor, nurse or patient
	 * @throws SQLException
	 * @throws Exception
	 */
	public User identifyUserType(String userDNI) throws SQLException, Exception {

		User ret = null;
		Doctor doctor = selectDoctor(userDNI);
		if (null != doctor) {
			ret = doctor;
		} else {
			Nurse nurse = selectNurse(userDNI);
			if (null != nurse) {
				ret = nurse;
			} else {
				Patient patient = selectPatient(userDNI);
				if (null != patient) {
					ret = patient;
				}
			}
		}
		return ret;
	}

	/**
	 * Returns a boolean to enable (or not) ModifyPatientOkButton. Checks if the
	 * Strings from fields tfPasswd, address and phoneNumber are filled or not. If
	 * any of of them is filled, the method returns true, and the button enables.
	 * 
	 * @param tfPasswd,    a String with the field's content.
	 * @param address,     a String with the field's content.
	 * @param phoneNumber, a String with the field's content.
	 * @return boolean
	 */
	public boolean enableModifyPatientOkButton(String password, String address, String phoneNumber) {
		boolean ret = false;

		if ((password.length() == 0) && (address.length() == 0) && (phoneNumber.length() == 0)) {
			ret = false;
		} else {
			ret = true;
		}

		return ret;
	}

	/**
	 * Returns a boolean to enable (or not) ModifySanitarianOkButton. Checks if the
	 * String from field tfPasswd is filled or not. If it's filled, the method
	 * returns true, and the button enables.
	 * 
	 * @param password, a String with the field's content.
	 * @return boolean
	 */
	public boolean enableModifySanitarianOkButton(String password) {
		boolean ret = false;

		if (password.length() > 0) {
			ret = true;
		} else {
			ret = false;
		}

		return ret;
	}

	/**
	 * Checks is the phone number is actually a phone number
	 * 
	 * @param phoneNumber String
	 * @return boolean
	 */
	public boolean isPhoneNumber(String phoneNumber) {
		boolean ret = false;

		Pattern pattern = Pattern.compile("^\\d{9}$");
		Matcher matcher = pattern.matcher(phoneNumber);

		if (matcher.matches())
			ret = true;

		return ret;
	}

	/**
	 * Updates 'direccion' atribute DB's patient table by patient's id
	 * 
	 * @param patient Object Patient
	 * @param address String of the new address
	 * @throws SQLException
	 * @throws Exception
	 */
	public void updatePatientAddress(Patient patient, String address) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			String sql = "update paciente set direccion = ? where dniPaciente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, address);
			preparedStatement.setString(2, patient.getDni());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	/**
	 * Updates 'telefono' atribute DB's patient table by patient's id
	 * 
	 * @param patient     Object Patient
	 * @param phoneNumber String of the new phone number
	 * @throws SQLException
	 * @throws Exception
	 */
	public void updatePatientPhoneNumber(Patient patient, String phoneNumber) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			String sql = "update paciente set telefono = ? where dniPaciente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phoneNumber);
			preparedStatement.setString(2, patient.getDni());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	/**
	 * Updates 'contrasena' atribute DB's usuario table by patient's id
	 * 
	 * @param patient  Object Patient
	 * @param password String of the new password
	 * @throws SQLException
	 * @throws Exception
	 */
	public void updatePatientPassword(Patient patient, String password) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			String sql = "update usuario set contrasena = ? where dni = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, patient.getDni());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	/**
	 * Updates 'contrasena' atribute DB's usuario table by doctor's id
	 * 
	 * @param patient  Object Doctor
	 * @param password String of the new password
	 * @throws SQLException
	 * @throws Exception
	 */
	public void updateDoctorPassword(Doctor doctor, String password) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			String sql = "update usuario set contrasena = ? where dni = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, doctor.getDni());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	/**
	 * Updates 'contrasena' atribute DB's usuario table by nurse's id
	 * 
	 * @param patient  Object Nurse
	 * @param password String of the new password
	 * @throws SQLException
	 * @throws Exception
	 */
	public void updateNursePassword(Nurse nurse, String password) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			String sql = "update usuario set contrasena = ? where dni = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, nurse.getDni());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	/**
	 * Deletes 'sanitario' table's row by dni
	 * 
	 * @param dni String of doctor's dni
	 * @throws SQLException
	 * @throws Exception
	 */
	public void deleteDoctor(String dni) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);
			String sql = "delete from sanitario where dniSanitario = '" + dni + "'";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	/**
	 * Deletes 'sanitario' table's row by dni
	 * 
	 * @param dni String of nurse dni
	 * @throws SQLException
	 * @throws Exception
	 */
	public void deleteNurse(String dni) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);
			String sql = "delete from sanitario where dniSanitario = '" + dni + "'";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

	/**
	 * Returns one Doctor from the DB by its id
	 * 
	 * @param id int
	 * @return Doctor object
	 */
	public Doctor selectDoctor(String dni) throws SQLException, Exception {
		Doctor ret = null;

		String sql = "select * from sanitario s join usuario u on s.dniSanitario=u.dni where dniSanitario= '" + dni
				+ "' and tipo='Medicina'";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				if (null == ret)
					ret = new Doctor();

				int staffNum = resultSet.getInt("numPersonal");
				float salary = resultSet.getFloat("salario");
				int idAmbulatory = resultSet.getInt("idAmbulatorio");
				String type = resultSet.getString("tipo");
				String speciality = resultSet.getString("especialidad");
				String mir = resultSet.getString("MIR");
				String name = resultSet.getString("nombre");
				String surname = resultSet.getString("apellido");
				String gender = resultSet.getString("sexo");
				Date birthDate = resultSet.getDate("fechaNac");
				String password = resultSet.getString("contrasena");

				Ambulatory ambulatory = new Ambulatory();
				ambulatory.setId(idAmbulatory);

				ret.setDni(dni);
				ret.setStaffNum(staffNum);
				ret.setSalary(salary);
				ret.setAmbulatory(ambulatory);
				ret.setType(type);
				ret.setSpeciality(speciality);
				if (mir.equalsIgnoreCase("true")) {
					ret.setMir(true);
				} else {
					ret.setMir(false);
				}
				ret.setName(name);
				ret.setSurname(surname);
				ret.setGender(gender);
				ret.setBirthDate(birthDate);
				ret.setPassword(password);
			}

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
			}
			;
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
		return ret;
	}

	/**
	 * Returns one Nurse from the DB by its id
	 * 
	 * @param id int
	 * @return Doctor object
	 */
	public Nurse selectNurse(String dni) throws SQLException, Exception {
		Nurse ret = null;

		String sql = "select * from sanitario s join usuario u on s.dniSanitario=u.dni where dniSanitario= '" + dni
				+ "' and tipo='Enfermeria'";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				if (null == ret)
					ret = new Nurse();

				int staffNum = resultSet.getInt("numPersonal");
				float salary = resultSet.getFloat("salario");
				int idAmbulatory = resultSet.getInt("idAmbulatorio");
				String type = resultSet.getString("tipo");
				String category = resultSet.getString("categoria");
				String eir = resultSet.getString("EIR");
				String name = resultSet.getString("nombre");
				String surname = resultSet.getString("apellido");
				String gender = resultSet.getString("sexo");
				Date birthDate = resultSet.getDate("fechaNac");
				String password = resultSet.getString("contrasena");

				Ambulatory ambulatory = new Ambulatory();
				ambulatory.setId(idAmbulatory);

				ret.setDni(dni);
				ret.setStaffNum(staffNum);
				ret.setSalary(salary);
				ret.setAmbulatory(ambulatory);
				ret.setType(type);
				ret.setCategory(category);
				if (eir.equalsIgnoreCase("true")) {
					ret.setEir(true);
				} else {
					ret.setEir(false);
				}
				ret.setName(name);
				ret.setSurname(surname);
				ret.setGender(gender);
				ret.setBirthDate(birthDate);
				ret.setPassword(password);
			}

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
			}
			;
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
		return ret;
	}

	/**
	 * Returns one Patient from the DB by its id
	 * 
	 * @param id int
	 * @return Patient object
	 */
	public Patient selectPatient(String dni) throws SQLException, Exception {
		Patient ret = null;

		String sql = "select * from paciente p join usuario u on p.dniPaciente=u.dni  where dniPaciente= '" + dni + "'";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				if (null == ret)
					ret = new Patient();

				String phoneNumber = resultSet.getString("telefono");
				String address = resultSet.getString("direccion");
				String name = resultSet.getString("nombre");
				String surname = resultSet.getString("apellido");
				String gender = resultSet.getString("sexo");
				Date birthDate = resultSet.getDate("fechaNac");
				String password = resultSet.getString("contrasena");
				String blocked = resultSet.getString("bloqueado");

				ret.setDni(dni);
				ret.setPhoneNumber(phoneNumber);
				ret.setAddress(address);
				ret.setName(name);
				ret.setSurname(surname);
				ret.setGender(gender);
				ret.setBirthDate(birthDate);
				ret.setPassword(password);
				if(blocked.equalsIgnoreCase("true")) {
					ret.setBlocked(true);
				}else {
					ret.setBlocked(false);
				}
			}

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
			}
			;
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
		return ret;
	}

	/**
	 * Deletes the row in 'Paciente' table of the DB by its id
	 * 
	 * @param id int
	 */
	public void deletePatient(String dni) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);
			String sql = "delete from paciente where dniPaciente = '" + dni + "'";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
			}
			;
		}
	}

}
