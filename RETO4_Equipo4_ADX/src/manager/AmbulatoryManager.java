package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.pojos.Ambulatory;
import model.utils.BBDDUtils;

public class AmbulatoryManager {

	// CRUD: Create, Read, Update and Delete

	public static final String AMBULATORY_TABLE = "ambulatorio";

	// create = insert
	public void create(Ambulatory ambulatory) throws SQLException, Exception {

		// Connection with the BD
		Connection connection = null;

		// SQL statement to BD
		Statement statement = null;

		try {
			// The Driver that we are going to to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// We open the connection with the BD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			// We are going to throw the statement
			statement = connection.createStatement();

			// SQL structure
			String sql = "insert into " + AMBULATORY_TABLE + " (id) VALUES ('" + ambulatory.getId() + "')";

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

	// read = select
	public void read(int id) throws SQLException, Exception {

	}

	public void update(Ambulatory ambulatory) throws SQLException, Exception {

		// Connection with the BD
		Connection connection = null;

		// SQL statement to BD
		PreparedStatement preparedStatement = null;

		try {
			// The Driver that we are going to to use
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			// We open the connection with the BD
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);

			// Montamos la SQL. Las ? se rellenan a continuacion
			String sql = "update t_alumno set edad = ? where nombre = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, nuevaEdad);
			preparedStatement.setString(2, ejemplo.getNombre());

			// La ejecutamos...
			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
			;
		}

	}

	public void delete(int id) throws SQLException, Exception {

	}

}
