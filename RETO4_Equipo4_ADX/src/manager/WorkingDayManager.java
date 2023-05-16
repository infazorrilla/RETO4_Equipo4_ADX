package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.pojos.WorkingDay;
import model.utils.BBDDUtils;

/**
 * The class manages the basic functions (CRUD) in databases
 * 
 * @author adx
 *
 */
public class WorkingDayManager extends AbstractManager<WorkingDay> {

	public static final String WORKINGDAY_TABLE = "jornada";

	/**
	 * Returns one WorkingDay from the DB by its id
	 * 
	 * @param id int
	 * @return WorkingDay object
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	@Override
	public WorkingDay select(int id) throws SQLException, Exception {
		WorkingDay ret = null;

		String sql = "select * from " + WORKINGDAY_TABLE + " where idJornada=" + id;

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

				Date date = resultSet.getDate("fecha");
				LocalTime startTime = LocalTime.parse(resultSet.getString("horaInicio"));
				LocalTime endTime = LocalTime.parse(resultSet.getString("horaFin"));

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
	 * Returns a list of all the WorkingDays from the DB
	 * 
	 * @return an ArrayList of WorkingDays
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	@Override
	public List<WorkingDay> select() throws SQLException, Exception {
		ArrayList<WorkingDay> ret = null;

		String sql = "select * from " + WORKINGDAY_TABLE;

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
					ret = new ArrayList<WorkingDay>();

				int id = resultSet.getInt("idJornada");
				java.sql.Date dateSql = resultSet.getDate("fecha");
				java.util.Date dateJava = dateSql;
				LocalTime startTime = LocalTime.parse(resultSet.getString("horaInicio"));
				LocalTime endTime = LocalTime.parse(resultSet.getString("horaFin"));

				WorkingDay workingDay = new WorkingDay();
				workingDay.setId(id);
				workingDay.setDate(dateJava);
				workingDay.setStartTime(startTime);
				workingDay.setEndTime(endTime);

				ret.add(workingDay);
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
	 * Inserts one WorkingDay into the DB
	 * 
	 * @param one object WorkingDay
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	@Override
	public void insert(WorkingDay workingDay) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			long milliseconds = workingDay.getDate().getTime();
			java.sql.Date date = new java.sql.Date(milliseconds);

			Time startTime = Time.valueOf(workingDay.getStartTime());
			Time endTime = Time.valueOf(workingDay.getEndTime());

			String sql = "insert into " + WORKINGDAY_TABLE + " (idJornada, fecha, horaInicio, horaFin) values ('"
					+ workingDay.getId() + "', '" + date + "', '" + startTime + "', '" + endTime + "')";

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
	 * Updates ''horaInicio' of a WorkingDay from the DB at '11:00' by its id
	 * 
	 * @param one object WorkingDay
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	@Override
	public void update(WorkingDay workingDay) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);

			String time = "11:00";

			String sql = "update " + WORKINGDAY_TABLE + " set horaInicio = ? where idJornada = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, time);
			preparedStatement.setInt(2, workingDay.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
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
	}

	/**
	 * Deletes the row in 'Jornada' table of the DB by its id
	 * 
	 * @param id int
	 * @throws SQLException | If there is an error on DB
	 * @throws Exception    | If there is a generic error
	 */
	@Override
	public void delete(int id) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);
			String sql = "delete from " + WORKINGDAY_TABLE + " where idJornada = " + id;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			throw e;
		} finally {
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
	}

}
