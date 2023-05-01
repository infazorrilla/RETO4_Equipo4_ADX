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

import model.pojos.Ambulatory;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Sanitarian;
import model.pojos.WorkingDay;
import model.pojos.WorkingDaySanitarian;
import model.utils.BBDDUtils;

public class WorkingDaySanitarianManager {
	
	public WorkingDaySanitarian select(String id) throws SQLException, Exception {
		WorkingDaySanitarian ret = null;

		String sql = "select * from jornadaSanitario where dniSanitario=" + id;

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
				String dni = resultSet.getString("dniSanitario");

				WorkingDay workingDay = new WorkingDay();
				workingDay.setId(idWorkingDay);
				Sanitarian sanitarian = new Doctor();
				sanitarian.setDni(dni);
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

		String sql = "SELECT * FROM jornadasanitario";

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

				WorkingDaySanitarian workingDaySanitarian = new WorkingDaySanitarian();

				int idWorkingDay = resultSet.getInt("idJornada");
				String dniSanitarian = resultSet.getString("dniSanitario");

				WorkingDay workingDay = new WorkingDay();
				workingDay.setId(idWorkingDay);
				
//				DoctorManager doctorManager=new DoctorManager();
//				NurseManager nurseManager = new NurseManager();
				
				Sanitarian sanitarian = null;
				
//				if(null!= doctorManager.select(dniSanitarian)) {
//					sanitarian = new Doctor();
//					sanitarian.setDni(dniSanitarian);
//				}else if (null!= nurseManager.select(dniSanitarian)) {
//					sanitarian = new Nurse();
//					sanitarian.setDni(dniSanitarian);
//				}

				workingDaySanitarian.setWorkingDay(workingDay);
				workingDaySanitarian.setSanitarian(sanitarian);

				ret.add(workingDaySanitarian);
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

	public List<Sanitarian> selectSanitarian(int id) throws SQLException, Exception {
		ArrayList<Sanitarian> ret = null;

		String sql = "SELECT dni, nombre, apellido, sexo, fechaNac, contrasena, numPersonal, salario, idAmbulatorio, tipo, "
				+ "especialidad, mir, categoria, eir "
				+ "FROM usuario u join sanitario s on u.dni=s.dniSanitario join jornadasanitario j on u.dni=j.dniSanitario "
				+ "where idJornada =" + id;

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
					ret = new ArrayList<Sanitarian>();

				String dniSanitarian = resultSet.getString("dni");
				String name = resultSet.getString("nombre");
				String surname = resultSet.getString("apellido");
				String gender = resultSet.getString("sexo");
				Date date = resultSet.getDate("fechaNac");
				String password = resultSet.getString("constrasena");
				int staffNum = resultSet.getInt("numPersonal");
				float salary = resultSet.getFloat("salario");
				int idAmbulatory = resultSet.getInt("idAmbulatorio");
				String type = resultSet.getString("tipo");
				String speciality = resultSet.getString("especialidad");
				boolean mir = Boolean.parseBoolean(resultSet.getString("mir"));
				String category = resultSet.getString("categoria");
				boolean eir = Boolean.parseBoolean(resultSet.getString("eir"));

				Ambulatory ambulatory = new Ambulatory();
				ambulatory.setId(idAmbulatory);

				if (type.equals("Medicina")) {
					Doctor doctor = new Doctor();
					doctor.setDni(dniSanitarian);
					doctor.setName(name);
					doctor.setSurname(surname);
					doctor.setGender(gender);
					doctor.setBirthDate(date);
					doctor.setPassword(password);
					doctor.setStaffNum(staffNum);
					doctor.setSalary(salary);
					doctor.setType(type);
					doctor.setSpeciality(speciality);
					doctor.setMir(mir);
					doctor.setAmbulatory(ambulatory);
					ret.add(doctor);
				} else {
					Nurse nurse = new Nurse();
					nurse.setDni(dniSanitarian);
					nurse.setName(name);
					nurse.setSurname(surname);
					nurse.setGender(gender);
					nurse.setBirthDate(date);
					nurse.setPassword(password);
					nurse.setStaffNum(staffNum);
					nurse.setSalary(salary);
					nurse.setType(type);
					nurse.setCategory(category);
					nurse.setEir(eir);
					nurse.setAmbulatory(ambulatory);
					ret.add(nurse);
				}
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

			String sql = "insert into jornadasanitario (idJornada, dniSanitario) values ('"
					+ workingDaySanitarian.getWorkingDay().getId() + "', '"
					+ workingDaySanitarian.getSanitarian().getDni() + "')";

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
	
	public void delete(WorkingDaySanitarian workingDaySanitarian) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);
			String sql = "delete from jornadasanitario where idJornada = " + workingDaySanitarian.getWorkingDay().getId() + " "
					+ "and dniSanitario = '"+ workingDaySanitarian.getSanitarian().getDni()+ "'";
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

//	No puedo cambiar nada, porque los dos ids son foreign keys
	public void update(WorkingDaySanitarian workingDaySanitarian) throws SQLException, Exception {
	}

}
