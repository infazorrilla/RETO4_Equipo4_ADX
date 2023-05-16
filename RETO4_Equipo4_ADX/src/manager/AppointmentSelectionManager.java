package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.pojos.Ambulatory;
import model.pojos.Appointment;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import model.pojos.Sanitarian;
import model.pojos.TimeSlot;
import model.pojos.WorkingDay;
import model.utils.BBDDUtils;

/**
 * The class manages the all the functions in the selection of an appointment
 * 
 * @author adx
 *
 */
public class AppointmentSelectionManager {

	/**
	 * Selects all atribute 'nombre' from every row in DB's 'Ambulatorio' table
	 * 
	 * @return ArrayList of type String
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public ArrayList<String> selectAmbulatoryNames() throws SQLException, Exception {
		ArrayList<String> ret = new ArrayList<String>();

		ArrayList<Ambulatory> ambulatories = (ArrayList<Ambulatory>) selectAmbulatories();

		for (Ambulatory ambulatory : ambulatories) {
			ret.add(ambulatory.getName());
		}

		return ret;
	}

	/**
	 * Returns every row in 'Ambulatorio' table
	 * 
	 * @return ArrayList of type Ambulatory
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	private List<Ambulatory> selectAmbulatories() throws SQLException, Exception {
		ArrayList<Ambulatory> ret = null;

		String sql = "select * from ambulatorio";

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
					ret = new ArrayList<Ambulatory>();

				int id = resultSet.getInt("idAmbulatorio");
				String name = resultSet.getString("nombre");
				String address = resultSet.getString("direccion");
				String phoneNumber = resultSet.getString("telefono");

				Ambulatory ambulatory = new Ambulatory();
				ambulatory.setId(id);
				ambulatory.setName(name);
				ambulatory.setAddress(address);
				ambulatory.setPhoneNumber(phoneNumber);

				ret.add(ambulatory);
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
	 * Calls a function created in the DB called 'sacarFechasDisponibles' which
	 * returns a list of Dates in which sanitarians work in one ambulatory.
	 * 
	 * @param type       |String that means type of sanitarian ('Enfermeria' or
	 *                   'Medicina')
	 * @param ambulatory | Is an Object (Ambulatory)
	 * @return ArrayList<Date>
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public ArrayList<String> showAvailableDates(String type, Ambulatory ambulatory) throws SQLException, Exception {
		ArrayList<String> ret = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sql = "select j.fecha from jornada j join jornadasanitario js on j.idJornada=js.idJornada "
				+ "join sanitario s on js.dniSanitario=s.dniSanitario " + "where tipo= '" + type
				+ "' and s.idAmbulatorio = " + ambulatory.getId();

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<String>();

				Date date = resultSet.getDate("fecha");
				String sDate = date.toString();

				ret.add(sDate);
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
	 * Returns one ambulatory's data from the DB by its id
	 * 
	 * @param name String. Ambuatory's name
	 * @return Ambulatory with all the data
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public Ambulatory selectAmbulatory(String name) throws SQLException, Exception {
		Ambulatory ret = null;

		String sql = "select * from ambulatorio where nombre='" + name + "'";

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
					ret = new Ambulatory();

				int id = resultSet.getInt("idAmbulatorio");
				String address = resultSet.getString("direccion");
				String phoneNumber = resultSet.getString("telefono");

				ret.setId(id);
				ret.setName(name);
				ret.setAddress(address);
				ret.setPhoneNumber(phoneNumber);
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
	 * Calls a function from the DB that returns the ids of the time slots available
	 * for one sanitarian in one day
	 * 
	 * @param sanitarian String. Sanitarian's dni
	 * @param dateString date
	 * @return ArrayList of available TimeSlots
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public ArrayList<TimeSlot> showAvailableTimeSlots(Sanitarian sanitarian, String dateString)
			throws SQLException, Exception {

		ArrayList<TimeSlot> ret = null;

		String sql = "select idFranja from franja where idFranja NOT IN (SELECT f.idFranja "
				+ "from franja f left join citajornadafranja cjf on f.idFranja=cjf.idFranja "
				+ "join cita c on c.idCita=cjf.idCita " + "join sanitario s on c.dniSanitario=s.dniSanitario "
				+ "left join jornada j on j.idJornada=cjf.idJornada " + "where s.dniSanitario = '" + sanitarian.getDni()
				+ "' and j.fecha='" + dateString + "');";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<TimeSlot>();

				int id = resultSet.getInt("idFranja");

				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setId(id);

				ret.add(timeSlot);
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
	 * Returns Sanitarian of one type who work in a date.
	 * 
	 * @param type       String. Type of sanitarian ('Enfermeria', 'Medicina')
	 * @param date       String.
	 * @param ambulatory | Is an Object
	 * @return ArrayList<Sanitarian>
	 ** @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public ArrayList<Sanitarian> showAvailableSanitarianByDate(String type, String date, Ambulatory ambulatory)
			throws SQLException, Exception {
		ArrayList<Sanitarian> ret = null;

		String sql = "select js.dniSanitario, u.nombre, u.apellido "
				+ "from jornadasanitario js join jornada j on js.idJornada=j.idJornada "
				+ "join sanitario s on js.dniSanitario=s.dniSanitario " + "join usuario u on s.dniSanitario=u.dni "
				+ "WHERE fecha= '" + date + "' and tipo='" + type + "' and s.idAmbulatorio=" + ambulatory.getId();

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
					ret = new ArrayList<Sanitarian>();

				String dni = resultSet.getString("dniSanitario");
				String name = resultSet.getString("nombre");
				String surname = resultSet.getString("apellido");
				Sanitarian sanitarian = null;

				if (type.equalsIgnoreCase("Enfermeria")) {
					sanitarian = new Nurse();
					sanitarian.setDni(dni);
					sanitarian.setName(name);
					sanitarian.setSurname(surname);
				}
				if (type.equalsIgnoreCase("Medicina")) {
					sanitarian = new Doctor();
					sanitarian.setDni(dni);
					sanitarian.setName(name);
					sanitarian.setSurname(surname);
				}

				ret.add(sanitarian);
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
	 * Selects Sanitarian from 'Sanitario' table in DB by name and surname
	 * 
	 * @param name    String
	 * @param surname String
	 * @return Sanitarian
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public Sanitarian selectSanitarian(String name, String surname) throws SQLException, Exception {
		Sanitarian ret = null;

		String sql = "select * from sanitario s join usuario u on s.dniSanitario = u.dni where u.nombre= '" + name
				+ "' and u.apellido = '" + surname + "'";

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String type = resultSet.getString("tipo");
				String dni = resultSet.getString("dniSanitario");
				String gender = resultSet.getString("sexo");
				Date birthDate = resultSet.getDate("fechaNac");
				String password = resultSet.getString("contrasena");

				if (type.equalsIgnoreCase("Medicina")) {
					ret = new Doctor();
					ret.setDni(dni);
					ret.setName(name);
					ret.setSurname(surname);
					ret.setBirthDate(birthDate);
					ret.setGender(gender);
					ret.setPassword(password);
				} else {
					ret = new Nurse();
					ret.setDni(dni);
					ret.setName(name);
					ret.setSurname(surname);
					ret.setBirthDate(birthDate);
					ret.setGender(gender);
					ret.setPassword(password);
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
	 * Inserts a row in DB's 'citajornadafranja' table. Gets ids from Appointment,
	 * WorkingDay and TimeSlot
	 * 
	 * @param appointment | Is an Object (Appointment)
	 * @param workingDay  | Is an Object (WorkingDay)
	 * @param timeSlot    | Is an Object (TimeSlot)
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public void insertAppointmentWorkingDayTimeSlot(Appointment appointment, WorkingDay workingDay, TimeSlot timeSlot)
			throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			String sql = "insert into citajornadafranja ( idCita, idJornada, idFranja) values ('" + appointment.getId()
					+ "', '" + workingDay.getId() + "', '" + timeSlot.getId() + "')";

			statement.executeUpdate(sql);

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
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

	/**
	 * Selects the last appointment from a certain patient and a certain doctor.
	 * Used to get appointment's id to make an insert in 'citajornadafranja' table
	 * 
	 * @param patient    | Is an Object (Patient)
	 * @param sanitarian | Is an Object (Sanitarian)
	 * @param ambulatory | Is an Object (Ambulatory)
	 * @return Appointment | Is an Object
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public Appointment selectAppointment(Patient patient, Sanitarian sanitarian, Ambulatory ambulatory)
			throws SQLException, Exception {
		Appointment ret = null;

		String sql = "select * from cita where dniPaciente= '" + patient.getDni() + "' and dniSanitario ='"
				+ sanitarian.getDni() + "' and idAmbulatorio = " + ambulatory.getId();

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

				int idAppointment = resultSet.getInt("idCita");

				ret.setId(idAppointment);
				ret.setSanitarian(sanitarian);
				ret.setPatient(patient);
				ret.setAmbulatory(ambulatory);
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
	 * Selects the workingDay by date Used to get workingDay's id to make an insert
	 * in 'citajornadafranja' table
	 * 
	 * @param sDate String of the date
	 * @return WorkingDay | Is an Object
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public WorkingDay selectWorkingDay(String sDate) throws SQLException, Exception {
		WorkingDay ret = null;

		String sql = "select * from jornada where fecha= '" + sDate + "'";

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
					ret = new WorkingDay();

				int id = resultSet.getInt("idJornada");
				LocalTime startTime = LocalTime.parse(resultSet.getString("horaInicio"));
				LocalTime endTime = LocalTime.parse(resultSet.getString("horaFin"));
				Date date = null;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				ret.setId(id);
				ret.setDate(date);
				ret.setStartTime(startTime);
				ret.setEndTime(endTime);
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
	 * Selects the timeSlot by startTime Used to get timeSlot's id to make an insert
	 * in 'citajornadafranja' table
	 * 
	 * @param sStartTime string of startTime
	 * @return TimeSlot | Is an Object
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	public TimeSlot selectTimeSlot(String sStartTime) throws SQLException, Exception {
		TimeSlot ret = null;

		String sql = "select * from franja where horaInicio = '" + sStartTime + "'";

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
					ret = new TimeSlot();

				int id = resultSet.getInt("idFranja");
				LocalTime startTime = LocalTime.parse(sStartTime);
				LocalTime endTime = LocalTime.parse(resultSet.getString("horaFin"));

				ret.setId(id);
				ret.setStartTime(startTime);
				ret.setEndTime(endTime);
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
