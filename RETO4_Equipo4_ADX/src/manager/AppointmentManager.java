package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

		try {
		
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			if (null == ret)
				ret = new ArrayList<Appointment>();

				int id = resultSet.getInt("id");
				
				Appointment appointment= new Appointment();
				appointment.setId(id);
				
			ret.add(appointment);
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
	public void insert(Appointment appointment) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;
	try {	
		 connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,BBDDUtils.PASS_LOCAL);
		 statement = connection.createStatement();
		Class.forName(BBDDUtils.DRIVER_LOCAL);

		String sql = "insert into " + APPOINTMENT_TABLE + " (id) values ('" + /**appointment.getPatient.getDniPatient()
				+ "', '" + appointment.getSanitarian.getDniSanitarian() + "', '" + appointment.getAmbulatory.getId
				+ "', '" +**/ appointment.getId() + "')";

		statement.executeUpdate(sql);

	} catch (SQLException sqle) {
	} catch (Exception e) {
	} finally {
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
	}

	@Override
	public void update(Appointment appointment) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);
			
			int id= 8000;

			 String sql = "update "+ APPOINTMENT_TABLE +" set id = ? where id = ?";
			 preparedStatement = connection.prepareStatement(sql);
			 preparedStatement.setInt(1, id);
			 preparedStatement.setInt(2, appointment.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
		} catch (Exception e) {
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

	@Override
	public void delete(int id) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
					BBDDUtils.PASS_LOCAL);
			preparedStatement = null;
	
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			String sql = "delete from " + APPOINTMENT_TABLE + " where id = " + id;
			preparedStatement = connection.prepareStatement(sql);
	
			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {  
		} catch(Exception e){ 
		} finally {
			try {
				if (preparedStatement != null) 
					preparedStatement.close(); 
			} catch(Exception e){ 
			};
			try {
				if (connection != null) 
					connection.close(); 
			} catch(Exception e){ 
			};					
		}
	}


}
