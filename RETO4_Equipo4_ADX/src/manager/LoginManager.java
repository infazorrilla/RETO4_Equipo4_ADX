package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import model.pojos.User;
import model.utils.BBDDUtils;

/**
 * The class manages the basic functions (CRUD) in databases
 * 
 * @author dannyelfloyd
 *
 */
public class LoginManager {

	DoctorManager doctorManager = new DoctorManager();
	NurseManager nurseManager = new NurseManager();
	PatientManager patientManager = new PatientManager();

	/**
	 * Return a string: Sanitarian, PatientBlock or PatientUnlock
	 * 
	 * @param userDNI | Is a String
	 * @param pass    | Is a String
	 * @return String
	 */
	public String chekingLogin(String userDNI, String pass) {

		String ret = null;

		String sql = "SELECT * FROM usuario WHERE dni= '" + userDNI + "' AND contrasena = '" + pass + "'";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			User userType = checkUserType(userDNI);

			if (resultSet.next()) {
				if (userType instanceof Patient) {

					if (patientManager.select(userDNI).isBlocked() == true) {
						ret = "PatientBlock";
					} else {
						ret = "PatientUnlock";
					}
				}

				if (userType instanceof Doctor) {
					ret = "Sanitarian";
				}

				if (userType instanceof Nurse) {
					ret = "Sanitarian";
				}

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
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
	 * @param userDNI | Is a String
	 * @return User
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public User checkUserType(String userDNI) throws SQLException, Exception {

		User ret = null;

		Doctor doctor = doctorManager.select(userDNI);
		if (null != doctor) {
			ret = doctor;
		} else {

			Nurse nurse = nurseManager.select(userDNI);
			if (null != nurse) {
				ret = nurse;
			} else {

				Patient patient = patientManager.select(userDNI);
				if (null != patient) {
					ret = patient;
				}
			}
		}
		return ret;
	}
}
