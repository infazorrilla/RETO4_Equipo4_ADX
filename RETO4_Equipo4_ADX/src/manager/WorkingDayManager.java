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


import model.pojos.WorkingDay;
import model.utils.BBDDUtils;

public class WorkingDayManager extends AbstractManager<WorkingDay> {

	public static final String WORKINGDAY_TABLE = "jornada";

	@Override
	public WorkingDay select(int id) throws SQLException, Exception{
		WorkingDay ret = null;

		String sql = "select * from " + WORKINGDAY_TABLE + " where id=" +id;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		Class.forName(BBDDUtils.DRIVER_LOCAL);
		connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			if (null == ret)
				ret = new WorkingDay();
			
			String weekDay = resultSet.getString("diaSemana");
			LocalTime startTime = LocalTime.parse(resultSet.getString("horaInicio"));
			LocalTime endTime = LocalTime.parse(resultSet.getString("horaFin"));

			ret.setId(id);
			ret.setWeekDay(weekDay);
			ret.setStartTime(startTime);
			ret.setEndTime(endTime);
		}

		resultSet.close();

		if (statement != null)
			statement.close();

		if (connection != null)
			connection.close();
		return ret;
	}

	@Override
	public List<WorkingDay> select() throws SQLException, Exception {
		ArrayList<WorkingDay> ret = null;

		String sql = "select * from " + WORKINGDAY_TABLE;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		Class.forName(BBDDUtils.DRIVER_LOCAL);
		connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			if (null == ret)
				ret = new ArrayList<WorkingDay>();

			int id = resultSet.getInt("id");
			String weekDay = resultSet.getString("diaSemana");
			LocalTime startTime = LocalTime.parse(resultSet.getString("horaInicio"));
			LocalTime endTime = LocalTime.parse(resultSet.getString("horaFin"));

			WorkingDay workingDay = new WorkingDay();
			workingDay.setId(id);
			workingDay.setWeekDay(weekDay);
			workingDay.setStartTime(startTime);
			workingDay.setEndTime(endTime);

			ret.add(workingDay);
		}

		resultSet.close();

		if (statement != null)
			statement.close();

		if (connection != null)
			connection.close();

		return ret;
	}

	@Override
	public void insert(WorkingDay workingDay) throws SQLException, Exception {
		Connection connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
				BBDDUtils.PASS_LOCAL);
		Statement statement = connection.createStatement();
		Class.forName(BBDDUtils.DRIVER_LOCAL);

		String sql = "insert into " + WORKINGDAY_TABLE + " (dniSanitario, diaSemana, horaInicio, horaFin) values ('"
				+ /* workingDay.getSanitarian.getDniSanitario() + "', '" + */ workingDay.getStartTime() + "', '"
				+ workingDay.getEndTime() + "')";

		statement.executeUpdate(sql);

		if (statement != null)
			statement.close();

		if (connection != null)
			connection.close();
	}

	@Override
	public void update(WorkingDay workingDay) throws SQLException, Exception {
		Connection connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
				BBDDUtils.PASS_LOCAL);
		PreparedStatement preparedStatement = null;

		Class.forName(BBDDUtils.DRIVER_LOCAL);

//		String sql = "update "+APPOINTMENT_TABLE+" set idFranja = ? where id = ?";
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
		String sql = "delete from " + WORKINGDAY_TABLE + " where id = " + id;
		preparedStatement = connection.prepareStatement(sql);

		preparedStatement.executeUpdate();

		if (preparedStatement != null)
			preparedStatement.close();
		if (connection != null)
			connection.close();
	}

}
