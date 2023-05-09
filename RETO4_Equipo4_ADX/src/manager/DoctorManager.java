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
import model.pojos.Doctor;
import model.utils.BBDDUtils;

public class DoctorManager extends AbstractManager<Doctor> {

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

	@Override
	public Doctor select(int staffNum) throws SQLException, Exception {
		Doctor ret = null;

		String sql = "SELECT * FROM `sanitario` WHERE numPersonal = ? AND tipo = 'Medicina';";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, staffNum);
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

	@Override
	public List<Doctor> select() throws SQLException, Exception {
		// Returns all rows of the ambulatory table
		// If there is nothing, it returns NULL

		ArrayList<Doctor> ret = null;

		// SQL we want to launch
		String sql = "SELECT * FROM `sanitario` WHERE tipo = 'Medicina'";

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
			resultSet = statement.executeQuery(sql);

			// It is not possible to know how many things the resultSet has returned.
			// You have to go 1 by 1 and save everything in its object
			while (resultSet.next()) {

				// If necessary, we initialize the list
				if (null == ret)
					ret = new ArrayList<Doctor>();

				Doctor doctor = new Doctor();

				// We take out the columns of the RS
				String dni = resultSet.getString("dniSanitario");
				int staffNum = resultSet.getInt("numPersonal");
				float salary = resultSet.getFloat("salario");
				int idAmbulatory = resultSet.getInt("idAmbulatorio");
				String type = resultSet.getString("tipo");
				String speciality = resultSet.getString("especialidad");
				String mir = resultSet.getString("MIR");

				// We put the data into Doctor
				Ambulatory ambulatory = new Ambulatory();
				ambulatory.setId(idAmbulatory);

				doctor.setDni(dni);
				doctor.setStaffNum(staffNum);
				doctor.setSalary(salary);
				doctor.setAmbulatory(ambulatory);
				doctor.setType(type);
				doctor.setSpeciality(speciality);
				if (mir.equals("true")) {
					doctor.setMir(true);
				} else {
					doctor.setMir(false);
				}

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

	@Override
	public void insert(Doctor doctor) throws SQLException, Exception {
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

			// SQL structure
			String sql = "INSERT INTO " + SANITARIAN_TABLE
					+ " (`dniSanitario`, `numPersonal`, `salario`, `idAmbulatorio`, `tipo`, `especialidad`, `MIR`, `categoria`, `EIR`) VALUES ('"
					+ doctor.getDni() + "', '" + doctor.getStaffNum() + "', '" + doctor.getSalary() + "', 'NULL', '"
					+ doctor.getType() + "', '" + doctor.getSpeciality() + "', '" + doctor.isMir() + "', NULL, NULL);";

			// We execute
			statement.executeUpdate(sql);

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

	@Override
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

			String speciality = null;

			// SQL structure.
			// The ?s are filled in below
			String sql = "update " + SANITARIAN_TABLE + " set especialidad = ? where dniSanitario = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, speciality);
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

	@Override
	public void delete(int staffNum) throws SQLException, Exception {
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
			String sql = "delete from " + SANITARIAN_TABLE + " where numPersonal = " + staffNum;
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
