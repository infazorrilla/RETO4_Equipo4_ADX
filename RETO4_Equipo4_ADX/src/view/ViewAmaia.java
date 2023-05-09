package view;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import manager.AppointmentManager;
import manager.AppointmentSelectionManager;
import manager.BlockUnlockPatientManager;
import manager.TimeSlotManager;
import manager.UserDataModificationManager;
import model.pojos.Ambulatory;
import model.pojos.Appointment;
//import manager.DoctorManager;
//import manager.NurseManager;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import model.pojos.Sanitarian;
import model.pojos.TimeSlot;
import model.pojos.User;
import model.pojos.WorkingDay;

public class ViewAmaia {
	private UserDataModificationManager userDataModificationManager;
	private AppointmentSelectionManager appointmentSelectionManager;
	private BlockUnlockPatientManager blockDesblockPatientManager;
	private TimeSlotManager timeSlotManager;
	private AppointmentManager appointmentManager;
	private String userDNI;
	private User user;
	private String wantedAmbulatory;
	private String wantedSanitarian;
	private ArrayList<String> dates;

	public JFrame frame;
	private JPanel panelLogin;
	private JPanel panelModifyPatient;
	private JPanel panelModifySanitarian;
	private JTextField tfModifyPatientAddress;
	private JTextField tfModifyPatientPhoneNumber;
	private JPasswordField tfModifyPatientPassword;
	private JButton btnModifyPatientOk;
	private JButton btnModifyPatientUnsubscribe;
	private JButton btnModifyPatientCancel;
	private JButton btnLoginOk;
	private JButton btnLoginCancel;
	private JButton btnLoginResgistration;
	private JTextField tfModifySanitarianDNI;
	private JPasswordField tfModifySanitarianPassword;
	private JButton btnModifySanitarianOk;
	private JButton btnModifySanitarianCancel;
	private JButton btnModifySanitarianUnsubscribe;
	private JRadioButton rdbtnSelectNurse;
	private JButton btnSelectAppointmentAmbulatoryTypeOk;
	private JButton btnSelectAppointmentAmbulatoryTypeCancel;
	private JPanel panelSelectAppointmentAmbulatoryType;
	private JPanel panelSelectAppointmentDateTimeSlot;
	private JTable tableSelectTimeSlot;
	private JComboBox<String> cbSelectAppointmentDate;
	private JComboBox<String> cbSelectAppointmentSanitarian;
	private JPanel panelBlockPatient;
	private JTable tableBlockPatients;
	private JButton btnBlockPatientOk;
	private JTable tablePatientData;
	private JScrollPane scrollPane_1;

	/**
	 * Create the application.
	 */
	public ViewAmaia() {
//		patientManager = new PatientManager();
//		doctorManager = new DoctorManager();
//		nurseManager = new NurseManager();
		userDataModificationManager = new UserDataModificationManager();
		appointmentSelectionManager = new AppointmentSelectionManager();
		blockDesblockPatientManager = new BlockUnlockPatientManager();
		timeSlotManager = new TimeSlotManager();
		appointmentManager = new AppointmentManager();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();

		frame.setBounds(100, 100, 632, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

//		Login panel
		panelLogin = new JPanel();
		panelLogin.setBackground(new Color(0, 128, 192));
		panelLogin.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelLogin);
		panelLogin.setLayout(null);
		panelLogin.setVisible(true);

		JLabel lblLogin = new JLabel("Inicio de sesión");
		lblLogin.setBounds(250, 66, 133, 14);
		panelLogin.add(lblLogin);

		JLabel lblLoginDNI = new JLabel("DNI:");
		lblLoginDNI.setBounds(154, 121, 109, 14);
		panelLogin.add(lblLoginDNI);

		JLabel lblLoginPassword = new JLabel("Contraseña:");
		lblLoginPassword.setBounds(154, 165, 109, 14);
		panelLogin.add(lblLoginPassword);

		JTextField tfLoginUser = new JTextField();
		tfLoginUser.setBounds(250, 118, 171, 20);
		panelLogin.add(tfLoginUser);
		tfLoginUser.setColumns(10);

		JPasswordField tfLoginPassword = new JPasswordField();
		tfLoginPassword.setBounds(250, 162, 171, 20);
		panelLogin.add(tfLoginPassword);

		btnLoginOk = new JButton("Aceptar");
		btnLoginOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDNI = tfLoginUser.getText();

				try {
					user = userDataModificationManager.identifyUserType(userDNI);
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", 0);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", 0);
				}

