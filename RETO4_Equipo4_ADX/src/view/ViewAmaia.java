package view;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import manager.AppointmentSelectionManager;
import manager.BlockUnlockPatientManager;
import manager.PatientManager;
import manager.TimeSlotManager;
import manager.UserDataModificationManager;
import model.pojos.Ambulatory;
//import manager.DoctorManager;
//import manager.NurseManager;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import model.pojos.Sanitarian;
import model.pojos.TimeSlot;
import model.pojos.User;
import model.pojos.WorkingDay;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ViewAmaia {
//	private PatientManager patientManager;
//	private DoctorManager doctorManager;
//	private NurseManager nurseManager;
	private UserDataModificationManager userDataModificationManager;
	private AppointmentSelectionManager appointmentSelectionManager;
	private BlockUnlockPatientManager blockDesblockPatientManager;
	private TimeSlotManager timeSlotManager;
	private String userDNI;
	private User user;
	private String wantedAmbulatory;
	private String wantedSanitarian;
	private ArrayList<Date> dates;

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
	private JTextField tfModifyPatientDNI;
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
	private JButton btnNewButton;
	private JPanel panelBlockPatient;
	private JTable tableBlockPatients;
	private JButton btnBlockPatientOk;

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
					tfModifyPatientDNI.setText(userDNI);
//					panelModifyPatient.setVisible(true);
					panelSelectAppointmentAmbulatoryType.setVisible(true);
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
		panelModifyPatient.setBounds(527, 0, 89, 351);
		frame.getContentPane().add(panelModifyPatient);
		panelModifyPatient.setLayout(null);
		panelModifyPatient.setVisible(false);

		JLabel lblModifyPatient = new JLabel("Modificación de datos");
		lblModifyPatient.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifyPatient.setBounds(225, 32, 165, 14);
		panelModifyPatient.add(lblModifyPatient);

		JLabel lblModifyPatientDNI = new JLabel("DNI:");
		lblModifyPatientDNI.setSize(94, 23);
		lblModifyPatientDNI.setLocation(106, 102);
		lblModifyPatientDNI.setBounds(106, 93, 119, 14);
		panelModifyPatient.add(lblModifyPatientDNI);

		JLabel lblModifyPatientAddress = new JLabel("Dirección:");
		lblModifyPatientAddress.setBounds(106, 118, 127, 14);
		panelModifyPatient.add(lblModifyPatientAddress);

		JLabel lblModifyPatientPhoneNumber = new JLabel("Teléfono:");
		lblModifyPatientPhoneNumber.setBounds(104, 143, 119, 14);
		panelModifyPatient.add(lblModifyPatientPhoneNumber);

		JLabel lblModifyPatientPassword = new JLabel("Contraseña:");
		lblModifyPatientPassword.setBounds(106, 168, 119, 14);
		panelModifyPatient.add(lblModifyPatientPassword);

		tfModifyPatientDNI = new JTextField();
		tfModifyPatientDNI.setEnabled(false);
		tfModifyPatientDNI.setEditable(false);
		tfModifyPatientDNI.setBounds(225, 90, 183, 20);
		panelModifyPatient.add(tfModifyPatientDNI);
		tfModifyPatientDNI.setColumns(10);

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
		tfModifyPatientPassword.setBounds(225, 165, 183, 20);
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
		tfModifyPatientAddress.setBounds(225, 115, 183, 20);
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
		tfModifyPatientPhoneNumber.setBounds(225, 140, 183, 20);
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
		btnModifyPatientUnsubscribe.setBounds(260, 206, 130, 23);
		panelModifyPatient.add(btnModifyPatientUnsubscribe);

//		Modify Sanitarian panel
		panelModifySanitarian = new JPanel();
		panelModifySanitarian.setBackground(new Color(0, 128, 192));
		panelModifySanitarian.setBounds(491, 0, 125, 351);
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
		panelBlockPatient.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelBlockPatient);
		panelBlockPatient.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(99, 77, 443, 173);
		panelBlockPatient.add(scrollPane);

		tableBlockPatients = new JTable();
		tableBlockPatients
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Nombre", "Apellido", "Estado" }));
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
				dates = new ArrayList<Date>();
				try {
					ambulatory = appointmentSelectionManager.selectAmbulatory(wantedAmbulatory);
					dates = appointmentSelectionManager.showAvailableDates(wantedSanitarian, ambulatory);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}

				for (Date date : dates) {
					cbSelectAppointmentDate.addItem(date.toString());
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
		tableSelectTimeSlot.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Profesional", "Ambulatorio", "Fecha", "Hora" }));
		scrollPaneSelectTimeSlot.setViewportView(tableSelectTimeSlot);

		JLabel lblSelectAppointmentDate = new JLabel("Seleccione una fecha");
		lblSelectAppointmentDate.setBounds(174, 23, 129, 14);
		panelSelectAppointmentDateTimeSlot.add(lblSelectAppointmentDate);

		cbSelectAppointmentDate = new JComboBox<String>();
		cbSelectAppointmentDate.setBounds(313, 19, 144, 22);
		panelSelectAppointmentDateTimeSlot.add(cbSelectAppointmentDate);

		JButton btnSelectAppointmentDateTimeSlotOk = new JButton("Aceptar");
		btnSelectAppointmentDateTimeSlotOk.setBounds(207, 292, 89, 23);
		panelSelectAppointmentDateTimeSlot.add(btnSelectAppointmentDateTimeSlotOk);

		JButton btnSelectAppointmentDateTimeSlotCancel = new JButton("Cancelar");
		btnSelectAppointmentDateTimeSlotCancel.setBounds(323, 292, 89, 23);
		panelSelectAppointmentDateTimeSlot.add(btnSelectAppointmentDateTimeSlotCancel);

		JLabel lblSelectAppontmentSanitarian = new JLabel("Seleccione un profesional");
		lblSelectAppontmentSanitarian.setBounds(174, 99, 129, 14);
		panelSelectAppointmentDateTimeSlot.add(lblSelectAppontmentSanitarian);

		cbSelectAppointmentSanitarian = new JComboBox<String>();
		cbSelectAppointmentSanitarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		cbSelectAppointmentSanitarian.setBounds(319, 91, 138, 22);
		panelSelectAppointmentDateTimeSlot.add(cbSelectAppointmentSanitarian);

		JButton btnSelectAppointmentDateOk = new JButton("Aceptar");
		btnSelectAppointmentDateOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateString = cbSelectAppointmentDate.getSelectedItem().toString();

				ArrayList<Sanitarian> sanitarians = appointmentSelectionManager
						.showAvailableSanitarianByDate(wantedSanitarian, dateString);

				for (Sanitarian sanitarian : sanitarians) {
					cbSelectAppointmentSanitarian.addItem(sanitarian.getName());
				}
			}
		});
		btnSelectAppointmentDateOk.setBounds(259, 52, 89, 23);
		panelSelectAppointmentDateTimeSlot.add(btnSelectAppointmentDateOk);

		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateString = cbSelectAppointmentDate.getSelectedItem().toString();

				ArrayList<Integer> timeSlotIds = null;
				try {
					timeSlotIds = appointmentSelectionManager.showAvailableTimeSlots(wantedSanitarian, dateString);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}

				DefaultTableModel model = (DefaultTableModel) tableSelectTimeSlot.getModel();
				model.setRowCount(0);

				for (Integer timeSlot : timeSlotIds) {
					try {
						model.addRow(new String[] { wantedSanitarian, wantedAmbulatory,
								cbSelectAppointmentDate.getSelectedItem().toString(),
								timeSlotManager.select(timeSlot).toString() });
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error",
								0);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
					}
				}

//				"Profesional", "Ambulatorio", "Fecha", "Hora"
			}
		});
		btnNewButton.setBounds(49, 91, 89, 23);
		panelSelectAppointmentDateTimeSlot.add(btnNewButton);

	}
}
