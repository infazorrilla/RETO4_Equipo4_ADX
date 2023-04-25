package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.pojos.Appointment;
import model.pojos.Patient;
import model.pojos.Sanitarian;
import model.pojos.TimeSlot;
import model.utils.BBDDUtils;

public class AppointmentManager extends AbstractManager<Appointment>{
	
	public static final String APPOINTMENT_TABLE = "cita";
	
	@Override
	public List<Appointment> select() throws SQLException, Exception{
		ArrayList<Appointment> ret = null;
		String sql = "select * from " + APPOINTMENT_TABLE;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		Class.forName(BBDDUtils.DRIVER_LOCAL);
		connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			if (null == ret)
				ret = new ArrayList<Appointment>();

			int id = resultSet.getInt("id");
			String type = resultSet.getString("tipo");
			
			//No sé si va a ser médico o enfermero
//			Sanitarian sanitarian = new Sanitarian();
			
			Patient patient = new Patient();
			int patientId = resultSet.getInt("dniPaciente");
//			patient.set
			//No puedo hacerlo porque está mal el POJO
			
//			TimeSlot = timeSlot.getInt("idFranja");
//			patient.set
			//No puedo hacerlo porque no está corregida la BBDD

//			Appointment appointment = new Appointment(id, type, sanitarians, patient, timeSlot);


//			ret.add(appointment);
		}

		resultSet.close();

		if (statement != null)
			statement.close();

		if (connection != null)
			connection.close();

		return ret;
	}

	@Override
	public void insert(Appointment appointment) throws SQLException, Exception {
		Connection connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
				BBDDUtils.PASS_LOCAL);
		Statement statement = connection.createStatement();
		Class.forName(BBDDUtils.DRIVER_LOCAL);

//		Cómo meto el id del Sanitario (puede ser Médico o Enfermero)
		String sql = "insert into " + APPOINTMENT_TABLE + " (tipo, dniPaciente, dniSanitario, idFranja) values ('" + appointment.getType()
				+ "', '" + /*appointment.getPatient().getId() +*/ "', '" + /*appointment.getSanitarian() +*/ "')";
//		No puedo meter el paciente porque el POJO está mal

		statement.executeUpdate(sql);

		if (statement != null)
			statement.close();

		if (connection != null)
			connection.close();

	}

	@Override
	public void delete(Appointment appointment) throws SQLException, Exception {
		Connection connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL,
				BBDDUtils.PASS_LOCAL);
		PreparedStatement preparedStatement = null;

		Class.forName(BBDDUtils.DRIVER_LOCAL);
		String sql = "delete from " + APPOINTMENT_TABLE + " where id = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, appointment.getId());

		preparedStatement.executeUpdate();

		if (preparedStatement != null)
			preparedStatement.close();
		if (connection != null)
			connection.close();
	}

//	No sé hacer este
	@Override
	public void update(Appointment appointment, TimeSlot timeSlot) throws SQLException, Exception {
		Connection connection = DriverManager.getConnection(BBDDUtils.URL_LOCAL, BBDDUtils.USER_LOCAL, BBDDUtils.PASS_LOCAL);
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
	

}
