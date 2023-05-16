package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;

import model.pojos.Patient;
import model.utils.BBDDUtils;

/**
 * @author in1dw3
 *
 */
public class AppointmentWorkingDayTimeSlotManager  {

	/**
	 * 
	 */
	public static final String AWT = "citaJornadaFranja";
	

	/**
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public AppointmentWorkingDayTimeSlotManager select(int id) throws SQLException, Exception {
		AppointmentWorkingDayTimeSlotManager ret = null;

		String sql = "select * from " + AWT + " where idCita=" + id;

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
					ret = new AppointmentWorkingDayTimeSlotManager();

				
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

	
	public List<AppointmentWorkingDayTimeSlotManager> select() throws SQLException, Exception {
		ArrayList<AppointmentWorkingDayTimeSlotManager> ret = null;

		String sql = "select * from " + AWT;

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
				ret = new ArrayList<AppointmentWorkingDayTimeSlotManager>();
				
			
				
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


	


	public void insert(Patient patient) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;
	try {	
		 connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,BBDDUtils.PASS_LOCAL);
		 statement = connection.createStatement();
		Class.forName(BBDDUtils.DRIVER_LOCAL);

		String sql = "insert into " + AWT + " (dniPaciente, telefono, direccion) values ('" + 
		patient.getDni() + "', '" + patient.getPhoneNumber()+ "', '" + patient.getAddress()+ "')";
		

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


	public void update(Patient patient) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);
			
			String phoneNumber = "999999999";

			 String sql = "update "+ AWT +" set telefono = ? where dni = ?";
			 preparedStatement = connection.prepareStatement(sql);
			 preparedStatement.setString(1, phoneNumber);
			 preparedStatement.setString(2, patient.getDni());

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


	public void delete(String dni) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
					BBDDUtils.PASS_LOCAL);
			preparedStatement = null;
	
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			String sql = "delete from " + AWT + " where dni_paciente = " + dni;
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