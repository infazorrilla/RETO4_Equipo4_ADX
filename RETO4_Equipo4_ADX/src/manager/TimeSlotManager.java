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

		String sql = "select * from " + TIMESLOT_TABLE + " where id=" + id;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		Class.forName(BBDDUtils.DRIVER_LOCAL);
		connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			if (null == ret)
				ret = new TimeSlot();

			LocalTime startTime = LocalTime.parse(resultSet.getString("horaInicio"));
			LocalTime endTime = LocalTime.parse(resultSet.getString("horaFin"));
			String available = resultSet.getString("disponible");
			boolean isAvailable = Boolean.valueOf(available);

			ret.setId(id);
			ret.setStartTime(startTime);
			ret.setEndTime(endTime);
			ret.setAvailable(isAvailable);
		}

		resultSet.close();

		if (statement != null)
			statement.close();

		if (connection != null)
			connection.close();
		return ret;
	}

	@Override
	public List<TimeSlot> select() throws SQLException, Exception {
		ArrayList<TimeSlot> ret = null;

		String sql = "select * from " + TIMESLOT_TABLE;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

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
			String available = resultSet.getString("disponible");
			boolean isAvailable = Boolean.valueOf(available);

			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setId(id);
			timeSlot.setStartTime(startTime);
			timeSlot.setEndTime(endTime);
			timeSlot.setAvailable(isAvailable);

			ret.add(timeSlot);
		}

		resultSet.close();

		if (statement != null)
			statement.close();

		if (connection != null)
			connection.close();

		return ret;
	}

	@Override
	public void insert(TimeSlot timeSlot) throws SQLException, Exception {
		Connection connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
				BBDDUtils.PASS_LOCAL);
		Statement statement = connection.createStatement();
		Class.forName(BBDDUtils.DRIVER_LOCAL);

		String sql = "insert into " + TIMESLOT_TABLE + " (idJornada, horaInicio, horaFin, disponible) values ('"
				+ timeSlot.getWorkingDay().getId() + "', '" + timeSlot.getStartTime() + "', '" + timeSlot.getEndTime()
				+ "', '" + timeSlot.isAvailable() + "')";

		statement.executeUpdate(sql);

		if (statement != null)
			statement.close();

		if (connection != null)
			connection.close();

	}

	@Override
	public void update(TimeSlot timeSlot) throws SQLException, Exception {
		Connection connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
				BBDDUtils.PASS_LOCAL);
		PreparedStatement preparedStatement = null;

		Class.forName(BBDDUtils.DRIVER_LOCAL);

//		String sql = "update "+TIMESLOT_TABLE+" set idFranja = ? where id = ?";
//		preparedStatement = connection.prepareStatement(sql);
//		preparedStatement.setInt(1, 1);
//		preparedStatement.setint(2, appointment.getId());

		preparedStatement.executeUpdate();

		if (preparedStatement != null)
			preparedStatement.close();

		if (connection != null)
			connection.close();
	}

	@Override
	public void delete(int id) throws SQLException, Exception {
		Connection connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
				BBDDUtils.PASS_LOCAL);
		PreparedStatement preparedStatement = null;

		Class.forName(BBDDUtils.DRIVER_LOCAL);
		String sql = "delete from " + TIMESLOT_TABLE + " where id = " + id;
		preparedStatement = connection.prepareStatement(sql);

		preparedStatement.executeUpdate();

		if (preparedStatement != null)
			preparedStatement.close();
		if (connection != null)
			connection.close();

	}

}
