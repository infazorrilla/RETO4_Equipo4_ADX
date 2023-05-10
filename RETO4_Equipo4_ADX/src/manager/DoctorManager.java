package manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.pojos.Ambulatory;
import model.pojos.Doctor;
import model.utils.BBDDUtils;

public class DoctorManager {

	// RUBRICA | SPRINT 2 | INDIVIDUAL
	// Management of the Table:
	/**
	 * Select: The equivalent of the operation “select * from table” | Insert:
	 * Insert a new entry in the table | Update: Update an entry in the table |
	 * Delete: Delete an entry in the table by its Id | Common errors have to be
	 * reported: empty table, does not exist, I could not delete, etc.
	 */
	// Complex operations
	/**
	 * At least one non-elementary operation is performed on the table chosen by the
	 * student | An operation that affects several tables, for example
	 */

	public static final String SANITARIAN_TABLE = "sanitario";
	public static final String SQL_SELECT_ONE = "SELECT * FROM `sanitario`  s JOIN `usuario` u ON s.dniSanitario = u.dni WHERE s.dniSanitario = ? AND tipo = 'Medicina'";
	public static final String SQL_SELECT_ALL = "SELECT * FROM `sanitario`  s JOIN `usuario` u ON s.dniSanitario = u.dni WHERE tipo = 'Medicina'";

	public static final String SQL_INSERT_USER = "INSERT INTO `usuario` (`dni`, `nombre`, `apellido`, `sexo`, `fechaNac`, `contrasena`) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String SQL_INSERT_SANITARIAN = "INSERT INTO `sanitario` (`dniSanitario`, `numPersonal`, `salario`, `idAmbulatorio`, `tipo`, `especialidad`, `MIR`, `categoria`, `EIR`) VALUES (?, ?, ?, ?, 'Medicina', ?, ?, NULL, NULL)";

	public static final String SQL_UPDATE = "";
	public static final String SQL_DELETE = "";

	public Doctor select(String dni) throws SQLException, Exception {
		Doctor ret = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = connection.prepareStatement(SQL_SELECT_ONE);
			preparedStatement.setString(1, dni); // We insert in "s.dniSanitario = ?"
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new Doctor();

				// We put the data into Doctor
				int idAmbulatory = resultSet.getInt("idAmbulatorio");
				Ambulatory ambulatory = new Ambulatory();
				ambulatory.setId(idAmbulatory);

				ret.setDni(resultSet.getString("dniSanitario"));
				ret.setStaffNum(resultSet.getInt("numPersonal"));
				ret.setSalary(resultSet.getFloat("salario"));
				ret.setAmbulatory(ambulatory);
				ret.setType(resultSet.getString("tipo"));
				ret.setSpeciality(resultSet.getString("especialidad"));
				String mir = resultSet.getString("MIR");
				if (mir.equals("true")) {
					ret.setMir(true);
				} else {
					ret.setMir(false);
				}
				// User information
				ret.setName(resultSet.getString("nombre"));
				ret.setSurname(resultSet.getString("apellido"));
				ret.setGender(resultSet.getString("sexo"));
				ret.setBirthDate(resultSet.getDate("fechaNac"));
				ret.setPassword(resultSet.getString("contrasena"));

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
		return ret;
	}

	public List<Doctor> select() throws SQLException, Exception {
		// Returns all rows of the ambulatory table
		// If there is nothing, it returns NULL

		ArrayList<Doctor> ret = null;

		// The connection with BBDD
		Connection connection = null;

		// SQL statement against BBDD
		// Result set will contain everything returned by the BBDD
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// The Driver we are going to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// Open the connection with BBDD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			// Let's launch the sentence. . .
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_SELECT_ALL);

			// It is not possible to know how many things the resultSet has returned.
			// You have to go 1 by 1 and save everything in its object
			while (resultSet.next()) {

				// If necessary, we initialize the list
				if (null == ret)
					ret = new ArrayList<Doctor>();

				Doctor doctor = new Doctor();

				// We put the data into Doctor
				doctor.setDni(resultSet.getString("dniSanitario"));
				doctor.setStaffNum(resultSet.getInt("numPersonal"));
				doctor.setSalary(resultSet.getFloat("salario"));

				int idAmbulatory = resultSet.getInt("idAmbulatorio");
				Ambulatory ambulatory = new Ambulatory();
				ambulatory.setId(idAmbulatory);
				doctor.setAmbulatory(ambulatory);

				doctor.setType(resultSet.getString("tipo"));
				doctor.setSpeciality(resultSet.getString("especialidad"));
				String mir = resultSet.getString("MIR");
				if (mir.equals("true")) {
					doctor.setMir(true);
				} else {
					doctor.setMir(false);
				}

				// + User information
				doctor.setName(resultSet.getString("nombre"));
				doctor.setSurname(resultSet.getString("apellido"));
				doctor.setGender(resultSet.getString("sexo"));
				doctor.setBirthDate(resultSet.getDate("fechaNac"));
				doctor.setPassword(resultSet.getString("contrasena"));

				// We save it in ret
				ret.add(doctor);
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

	public void insert(Doctor doctor) throws SQLException, Exception {
		// Connection with the DB
		Connection connection = null;

		// SQL statement to DB
		Statement statement = null;

		try {
			// The Driver that we are going to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// We open the connection with the BD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			// We are going to throw the statement
			statement = connection.createStatement();

			// Insert data into parameters | USER TABLE
			long milliseconds = doctor.getBirthDate().getTime();
			java.sql.Date date = new java.sql.Date(milliseconds);
			String sqlUser = "INSERT INTO `usuario` (`dni`, `nombre`, `apellido`, `sexo`, `fechaNac`, `contrasena`) VALUES ('"
					+ doctor.getDni() + "', '" + doctor.getName() + "', '" + doctor.getSurname() + "','"
					+ doctor.getGender() + "','" + date + "','" + doctor.getPassword() + "')";

			// Insert data into parameters | SANITARIAN TABLE
			String sqlSanitarian = "INSERT INTO `sanitario` (`dniSanitario`, `numPersonal`, `salario`, `idAmbulatorio`, `tipo`, `especialidad`, `MIR`, `categoria`, `EIR`) VALUES ('"
					+ doctor.getDni() + "', '" + doctor.getStaffNum() + "', '" + doctor.getSalary() + "', '"
					+ doctor.getAmbulatory().getId() + "', 'Medicina', '" + doctor.getSpeciality() + "', '"
					+ doctor.isMir() + "', NULL, NULL)";

			// We execute
			statement.executeUpdate(sqlUser);
			statement.executeUpdate(sqlSanitarian);

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

	public void update(Doctor doctor) throws SQLException, Exception {
		// Connection with the BD
		Connection connection = null;

		// SQL statement to BD
		PreparedStatement preparedStatement = null;

		try {
			// The Driver that we are going to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// We open the connection with the BD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			

			// SQL structure.
			// The ?s are filled in below
			String sql = "update " + SANITARIAN_TABLE + " set especialidad = ? where dniSanitario = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, doctor.getSpeciality());
			preparedStatement.setString(2, doctor.getDni());

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

	public void delete(String dni) throws SQLException, Exception {
		// Connection with the BD
		Connection connection = null;

		// SQL statement to BD
		PreparedStatement preparedStatement = null;

		try {
			// The Driver that we are going to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// We open the connection with the BD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			// SQL structure
			String sql = "delete from " + SANITARIAN_TABLE + " where dniSanitario = '" + dni + "'";
			preparedStatement = connection.prepareStatement(sql);

			// We execute
			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error with the BD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("General Error - " + e.getMessage());
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
}