				if (user instanceof Patient) {
//					panelModifyPatient.setVisible(true);
					panelSelectAppointmentAmbulatoryType.setVisible(true);

					// Panel Modify Patient
					tablePatientData.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "", "" }));

					DefaultTableModel model = (DefaultTableModel) tablePatientData.getModel();
					model.setRowCount(0);

					model.addRow(new String[] { "DNI", user.getDni() });
					model.addRow(new String[] { "Nombre", user.getName() });
					model.addRow(new String[] { "Apellido", user.getSurname() });
					model.addRow(new String[] { "Fecha de nacimiento", user.getBirthDate().toString() });
					model.addRow(new String[] { "Dirección", ((Patient) user).getAddress() });
					model.addRow(new String[] { "Teléfono", ((Patient) user).getPhoneNumber() });
				}

				if (user instanceof Doctor) {
					tfModifySanitarianDNI.setText(userDNI);
//					panelModifySanitarian.setVisible(true);

					// Panel Block Patient
					panelBlockPatient.setVisible(true);
					ArrayList<Patient> patients = null;
					try {
						patients = blockDesblockPatientManager.showPatientByAmbulatoryId(
								userDataModificationManager.selectDoctor(userDNI).getAmbulatory().getId());
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(null, "Se ha producido un error con la BBDD.", "Error", 0);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);

					}
					DefaultTableModel model = (DefaultTableModel) tableBlockPatients.getModel();
					model.setRowCount(0);

					for (Patient patient : patients) {
						model.addRow(new String[] { patient.getDni(), patient.getName(), patient.getSurname(),
								blockDesblockPatientManager.patientState(patient.isBlocked()) });
					}

				}

				if (user instanceof Nurse) {
					tfModifySanitarianDNI.setText(userDNI);
//					panelModifySanitarian.setVisible(true);

					// Panel Block Patient
					panelBlockPatient.setVisible(true);
					ArrayList<Patient> patients = null;
					try {
						patients = blockDesblockPatientManager.showPatientByAmbulatoryId(
								userDataModificationManager.selectDoctor(userDNI).getAmbulatory().getId());
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(null, "Se ha producido un error con la BBDD.", "Error", 0);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);

					}
					DefaultTableModel model = (DefaultTableModel) tableBlockPatients.getModel();
					model.setRowCount(0);

					for (Patient patient : patients) {
						model.addRow(new String[] { patient.getDni(), patient.getName(),
								blockDesblockPatientManager.patientState(patient.isBlocked()) });
					}
				}

				panelLogin.setVisible(false);
			}
		});
		btnLoginOk.setBounds(204, 245, 89, 23);
		panelLogin.add(btnLoginOk);

		btnLoginCancel = new JButton("Cancelar");
		btnLoginCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btnLoginCancel.setBounds(346, 245, 89, 23);
		panelLogin.add(btnLoginCancel);

		btnLoginResgistration = new JButton("Registro");
		btnLoginResgistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btnLoginResgistration.setBounds(442, 10, 154, 37);
		panelLogin.add(btnLoginResgistration);

