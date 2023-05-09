package jUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manager.BlockUnlockPatientManager;
import manager.UserDataModificationManager;
import model.pojos.Patient;

class BlockUnlockPatientManagerTest {

	BlockUnlockPatientManager manager = new BlockUnlockPatientManager();

	@Test
	void testShowPatientByAmbulatoryId() {
		ArrayList<Patient> patients = null;
		try {
			patients = manager.showPatientByAmbulatoryId(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(2, patients.size());
	}

	@Test
	void testPatientState() {
		// Case 1
		Patient patient = new Patient();
		patient.setBlocked(true);
		String caseOne = manager.patientState(patient.isBlocked());
		assertEquals("Bloqueado/a", caseOne);

		// Case 2
		patient.setBlocked(false);
		String caseTwo = manager.patientState(patient.isBlocked());
		assertEquals("Desbloqueado/a", caseTwo);
	}

	@Test
	void testBlockPatient() {
		UserDataModificationManager userDataModificationManager = new UserDataModificationManager();
		Patient patient = new Patient();
		try {
			patient = userDataModificationManager.selectPatient("00000000A");
			manager.blockPatient(patient);
			patient = userDataModificationManager.selectPatient("00000000A");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(true, patient.isBlocked());
	}

	@Test
	void testUnlockPatient() {
		UserDataModificationManager userDataModificationManager = new UserDataModificationManager();
		Patient patient = new Patient();
		try {
			patient = userDataModificationManager.selectPatient("00000000A");
			manager.unlockPatient(patient);
			patient = userDataModificationManager.selectPatient("00000000A");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(false, patient.isBlocked());
	}

}
