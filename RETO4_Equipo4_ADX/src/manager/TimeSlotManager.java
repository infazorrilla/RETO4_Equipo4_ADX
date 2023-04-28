package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.pojos.TimeSlot;
import model.utils.BBDDUtils;

public class TimeSlotManager extends AbstractManager<TimeSlot> {

	public static final String TIMESLOT_TABLE = "franja";

	@Override
	public TimeSlot select(int id) throws SQLException, Exception {
		TimeSlot ret = null;

		String sql = "select * from " + TIMESLOT_TABLE + " where idFranja=" + id;

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

				LocalTime startTime = LocalTime.parse(resultSet.getString("horaInicio"));
				LocalTime endTime = LocalTime.parse(resultSet.getString("horaFin"));

				ret.setId(id);
				ret.setStartTime(startTime);
				ret.setEndTime(endTime);
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
	public List<TimeSlot> select() throws SQLException, Exception {
		ArrayList<TimeSlot> ret = null;

		String sql = "select * from " + TIMESLOT_TABLE;

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
					ret = new ArrayList<TimeSlot>();

				int id = resultSet.getInt("id");
				LocalTime startTime = LocalTime.parse(resultSet.getString("horaInicio"));
				LocalTime endTime = LocalTime.parse(resultSet.getString("horaFin"));

				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setId(id);
				timeSlot.setStartTime(startTime);
				timeSlot.setEndTime(endTime);

				ret.add(timeSlot);
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
	public void insert(TimeSlot timeSlot) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			Class.forName(BBDDUtils.DRIVER_LOCAL);

			String sql = "insert into " + TIMESLOT_TABLE + " (horaInicio, horaFin) values ('" + timeSlot.getStartTime()
					+ "', '" + timeSlot.getEndTime() + "')";

			statement.executeUpdate(sql);

		} catch (SQLException sqle) {
		} catch (Exception e) {
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

	@Override
	public void update(TimeSlot timeSlot) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);

			String time = "11:00";

			String sql = "update " + TIMESLOT_TABLE + " set horaInicio = ? where idFranja = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, time);
			preparedStatement.setInt(2, timeSlot.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
		} catch (Exception e) {
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

	@Override
	public void delete(int id) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);
			String sql = "delete from " + TIMESLOT_TABLE + " where idFranja = " + id;
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
		} catch (Exception e) {
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
