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

import model.pojos.Ambulatory;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.utils.BBDDUtils;

public class NurseManager extends AbstractManager<Nurse> {

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
	public Nurse select(int id) throws SQLException, Exception {
		Nurse ret = null;

		String sql = "select * from " + SANITARIAN_TABLE + " where id=" + id + "' and tipo='Enfermeria'";

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
					ret = new Nurse();

				ret.setStaffNum(id);

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
	public List<Nurse> select() throws SQLException, Exception {
		// Returns all rows of the ambulatory table
		// If there is nothing, it returns NULL

		ArrayList<Nurse> ret = null;

		// SQL we want to launch
		String sql = "select * from" + SANITARIAN_TABLE;

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
					ret = new ArrayList<Nurse>();

				Nurse nurse = new Nurse();

				// We take out the columns of the RS
				String dni = resultSet.getString("dni");
				int staffNum = resultSet.getInt("numPersonal");
				float salary = resultSet.getFloat("salario");
				int idAmbulatory = resultSet.getInt("idAmbulatorio");
				String type = resultSet.getString("tipo");
				String category = resultSet.getString("categoria");
				String mir = resultSet.getString("MIR");
				String name = resultSet.getString("nombre");
				String surname = resultSet.getString("apellido");
				String gender = resultSet.getString("sexo");
				Date birthDate = resultSet.getDate("fechaNac");
				String password = resultSet.getString("contrasena");

				// We put the data into Doctor
				Ambulatory ambulatory = new Ambulatory();
				ambulatory.setId(idAmbulatory);

				nurse.setDni(dni);
				nurse.setStaffNum(staffNum);
				nurse.setSalary(salary);
				nurse.setAmbulatory(ambulatory);
				nurse.setType(type);
				nurse.setCategory(category);
				if (mir.equalsIgnoreCase("true")) {
					nurse.setEir(true);
				} else {
					nurse.setEir(false);
				}
				nurse.setName(name);
				nurse.setSurname(surname);
				nurse.setGender(gender);
				nurse.setBirthDate(birthDate);
				nurse.setPassword(password);

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

	@Override
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

			// SQL structure
			String sql = "INSERT INTO " + SANITARIAN_TABLE
					+ " (`dniSanitario`, `numPersonal`, `salario`, `idAmbulatorio`, `tipo`, `especialidad`, `MIR`, `categoria`, `EIR`) VALUES ('"
					+ nurse.getDni() + "', '" + nurse.getStaffNum() + "', '" + nurse.getSalary() + "', 'NULL', '"
					+ nurse.getType() + "', 'NULL', 'NULL', " + nurse.getCategory() + ", " + nurse.isEir() + ");";

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

			String category = null;

			// SQL structure.
			// The ?s are filled in below
			String sql = "update " + SANITARIAN_TABLE + " set categoria = ? where dni = ?";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, category);
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

	@Override
	public void delete(int id) throws SQLException, Exception {
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
			String sql = "delete from " + SANITARIAN_TABLE + " where dniSanitario = '" + id;
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
