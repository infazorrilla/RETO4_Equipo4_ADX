package manager;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import manager.DoctorManager;
//import manager.NurseManager;
import manager.PatientManager;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import model.pojos.User;

public class UserDataModificationManager {
	
	/**
	 * Identifies which kind of user enters the application
	 * 
	 * @param userDNI, String with user's id
	 * @return doctor, nurse or patient
	 * @throws SQLException
	 * @throws Exception
	 */
	public User identifyUserType(String userDNI) throws SQLException, Exception {
		
		User ret = null;

//		Doctor doctor = new DoctorManager().select(userDNI);
//		if (null != doctor) {
//			ret = doctor;
//		}

//		Nurse nurse = new NurseManager().select(userDNI);
//		if (null != nurse) {
//			ret = nurse;
//		}

		Patient patient = new PatientManager().select(userDNI);
		if (null != patient) {
			ret = patient;
		}
		return ret;
	}

	/**
	 * Returns a boolean to enable (or not) ModifyPatientOkButton. Checks if the
	 * Strings from fields tfPasswd, address and phoneNumber are filled or not. If any of of
	 * them is filled, the method returns true, and the button enables.
	 * 
	 * @param tfPasswd,    a String with the field's content.
	 * @param address,     a String with the field's content.
	 * @param phoneNumber, a String with the field's content.
	 * @return boolean
	 */
	public boolean enableModifyPatientOkButton(String password, String address, String phoneNumber) {
		boolean ret = false;

		if ((password.length() == 0) && (address.length() == 0) && (phoneNumber.length() == 0)) {
			ret = false;
		} else {
			ret = true;
		}

		return ret;
	}

	/**
	 * Returns a boolean to enable (or not) ModifySanitarianOkButton. Checks if the
	 * String from field tfPasswd is filled or not. If it's filled, the method returns true, and the button enables.
	 * @param password, a String with the field's content.
	 * @return boolean
	 */
	public boolean enableModifySanitarianOkButton(String password) {
		boolean ret = false;

		if (password.length() > 0) {
			ret = true;
		} else {
			ret = false;
		}

		return ret;
	}
	
	public boolean isPhoneNumber(String phoneNumber) {
		boolean ret=false;
		
		 Pattern pattern = Pattern.compile("^\\d{9}$");
		 Matcher matcher = pattern.matcher(phoneNumber);
		 
		 if(matcher.matches())
			 ret=true;
		
		return ret;
	}

}
