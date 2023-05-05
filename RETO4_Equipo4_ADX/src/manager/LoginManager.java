package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import model.pojos.Ambulatory;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import model.pojos.User;
import model.utils.BBDDUtils;

public class LoginManager {
	public int getUserByDniAndPassword(String userDNI, String pass) {

		int convertion = 0;

		String sql = "SELECT * FROM usuario WHERE dni= '" + userDNI + "' AND contrasena = '" + pass + "'";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			User userType = identifyUserType(userDNI);

			if (resultSet.next()) {
				if (userType instanceof Patient) {
					convertion = 1;
				}

				if (userType instanceof Doctor) {
					convertion = 2;
				}

				if (userType instanceof Nurse) {
					convertion = 2;
				}

			}
		} catch (SQLException sqle) {
		} catch (Exception e) {
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
		return convertion;
	}

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

	private Doctor selectDoctor(String dni) throws SQLException, Exception {
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
		} catch (Exception e) {
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

	private Nurse selectNurse(String dni) throws SQLException, Exception {
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
		} catch (Exception e) {
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

	public Patient selectPatient(String dni) throws SQLException, Exception {
		Patient ret = null;

		String sql = "select * from paciente where dniPaciente= '" + dni + "'";

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

				ret.setDni(dni);
				ret.setPhoneNumber(phoneNumber);
				ret.setAddress(address);
			}

		} catch (SQLException sqle) {
		} catch (Exception e) {
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
}