//		Modify Patient panel
		panelModifyPatient = new JPanel();
		panelModifyPatient.setBackground(new Color(0, 128, 192));
		panelModifyPatient.setBounds(556, 0, 60, 351);
		frame.getContentPane().add(panelModifyPatient);
		panelModifyPatient.setLayout(null);
		panelModifyPatient.setVisible(false);

		JLabel lblModifyPatient = new JLabel("Modificación de datos");
		lblModifyPatient.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifyPatient.setBounds(336, 32, 165, 14);
		panelModifyPatient.add(lblModifyPatient);

		JLabel lblModifyPatientAddress = new JLabel("Dirección:");
		lblModifyPatientAddress.setBounds(301, 97, 89, 14);
		panelModifyPatient.add(lblModifyPatientAddress);

		JLabel lblModifyPatientPhoneNumber = new JLabel("Teléfono:");
		lblModifyPatientPhoneNumber.setBounds(301, 127, 81, 14);
		panelModifyPatient.add(lblModifyPatientPhoneNumber);

		JLabel lblModifyPatientPassword = new JLabel("Contraseña:");
		lblModifyPatientPassword.setBounds(301, 157, 81, 14);
		panelModifyPatient.add(lblModifyPatientPassword);

		tfModifyPatientPassword = new JPasswordField();
		tfModifyPatientPassword.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (null == tfModifyPatientPassword)
					tfModifyPatientPassword.setText("");
				if (null == tfModifyPatientAddress)
					tfModifyPatientAddress.setText("");
				if (null == tfModifyPatientPhoneNumber)
					tfModifyPatientPhoneNumber.setText("");
				String tfPasswd = String.valueOf(tfModifyPatientPassword.getPassword());
				btnModifyPatientOk.setEnabled(userDataModificationManager.enableModifyPatientOkButton(tfPasswd,
						tfModifyPatientAddress.getText(), tfModifyPatientPhoneNumber.getText()));
			}
		});
		tfModifyPatientPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tfModifyPatientPassword.getCaretPosition() >= 50) {
					e.consume();
				}
			}
		});
		tfModifyPatientPassword.setBounds(398, 155, 183, 20);
		panelModifyPatient.add(tfModifyPatientPassword);

		tfModifyPatientAddress = new JTextField();
		tfModifyPatientAddress.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (null == tfModifyPatientPassword)
					tfModifyPatientPassword.setText("");
				if (null == tfModifyPatientAddress)
					tfModifyPatientAddress.setText("");
				if (null == tfModifyPatientPhoneNumber)
					tfModifyPatientPhoneNumber.setText("");
				String tfPasswd = String.valueOf(tfModifyPatientPassword.getPassword());
				btnModifyPatientOk.setEnabled(userDataModificationManager.enableModifyPatientOkButton(tfPasswd,
						tfModifyPatientAddress.getText(), tfModifyPatientPhoneNumber.getText()));
			}
		});
		tfModifyPatientAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tfModifyPatientAddress.getCaretPosition() >= 60) {
					e.consume();
				}
			}
		});
		tfModifyPatientAddress.setColumns(10);
		tfModifyPatientAddress.setBounds(398, 95, 183, 20);
		panelModifyPatient.add(tfModifyPatientAddress);

		tfModifyPatientPhoneNumber = new JTextField();
		tfModifyPatientPhoneNumber.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {

				if (null == tfModifyPatientPassword)
					tfModifyPatientPassword.setText("");
				if (null == tfModifyPatientAddress)
					tfModifyPatientAddress.setText("");
				if (null == tfModifyPatientPhoneNumber)
					tfModifyPatientPhoneNumber.setText("");
				String tfPasswd = String.valueOf(tfModifyPatientPassword.getPassword());
				btnModifyPatientOk.setEnabled(userDataModificationManager.enableModifyPatientOkButton(tfPasswd,
						tfModifyPatientAddress.getText(), tfModifyPatientPhoneNumber.getText()));

			}
		});
		tfModifyPatientPhoneNumber.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (tfModifyPatientPhoneNumber.getCaretPosition() >= 9) {
					e.consume();
				}
			}
		});
		tfModifyPatientPhoneNumber.setColumns(10);
		tfModifyPatientPhoneNumber.setBounds(398, 125, 183, 20);
		panelModifyPatient.add(tfModifyPatientPhoneNumber);

		btnModifyPatientOk = new JButton("Guardar");
		btnModifyPatientOk.setEnabled(false);
		btnModifyPatientOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isOk = true;
				String tfPasswd = String.valueOf(tfModifyPatientPassword.getPassword());
				try {
					if (tfPasswd.length() > 0) {
						user.setPassword(tfPasswd);
						userDataModificationManager.updatePatientPassword((Patient) user, tfPasswd);
					}
					if (tfModifyPatientAddress.getText().length() > 0) {
						((Patient) user).setAddress(tfModifyPatientAddress.getText());
						userDataModificationManager.updatePatientAddress((Patient) user,
								tfModifyPatientAddress.getText());
					}
					if (tfModifyPatientPhoneNumber.getText().length() > 0) {
						if (userDataModificationManager.isPhoneNumber(tfModifyPatientPhoneNumber.getText())) {
							((Patient) user).setPhoneNumber(tfModifyPatientPhoneNumber.getText());
							userDataModificationManager.updatePatientPhoneNumber((Patient) user,
									tfModifyPatientPhoneNumber.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Ha introducido un número de teléfono incorrecto",
									"Error", 0);
							tfModifyPatientPhoneNumber.setText("");
							isOk = false;
						}

					}
				} catch (SQLException sqle) {
					JOptionPane.showMessageDialog(null,
							"Se ha producido un error con la Base de Datos. Imposible modificar datos del usuario.",
							"Error", 0);
					isOk = false;
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Se ha producido un error. Imposible modificar datos del usuario.", "Error", 0);
					isOk = false;
				}

				if (isOk)
					JOptionPane.showMessageDialog(null, "Información actualizada", "Confirmación", 1);
			}
		});
		btnModifyPatientOk.setBounds(200, 271, 89, 23);
		panelModifyPatient.add(btnModifyPatientOk);

		btnModifyPatientCancel = new JButton("Cancelar");
		btnModifyPatientCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btnModifyPatientCancel.setBounds(350, 271, 89, 23);
		panelModifyPatient.add(btnModifyPatientCancel);

		btnModifyPatientUnsubscribe = new JButton("Eliminar cuenta");
		btnModifyPatientUnsubscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userDataModificationManager.deletePatient(userDNI);
					if (null == userDataModificationManager.selectPatient(userDNI))
						JOptionPane.showMessageDialog(null, "Cuenta eliminada", "Confirmación", 1);
				} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error. Imposible eliminar usuario.",
							"Error", 0);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,
							"Se ha producido un error con la Base de Datos. Imposible eliminar usuario.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error. Imposible eliminar usuario.",
							"Error", 0);
				}
			}
		});
		btnModifyPatientUnsubscribe.setBounds(398, 202, 183, 23);
		panelModifyPatient.add(btnModifyPatientUnsubscribe);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 84, 268, 103);
		panelModifyPatient.add(scrollPane_1);

		tablePatientData = new JTable();
		tablePatientData.setEnabled(false);
		tablePatientData.setRowSelectionAllowed(false);
		scrollPane_1.setViewportView(tablePatientData);
		tablePatientData.setDefaultEditor(Object.class, null);

