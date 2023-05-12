package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.Date;

import model.pojos.Ambulatory;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Sanitarian;
import model.pojos.TimeSlot;
import model.utils.BBDDUtils;

public class SanitarianManager {

	public Sanitarian select(String dni) throws SQLException, Exception {
		S ret = null;

		String sql = "select * from sanitario where dniSanitario='" + dni + "'";

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

				String name = resultSet.getString("nombre");
				String surname = resultSet.getString("apellido");
				String gender = resultSet.getString("sexo");
				Date birthDate = resultSet.getDate("fechaNac");
				String password = resultSet.getString("contrasena");
				int idAmbulatory = resultSet.getInt("idAmbulatorio");
				Ambulatory ambulatory = new Ambulatory();
				ambulatory.setId(idAmbulatory);
				String type = resultSet.getString("tipo");
				
				if (ret.setType == "Medicina") {           
					ret.setName(name);
					ret.setSurname(surname);
					ret.setGender(gender);
					ret.setBirthDate(birthDate);
					ret.setPassword(password);
					ret.setIdambulatory(idAmbulatory);
					
				} else {
					
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
}
