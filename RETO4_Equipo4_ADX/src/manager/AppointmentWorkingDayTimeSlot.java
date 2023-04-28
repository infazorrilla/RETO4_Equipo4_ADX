package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.pojos.Appointment;
import model.pojos.Patient;
import model.utils.BBDDUtils;

public class AppointmentWorkingDayTimeSlot extends AbstractManager<Appointment> {

	public static final String AWT = "citaJornadaFranja";
	
	@Override
	public AppointmentWorkingDayTimeSlot select(int id) throws SQLException, Exception {
		AppointmentWorkingDayTimeSlot ret = null;

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
					ret = new AppointmentWorkingDayTimeSlot();

				
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
	public List<Patient> select() throws SQLException, Exception {
		ArrayList<Patient> ret = null;

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
				ret = new ArrayList<Patient>();
				
			
				String phoneNumber = resultSet.getString("telefono");
				String address = resultSet.getString("direccion");
				String dni = resultSet.getString("dniPaciente");
				String name = resultSet.getString("nombre");
				String surname = resultSet.getString("apellido");
				String gender = resultSet.getString("genero");
				Date birthDate = resultSet.getDate("fechaNac");
				String password = resultSet.getString("contrasena");
				
				Patient patient= new Patient();
				patient.setPhoneNumber(phoneNumber);
				patient.setAddress(address);
				patient.setDni(dni);
				patient.setName(name);
				patient.setSurname(surname);
				patient.setGender(gender);
				patient.setBirthDate(birthDate);
				patient.setPassword(password);
				
			ret.add(patient);
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

	@Override
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

	@Override
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

	@Override
	public void insert(Appointment t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Appointment t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

	
	


}