//		Modify Sanitarian panel
		panelModifySanitarian = new JPanel();
		panelModifySanitarian.setBackground(new Color(0, 128, 192));
		panelModifySanitarian.setBounds(576, 0, 40, 351);
		frame.getContentPane().add(panelModifySanitarian);
		panelModifySanitarian.setLayout(null);
		panelModifySanitarian.setVisible(false);

		JLabel lblModifySanitarian = new JLabel("Modificación de datos");
		lblModifySanitarian.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifySanitarian.setBounds(225, 32, 165, 14);
		panelModifySanitarian.add(lblModifySanitarian);

		JLabel lblModifySanitarianDNI = new JLabel("DNI:");
		lblModifySanitarianDNI.setSize(94, 23);
		lblModifySanitarianDNI.setLocation(106, 102);
		lblModifySanitarianDNI.setBounds(106, 93, 119, 14);
		panelModifySanitarian.add(lblModifySanitarianDNI);

		JLabel lblModifySanitarianPassword = new JLabel("Contraseña:");
		lblModifySanitarianPassword.setBounds(106, 142, 119, 14);
		panelModifySanitarian.add(lblModifySanitarianPassword);

		tfModifySanitarianDNI = new JTextField();
		tfModifySanitarianDNI.setEnabled(false);
		tfModifySanitarianDNI.setEditable(false);
		tfModifySanitarianDNI.setBounds(225, 90, 183, 20);
		panelModifySanitarian.add(tfModifySanitarianDNI);
		tfModifySanitarianDNI.setColumns(10);

		tfModifySanitarianPassword = new JPasswordField();
		tfModifySanitarianPassword.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (null == tfModifySanitarianPassword)
					tfModifySanitarianPassword.setText("");
				String tfPasswd = String.valueOf(tfModifySanitarianPassword.getPassword());
				btnModifySanitarianOk.setEnabled(userDataModificationManager.enableModifySanitarianOkButton(tfPasswd));
			}
		});
		tfModifySanitarianPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (tfModifySanitarianPassword.getCaretPosition() >= 50) {
					e.consume();
				}
			}
		});
		tfModifySanitarianPassword.setBounds(225, 140, 183, 20);
		panelModifySanitarian.add(tfModifySanitarianPassword);

		btnModifySanitarianOk = new JButton("Guardar");
		btnModifySanitarianOk.setEnabled(false);
		btnModifySanitarianOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tfPasswd = String.valueOf(tfModifySanitarianPassword.getPassword());
				if (tfPasswd.length() > 0) {
					try {
						if (user instanceof Doctor)
							userDataModificationManager.updateDoctorPassword((Doctor) user, tfPasswd);

						if (user instanceof Nurse)
							userDataModificationManager.updateNursePassword((Nurse) user, tfPasswd);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,
								"Se ha producido un error con la Base de Datos. Imposible modificar datos del usuario.",
								"Error", 0);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null,
								"Se ha producido un error. Imposible modificar datos del usuario.", "Error", 0);

					}
				}
				JOptionPane.showMessageDialog(null, "Información actualizada", "Confirmación", 1);
			}
		});
		btnModifySanitarianOk.setBounds(200, 271, 89, 23);
		panelModifySanitarian.add(btnModifySanitarianOk);

		btnModifySanitarianCancel = new JButton("Cancelar");
		btnModifySanitarianCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btnModifySanitarianCancel.setBounds(350, 271, 89, 23);
		panelModifySanitarian.add(btnModifySanitarianCancel);

		btnModifySanitarianUnsubscribe = new JButton("Eliminar cuenta");
		btnModifySanitarianUnsubscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (user instanceof Doctor)
						userDataModificationManager.deleteDoctor(userDNI);

					if (user instanceof Nurse)
						userDataModificationManager.deleteNurse(userDNI);

				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,
							"Se ha producido un error con la Base de Datos. Imposible eliminar usuario.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error. Imposible eliminar usuario.",
							"Error", 0);
				}
				JOptionPane.showMessageDialog(null, "Cuenta eliminada", "Confirmación", 1);
			}
		});
		btnModifySanitarianUnsubscribe.setBounds(260, 195, 130, 23);
		panelModifySanitarian.add(btnModifySanitarianUnsubscribe);

