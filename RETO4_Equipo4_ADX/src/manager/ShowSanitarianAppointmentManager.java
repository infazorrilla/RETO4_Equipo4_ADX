package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.pojos.Appointment;
import model.pojos.AppointmentWorkingDayTimeSlot;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import model.pojos.Sanitarian;
import model.pojos.TimeSlot;
import model.pojos.WorkingDay;
import model.utils.BBDDUtils;

public class ShowSanitarianAppointmentManager {

	public List<Appointment> select(String dni) throws SQLException, Exception {
		ArrayList<Appointment> ret = null;

		String sql = "select c.idCita, c.dniPaciente, j.fecha, f.horaInicio"
				+ "from cita c join citajornadafranja cjf on c.idCita=cjf.idCita"
				+ "join jornada j on j.idJornada=cjf.idJornada"
				+ "join franja f on f.idFranja=cjf.idFranja"
				+ "where c.dniSanitario like '" + dni + "'";

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
					ret = new ArrayList<Appointment>();

				
			
				

				// We take out the columns of the RS
				int id = resultSet.getInt("idCita");
				String dniPatient = resultSet.getString("dniPaciente");
				Date date = resultSet.getDate("fecha");
				WorkingDay workingDay = new WorkingDay();
				workingDay.setDate(date);
				LocalTime startTime = LocalTime.parse(resultSet.getString("horaInicio"));
				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setStartTime(startTime);

				// We put the data into Example
				Appointment appointment = new Appointment();
				Patient patient = new Patient();
				AppointmentWorkingDayTimeSlot appointmentWorkingDayTimeSlot = new AppointmentWorkingDayTimeSlot();
				patient.setDni(dniPatient);
				appointmentWorkingDayTimeSlot.setWorkingDay(workingDay);
				
				
				appointment.setId(id);
				appointment.setPatient(patient);
				appointment.setTimeSlot(timeSlot);
				appointment.setPatient(patient);
				appointment.setAppointmentWorkingDayTimeSlot(appointmentWorkingDayTimeSlot);
			
				
				

				// We save it in ret
				
				ret.add(appointment);
				
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
}
