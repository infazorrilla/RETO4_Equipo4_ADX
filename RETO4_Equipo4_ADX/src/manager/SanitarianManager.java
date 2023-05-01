package manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.pojos.*;
import model.utils.BBDDUtils;

public class SanitarianManager {

	public static final String SQL_SELECT = "SELECT * FROM adx.cita WHERE dniSanitario = ?";

	// USER PERMISSSIONS (IN THE DB): 
	// grant insert, delete, update on adx.cita to rol_sanitario;
	public static final String SQL_INSERT_CITA = "INSERT INTO adx.cita (dniPaciente, dniSanitario, idAmbulatorio) VALUES (?, ?, ?)";
	public static final String SQL_UPDATE_CITA = "UPDATE adx.cita SET dniPaciente = ?, dniSanitario = ?, idAmbulatorio = ? WHERE idCita = ?";
	public static final String SQL_DELETE_CITA = "DELETE FROM adx.cita WHERE idCita = ? ";
	// USER PERMISSSIONS (IN THE DB): grant update on adx.jornada to rol_sanitario;
	public static final String SQL_UPDATE_JORNADA = "UPDATE adx.jornada SET fecha = ?, horaInicio = ?, horaFin = ? WHERE idJornada = ?";

	// STRUCTURE OF THE DATABASE
	/**
	 * Usuario (dni, nombre, apellido, sexo, fechaNac, contrasena) 
	 * Paciente(dniPaciente(FK), telefono, direccion) 
	 * Sanitario (dniSanitario(FK), numPersonal, idAmbulatorio (FK), salario, tipo, especialidad, MIR, categoria, EIR) 
	 * JornadaSanitario(dniSanitario(FK), idJornada(FK)) 
	 * Cita (idCita, dniPaciente(FK), dniSanitario(FK), idAmbulatorio (FK)) 
	 * Jornada (idJornada,fecha, horaInicio, horaFin) 
	 * Franja (idFranja, horaInicio, horaFin)
	 * CitaJornadaFranja(idFranja(FK),idJornada(FK), idCita(FK)) 
	 * Ambulatorio(idAmbulatorio, nombre, direccion, telefono)
	 */

//	public T select(int id) throws SQLException, Exception;

