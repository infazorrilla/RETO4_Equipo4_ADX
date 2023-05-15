package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

import manager.BlockUnlockPatientManager;
import manager.UserDataModificationManager;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import model.pojos.Sanitarian;
import model.pojos.User;
import view.View;

class ArrayListWindowTest {
	private JFrame frame = null;
	private JPanel panelBlockPatient = null;
	private JTable tableBlockPatients = null;

	private User user;
	private String userDNI;

	private BlockUnlockPatientManager blockUnlockPatientManager = new BlockUnlockPatientManager();
	private UserDataModificationManager userDataModificationManager = new UserDataModificationManager();

	@Test
	void testArrayListToPanelBlockPatient() {
		
		// We set values
//		userDNI="11111111A";
		userDNI="22222222A";
		
		// We declare and initialize the Array List
		ArrayList<Patient> patients = null;
		
		// FRAME
		frame = new JFrame();
		frame.setBounds(100, 100, 632, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false); // fixed dimensions
		frame.setLocationRelativeTo(null); // central position on the screen

		// PANEL | BLOCK OR UNLOCK PATIENTS
		panelBlockPatient = new JPanel();
		panelBlockPatient.setBackground(new Color(16, 169, 121));
		panelBlockPatient.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelBlockPatient);
		panelBlockPatient.setLayout(null);
		panelBlockPatient.setVisible(false);
		
		// SCROLLPANE + JTABLE | PANELBLOCKPATIENT
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 77, 443, 173);
		panelBlockPatient.add(scrollPane);
		tableBlockPatients = new JTable();
		tableBlockPatients.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Nombre", "Apellido", "Estado" }));
		scrollPane.setViewportView(tableBlockPatients);
		tableBlockPatients.setDefaultEditor(Object.class, null);
		
		// FUNCTION OR METHOD
		try {
			user = userDataModificationManager.identifyUserType(userDNI);
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", 0);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", 0);
		}

		
		if (user instanceof Sanitarian) {
			panelBlockPatient.setVisible(true);
			
			if (user instanceof Doctor) {
				try {
					patients = blockUnlockPatientManager.showPatientByAmbulatoryId(
							userDataModificationManager.selectDoctor(userDNI).getAmbulatory().getId());
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la BBDD.", "Error", 0);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);

				}
			}

			if (user instanceof Nurse) {
				try {
					patients = blockUnlockPatientManager.showPatientByAmbulatoryId(
							userDataModificationManager.selectNurse(userDNI).getAmbulatory().getId());
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la BBDD.", "Error", 0);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);

				}
			}
			DefaultTableModel model = (DefaultTableModel) tableBlockPatients.getModel();
			model.setRowCount(0);
			for (Patient patient : patients) {
				model.addRow(new String[] { patient.getDni(), patient.getName(), patient.getSurname(),
						blockUnlockPatientManager.patientState(patient.isBlocked()) });
			}
		}
				
		
/**
		// We create an array to compare the results | FOR DOCTOR
		ArrayList<Patient> patientsExpectedInDoctor = null;
		try {
			// We initialize the method that selects all
			patientsExpectedInDoctor = blockUnlockPatientManager.showPatientByAmbulatoryId(
					userDataModificationManager.selectDoctor(userDNI).getAmbulatory().getId());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
*/		
		// We create an array to compare the results | FOR NURSE
		ArrayList<Patient> patientsExpectedInNurse = null;
		try {
			// We initialize the method that selects all
			patientsExpectedInNurse = blockUnlockPatientManager.showPatientByAmbulatoryId(
					userDataModificationManager.selectNurse(userDNI).getAmbulatory().getId());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/** Here we show by console the content
		System.out.println("IN DOCTOR" + patientsExpectedInDoctor);
		System.out.println("IN NURSE" + patientsExpectedInNurse);
		System.out.println("ALL" + patients);
		*/
		
		// Here we check that they are not empty
		assertNotNull(patients);
//		assertNotNull(patientsExpectedInDoctor);
		assertNotNull(patientsExpectedInNurse);
		
		
		// Here we compare the sizes
//		assertEquals(patientsExpectedInDoctor.size(), tableBlockPatients.getRowCount());
		assertEquals(patientsExpectedInNurse.size(), tableBlockPatients.getRowCount());
	}

}
