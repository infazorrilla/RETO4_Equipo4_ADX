package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.pojos.Patient;
import model.utils.BBDDUtils;

public class BlockUnlockPatientManager {

	/**
	 * Returns an arrayList of patients who have appointments in one ambulatory (selected by ambulatory's id)
	 * @param id int
	 * @return ArrayList<Patient>
	 * @throws SQLException, Exception 
	 */
	public ArrayList<Patient> showPatientByAmbulatoryId(int id) throws SQLException, Exception {
		ArrayList<Patient> ret = null;

		String sql = "select ci.dniPaciente from citajornadafranja c " + "join jornada j on  c.idJornada=j.idJornada "
				+ "join franja f on c.idFranja=f.idFranja " + "join cita ci on c.idCita=ci.idCita "
				+ "join paciente p on ci.dniPaciente = p.dniPaciente" + " join usuario u on p.dniPaciente=u.dni"
				+ " join sanitario s on ci.dniSanitario = s.dniSanitario" + " where s.idAmbulatorio=" + id
				+ " group by p.dniPaciente";

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
					ret = new ArrayList<Patient>();

				String dni = resultSet.getString("dniPaciente");

				UserDataModificationManager userDataModificationManager	= new UserDataModificationManager();	
				Patient patient = userDataModificationManager.selectPatient(dni);

				ret.add(patient);
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
	 * Returns "Bloqueado/a" boolean if true and "Desbloqueado/a" if false
	 * @param blocked boolean
	 * @return String
	 */
	public String patientState(boolean blocked) {
		String ret;

		if (blocked == true) {
			ret = "Bloqueado/a";
		} else {
			ret = "Desbloqueado/a";
		}

		return ret;
	}

	/**
	 * Sets 'true' in 'Paciente' table's 'bloqueado'.
	 * @param patient Patient
	 * @throws SQLException, Exception 
	 */
	public void blockPatient(Patient patient) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);

			String sql = "update paciente set bloqueado = ? where dniPaciente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "true");
			preparedStatement.setString(2, patient.getDni());

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
	 * Sets 'false' in 'Paciente' table's 'bloqueado'.
	 * @param patient Patient
	 * @throws SQLException, Exception 
	 */
	public void unlockPatient(Patient patient) throws SQLException, Exception {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
			preparedStatement = null;

			Class.forName(BBDDUtils.DRIVER_LOCAL);

			String sql = "update paciente set bloqueado = ? where dniPaciente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "false");
			preparedStatement.setString(2, patient.getDni());

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
