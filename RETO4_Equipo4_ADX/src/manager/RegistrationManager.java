package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.pojos.Ambulatory;
import model.pojos.Patient;
import model.utils.BBDDUtils;

public class RegistrationManager  {

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

	public static final String AMBULATORY_TABLE = "ambulatorio";

	
	/**
	 * Returns one Ambulatory from the DB by its name
	 * 
	 * @param name String
	 * @return Ambulatory object
	 */
	public Ambulatory select(String name) throws SQLException, Exception {
		Ambulatory ret = null;

		
		String sql = "select * from ambulatorio where nombre=  '" + name + "'";

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

				int id = resultSet.getInt("idSanitario");
				String address = resultSet.getString("direccion");
				String phoneNumber = resultSet.getString("telefono");
				
				ret.setName(name);
				ret.setAddress(address);
				ret.setId(id);
				ret.setPhoneNumber(phoneNumber);

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
	
	/**
	 * Returns a list of all the Ambulatory from the DB
	 * 
	 * @return an ArrayList of Ambulatory
	 */
	public List<Ambulatory> select() throws SQLException, Exception {
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
	
	//private ArrayList<int>
}