	// A DOCTOR OR NURSE CAN VIEW ALL THE APPOINTMENTS FOR THEIR DNI
	public List<Appointment> select() throws SQLException, Exception {
		// Returns all rows of the appointments table
		// If there is nothing, it returns NULL

		ArrayList<Appointment> ret = null;

		// The connection with BBDD
		Connection connection = null;

		// SQL statement against BBDD
		// Result set will contain everything returned by the BBDD
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			// The Driver we are going to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// Open the connection with BBDD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			// Let's launch the sentence. . .
			// SQL_SELECT = "SELECT * FROM adx.cita WHERE dniSanitario = ?";
			statement = connection.prepareStatement(SQL_SELECT);
			resultSet = statement.executeQuery();

			// It is not possible to know how many things the resultSet has returned.
			// You have to go 1 by 1 and save everything in its object
			while (resultSet.next()) {

				// If necessary, we initialize the list
				if (null == ret)
					ret = new ArrayList<Appointment>();

				Appointment appointment = new Appointment();

				// We put the data into Example
				appointment.setId(resultSet.getInt("idCita")); // I'm not sure the data entry is correct.
				// How do I insert a database data into a Java object?
				appointment.setSanitarian(resultSet.getString("dniSanitario"));
				appointment.setPatient(resultSet.getString("dniPaciente"));
				appointment.setAmbulatory(resultSet.getInt("idAmbulatorio"));

				// Insert data into parameters
				statement.setString(1, appointment.getSanitarian()); // I don't know how to put a data through an object

				// We save it in ret
				ret.add(appointment);
			}
		} catch (SQLException sqle) {
			System.out.println("Error with BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Generic error - " + e.getMessage());
		} finally {
			// We close in reverse of how we open them
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No need
			}
			;
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// No need
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No need
			}
			;
		}
		return ret;
	}

	// A DOCTOR CAN INSERT A NEW APPOINTMENT
	public void insert(Appointment appointment) throws SQLException, Exception {

		// Connection with the DB
		Connection connection = null;

		// SQL statement to DB
		PreparedStatement statement = null;

		try {
			// The Driver that we are going to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// We open the connection with the BD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			// We are going to throw the statement
			// SQL_INSERT_CITA = "INSERT INTO adx.cita (dniPaciente, dniSanitario,
			// idAmbulatorio) VALUES (?, ?, ?)";
			statement = connection.prepareStatement(SQL_INSERT_CITA);

			// Instantiate the necessary classes
			User user = new User(); // I can not instantiate an abstract class. But i need to get IDs, how???
			Ambulatory ambulatory = new Ambulatory();

			// Insert data into parameters
			statement.setString(1, user.getDni());
			statement.setString(2, user.getDni());
			statement.setInt(3, ambulatory.getId());

			// We execute
			statement.executeUpdate(SQL_INSERT_CITA);

		} catch (SQLException sqle) {
			System.out.println("Error with the BD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("General Error - " + e.getMessage());
		} finally {
			// We close in reverse of how we open them
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// Don't care
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// Don't care
			}
			;
		}

	}

	// A DOCTOR CAN UPDATE AN APPOINTMENT
	public void update(Appointment appointment) throws SQLException, Exception {

		// Connection with the BD
		Connection connection = null;

		// SQL statement to BD
		PreparedStatement preparedStatement = null;

		try {
			// The Driver that we are going to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// We open the connection with the BD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			int id = 0;

			// We are going to throw the statement
			// SQL_UPDATE_CITA = "UPDATE adx.cita SET dniPaciente = ?, dniSanitario = ?,
			// idAmbulatorio = ? WHERE idCita = ?";
			preparedStatement = connection.prepareStatement(SQL_UPDATE_CITA);

			// Instantiate the necessary classes
			User user = new User(); // I can not instantiate an abstract class. But i need to get IDs, how???
			Ambulatory ambulatory = new Ambulatory();

			// Insert data into parameters
			preparedStatement.setString(1, user.getDni());
			preparedStatement.setString(2, user.getDni());
			preparedStatement.setInt(3, ambulatory.getId());
			preparedStatement.setInt(4, appointment.getId());

			// We execute
			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error with the BD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// We close in reverse of how we open them
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// Don't care
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// Don't care
			}
			;
		}

	}

	// A DOCTOR CAN UPDATE AN WORKINGDAY
	public void update(WorkingDay workingDay) throws SQLException, Exception {

		// Connection with the BD
		Connection connection = null;

		// SQL statement to BD
		PreparedStatement statement = null;

		try {
			// The Driver that we are going to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// We open the connection with the BD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			// We are going to throw the statement
			// SQL_UPDATE_JORNADA = "UPDATE adx.jornada SET fecha = ?, horaInicio = ?,
			// horaFin = ? WHERE idJornada = ?";
			statement = connection.prepareStatement(SQL_UPDATE_CITA);

			// Insert data into parameters
			statement.setDate(1, workingDay.getDate()); // I don't know how to manage time variables.
			statement.setTime(2, workingDay.getStartTime()); // I don't know how to manage time variables.
			statement.setTime(3, workingDay.getEndTime()); // I don't know how to manage time variables.
			statement.setInt(4, workingDay.getId());

			// We execute
			statement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error with the BD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// We close in reverse of how we open them
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// Don't care
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// Don't care
			}
			;
		}

	}

	// A DOCTOR CAN DELETE AN APPOINTMENT
	public void delete(int id) throws SQLException, Exception {

		// Connection with the BD
		Connection connection = null;

		// SQL statement to BD
		PreparedStatement statement = null;

		try {
			// The Driver that we are going to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// We open the connection with the BD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			// We are going to throw the statement
			// SQL_DELETE_CITA = "DELETE FROM adx.cita WHERE idCita = ? ";
			statement = connection.prepareStatement(SQL_DELETE_CITA);

			// Instantiate the necessary classes
			Appointment appointment = new Appointment();

			// Insert data into parameters
			statement.setInt(1, appointment.getId());

			// We execute
			statement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error with the BD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("General Error - " + e.getMessage());
		} finally {
			// We close in reverse of how we open them
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// Don't care
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// Don't care
			}
			;
		}

	}

}
