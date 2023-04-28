package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import model.pojos.Appointment;

import model.utils.BBDDUtils;

public class AppointmentManager extends AbstractManager<Appointment> {

	public static final String APPOINTMENT_TABLE = "cita";

	@Override
	public Appointment select(int id) throws SQLException, Exception {
		Appointment ret = null;

		String sql = "select * from " + APPOINTMENT_TABLE + " where id=" + id;

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
					ret = new Appointment();

				int id = 0;

				ret.setId(id);

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

	@Override
	public List<Appointment> select() throws SQLException, Exception {
		ArrayList<Appointment> ret = null;

		String sql = "select * from " + APPOINTMENT_TABLE;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		Class.forName(BBDDUtils.DRIVER_LOCAL);
		connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

	}

	resultSet.close();

	if(statement!=null)statement.close();

	if(connection!=null)connection.close();

	return ret;

	}

	@Override
	public void insert(Appointment appointment) throws SQLException, Exception {
		
		Connection connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
				BBDDUtils.PASS_LOCAL);
		Statement statement = connection.createStatement();
		Class.forName(BBDDUtils.DRIVER_LOCAL);

		String sql = "insert into " + APPOINTMENT_TABLE + " (id) values ('"
				+ appointment.getPatient.getDniPatient()  + "', '" + appointment.getSanitarian.getDniSanitarian() + "', '" 
				+ appointment.getAmbulatory.getId  + "', '" + appointment.getId() +  "')";
				

		statement.executeUpdate(sql);

		if (statement != null)
			statement.close();

		if (connection != null)
			connection.close();
		
	}

	@Override
	public void update(Appointment appointment) throws SQLException, Exception {
		
		
	}

	@Override
	public void delete(int id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}
}