//		panel BlockPatient
		panelBlockPatient = new JPanel();
		panelBlockPatient.setBackground(new Color(0, 128, 192));
		panelBlockPatient.setBounds(535, 0, 81, 351);
		frame.getContentPane().add(panelBlockPatient);
		panelBlockPatient.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(99, 77, 443, 173);
		panelBlockPatient.add(scrollPane);

		tableBlockPatients = new JTable();
		tableBlockPatients.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Nombre", "Apellido", "Estado" }));
		scrollPane.setViewportView(tableBlockPatients);
		tableBlockPatients.setDefaultEditor(Object.class, null);
		tableBlockPatients.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = tableBlockPatients.getSelectedRow();
				String patientDni = (String) tableBlockPatients.getValueAt(fila, 0);
				Patient patient = null;
				try {
					patient = userDataModificationManager.selectPatient(patientDni);
					if (false == patient.isBlocked()) {
						btnBlockPatientOk.setText("Bloquear");
					} else {
						btnBlockPatientOk.setText("Desbloquear");
					}
				} catch (SQLException e1) {
				} catch (Exception e1) {
				}
			}
		});

		btnBlockPatientOk = new JButton("Bloquear");
		btnBlockPatientOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tableBlockPatients.getSelectedRow();
				String patientDni = (String) tableBlockPatients.getValueAt(fila, 0);
				Patient patient = null;
				try {
					patient = userDataModificationManager.selectPatient(patientDni);
					if (false == patient.isBlocked()) {
						blockDesblockPatientManager.blockPatient(patient);
						JOptionPane.showMessageDialog(null, "Paciente bloqueado/a", "Confirmación", 1);
					} else {
						blockDesblockPatientManager.unlockPatient(patient);
						JOptionPane.showMessageDialog(null, "Paciente desbloqueado/a", "Confirmación", 1);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}
				tableBlockPatients.repaint();
			}
		});
		btnBlockPatientOk.setBounds(194, 279, 85, 21);
		panelBlockPatient.add(btnBlockPatientOk);

		JButton btnBlockPatienCancel = new JButton("Cancelar");
		btnBlockPatienCancel.setBounds(332, 279, 85, 21);
		panelBlockPatient.add(btnBlockPatienCancel);
		panelBlockPatient.setVisible(false);

