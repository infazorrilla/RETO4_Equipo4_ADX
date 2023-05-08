package manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.pojos.Ambulatory;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Sanitarian;
import model.pojos.TimeSlot;
import model.utils.BBDDUtils;

public class AppointmentSelectionManager {

	/**
	 * Selects all atribute 'nombre' from every row in DB's 'Ambulatorio' table
	 * 
	 * @return ArrayList of type String
	 * @throws SQLException
	 * @throws Exception
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
	 * @throws SQLException
	 * @throws Exception
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
	 * @param type String that means type of sanitarian ('Enfermeria' or 'Medicina')
	 * @param ambulatory Ambulatory
	 * @return ArrayList<Date>
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList<Date> showAvailableDates(String type, Ambulatory ambulatory) throws SQLException, Exception {
		ArrayList<Date> ret = null;

		Connection connection = null;
		ResultSet resultSet = null;
		CallableStatement stmt = null;

		String query = "{call sacarFechasDisponibles(?,?)}";

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			stmt = connection.prepareCall(query);
			stmt.setString(1, type);
			stmt.setInt(2, ambulatory.getId());

			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Date>();

				Date date = resultSet.getDate("Fecha");

				ret.add(date);
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
				if (stmt != null)
					stmt.close();
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
	 * @param name String. Ambuatory's name
	 * @return Ambulatory with all the data
	 * @throws SQLException
	 * @throws Exception
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
	 * Calls a function from the DB that returns the ids of the time slots available for one sanitarian in one day
	 * @param sanitarian String. Sanitarian's dni
	 * @param dateString date
	 * @return ArrayList of available TimeSlots
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList<Integer> showAvailableTimeSlots(String sanitarian, String dateString)
			throws SQLException, Exception {
		ArrayList<Integer> ret = null;

		Connection connection = null;
		ResultSet resultSet = null;
		CallableStatement stmt = null;

		String query = "{call sacarCitasDisponibles(?,?)}";

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			stmt = connection.prepareCall(query);
			stmt.setString(1, sanitarian);
			stmt.setString(2, dateString);

			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Integer>();

				int id = resultSet.getInt("Franja");

				ret.add(id);
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
				if (stmt != null)
					stmt.close();
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
	 * @param type String. Type of sanitarian ('Enfermeria', 'Medicina')
	 * @param date String.
	 * @return
	 * @throws SQLException, Exception 
	 */
	public ArrayList<Sanitarian> showAvailableSanitarianByDate(String type, String date) throws SQLException, Exception {
		ArrayList<Sanitarian> ret = null;

		String sql = "select js.dniSanitario, u.nombre from jornadasanitario js join jornada j on js.idJornada=j.idJornada "
				+ "join sanitario s on js.dniSanitario=s.dniSanitario join usuario u on s.dniSanitario=u.dni "
				+ "WHERE fecha= '" + date + "' and tipo='" + type + "'";

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
				String nombre = resultSet.getString("nombre");
				Sanitarian sanitarian = null;

				if (type.equalsIgnoreCase("Enfermeria")) {
					sanitarian = new Nurse();
					sanitarian.setDni(dni);
					sanitarian.setName(nombre);
				}
				if (type.equalsIgnoreCase("Medicina")) {
					sanitarian = new Doctor();
					sanitarian.setDni(dni);
					sanitarian.setName(nombre);
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

}
