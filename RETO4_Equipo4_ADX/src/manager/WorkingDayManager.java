package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.pojos.WorkingDay;
import model.utils.BBDDUtils;

public class WorkingDayManager extends AbstractManager<WorkingDay> {

	public static final String WORKINGDAY_TABLE = "jornada";

	@Override
	public WorkingDay select(int id) throws SQLException, Exception {
		WorkingDay ret = null;

		String sql = "select * from " + WORKINGDAY_TABLE + " where id=" + id;

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

				int id = resultSet.getInt("id");
				java.sql.Date date = resultSet.getDate("fecha");
				LocalTime startTime = LocalTime.parse(resultSet.getString("horaInicio"));
				LocalTime endTime = LocalTime.parse(resultSet.getString("horaFin"));

				WorkingDay workingDay = new WorkingDay();
				workingDay.setId(id);
				workingDay.setDate(date);
				workingDay.setStartTime(startTime);
				workingDay.setEndTime(endTime);

				ret.add(workingDay);
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
	public void insert(WorkingDay workingDay) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			
//			Necesito el gestor de Doctor y de Nurse para hacer un select

			String sql = "insert into " + WORKINGDAY_TABLE + " (dniSanitario, fecha, horaInicio, horaFin) values ('"
					+ /* workingDay.getSanitarian.getDniSanitario() + "', '" + */ workingDay.getStartTime() + "', '"
					+ workingDay.getEndTime() + "')";

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
	public void update(WorkingDay workingDay) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);
			
			String time = "11:00";

			 String sql = "update "+ WORKINGDAY_TABLE +" set horaInicio = ? where id = ?";
			 preparedStatement = connection.prepareStatement(sql);
			 preparedStatement.setString(1, time);
			 preparedStatement.setInt(2, workingDay.getId());

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
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
					BBDDUtils.PASS_LOCAL);
			preparedStatement = null;
	
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			String sql = "delete from " + WORKINGDAY_TABLE + " where id = " + id;
			preparedStatement = connection.prepareStatement(sql);
	
			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {  
		} catch(Exception e){ 
		} finally {
			try {
				if (preparedStatement != null) 
					preparedStatement.close(); 
			} catch(Exception e){ 
			};
			try {
				if (connection != null) 
					connection.close(); 
			} catch(Exception e){ 
			};					
		}
	}

}