//		panel SelectAppointmentAmbulatoryType
		panelSelectAppointmentAmbulatoryType = new JPanel();
		panelSelectAppointmentAmbulatoryType.setBackground(new Color(0, 128, 192));
		panelSelectAppointmentAmbulatoryType.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelSelectAppointmentAmbulatoryType);
		panelSelectAppointmentAmbulatoryType.setLayout(null);
		panelSelectAppointmentAmbulatoryType.setVisible(false);

		JLabel lblSelectAmbulatory = new JLabel("Seleccione un ambulatorio");
		lblSelectAmbulatory.setBounds(155, 112, 129, 13);
		panelSelectAppointmentAmbulatoryType.add(lblSelectAmbulatory);

		JComboBox<String> cbAmbulatory = new JComboBox<String>();
		cbAmbulatory.setBounds(295, 108, 134, 21);
		ArrayList<String> ambulatories = null;
		try {
			ambulatories = appointmentSelectionManager.selectAmbulatoryNames();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
		}
		for (String ambulatory : ambulatories) {
			cbAmbulatory.addItem(ambulatory);
		}
		panelSelectAppointmentAmbulatoryType.add(cbAmbulatory);

		JLabel lblSelectSanitarian = new JLabel("Seleccione tipo de consulta");
		lblSelectSanitarian.setBounds(153, 170, 276, 13);
		panelSelectAppointmentAmbulatoryType.add(lblSelectSanitarian);

		JRadioButton rdbtnSelectDoctor = new JRadioButton("Medicina");
		rdbtnSelectDoctor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if ((true == rdbtnSelectDoctor.isSelected()) || (true == rdbtnSelectNurse.isSelected())) {
					btnSelectAppointmentAmbulatoryTypeOk.setEnabled(true);
				} else {
					btnSelectAppointmentAmbulatoryTypeOk.setEnabled(false);
				}
			}
		});
		rdbtnSelectDoctor.setBounds(181, 189, 103, 21);
		panelSelectAppointmentAmbulatoryType.add(rdbtnSelectDoctor);

		rdbtnSelectNurse = new JRadioButton("Enfermeria");
		rdbtnSelectNurse.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if ((true == rdbtnSelectDoctor.isSelected()) || (true == rdbtnSelectNurse.isSelected())) {
					btnSelectAppointmentAmbulatoryTypeOk.setEnabled(true);
				} else {
					btnSelectAppointmentAmbulatoryTypeOk.setEnabled(false);
				}
			}
		});
		rdbtnSelectNurse.setBounds(315, 189, 103, 21);
		panelSelectAppointmentAmbulatoryType.add(rdbtnSelectNurse);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSelectDoctor);
		group.add(rdbtnSelectNurse);

		btnSelectAppointmentAmbulatoryTypeOk = new JButton("Siguiente");
		btnSelectAppointmentAmbulatoryTypeOk.setEnabled(false);
		btnSelectAppointmentAmbulatoryTypeOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wantedAmbulatory = (String) cbAmbulatory.getSelectedItem();

				for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();
					if (button.isSelected()) {
						wantedSanitarian = button.getText();
					}

				}

				Ambulatory ambulatory = new Ambulatory();
				dates = new ArrayList<String>();
				try {
					ambulatory = appointmentSelectionManager.selectAmbulatory(wantedAmbulatory);
					dates = appointmentSelectionManager.showAvailableDates(wantedSanitarian, ambulatory);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}

				cbSelectAppointmentDate.addItem("");
				for (String date : dates) {
					cbSelectAppointmentDate.addItem(date);
				}

				panelSelectAppointmentAmbulatoryType.setVisible(false);
				panelSelectAppointmentDateTimeSlot.setVisible(true);
			}
		});
		btnSelectAppointmentAmbulatoryTypeOk.setBounds(199, 273, 85, 21);
		panelSelectAppointmentAmbulatoryType.add(btnSelectAppointmentAmbulatoryTypeOk);

		btnSelectAppointmentAmbulatoryTypeCancel = new JButton("Cancelar");
		btnSelectAppointmentAmbulatoryTypeCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btnSelectAppointmentAmbulatoryTypeCancel.setBounds(315, 273, 85, 21);
		panelSelectAppointmentAmbulatoryType.add(btnSelectAppointmentAmbulatoryTypeCancel);
		panelSelectAppointmentAmbulatoryType.setVisible(false);

