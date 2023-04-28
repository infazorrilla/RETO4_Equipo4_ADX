package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.pojos.WorkingDaySanitarian;
import model.utils.BBDDUtils;

public class WorkingDaySanitarianManager{
	
	public static final String WORKINGDAYSANITARIAN_TABLE = "jornadasanitario";

	public WorkingDaySanitarian select(String id) throws SQLException, Exception {
		WorkingDaySanitarian ret = null;

		String sql = "select * from " + WORKINGDAYSANITARIAN_TABLE + " where dniSanitario =" + id;

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
					ret = new WorkingDaySanitarian();

				int idWorkingDay = resultSet.getInt("idJornada");
				String dniSanitarian = resultSet.getString("dniSanitario");
				
				//TODO
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

	public List<WorkingDaySanitarian> select() throws SQLException, Exception {
		ArrayList<WorkingDaySanitarian> ret = null;

		String sql = "select * from " + WORKINGDAYSANITARIAN_TABLE;

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
					ret = new ArrayList<WorkingDaySanitarian>();

				int idWorkingDay = resultSet.getInt("idJornada");
				String dniSanitarian = resultSet.getString("dniSanitario");

				//TODO
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

	public void insert(WorkingDaySanitarian workingDaySanitarian) throws SQLException, Exception {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			statement = connection.createStatement();
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			
			String sql = "insert into " + WORKINGDAYSANITARIAN_TABLE + " (idJornada, dniSanitario) values ('"
					+  workingDaySanitarian.getWorkingDay().getId() + "', '" +  workingDaySanitarian.getSanitarian().getDni() +  "')";

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

//	No puedo cambiar nada, porque los dos ids son foreign keys
	public void update(WorkingDaySanitarian workingDaySanitarian) throws SQLException, Exception {
	}

	public void delete(String id) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
					BBDDUtils.PASS_LOCAL);
			preparedStatement = null;
	
			Class.forName(BBDDUtils.DRIVER_LOCAL);
			String sql = "delete from " + WORKINGDAYSANITARIAN_TABLE + " where dniSanitario = " + id;
			
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
