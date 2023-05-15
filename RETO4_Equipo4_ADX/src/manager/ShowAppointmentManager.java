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

import model.pojos.Ambulatory;
import model.pojos.Appointment;
import model.pojos.AppointmentWorkingDayTimeSlot;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import model.pojos.Sanitarian;
import model.pojos.TimeSlot;
import model.pojos.WorkingDay;
import model.utils.BBDDUtils;

public class ShowAppointmentManager {

	/**
	 * Returns a list of all the Appointment from a Patient from the DB
	 * 
	 * @return an ArrayList of Appointments
	 */
	public List<Appointment> select(String dni) throws SQLException, Exception {
		ArrayList<Appointment> ret = null;

		String sql = "select  c.idCita, s.dniSanitario, s.tipo, f.horaInicio, j.fecha "
				+ "from sanitario s join cita c on s.dniSanitario=c.dniSanitario "
				+ "join citajornadafranja cjf on c.idCita=cjf.idCita "
				+ "join franja f on  f.idFranja=cjf.idFranja "
				+ "join jornada j on j.idJornada=cjf.idJornada "
				+ "where c.dniPaciente like '" + dni +"'";

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

				Sanitarian sanitarian = null;
			
				

				// We take out the columns of the RS
				int id = resultSet.getInt("idCita");
				String dniSanitarian = resultSet.getString("dniSanitario");
				String type = resultSet.getString("tipo");
				if(type.equalsIgnoreCase("Enfermeria")) {
					sanitarian = new Nurse();
					sanitarian.setDni(dniSanitarian);
					sanitarian.setType(type);
					
				} else {
					sanitarian = new Doctor();
					sanitarian.setDni(dniSanitarian);
					sanitarian.setType(type);
				}
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
				patient.setDni(dni);
				appointmentWorkingDayTimeSlot.setWorkingDay(workingDay);
				
				
				appointment.setId(id);
				appointment.setPatient(patient);
				appointment.setTimeSlot(timeSlot);
				appointment.setSanitarian(sanitarian);
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