//		panel SelectAppointmentDateTimeSlot
		panelSelectAppointmentDateTimeSlot = new JPanel();
		panelSelectAppointmentDateTimeSlot.setBackground(new Color(0, 128, 192));
		panelSelectAppointmentDateTimeSlot.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelSelectAppointmentDateTimeSlot);
		panelSelectAppointmentDateTimeSlot.setLayout(null);
		panelSelectAppointmentDateTimeSlot.setVisible(false);

		JScrollPane scrollPaneSelectTimeSlot = new JScrollPane();
		scrollPaneSelectTimeSlot.setBounds(61, 124, 517, 147);
		panelSelectAppointmentDateTimeSlot.add(scrollPaneSelectTimeSlot);

		tableSelectTimeSlot = new JTable();
		tableSelectTimeSlot.setDefaultEditor(Object.class, null);
		tableSelectTimeSlot.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Profesional", "Ambulatorio", "Fecha", "Hora" }));
		scrollPaneSelectTimeSlot.setViewportView(tableSelectTimeSlot);

		JLabel lblSelectAppointmentDate = new JLabel("Seleccione una fecha");
		lblSelectAppointmentDate.setBounds(174, 23, 129, 14);
		panelSelectAppointmentDateTimeSlot.add(lblSelectAppointmentDate);

		cbSelectAppointmentDate = new JComboBox<String>();
		cbSelectAppointmentDate.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (!cbSelectAppointmentDate.getSelectedItem().toString().equals("")) {
					cbSelectAppointmentSanitarian.removeAllItems();
					String dateString = cbSelectAppointmentDate.getSelectedItem().toString();

					ArrayList<Sanitarian> sanitarians;
					try {
						sanitarians = appointmentSelectionManager.showAvailableSanitarianByDate(wantedSanitarian,
								dateString);
						cbSelectAppointmentSanitarian.addItem("");
						for (Sanitarian sanitarian : sanitarians) {
							cbSelectAppointmentSanitarian
									.addItem(sanitarian.getSurname() + ", " + sanitarian.getName());
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error",
								0);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
					}
				} else {
					cbSelectAppointmentSanitarian.removeAllItems();
					cbSelectAppointmentSanitarian.addItem("");
				}
			}
		});
		cbSelectAppointmentDate.setBounds(313, 19, 144, 22);
		panelSelectAppointmentDateTimeSlot.add(cbSelectAppointmentDate);

		JButton btnSelectAppointmentDateTimeSlotOk = new JButton("Aceptar");
		btnSelectAppointmentDateTimeSlotOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tableSelectTimeSlot.getSelectedRow();
				String hour = (String) tableSelectTimeSlot.getValueAt(fila, 3);
				String dateString = cbSelectAppointmentDate.getSelectedItem().toString();

				Appointment appointment = new Appointment();
				try {
					String nameSurname = (String) cbSelectAppointmentSanitarian.getSelectedItem();
					String[] parts = nameSurname.split(",");
					String surname = parts[0].trim();
					String name = parts[1].trim();
					Sanitarian sanitarian = appointmentSelectionManager.selectSanitarian(name, surname);

					Ambulatory ambulatory = appointmentSelectionManager.selectAmbulatory(wantedAmbulatory);
					appointment.setAmbulatory(ambulatory);
					appointment.setPatient((Patient) user);
					appointment.setSanitarian(sanitarian);

					appointmentManager.insert(appointment);
					
					appointment = appointmentSelectionManager.selectAppointment((Patient) user, sanitarian,
							appointmentSelectionManager.selectAmbulatory(wantedAmbulatory));
					
					WorkingDay workingDay = appointmentSelectionManager.selectWorkingDay(dateString);
					TimeSlot timeSlot = appointmentSelectionManager.selectTimeSlot(hour);
					JOptionPane.showMessageDialog(null,timeSlot.getId(), "Error", 0);

					appointmentSelectionManager.insertAppointmentWorkingDayTimeSlot(appointment, workingDay, timeSlot);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}

			}
		});
		btnSelectAppointmentDateTimeSlotOk.setBounds(207, 292, 89, 23);
		panelSelectAppointmentDateTimeSlot.add(btnSelectAppointmentDateTimeSlotOk);

		JButton btnSelectAppointmentDateTimeSlotCancel = new JButton("Cancelar");
		btnSelectAppointmentDateTimeSlotCancel.setBounds(323, 292, 89, 23);
		panelSelectAppointmentDateTimeSlot.add(btnSelectAppointmentDateTimeSlotCancel);

		JLabel lblSelectAppontmentSanitarian = new JLabel("Profesionales disponibles");
		lblSelectAppontmentSanitarian.setBounds(174, 80, 129, 14);
		panelSelectAppointmentDateTimeSlot.add(lblSelectAppontmentSanitarian);

		cbSelectAppointmentSanitarian = new JComboBox<String>();
		try {
			cbSelectAppointmentSanitarian.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (null != cbSelectAppointmentSanitarian.getSelectedItem()) {
						if (!cbSelectAppointmentSanitarian.getSelectedItem().toString().equals("")) {
							String dateString = cbSelectAppointmentDate.getSelectedItem().toString();
							String nameSurname = (String) cbSelectAppointmentSanitarian.getSelectedItem();

							String[] parts = nameSurname.split(",");
							String surname = parts[0].trim();
							String name = parts[1].trim();
							ArrayList<TimeSlot> timeSlots = null;
							try {
								Sanitarian sanitarian = appointmentSelectionManager.selectSanitarian(name, surname);
								timeSlots = appointmentSelectionManager.showAvailableTimeSlots(sanitarian, dateString);

								DefaultTableModel model = (DefaultTableModel) tableSelectTimeSlot.getModel();
								model.setRowCount(0);

								for (TimeSlot timeSlot : timeSlots) {
									model.addRow(new String[] { surname + ", " + name, wantedAmbulatory,
											cbSelectAppointmentDate.getSelectedItem().toString(),
											timeSlotManager.select(timeSlot.getId()).getStartTime().toString() });
								}
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.",
										"Error", 0);
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
							}
						} else {
							DefaultTableModel model = (DefaultTableModel) tableSelectTimeSlot.getModel();
							model.setRowCount(0);
						}
					}
				}
			});
		} catch (Exception e) {
		}
		cbSelectAppointmentSanitarian.setBounds(319, 76, 138, 22);
		panelSelectAppointmentDateTimeSlot.add(cbSelectAppointmentSanitarian);

	}
}
