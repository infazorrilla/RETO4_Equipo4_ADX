package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.pojos.Ambulatory;
import model.pojos.Nurse;
import model.utils.BBDDUtils;

public class NurseManager {

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
	public static final String SQL_SELECT_ONE = "SELECT * FROM `sanitario`  s JOIN `usuario` u ON s.dniSanitario = u.dni WHERE s.dniSanitario = ? AND tipo = 'Enfermeria'";
	public static final String SQL_SELECT_ALL = "SELECT * FROM `sanitario`  s JOIN `usuario` u ON s.dniSanitario = u.dni WHERE tipo = 'Enfermeria'";
	public static final String SQL_UPDATE = "UPDATE `sanitario` set categoria = ? where dniSanitario = ?";

	public Nurse select(String dni) throws SQLException, Exception {
		Nurse ret = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = connection.prepareStatement(SQL_SELECT_ONE);
			preparedStatement.setString(1, dni);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret)
					ret = new Nurse();

				// We put the data into Doctor
				int idAmbulatory = resultSet.getInt("idAmbulatorio");
				Ambulatory ambulatory = new Ambulatory();
				ambulatory.setId(idAmbulatory);

				ret.setDni(resultSet.getString("dniSanitario"));
				ret.setStaffNum(resultSet.getInt("numPersonal"));
				ret.setSalary(resultSet.getFloat("salario"));
				ret.setAmbulatory(ambulatory);
				ret.setType(resultSet.getString("tipo"));
				ret.setCategory(resultSet.getString("categoria"));
				String eir = resultSet.getString("EIR");
				if (eir.equals("true")) {
					ret.setEir(true);
				} else {
					ret.setEir(false);
				}
				// User information
				ret.setName(resultSet.getString("nombre"));
				ret.setSurname(resultSet.getString("apellido"));
				ret.setGender(resultSet.getString("sexo"));
				ret.setBirthDate(resultSet.getDate("fechaNac"));
				ret.setPassword(resultSet.getString("contrasena"));

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

	public List<Nurse> select() throws SQLException, Exception {
		// Returns all rows of the ambulatory table
		// If there is nothing, it returns NULL

		ArrayList<Nurse> ret = null;

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
					ret = new ArrayList<Nurse>();

				Nurse nurse = new Nurse();

				// We take out the columns of the RS

				// We put the data into Doctor

				nurse.setDni(resultSet.getString("dniSanitario"));
				nurse.setStaffNum(resultSet.getInt("numPersonal"));
				nurse.setSalary(resultSet.getFloat("salario"));

				int idAmbulatory = resultSet.getInt("idAmbulatorio");
				Ambulatory ambulatory = new Ambulatory();
				ambulatory.setId(idAmbulatory);
				nurse.setAmbulatory(ambulatory);

				nurse.setType(resultSet.getString("tipo"));
				nurse.setCategory(resultSet.getString("categoria"));
				String eir = resultSet.getString("EIR");
				if (eir.equals("true")) {
					nurse.setEir(true);
				} else {
					nurse.setEir(false);
				}

				// + User information
				nurse.setName(resultSet.getString("nombre"));
				nurse.setSurname(resultSet.getString("apellido"));
				nurse.setGender(resultSet.getString("sexo"));
				nurse.setBirthDate(resultSet.getDate("fechaNac"));
				nurse.setPassword(resultSet.getString("contrasena"));

				// We save it in ret
				ret.add(nurse);
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

	public void insert(Nurse nurse) throws SQLException, Exception {
		// Connection with the BD
		Connection connection = null;

		// SQL statement to BD
		Statement statement = null;

		try {
			// The Driver that we are going to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// We open the connection with the BD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			// We are going to throw the statement
			statement = connection.createStatement();

			// Insert data into parameters | USER TABLE
			long milliseconds = nurse.getBirthDate().getTime();
			java.sql.Date date = new java.sql.Date(milliseconds);
			String sqlUser = "INSERT INTO `usuario` (`dni`, `nombre`, `apellido`, `sexo`, `fechaNac`, `contrasena`) VALUES ('"
					+ nurse.getDni() + "', '" + nurse.getName() + "', '" + nurse.getSurname() + "','"
					+ nurse.getGender() + "','" + date + "','" + nurse.getPassword() + "')";

			// Insert data into parameters | SANITARIAN TABLE
			String sqlSanitarian = "INSERT INTO `sanitario` (`dniSanitario`, `numPersonal`, `salario`, `idAmbulatorio`, `tipo`, `especialidad`, `MIR`, `categoria`, `EIR`) VALUES ('"
					+ nurse.getDni() + "', '" + nurse.getStaffNum() + "', '" + nurse.getSalary() + "', '"
					+ nurse.getAmbulatory().getId() + "', 'Enfermeria', NULL, NULL, '" + nurse.getCategory() + "', '"
					+ nurse.isEir() + "');";

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

	public void update(Nurse nurse) throws SQLException, Exception {
		// Connection with the BD
		Connection connection = null;

		// SQL statement to BD
		PreparedStatement preparedStatement = null;

		try {
			// The Driver that we are going to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// We open the connection with the BD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			preparedStatement = connection.prepareStatement(SQL_UPDATE);

			preparedStatement.setString(1, nurse.getCategory());
			preparedStatement.setString(2, nurse.getDni());

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
