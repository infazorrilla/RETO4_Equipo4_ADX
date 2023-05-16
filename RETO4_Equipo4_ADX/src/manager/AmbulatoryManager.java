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
import model.utils.BBDDUtils;

/**
 * The class manages the basic functions (CRUD) in databases
 * 
 * @author dannyelfloyd
 *
 */
public class AmbulatoryManager extends AbstractManager<Ambulatory> {

	/**
	 * 
	 */
	public static final String AMBULATORY_TABLE = "ambulatorio";

	/**
	 * Returns data from the database about Ambulatory with a ID as a conditional
	 * parameter or Null
	 * 
	 * @param ID | Is a Integer
	 * @return Ambulatory or Null | Is an Object
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	@Override
	public Ambulatory select(int id) throws SQLException, Exception {
		Ambulatory ret = null;

		String sql = "SELECT * FROM `ambulatorio` WHERE idAmbulatorio = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (null == ret)
					ret = new Ambulatory();

				// We put the data into Doctor
				ret.setId(resultSet.getInt("idAmbulatorio"));
				ret.setName(resultSet.getString("nombre"));
				ret.setAddress(resultSet.getString("direccion"));
				ret.setPhoneNumber(resultSet.getString("telefono"));

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

	/**
	 * Returns a List from the database about Ambulatory or Null
	 * 
	 * @return List<Ambulatory> or Null
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	@Override
	public List<Ambulatory> select() throws SQLException, Exception {
		// Returns all rows of the ambulatory table
		// If there is nothing, it returns NULL

		ArrayList<Ambulatory> ret = null;

		// SQL we want to launch
		String sql = "SELECT * FROM `ambulatorio`";

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
					ret = new ArrayList<Ambulatory>();

				Ambulatory ambulatory = new Ambulatory();

				// We take out the columns of the RS
				int id = resultSet.getInt("idAmbulatorio");
				String name = resultSet.getString("nombre");
				String phoneNumber = resultSet.getString("telefono");
				String address = resultSet.getString("direccion");

				// We put the data into Example
				ambulatory.setId(id);
				ambulatory.setName(name);
				ambulatory.setPhoneNumber(phoneNumber);
				ambulatory.setAddress(address);

				// We save it in ret
				ret.add(ambulatory);
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

	/**
	 * Enter an object in the database as an output parameter
	 * 
	 * @param ambulatory | Is an Object
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	@Override
	public void insert(Ambulatory ambulatory) throws SQLException, Exception {
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
			String sql = "INSERT INTO `ambulatorio` (`idAmbulatorio`, `nombre`, `direccion`, `telefono`) VALUES ('"
					+ ambulatory.getId() + "', '" + ambulatory.getName() + "', '" + ambulatory.getAddress() + "', '"
					+ ambulatory.getPhoneNumber() + "');";
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

	/**
	 * Update in the database using an object as output parameter
	 * 
	 * @param ambulatory | Is an Object
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	@Override
	public void update(Ambulatory ambulatory) throws SQLException, Exception {

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
			String sql = "update " + AMBULATORY_TABLE
					+ " set nombre = ?, direccion = ?, telefono = ? where idAmbulatorio = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ambulatory.getName());
			preparedStatement.setString(2, ambulatory.getAddress());
			preparedStatement.setString(3, ambulatory.getPhoneNumber());
			preparedStatement.setInt(4, ambulatory.getId());

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

	/**
	 * Delete in the database via a String conditional output parameter
	 * 
	 * @param id | Is a String
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
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
			String sql = "delete from " + AMBULATORY_TABLE + " where idAmbulatorio = " + id;
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
