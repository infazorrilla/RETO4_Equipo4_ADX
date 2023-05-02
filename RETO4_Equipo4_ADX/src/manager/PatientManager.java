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


import model.pojos.Patient;
import model.utils.BBDDUtils;

public class PatientManager  {

	public static final String PATIENT_TABLE = "paciente";

	public Patient select(String dni) throws SQLException, Exception {
		Patient ret = null;

		String sql = "select * from paciente p join usuario u on p.dniPaciente=u.dni  where dniPaciente=" + dni;

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
				
				ret.setDni(dni);
				ret.setPhoneNumber(phoneNumber);
				ret.setAddress(address);
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

	
	public List<Patient> select() throws SQLException, Exception {
		ArrayList<Patient> ret = null;

		String sql = "select * from paciente p join usuario u on p.dniPaciente=u.dni";

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
				String gender = resultSet.getString("sexo");
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


	

	
	public void insert(Patient patient) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;
	try {	
		 connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,BBDDUtils.PASS_LOCAL);
		 statement = connection.createStatement();
		Class.forName(BBDDUtils.DRIVER_LOCAL);

		String sql = "insert into " + PATIENT_TABLE + " (dniPaciente, telefono, direccion) values ('" + 
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

			 String sql = "update "+ PATIENT_TABLE +" set telefono = ? where dni = ?";
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
			String sql = "delete from " + PATIENT_TABLE + " where dni_paciente = " + dni;
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