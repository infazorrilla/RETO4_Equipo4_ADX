package EDE.amaia.sprint2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.utils.BBDDUtils;

public class DBUsers {

		private ArrayList <String> getAllUsers (){
			ArrayList <String> ret = null;
			
			String sql = "select user from mysql.user;";
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			
			try {
				Class.forName(BBDDUtils.DRIVER_LOCAL);
				connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sql);
				
				while(resultSet.next()) {
					if (null == ret)
						ret = new ArrayList<String>  ();
					
					String user = resultSet.getString("user");
	                ret.add(user);
				}
			} catch (SQLException sqle) {  
				System.out.println("Error con la BBDD - " + sqle.getMessage());
			} catch(Exception e){ 
				System.out.println("Error generico - " + e.getMessage());
			} finally {
				try {
					if (resultSet != null) 
						resultSet.close(); 
				} catch(Exception e){ 
				};
				try {
					if (statement != null) 
						statement.close(); 
				} catch(Exception e){ 
				};
				try {
					if (connection != null) 
						connection.close(); 
				} catch(Exception e){ 
				};					
			}
			return ret;
		}

		public static void main(String[] args) {
			DBUsers dBUsers = new DBUsers();
			ArrayList<String> users = dBUsers.getAllUsers();
			
			for(String user: users) {
				System.out.println(user);
			}
		}
}
