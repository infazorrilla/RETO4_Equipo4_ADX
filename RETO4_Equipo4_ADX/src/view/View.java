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

import manager.AppointmentManager;
import manager.AppointmentSelectionManager;
import manager.BlockUnlockPatientManager;
import manager.PatientManager;
import manager.TimeSlotManager;
import manager.UserDataModificationManager;
import model.pojos.Ambulatory;
import model.pojos.Appointment;
import manager.DoctorManager;
import manager.LoginManager;
import manager.NurseManager;
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
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;

public class View {
//	private PatientManager patientManager;
//	private DoctorManager doctorManager;
//	private NurseManager nurseManager;
	private UserDataModificationManager userDataModificationManager;
	private AppointmentSelectionManager appointmentSelectionManager;
	private TimeSlotManager timeSlotManager;
	private AppointmentManager appointmentManager;

	private BlockUnlockPatientManager blockUnlockPatientManager;
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
	private JPanel panelSanitarian;
	private JLabel lblCross;
	private JPanel panelPatient;
	private JLabel lblCrossPanelPatient;
	private JLabel lblLettersPanelSanitarian_1;
	private JLabel lblPatientTitule;
	private JButton btnLogOutPanelPatient;
	private PatientManager patientManager;
	private DoctorManager doctorManager;
	private NurseManager nurseManager;
	private JPanel panelRegistrationPatient;
	private JTextField textFieldBirthDatePatient;
	private JTextField textFieldPhoneNumberPatient;
	private JTextField textFieldAddressPatient;
	private JButton btnSelectDoctor;
	private JButton btnSelectNurse;
	private JButton btnRegistroSanitarian;
	private JPanel panelNurseOrDoctor;
	private JPanel panelRegistrationDoctor;
	private JTextField textFieldDniDoctor;
	private JTextField textFieldNameDoctor;
	private JTextField textFieldSurnameDoctor;
	private JTextField textFieldBirthDateDoctor;
	private JPasswordField passwordFieldDoctor;
	private JTextField textFieldStaffNumDoctor;
	private JTextField textFieldSalaryDoctor;
	private JTextField textFieldSpecialityDoctor;
	private JButton btnAceptDoctor;
	private JButton btnCancelDoctor;
	private JComboBox<String> comboBoxGenderDoctor;
	private JPanel panelRegistrationNurse;
	private JTextField textFieldDniNurse;
	private JTextField textFieldNameNurse;
	private JTextField textFieldSurnameNurse;
	private JTextField textFieldBirthDateNurse;
	private JTextField textFieldStaffNumberNurse;
	private JTextField textFieldSalaryNurse;
	private JTextField textFieldCategoryNurse;
	private JButton btnAceptNurse;
	private JButton btnCancelNurse;
	private JPasswordField passwordFieldNurse;
	private JComboBox<String> comboBoxAmbulatoryNurse;
	private JComboBox<String> comboBoxAmbulatoryDoctor;
	private JPanel panelBlockPatient;
	private JTable tableBlockPatients;
	private JButton btnBlockPatientOk;
	private JTable tablePatientData;
	private JScrollPane scrollPane_1;
	private JPasswordField tfLoginPassword;
	private JTextField tfLoginUser;

	/**
	 * Create the application.
	 */
	public View() {
		userDataModificationManager = new UserDataModificationManager();
		appointmentSelectionManager = new AppointmentSelectionManager();
		timeSlotManager = new TimeSlotManager();
		blockUnlockPatientManager = new BlockUnlockPatientManager();
		patientManager = new PatientManager();
		doctorManager = new DoctorManager();
		nurseManager = new NurseManager();
		appointmentManager = new AppointmentManager();

		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(View.class.getResource("/view/images/OsasunbideLogo.jpg")));

		frame.setBounds(100, 100, 632, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false); // fixed dimensions
		frame.setLocationRelativeTo(null); // central position on the screen

		// LOGIN | PANEL
		panelLogin = new JPanel();
		panelLogin.setBackground(new Color(16, 169, 121));
		panelLogin.setBounds(0, 0, 344, 601);
		frame.getContentPane().add(panelLogin);
		panelLogin.setLayout(null);
		panelLogin.setVisible(true);

		JLabel lblLogin = new JLabel("Inicio de sesión");
		panelLogin.setBounds(0, 0, 616, 351);
		panelLogin.add(lblLogin);

		JButton btnLoginResgistrationPatient = new JButton("Registro Paciente");
		btnLoginResgistrationPatient.setBackground(new Color(16, 169, 121));
		btnLoginResgistrationPatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLoginResgistrationPatient.setForeground(new Color(255, 255, 255));
		btnLoginResgistrationPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(false);
				panelRegistrationPatient.setVisible(true);
			}
		});
		btnLoginResgistrationPatient.setBounds(231, 245, 154, 23);
		panelLogin.add(btnLoginResgistrationPatient);

		btnRegistroSanitarian = new JButton("Registro Sanitario");
		btnRegistroSanitarian.setBackground(new Color(16, 169, 121));
		btnRegistroSanitarian.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistroSanitarian.setForeground(new Color(255, 255, 255));
		btnRegistroSanitarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(false);
				panelNurseOrDoctor.setVisible(true);
			}
		});
		btnRegistroSanitarian.setBounds(231, 279, 154, 23);
		panelLogin.add(btnRegistroSanitarian);

		lblCross = new JLabel("");
		lblCross.setIcon(new ImageIcon(View.class.getResource("/view/images/OsasunbideCross.jpg")));
		lblCross.setBounds(476, 105, 140, 140);
		panelLogin.add(lblCross);

		JLabel lblOsasunbide = new JLabel("");
		lblOsasunbide.setIcon(new ImageIcon(View.class.getResource("/view/images/Letters.jpg")));
		lblOsasunbide.setBounds(0, 105, 140, 140);
		panelLogin.add(lblOsasunbide);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(180, 74, 237, 157);
		panelLogin.add(panel);
		panel.setLayout(null);

		JLabel lblLoginDNI = new JLabel("DNI:");
		lblLoginDNI.setBounds(41, 11, 33, 17);
		panel.add(lblLoginDNI);
		lblLoginDNI.setBackground(new Color(0, 0, 0));
		lblLoginDNI.setForeground(new Color(16, 169, 121));
		lblLoginDNI.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblLoginPassword = new JLabel("Contraseña:");
		lblLoginPassword.setBounds(41, 60, 122, 14);
		panel.add(lblLoginPassword);
		lblLoginPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLoginPassword.setForeground(new Color(16, 169, 121));

		btnLoginOk = new JButton("Entrar");
		btnLoginOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDNI = tfLoginUser.getText();
				// LOGING CHECK
				String pass = new String(tfLoginPassword.getPassword());

				try {
					user = userDataModificationManager.identifyUserType(userDNI);
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", 0);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", 0);
				}

				int verifiedUser = new LoginManager().getUserByDniAndPassword(userDNI, pass);

				switch (verifiedUser) {
				case 1:
					JOptionPane.showMessageDialog(null, "ACCEDIENDO COMO PACIENTE");
					panelLogin.setVisible(false);
					panelPatient.setVisible(true);

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
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "ACCEDIENDO COMO EMPLEADO"); // ADMIN
					panelLogin.setVisible(false);
					panelSanitarian.setVisible(true);
					tfModifySanitarianDNI.setText(userDNI);

					break;
				case 3:
					JOptionPane.showMessageDialog(null, "USUARIO BLOQUEADO"); // PATIENT BLOCKED
					break;
				default:
					JOptionPane.showMessageDialog(null, "USUARIO O CONTRSEÑA INCORRETO");
					tfLoginPassword.setText("");
				}

				if (user instanceof Sanitarian) {
					panelBlockPatient.setVisible(true);
					ArrayList<Patient> patients = null;
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
			}
		});
		btnLoginOk.setForeground(new Color(255, 255, 255));
		btnLoginOk.setBackground(new Color(16, 169, 121));
		btnLoginOk.setBounds(41, 123, 171, 23);
		panel.add(btnLoginOk);

		tfLoginPassword = new JPasswordField();
		tfLoginPassword.setBounds(41, 81, 171, 20);
		panel.add(tfLoginPassword);

		tfLoginUser = new JTextField();
		tfLoginUser.setBounds(41, 29, 171, 20);
		panel.add(tfLoginUser);
		tfLoginUser.setColumns(10);

		// PANEL | REGISTRATION: SANITARIAN
		panelNurseOrDoctor = new JPanel();
		panelNurseOrDoctor.setBounds(0, 0, 616, 351);
		panelNurseOrDoctor.setBackground(new Color(16, 169, 121));
		frame.getContentPane().add(panelNurseOrDoctor);
		panelNurseOrDoctor.setLayout(null);
		panelNurseOrDoctor.setVisible(false);

		btnSelectDoctor = new JButton("MEDICO");
		btnSelectDoctor.setBackground(new Color(255, 255, 255));
		btnSelectDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelectDoctor.setForeground(new Color(16, 169, 121));
		btnSelectDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationDoctor.setVisible(true);
			}
		});
		btnSelectDoctor.setBounds(176, 128, 100, 40);
		panelNurseOrDoctor.add(btnSelectDoctor);

		btnSelectNurse = new JButton("ENFERMERO");
		btnSelectNurse.setBackground(new Color(255, 255, 255));
		btnSelectNurse.setForeground(new Color(16, 169, 121));
		btnSelectNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelectNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationNurse.setVisible(true);
			}
		});
		btnSelectNurse.setBounds(310, 128, 100, 40);
		panelNurseOrDoctor.add(btnSelectNurse);

		JLabel lblTitleNurseOrDoctor = new JLabel("Seleccione el tipo de Sanitario:");
		lblTitleNurseOrDoctor.setForeground(new Color(255, 255, 255));
		lblTitleNurseOrDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleNurseOrDoctor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitleNurseOrDoctor.setBounds(158, 77, 300, 40);
		panelNurseOrDoctor.add(lblTitleNurseOrDoctor);

		JLabel lblNurseIcon = new JLabel("");
		lblNurseIcon.setIcon(new ImageIcon(View.class.getResource("/view/images/NurseIcon.jpg")));
		lblNurseIcon.setBounds(310, 179, 140, 140);
		panelNurseOrDoctor.add(lblNurseIcon);

		JLabel lblDoctorIcon = new JLabel("");
		lblDoctorIcon.setIcon(new ImageIcon(View.class.getResource("/view/images/DoctorIcon.jpg")));
		lblDoctorIcon.setBounds(136, 179, 140, 140);
		panelNurseOrDoctor.add(lblDoctorIcon);

		JLabel lblLettersNurseOrDoctor = new JLabel("");
		lblLettersNurseOrDoctor.setIcon(new ImageIcon(View.class.getResource("/view/images/Letters.jpg")));
		lblLettersNurseOrDoctor.setBounds(0, 105, 140, 140);
		panelNurseOrDoctor.add(lblLettersNurseOrDoctor);

		JLabel lblCrossNurseOrDoctor = new JLabel("");
		lblCrossNurseOrDoctor.setIcon(new ImageIcon(View.class.getResource("/view/images/OsasunbideCross.jpg")));
		lblCrossNurseOrDoctor.setBounds(476, 105, 140, 140);
		panelNurseOrDoctor.add(lblCrossNurseOrDoctor);

		// PANEL | REGISTRATION: PATIENT
		panelRegistrationPatient = new JPanel();
		panelRegistrationPatient.setBounds(0, 0, 616, 351);
		panelRegistrationPatient.setBackground(new Color(16, 169, 121));
		frame.getContentPane().add(panelRegistrationPatient);
		panelRegistrationPatient.setLayout(null);
		panelRegistrationPatient.setVisible(false);

		JLabel lblTitleUserSingUp = new JLabel("Registro de usuario Paciente");
		lblTitleUserSingUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleUserSingUp.setForeground(new Color(255, 255, 255));
		lblTitleUserSingUp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitleUserSingUp.setBounds(235, 82, 224, 20);
		panelRegistrationPatient.add(lblTitleUserSingUp);

		JLabel lblDniSingUpPatient = new JLabel("DNI:");
		lblDniSingUpPatient.setForeground(new Color(255, 255, 255));
		lblDniSingUpPatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDniSingUpPatient.setBounds(98, 34, 119, 14);
		panelRegistrationPatient.add(lblDniSingUpPatient);

		JLabel lblNameSingUpPatient = new JLabel("Nombre:");
		lblNameSingUpPatient.setForeground(new Color(255, 255, 255));
		lblNameSingUpPatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNameSingUpPatient.setBounds(98, 69, 119, 14);
		panelRegistrationPatient.add(lblNameSingUpPatient);

		JLabel lblSurnameSingUpPatient = new JLabel("Apellido:");
		lblSurnameSingUpPatient.setForeground(new Color(255, 255, 255));
		lblSurnameSingUpPatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSurnameSingUpPatient.setBounds(98, 104, 119, 14);
		panelRegistrationPatient.add(lblSurnameSingUpPatient);

		JLabel lblGenderSingUpPatient = new JLabel("Sexo:");
		lblGenderSingUpPatient.setForeground(new Color(255, 255, 255));
		lblGenderSingUpPatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGenderSingUpPatient.setBounds(98, 140, 119, 14);
		panelRegistrationPatient.add(lblGenderSingUpPatient);

		JLabel lblPassSingUpPatient = new JLabel("Contraseña:");
		lblPassSingUpPatient.setForeground(new Color(255, 255, 255));
		lblPassSingUpPatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassSingUpPatient.setBounds(98, 175, 119, 14);
		panelRegistrationPatient.add(lblPassSingUpPatient);

		JTextField textFieldDNIPatient = new JTextField();
		textFieldDNIPatient.setBounds(98, 49, 119, 20);
		panelRegistrationPatient.add(textFieldDNIPatient);
		textFieldDNIPatient.setColumns(10);
		textFieldDNIPatient.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldDNIPatient.getCaretPosition() >= 9) {
					e.consume();
				}
			}
		});

		JTextField textFieldNamePatient = new JTextField();
		textFieldNamePatient.setBounds(98, 84, 119, 20);
		panelRegistrationPatient.add(textFieldNamePatient);
		textFieldNamePatient.setColumns(10);
		textFieldNamePatient.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldNamePatient.getCaretPosition() >= 50) {
					e.consume();
				}
			}
		});

		JTextField textFieldSurnamePatient = new JTextField();
		textFieldSurnamePatient.setBounds(98, 118, 119, 20);
		panelRegistrationPatient.add(textFieldSurnamePatient);
		textFieldSurnamePatient.setColumns(10);
		textFieldSurnamePatient.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldSurnamePatient.getCaretPosition() >= 50) {
					e.consume();
				}
			}
		});

		JPasswordField textFieldPasswordPatient = new JPasswordField();
		textFieldPasswordPatient.setBounds(98, 190, 119, 20);
		panelRegistrationPatient.add(textFieldPasswordPatient);
		textFieldPasswordPatient.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldPasswordPatient.getCaretPosition() >= 50) {
					e.consume();
				}
			}
		});

		JComboBox<String> comboBoxGenderPatient = new JComboBox<String>();
		comboBoxGenderPatient.setBounds(98, 153, 119, 20);
		panelRegistrationPatient.add(comboBoxGenderPatient);
		comboBoxGenderPatient.addItem("Hombre");
		comboBoxGenderPatient.addItem("Mujer");

		JButton btnAceptarRegistro = new JButton("Aceptar");
		btnAceptarRegistro.setBackground(new Color(255, 255, 255));
		btnAceptarRegistro.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAceptarRegistro.setForeground(new Color(16, 169, 121));
		btnAceptarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient patient = new Patient();
				patient.setDni(textFieldDNIPatient.getText().trim());
				patient.setName(textFieldNamePatient.getText().trim());
				patient.setSurname(textFieldSurnamePatient.getText().trim());
				patient.setGender((String) comboBoxGenderPatient.getSelectedItem());
				String sDate1 = textFieldBirthDatePatient.getText().trim();
				Date date1 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
				} catch (ParseException e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}
				patient.setBirthDate(date1);
				String password = new String(textFieldPasswordPatient.getPassword());
				patient.setPassword(password);
				patient.setPhoneNumber(textFieldPhoneNumberPatient.getText().trim());
				patient.setAddress(textFieldAddressPatient.getText());

				try {
					patientManager.insert(patient);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}

				panelLogin.setVisible(true);
				panelRegistrationPatient.setVisible(false);
				tfLoginPassword.setText("");
				tfLoginUser.setText("");
			}

		});
		btnAceptarRegistro.setBounds(274, 257, 89, 23);
		panelRegistrationPatient.add(btnAceptarRegistro);

		JButton btnCancelarRegistro = new JButton("Cancelar");
		btnCancelarRegistro.setBackground(new Color(255, 255, 255));
		btnCancelarRegistro.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelarRegistro.setForeground(new Color(16, 169, 121));
		btnCancelarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(true);
				panelRegistrationPatient.setVisible(false);
				tfLoginPassword.setText("");
				tfLoginUser.setText("");
			}
		});
		btnCancelarRegistro.setBounds(373, 257, 89, 23);
		panelRegistrationPatient.add(btnCancelarRegistro);

		JLabel lblBirthDatePatient = new JLabel("Fecha Nacimiento:");
		lblBirthDatePatient.setForeground(new Color(255, 255, 255));
		lblBirthDatePatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBirthDatePatient.setBounds(98, 213, 119, 14);
		panelRegistrationPatient.add(lblBirthDatePatient);

		textFieldBirthDatePatient = new JTextField();
		textFieldBirthDatePatient.setColumns(10);
		textFieldBirthDatePatient.setBounds(98, 228, 119, 20);
		panelRegistrationPatient.add(textFieldBirthDatePatient);

		JLabel lblNumberPhonePatient = new JLabel("Numero Telefono:");
		lblNumberPhonePatient.setForeground(new Color(255, 255, 255));
		lblNumberPhonePatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumberPhonePatient.setBounds(98, 248, 119, 14);
		panelRegistrationPatient.add(lblNumberPhonePatient);

		textFieldPhoneNumberPatient = new JTextField();
		textFieldPhoneNumberPatient.setColumns(10);
		textFieldPhoneNumberPatient.setBounds(98, 263, 119, 20);
		panelRegistrationPatient.add(textFieldPhoneNumberPatient);
		textFieldPhoneNumberPatient.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldPhoneNumberPatient.getCaretPosition() >= 9) {
					e.consume();
				}
			}
		});

		JLabel lblAddressPatient = new JLabel("Direccion:");
		lblAddressPatient.setForeground(new Color(255, 255, 255));
		lblAddressPatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddressPatient.setBounds(98, 283, 119, 14);
		panelRegistrationPatient.add(lblAddressPatient);

		textFieldAddressPatient = new JTextField();
		textFieldAddressPatient.setColumns(10);
		textFieldAddressPatient.setBounds(98, 297, 119, 20);
		panelRegistrationPatient.add(textFieldAddressPatient);
		textFieldAddressPatient.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldAddressPatient.getCaretPosition() >= 70) {
					e.consume();
				}
			}
		});

		JLabel lblPatientIcon = new JLabel("");
		lblPatientIcon.setIcon(new ImageIcon(View.class.getResource("/view/images/PatientIcon.jpg")));
		lblPatientIcon.setBounds(264, 104, 140, 140);
		panelRegistrationPatient.add(lblPatientIcon);

		JLabel lblLettersRegistrationPatient = new JLabel("");
		lblLettersRegistrationPatient.setIcon(new ImageIcon(View.class.getResource("/view/images/Letters.jpg")));
		lblLettersRegistrationPatient.setBounds(0, 105, 140, 140);
		panelRegistrationPatient.add(lblLettersRegistrationPatient);

		JLabel lblCrossRegistrationPatient = new JLabel("");
		lblCrossRegistrationPatient.setIcon(new ImageIcon(View.class.getResource("/view/images/OsasunbideCross.jpg")));
		lblCrossRegistrationPatient.setBounds(476, 105, 140, 140);
		panelRegistrationPatient.add(lblCrossRegistrationPatient);

		panelRegistrationDoctor = new JPanel();
		panelRegistrationDoctor.setBackground(new Color(16, 169, 121));
		panelRegistrationDoctor.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelRegistrationDoctor);
		panelRegistrationDoctor.setLayout(null);
		panelRegistrationDoctor.setVisible(false);

		JLabel lblTitleSingUpDoctor = new JLabel("Usted se esta registrando como Medico");
		lblTitleSingUpDoctor.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleSingUpDoctor.setForeground(new Color(255, 255, 255));
		lblTitleSingUpDoctor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitleSingUpDoctor.setBounds(195, 37, 300, 25);
		panelRegistrationDoctor.add(lblTitleSingUpDoctor);

		JLabel lblDniDoctor = new JLabel("DNI:");
		lblDniDoctor.setBackground(new Color(240, 240, 240));
		lblDniDoctor.setForeground(new Color(255, 255, 255));
		lblDniDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDniDoctor.setBounds(60, 22, 119, 14);
		panelRegistrationDoctor.add(lblDniDoctor);

		textFieldDniDoctor = new JTextField();
		textFieldDniDoctor.setBounds(60, 35, 119, 20);
		panelRegistrationDoctor.add(textFieldDniDoctor);
		textFieldDniDoctor.setColumns(10);
		textFieldDniDoctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldDniDoctor.getCaretPosition() >= 9) {
					e.consume();
				}
			}
		});

		JLabel lblNameDoctor = new JLabel("Nombre:");
		lblNameDoctor.setBackground(new Color(240, 240, 240));
		lblNameDoctor.setForeground(new Color(255, 255, 255));
		lblNameDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNameDoctor.setBounds(60, 60, 119, 14);
		panelRegistrationDoctor.add(lblNameDoctor);

		textFieldNameDoctor = new JTextField();
		textFieldNameDoctor.setColumns(10);
		textFieldNameDoctor.setBounds(60, 74, 119, 20);
		panelRegistrationDoctor.add(textFieldNameDoctor);
		textFieldNameDoctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldNameDoctor.getCaretPosition() >= 50) {
					e.consume();
				}
			}
		});

		JLabel lblSurnameDoctor = new JLabel("Apellido:");
		lblSurnameDoctor.setBackground(new Color(240, 240, 240));
		lblSurnameDoctor.setForeground(new Color(255, 255, 255));
		lblSurnameDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSurnameDoctor.setBounds(60, 95, 119, 14);
		panelRegistrationDoctor.add(lblSurnameDoctor);

		textFieldSurnameDoctor = new JTextField();
		textFieldSurnameDoctor.setColumns(10);
		textFieldSurnameDoctor.setBounds(60, 110, 119, 20);
		panelRegistrationDoctor.add(textFieldSurnameDoctor);
		textFieldSurnameDoctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldSurnameDoctor.getCaretPosition() >= 50) {
					e.consume();
				}
			}
		});

		JLabel lblGenderDoctor = new JLabel("Sexo:");
		lblGenderDoctor.setBackground(new Color(240, 240, 240));
		lblGenderDoctor.setForeground(new Color(255, 255, 255));
		lblGenderDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGenderDoctor.setBounds(60, 133, 119, 14);
		panelRegistrationDoctor.add(lblGenderDoctor);

		comboBoxGenderDoctor = new JComboBox<String>();
		comboBoxGenderDoctor.setBounds(60, 145, 119, 20);
		panelRegistrationDoctor.add(comboBoxGenderDoctor);
		comboBoxGenderDoctor.addItem("Hombre");
		comboBoxGenderDoctor.addItem("Mujer");

		JLabel lblBirthDateDoctor = new JLabel("Fecha Nacimiento:");
		lblBirthDateDoctor.setBackground(new Color(240, 240, 240));
		lblBirthDateDoctor.setForeground(new Color(255, 255, 255));
		lblBirthDateDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBirthDateDoctor.setBounds(60, 170, 119, 14);
		panelRegistrationDoctor.add(lblBirthDateDoctor);

		textFieldBirthDateDoctor = new JTextField();
		textFieldBirthDateDoctor.setColumns(10);
		textFieldBirthDateDoctor.setBounds(60, 185, 119, 20);
		panelRegistrationDoctor.add(textFieldBirthDateDoctor);

		passwordFieldDoctor = new JPasswordField();
		passwordFieldDoctor.setBounds(60, 220, 119, 20);
		panelRegistrationDoctor.add(passwordFieldDoctor);
		passwordFieldDoctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (passwordFieldDoctor.getCaretPosition() >= 50) {
					e.consume();
				}
			}
		});

		JLabel lblContrasenaDoctor = new JLabel("Contrasena:");
		lblContrasenaDoctor.setBackground(new Color(240, 240, 240));
		lblContrasenaDoctor.setForeground(new Color(255, 255, 255));
		lblContrasenaDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContrasenaDoctor.setBounds(60, 205, 119, 14);
		panelRegistrationDoctor.add(lblContrasenaDoctor);

		JLabel lblStaffNumDoctor = new JLabel("Numero Personal:");
		lblStaffNumDoctor.setBackground(new Color(240, 240, 240));
		lblStaffNumDoctor.setForeground(new Color(255, 255, 255));
		lblStaffNumDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStaffNumDoctor.setBounds(60, 245, 119, 14);
		panelRegistrationDoctor.add(lblStaffNumDoctor);

		textFieldStaffNumDoctor = new JTextField();
		textFieldStaffNumDoctor.setColumns(10);
		textFieldStaffNumDoctor.setBounds(60, 260, 119, 20);
		panelRegistrationDoctor.add(textFieldStaffNumDoctor);

		JLabel lblSalaryDoctor = new JLabel("Salario:");
		lblSalaryDoctor.setBackground(new Color(240, 240, 240));
		lblSalaryDoctor.setForeground(new Color(255, 255, 255));
		lblSalaryDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSalaryDoctor.setBounds(60, 282, 119, 14);
		panelRegistrationDoctor.add(lblSalaryDoctor);

		textFieldSalaryDoctor = new JTextField();
		textFieldSalaryDoctor.setColumns(10);
		textFieldSalaryDoctor.setBounds(60, 300, 119, 20);
		panelRegistrationDoctor.add(textFieldSalaryDoctor);

		JLabel lblSpecialityDoctor = new JLabel("Especialidad:");
		lblSpecialityDoctor.setBackground(new Color(240, 240, 240));
		lblSpecialityDoctor.setForeground(new Color(255, 255, 255));
		lblSpecialityDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSpecialityDoctor.setBounds(206, 133, 119, 14);
		panelRegistrationDoctor.add(lblSpecialityDoctor);

		textFieldSpecialityDoctor = new JTextField();
		textFieldSpecialityDoctor.setColumns(10);
		textFieldSpecialityDoctor.setBounds(206, 148, 119, 20);
		panelRegistrationDoctor.add(textFieldSpecialityDoctor);
		textFieldSpecialityDoctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldSpecialityDoctor.getCaretPosition() >= 30) {
					e.consume();
				}
			}
		});

		btnCancelDoctor = new JButton("Cancelar");
		btnCancelDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelDoctor.setBackground(new Color(255, 255, 255));
		btnCancelDoctor.setForeground(new Color(16, 169, 121));
		btnCancelDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(true);
				panelRegistrationDoctor.setVisible(false);
				tfLoginPassword.setText("");
				tfLoginUser.setText("");
			}
		});
		btnCancelDoctor.setBounds(341, 275, 89, 23);
		panelRegistrationDoctor.add(btnCancelDoctor);

		btnAceptDoctor = new JButton("Aceptar");
		btnAceptDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAceptDoctor.setBackground(new Color(255, 255, 255));
		btnAceptDoctor.setForeground(new Color(16, 169, 121));
		btnAceptDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doctor doctor = new Doctor();
				try {
					if (nurseManager.select(textFieldDniDoctor.getText()) != null
							|| doctorManager.select(textFieldDniDoctor.getText()) != null) {
						JOptionPane.showMessageDialog(btnAceptarRegistro, "Usuario ya registrado", "Aviso", 2);

					} else {
						doctor.setDni(textFieldDniDoctor.getText());
						doctor.setName(textFieldNameDoctor.getText());
						doctor.setSurname(textFieldSurnameDoctor.getText());
						doctor.setGender((String) comboBoxGenderDoctor.getSelectedItem());
						String sDate1 = textFieldBirthDateDoctor.getText();
						Date date1 = null;
						date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						doctor.setBirthDate(date1);
						String password = new String(passwordFieldDoctor.getPassword());
						doctor.setPassword(password);
						doctor.setStaffNum(Integer.parseInt(textFieldStaffNumDoctor.getText()));
						doctor.setSalary(Integer.parseInt(textFieldSalaryDoctor.getText()));
						doctor.setMir(true);
						doctor.setType("Medicina");
						doctor.setSpeciality(textFieldSpecialityDoctor.getText());
						Ambulatory ambulatory = null;

						ambulatory = appointmentSelectionManager
								.selectAmbulatory((String) comboBoxAmbulatoryDoctor.getSelectedItem());
						doctor.setAmbulatory(ambulatory);
						doctorManager.insert(doctor);
						JOptionPane.showMessageDialog(null, "Usuario registrado", "Bien", 3);
						panelLogin.setVisible(true);
						panelRegistrationDoctor.setVisible(false);
						tfLoginPassword.setText("");
						tfLoginUser.setText("");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAceptDoctor.setBounds(245, 275, 89, 23);
		panelRegistrationDoctor.add(btnAceptDoctor);

		JLabel lblAmbulatoryDoctor = new JLabel("Ambulatorio:");
		lblAmbulatoryDoctor.setBackground(new Color(240, 240, 240));
		lblAmbulatoryDoctor.setForeground(new Color(255, 255, 255));
		lblAmbulatoryDoctor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAmbulatoryDoctor.setBounds(206, 173, 119, 14);
		panelRegistrationDoctor.add(lblAmbulatoryDoctor);

		comboBoxAmbulatoryDoctor = new JComboBox<String>();
		comboBoxAmbulatoryDoctor.setBounds(206, 188, 119, 20);
		panelRegistrationDoctor.add(comboBoxAmbulatoryDoctor);

		JLabel lblCrossSingUpDoctor = new JLabel("");
		lblCrossSingUpDoctor.setIcon(new ImageIcon(View.class.getResource("/view/images/OsasunbideCross.jpg")));
		lblCrossSingUpDoctor.setBounds(476, 105, 140, 140);
		panelRegistrationDoctor.add(lblCrossSingUpDoctor);

		JLabel lblLettersSingUpDoctor = new JLabel("");
		lblLettersSingUpDoctor.setIcon(new ImageIcon(View.class.getResource("/view/images/Letters.jpg")));
		lblLettersSingUpDoctor.setBounds(0, 105, 140, 140);
		panelRegistrationDoctor.add(lblLettersSingUpDoctor);

		JLabel lblIconSingUpDoctor = new JLabel("");
		lblIconSingUpDoctor.setIcon(new ImageIcon(View.class.getResource("/view/images/DoctorIcon.jpg")));
		lblIconSingUpDoctor.setBounds(342, 65, 140, 140);
		panelRegistrationDoctor.add(lblIconSingUpDoctor);

		JLabel lblRegistro = new JLabel("REGISTRO");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setForeground(Color.WHITE);
		lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegistro.setBounds(290, 11, 100, 25);
		panelRegistrationDoctor.add(lblRegistro);

		panelRegistrationNurse = new JPanel();
		panelRegistrationNurse.setBackground(new Color(16, 169, 121));
		panelRegistrationNurse.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelRegistrationNurse);
		panelRegistrationNurse.setLayout(null);
		panelRegistrationNurse.setVisible(false);

		JLabel lblTitleSingUpNurse = new JLabel("REGISTRO");
		lblTitleSingUpNurse.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleSingUpNurse.setForeground(new Color(255, 255, 255));
		lblTitleSingUpNurse.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitleSingUpNurse.setBounds(290, 11, 100, 25);
		panelRegistrationNurse.add(lblTitleSingUpNurse);

		JLabel lblNameNurse = new JLabel("Nombre:");
		lblNameNurse.setForeground(new Color(255, 255, 255));
		lblNameNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNameNurse.setBounds(60, 65, 119, 14);
		panelRegistrationNurse.add(lblNameNurse);

		JLabel lblSurnameNurse = new JLabel("Apellido:");
		lblSurnameNurse.setForeground(new Color(255, 255, 255));
		lblSurnameNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSurnameNurse.setBounds(60, 100, 119, 14);
		panelRegistrationNurse.add(lblSurnameNurse);

		JLabel lblGenderNurse = new JLabel("Sexo:");
		lblGenderNurse.setForeground(new Color(255, 255, 255));
		lblGenderNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGenderNurse.setBounds(60, 135, 119, 14);
		panelRegistrationNurse.add(lblGenderNurse);

		JLabel lblBirthDateNurse = new JLabel("Fecha Nacimiento:");
		lblBirthDateNurse.setForeground(new Color(255, 255, 255));
		lblBirthDateNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBirthDateNurse.setBounds(60, 175, 119, 14);
		panelRegistrationNurse.add(lblBirthDateNurse);

		JLabel lblDniNurse = new JLabel("Dni:");
		lblDniNurse.setForeground(new Color(255, 255, 255));
		lblDniNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDniNurse.setBounds(60, 27, 119, 14);
		panelRegistrationNurse.add(lblDniNurse);

		JComboBox<String> comboBoxGenderNurse = new JComboBox<String>();
		comboBoxGenderNurse.setBounds(60, 150, 119, 22);
		panelRegistrationNurse.add(comboBoxGenderNurse);
		comboBoxGenderNurse.addItem("Hombre");
		comboBoxGenderNurse.addItem("Mujer");

		textFieldDniNurse = new JTextField();
		textFieldDniNurse.setText("");
		textFieldDniNurse.setBounds(60, 40, 119, 20);
		panelRegistrationNurse.add(textFieldDniNurse);
		textFieldDniNurse.setColumns(10);
		textFieldDniNurse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldDniNurse.getCaretPosition() >= 9) {
					e.consume();
				}
			}
		});

		textFieldNameNurse = new JTextField();
		textFieldNameNurse.setText("");
		textFieldNameNurse.setColumns(10);
		textFieldNameNurse.setBounds(60, 78, 119, 20);
		panelRegistrationNurse.add(textFieldNameNurse);
		textFieldNameNurse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldNameNurse.getCaretPosition() >= 50) {
					e.consume();
				}
			}
		});

		textFieldSurnameNurse = new JTextField();
		textFieldSurnameNurse.setText("");
		textFieldSurnameNurse.setColumns(10);
		textFieldSurnameNurse.setBounds(60, 115, 119, 20);
		panelRegistrationNurse.add(textFieldSurnameNurse);
		textFieldSurnameNurse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldSurnameNurse.getCaretPosition() >= 50) {
					e.consume();
				}
			}
		});

		textFieldBirthDateNurse = new JTextField();
		textFieldBirthDateNurse.setText("");
		textFieldBirthDateNurse.setColumns(10);
		textFieldBirthDateNurse.setBounds(60, 190, 119, 20);
		panelRegistrationNurse.add(textFieldBirthDateNurse);

		JLabel lblPasswordNurse = new JLabel("Contrasena:");
		lblPasswordNurse.setForeground(new Color(255, 255, 255));
		lblPasswordNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPasswordNurse.setBounds(60, 210, 119, 14);
		panelRegistrationNurse.add(lblPasswordNurse);

		JLabel lblStaffNumberNurse = new JLabel("Numero Personal:");
		lblStaffNumberNurse.setForeground(new Color(255, 255, 255));
		lblStaffNumberNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStaffNumberNurse.setBounds(60, 245, 119, 14);
		panelRegistrationNurse.add(lblStaffNumberNurse);

		JLabel lblSalaryNurse = new JLabel("Salario:");
		lblSalaryNurse.setForeground(new Color(255, 255, 255));
		lblSalaryNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSalaryNurse.setBounds(60, 280, 119, 14);
		panelRegistrationNurse.add(lblSalaryNurse);

		JLabel lblCategoryNurse = new JLabel("Categoria:");
		lblCategoryNurse.setForeground(new Color(255, 255, 255));
		lblCategoryNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCategoryNurse.setBounds(200, 150, 119, 14);
		panelRegistrationNurse.add(lblCategoryNurse);

		textFieldStaffNumberNurse = new JTextField();
		textFieldStaffNumberNurse.setText("");
		textFieldStaffNumberNurse.setColumns(10);
		textFieldStaffNumberNurse.setBounds(60, 260, 119, 20);
		panelRegistrationNurse.add(textFieldStaffNumberNurse);

		textFieldSalaryNurse = new JTextField();
		textFieldSalaryNurse.setText("");
		textFieldSalaryNurse.setColumns(10);
		textFieldSalaryNurse.setBounds(60, 295, 119, 20);
		panelRegistrationNurse.add(textFieldSalaryNurse);

		textFieldCategoryNurse = new JTextField();
		textFieldCategoryNurse.setText("");
		textFieldCategoryNurse.setColumns(10);
		textFieldCategoryNurse.setBounds(200, 165, 119, 20);
		panelRegistrationNurse.add(textFieldCategoryNurse);
		textFieldCategoryNurse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldCategoryNurse.getCaretPosition() >= 30) {
					e.consume();
				}
			}
		});

		btnAceptNurse = new JButton("Aceptar");
		btnAceptNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAceptNurse.setForeground(new Color(16, 169, 121));
		btnAceptNurse.setBackground(new Color(255, 255, 255));
		btnAceptNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (nurseManager.select(textFieldDniNurse.getText()) != null
							|| doctorManager.select(textFieldDniNurse.getText()) != null) {
						JOptionPane.showMessageDialog(btnAceptarRegistro, "Usuario ya registrado", "Aviso", 2);
					} else {
						Nurse nurse = new Nurse();
						nurse.setDni(textFieldDniNurse.getText());
						nurse.setName(textFieldNameNurse.getText());
						nurse.setSurname(textFieldSurnameNurse.getText());
						nurse.setGender((String) comboBoxGenderNurse.getSelectedItem());
						String sDate1 = textFieldBirthDateNurse.getText();
						Date date1 = null;
						try {
							date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
						} catch (ParseException e2) {
							JOptionPane.showMessageDialog(null, "Se ha producido un error .", "Error", 0);
						}
						nurse.setBirthDate(date1);
						String password = new String(passwordFieldNurse.getPassword());
						nurse.setPassword(password);
						nurse.setStaffNum(Integer.parseInt(textFieldStaffNumberNurse.getText()));
						nurse.setSalary(Integer.parseInt(textFieldSalaryNurse.getText()));
						nurse.setEir(true);
						nurse.setType("Enfermeria");
						nurse.setCategory(textFieldCategoryNurse.getText());

						Ambulatory ambulatory = null;
						try {
							ambulatory = appointmentSelectionManager
									.selectAmbulatory((String) comboBoxAmbulatoryNurse.getSelectedItem());
						} catch (SQLException e2) {
							JOptionPane.showMessageDialog(null, "Se ha producido un error en la base de datos.",
									"Error", 0);
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
						}
						nurse.setAmbulatory(ambulatory);

						try {
							nurseManager.insert(nurse);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Error Base De Datos", "Aviso", 2);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Errorª", "Aviso", 2);
						}

						JOptionPane.showMessageDialog(null, "Usuario registrado", "Bien", 3);
						panelLogin.setVisible(true);
						panelRegistrationNurse.setVisible(false);
						tfLoginPassword.setText("");
						tfLoginUser.setText("");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAceptNurse.setBounds(245, 275, 89, 23);
		panelRegistrationNurse.add(btnAceptNurse);

		btnCancelNurse = new JButton("Cancelar");
		btnCancelNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelNurse.setForeground(new Color(16, 169, 121));
		btnCancelNurse.setBackground(new Color(255, 255, 255));
		btnCancelNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(true);
				panelRegistrationNurse.setVisible(false);
				tfLoginPassword.setText("");
				tfLoginUser.setText("");
			}
		});
		btnCancelNurse.setBounds(341, 275, 89, 23);
		panelRegistrationNurse.add(btnCancelNurse);

		passwordFieldNurse = new JPasswordField();
		passwordFieldNurse.setBounds(60, 225, 119, 20);
		panelRegistrationNurse.add(passwordFieldNurse);
		passwordFieldNurse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (passwordFieldNurse.getCaretPosition() >= 50) {
					e.consume();
				}
			}
		});

		JLabel lblAmbulatoryNurse = new JLabel("Ambulatorio:");
		lblAmbulatoryNurse.setForeground(new Color(255, 255, 255));
		lblAmbulatoryNurse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAmbulatoryNurse.setBounds(200, 190, 119, 14);
		panelRegistrationNurse.add(lblAmbulatoryNurse);

		comboBoxAmbulatoryNurse = new JComboBox<String>();
		comboBoxAmbulatoryNurse.setBounds(200, 205, 119, 20);
		panelRegistrationNurse.add(comboBoxAmbulatoryNurse);

		JLabel lblCrossSingUpNurse = new JLabel("");
		lblCrossSingUpNurse.setIcon(new ImageIcon(View.class.getResource("/view/images/OsasunbideCross.jpg")));
		lblCrossSingUpNurse.setBounds(475, 105, 140, 140);
		panelRegistrationNurse.add(lblCrossSingUpNurse);

		JLabel lblLettersSingUpNurse = new JLabel("");
		lblLettersSingUpNurse.setIcon(new ImageIcon(View.class.getResource("/view/images/Letters.jpg")));
		lblLettersSingUpNurse.setBounds(0, 105, 140, 140);
		panelRegistrationNurse.add(lblLettersSingUpNurse);

		JLabel lblIconSingUpNurse = new JLabel("");
		lblIconSingUpNurse.setIcon(new ImageIcon(View.class.getResource("/view/images/NurseIcon.jpg")));
		lblIconSingUpNurse.setBounds(339, 70, 140, 140);
		panelRegistrationNurse.add(lblIconSingUpNurse);

		JLabel lblUstedSeEsta = new JLabel("Usted se esta registrando como Enfermer@");
		lblUstedSeEsta.setBackground(new Color(255, 255, 255));
		lblUstedSeEsta.setHorizontalAlignment(SwingConstants.CENTER);
		lblUstedSeEsta.setForeground(Color.WHITE);
		lblUstedSeEsta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUstedSeEsta.setBounds(195, 37, 300, 25);
		panelRegistrationNurse.add(lblUstedSeEsta);

		// PANEL | SANITARIAN
		panelSanitarian = new JPanel();
		panelSanitarian.setBackground(new Color(16, 169, 121));
		panelSanitarian.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelSanitarian);
		panelSanitarian.setLayout(null);
		panelSanitarian.setVisible(false);

		JLabel lblDoctorIconPanelSanitarian = new JLabel("");
		lblDoctorIconPanelSanitarian.setIcon(new ImageIcon(View.class.getResource("/view/images/DoctorIcon.jpg")));
		lblDoctorIconPanelSanitarian.setBounds(35, 211, 140, 140);
		panelSanitarian.add(lblDoctorIconPanelSanitarian);

		JLabel lblPatient = new JLabel("EMPLEADO");
		lblPatient.setForeground(new Color(255, 255, 255));
		lblPatient.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPatient.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatient.setBounds(208, 27, 200, 25);
		panelSanitarian.add(lblPatient);

		JButton btnLogOutPanelSanitarian = new JButton("LOGOUT");
		btnLogOutPanelSanitarian.setBackground(new Color(255, 255, 255));
		btnLogOutPanelSanitarian.setForeground(new Color(16, 169, 121));
		btnLogOutPanelSanitarian.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOutPanelSanitarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSanitarian.setVisible(false);
				panelLogin.setVisible(true);
				tfLoginUser.setText("");
				tfLoginPassword.setText("");
			}
		});
		btnLogOutPanelSanitarian.setBounds(263, 264, 89, 23);
		panelSanitarian.add(btnLogOutPanelSanitarian);

		JButton btnBlockAmbulatoryPatient = new JButton("Gestión de pacientes");
		btnBlockAmbulatoryPatient.setForeground(new Color(255, 255, 255));
		btnBlockAmbulatoryPatient.setBackground(new Color(16, 169, 121));
		btnBlockAmbulatoryPatient.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBlockAmbulatoryPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBlockPatient.setVisible(true);
				panelSanitarian.setVisible(false);
			}
		});
		btnBlockAmbulatoryPatient.setBounds(228, 90, 160, 20);
		panelSanitarian.add(btnBlockAmbulatoryPatient);

		JButton btntodo2 = new JButton("TO DO 2");
		btntodo2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btntodo2.setForeground(new Color(255, 255, 255));
		btntodo2.setBackground(new Color(16, 169, 121));
		btntodo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btntodo2.setBounds(228, 150, 160, 20);
		panelSanitarian.add(btntodo2);

		JButton btntodo1 = new JButton("TO DO");
		btntodo1.setForeground(new Color(255, 255, 255));
		btntodo1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btntodo1.setBackground(new Color(16, 169, 121));
		btntodo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btntodo1.setBounds(228, 120, 160, 20);
		panelSanitarian.add(btntodo1);

		JButton btnModifySanitarianData = new JButton("Modificar contraseña");
		btnModifySanitarianData.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModifySanitarianData.setForeground(new Color(255, 255, 255));
		btnModifySanitarianData.setBackground(new Color(16, 169, 121));
		btnModifySanitarianData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModifySanitarian.setVisible(true);
				panelSanitarian.setVisible(false);
			}
		});
		btnModifySanitarianData.setBounds(228, 180, 160, 20);
		panelSanitarian.add(btnModifySanitarianData);

		JLabel lblLettersPanelSanitarian = new JLabel("");
		lblLettersPanelSanitarian.setIcon(new ImageIcon(View.class.getResource("/view/images/Letters.jpg")));
		lblLettersPanelSanitarian.setBounds(0, 105, 140, 140);
		panelSanitarian.add(lblLettersPanelSanitarian);

		JPanel panelBackGroundSanitarianMenu = new JPanel();
		panelBackGroundSanitarianMenu.setBackground(new Color(255, 255, 255));
		panelBackGroundSanitarianMenu.setBounds(198, 73, 220, 140);
		panelSanitarian.add(panelBackGroundSanitarianMenu);

		JLabel lblChooseMenuSanitarian = new JLabel("Seleccione una opción:");
		lblChooseMenuSanitarian.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseMenuSanitarian.setForeground(Color.WHITE);
		lblChooseMenuSanitarian.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChooseMenuSanitarian.setBounds(208, 45, 200, 25);
		panelSanitarian.add(lblChooseMenuSanitarian);

		JLabel lblNurseIconPanelSanitarian = new JLabel("");
		lblNurseIconPanelSanitarian.setIcon(new ImageIcon(View.class.getResource("/view/images/NurseIcon.jpg")));
		lblNurseIconPanelSanitarian.setBounds(395, 211, 140, 140);
		panelSanitarian.add(lblNurseIconPanelSanitarian);

		JLabel lblCrossPanelSanitarian = new JLabel("");
		lblCrossPanelSanitarian
				.setIcon(new ImageIcon(View.class.getResource("/view/images/OsasunbideCross.jpg")));
		lblCrossPanelSanitarian.setBounds(476, 105, 140, 140);
		panelSanitarian.add(lblCrossPanelSanitarian);

		// PANEL | PATIENT
		panelPatient = new JPanel();
		panelPatient.setBackground(new Color(16, 169, 121));
		panelPatient.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelPatient);
		panelPatient.setLayout(null);
		panelPatient.setVisible(false);

		JLabel lblLettersPanelSanitarian_1_1 = new JLabel("");
		lblLettersPanelSanitarian_1_1.setIcon(new ImageIcon(View.class.getResource("/view/images/PatientIcon.jpg")));
		lblLettersPanelSanitarian_1_1.setBounds(50, 211, 140, 140);
		panelPatient.add(lblLettersPanelSanitarian_1_1);

		lblCrossPanelPatient = new JLabel("");
		lblCrossPanelPatient.setIcon(new ImageIcon(View.class.getResource("/view/images/OsasunbideCross.jpg")));
		lblCrossPanelPatient.setBounds(476, 105, 140, 140);
		panelPatient.add(lblCrossPanelPatient);

		lblLettersPanelSanitarian_1 = new JLabel("");
		lblLettersPanelSanitarian_1.setIcon(new ImageIcon(View.class.getResource("/view/images/Letters.jpg")));
		lblLettersPanelSanitarian_1.setBounds(0, 105, 140, 140);
		panelPatient.add(lblLettersPanelSanitarian_1);

		lblPatientTitule = new JLabel("PACIENTE");
		lblPatientTitule.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatientTitule.setForeground(Color.WHITE);
		lblPatientTitule.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPatientTitule.setBounds(208, 27, 200, 25);
		panelPatient.add(lblPatientTitule);

		btnLogOutPanelPatient = new JButton("LOGOUT");
		btnLogOutPanelPatient.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOutPanelPatient.setForeground(new Color(16, 169, 121));
		btnLogOutPanelPatient.setBackground(new Color(255, 255, 255));
		btnLogOutPanelPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPatient.setVisible(false);
				panelLogin.setVisible(true);
				tfLoginUser.setText("");
				tfLoginPassword.setText("");
			}
		});
		btnLogOutPanelPatient.setBounds(263, 264, 89, 23);
		panelPatient.add(btnLogOutPanelPatient);

		JButton btnSelectAppointment = new JButton("Cita previa");
		btnSelectAppointment.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelectAppointment.setForeground(new Color(255, 255, 255));
		btnSelectAppointment.setBackground(new Color(16, 169, 121));
		btnSelectAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectAppointmentAmbulatoryType.setVisible(true);
				panelPatient.setVisible(false);
			}
		});
		btnSelectAppointment.setBounds(228, 90, 160, 20);
		panelPatient.add(btnSelectAppointment);

		JButton btnCancelAppointment = new JButton("Cancelar cita");
		btnCancelAppointment.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelAppointment.setForeground(new Color(255, 255, 255));
		btnCancelAppointment.setBackground(new Color(16, 169, 121));
		btnCancelAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btnCancelAppointment.setBounds(228, 150, 160, 20);
		panelPatient.add(btnCancelAppointment);

		JButton btnShowAppointments = new JButton("Ver citas");
		btnShowAppointments.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowAppointments.setForeground(new Color(255, 255, 255));
		btnShowAppointments.setBackground(new Color(16, 169, 121));
		btnShowAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btnShowAppointments.setBounds(228, 120, 160, 20);
		panelPatient.add(btnShowAppointments);

		JButton btnModifyPatientData = new JButton("Modificar datos");
		btnModifyPatientData.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModifyPatientData.setForeground(new Color(255, 255, 255));
		btnModifyPatientData.setBackground(new Color(16, 169, 121));
		btnModifyPatientData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModifyPatient.setVisible(true);
				panelPatient.setVisible(false);
			}
		});
		btnModifyPatientData.setBounds(228, 180, 160, 20);
		panelPatient.add(btnModifyPatientData);

		JPanel panelBackGroundPatientMenu = new JPanel();
		panelBackGroundPatientMenu.setBackground(Color.WHITE);
		panelBackGroundPatientMenu.setBounds(198, 73, 220, 140);
		panelPatient.add(panelBackGroundPatientMenu);

		JLabel lblChooseMenuPatient = new JLabel("Seleccione una opción:");
		lblChooseMenuPatient.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseMenuPatient.setForeground(Color.WHITE);
		lblChooseMenuPatient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChooseMenuPatient.setBounds(208, 45, 200, 25);
		panelPatient.add(lblChooseMenuPatient);

		// PANEL | MODIFICATION: PATIENT
		panelModifyPatient = new JPanel();
		panelModifyPatient.setBackground(new Color(16, 169, 121));
		panelModifyPatient.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelModifyPatient);
		panelModifyPatient.setLayout(null);
		panelModifyPatient.setVisible(false);

		JLabel lblModifyPatient = new JLabel("MODIFICACIÓN DE DATOS");
		lblModifyPatient.setForeground(new Color(255, 255, 255));
		lblModifyPatient.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblModifyPatient.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifyPatient.setBounds(158, 40, 300, 20);
		panelModifyPatient.add(lblModifyPatient);

		JLabel lblModifyPatientAddress = new JLabel("Dirección:");
		lblModifyPatientAddress.setForeground(new Color(255, 255, 255));
		lblModifyPatientAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModifyPatientAddress.setBounds(25, 241, 89, 14);
		panelModifyPatient.add(lblModifyPatientAddress);

		JLabel lblModifyPatientPhoneNumber = new JLabel("Teléfono:");
		lblModifyPatientPhoneNumber.setForeground(new Color(255, 255, 255));
		lblModifyPatientPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModifyPatientPhoneNumber.setBounds(215, 241, 81, 14);
		panelModifyPatient.add(lblModifyPatientPhoneNumber);

		JLabel lblModifyPatientPassword = new JLabel("Contraseña:");
		lblModifyPatientPassword.setForeground(new Color(255, 255, 255));
		lblModifyPatientPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModifyPatientPassword.setBounds(404, 241, 81, 14);
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
		tfModifyPatientPassword.setBounds(404, 256, 183, 20);
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
		tfModifyPatientAddress.setBounds(25, 256, 183, 20);
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
		tfModifyPatientPhoneNumber.setBounds(215, 256, 183, 20);
		panelModifyPatient.add(tfModifyPatientPhoneNumber);

		btnModifyPatientOk = new JButton("Guardar");
		btnModifyPatientOk.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModifyPatientOk.setBackground(new Color(255, 255, 255));
		btnModifyPatientOk.setForeground(new Color(16, 169, 121));
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

				panelModifyPatient.setVisible(false);
				panelPatient.setVisible(true);
			}
		});
		btnModifyPatientOk.setBounds(137, 293, 89, 23);
		panelModifyPatient.add(btnModifyPatientOk);

		btnModifyPatientCancel = new JButton("Cancelar");
		btnModifyPatientCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModifyPatientCancel.setBackground(new Color(255, 255, 255));
		btnModifyPatientCancel.setForeground(new Color(16, 169, 121));
		btnModifyPatientCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModifyPatient.setVisible(false);
				panelPatient.setVisible(true);
			}
		});
		btnModifyPatientCancel.setBounds(386, 293, 89, 23);
		panelModifyPatient.add(btnModifyPatientCancel);

		btnModifyPatientUnsubscribe = new JButton("Eliminar cuenta");
		btnModifyPatientUnsubscribe.setForeground(new Color(255, 255, 255));
		btnModifyPatientUnsubscribe.setBackground(new Color(16, 169, 121));
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

				panelModifyPatient.setVisible(false);
				panelPatient.setVisible(true);
			}
		});
		btnModifyPatientUnsubscribe.setBounds(242, 293, 130, 23);
		panelModifyPatient.add(btnModifyPatientUnsubscribe);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(174, 87, 268, 103);
		panelModifyPatient.add(scrollPane_1);

		tablePatientData = new JTable();
		tablePatientData.setEnabled(false);
		tablePatientData.setRowSelectionAllowed(false);
		scrollPane_1.setViewportView(tablePatientData);

		JLabel lblCrossModPatient = new JLabel("");
		lblCrossModPatient.setIcon(new ImageIcon(View.class.getResource("/view/images/OsasunbideCross.jpg")));
		lblCrossModPatient.setBounds(476, 105, 140, 140);
		panelModifyPatient.add(lblCrossModPatient);

		JLabel lblLettersModPatient = new JLabel("");
		lblLettersModPatient.setIcon(new ImageIcon(View.class.getResource("/view/images/Letters.jpg")));
		lblLettersModPatient.setBounds(0, 105, 140, 140);
		panelModifyPatient.add(lblLettersModPatient);

		JLabel lblShowDataModifyPatient = new JLabel("Acontinuación se muestran su datos actuales:");
		lblShowDataModifyPatient.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowDataModifyPatient.setForeground(Color.WHITE);
		lblShowDataModifyPatient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblShowDataModifyPatient.setBounds(158, 58, 300, 20);
		panelModifyPatient.add(lblShowDataModifyPatient);

		JLabel lblChangeTextModifyPatient = new JLabel("Si desea modificar, rellene las casillas:");
		lblChangeTextModifyPatient.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeTextModifyPatient.setForeground(Color.WHITE);
		lblChangeTextModifyPatient.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChangeTextModifyPatient.setBounds(158, 216, 300, 20);
		panelModifyPatient.add(lblChangeTextModifyPatient);

		// PANEL | MODIFICATION: SANITARIAN
		panelModifySanitarian = new JPanel();
		panelModifySanitarian.setBackground(new Color(16, 169, 121));
		panelModifySanitarian.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelModifySanitarian);
		panelModifySanitarian.setLayout(null);
		panelModifySanitarian.setVisible(false);

		JLabel lblModifySanitarian = new JLabel("MODIFICACIÓN DE DATOS");
		lblModifySanitarian.setForeground(new Color(255, 255, 255));
		lblModifySanitarian.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblModifySanitarian.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifySanitarian.setBounds(158, 32, 300, 20);
		panelModifySanitarian.add(lblModifySanitarian);

		JLabel lblModifySanitarianDNI = new JLabel("DNI:");
		lblModifySanitarianDNI.setForeground(new Color(255, 255, 255));
		lblModifySanitarianDNI.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModifySanitarianDNI.setSize(94, 23);
		lblModifySanitarianDNI.setLocation(106, 102);
		lblModifySanitarianDNI.setBounds(216, 105, 119, 14);
		panelModifySanitarian.add(lblModifySanitarianDNI);

		JLabel lblModifySanitarianPassword = new JLabel("Contraseña:");
		lblModifySanitarianPassword.setForeground(new Color(255, 255, 255));
		lblModifySanitarianPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModifySanitarianPassword.setBounds(216, 151, 119, 14);
		panelModifySanitarian.add(lblModifySanitarianPassword);

		tfModifySanitarianDNI = new JTextField();
		tfModifySanitarianDNI.setEnabled(false);
		tfModifySanitarianDNI.setEditable(false);
		tfModifySanitarianDNI.setBounds(216, 118, 183, 20);
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
		tfModifySanitarianPassword.setBounds(216, 165, 183, 20);
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

				panelModifySanitarian.setVisible(false);
				panelSanitarian.setVisible(true);
			}
		});
		btnModifySanitarianOk.setBounds(135, 271, 89, 23);
		panelModifySanitarian.add(btnModifySanitarianOk);

		btnModifySanitarianCancel = new JButton("Cancelar");
		btnModifySanitarianCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModifySanitarian.setVisible(false);
				panelSanitarian.setVisible(true);
			}
		});
		btnModifySanitarianCancel.setBounds(395, 271, 89, 23);
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

				panelModifySanitarian.setVisible(false);
				panelSanitarian.setVisible(true);
			}
		});
		btnModifySanitarianUnsubscribe.setBounds(243, 271, 130, 23);
		panelModifySanitarian.add(btnModifySanitarianUnsubscribe);

		JLabel lblCrossModSanitarian = new JLabel("");
		lblCrossModSanitarian.setIcon(new ImageIcon(View.class.getResource("/view/images/OsasunbideCross.jpg")));
		lblCrossModSanitarian.setBounds(476, 105, 140, 140);
		panelModifySanitarian.add(lblCrossModSanitarian);

		JLabel lblLettersModSanitarian = new JLabel("");
		lblLettersModSanitarian.setIcon(new ImageIcon(View.class.getResource("/view/images/Letters.jpg")));
		lblLettersModSanitarian.setBounds(0, 105, 140, 140);
		panelModifySanitarian.add(lblLettersModSanitarian);

		// PANEL | SELECT APPOINTMENT : AMBULATORY & SANITARIAN TYPE
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
		ArrayList<String> ambulatoriesNames = null;
		try {
			ambulatoriesNames = appointmentSelectionManager.selectAmbulatoryNames();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
		}
		for (String ambulatory : ambulatoriesNames) {
			cbAmbulatory.addItem(ambulatory);
			comboBoxAmbulatoryNurse.addItem(ambulatory);
			comboBoxAmbulatoryDoctor.addItem(ambulatory);
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

		ButtonGroup groupType = new ButtonGroup();
		groupType.add(rdbtnSelectDoctor);
		groupType.add(rdbtnSelectNurse);

		btnSelectAppointmentAmbulatoryTypeOk = new JButton("Siguiente");
		btnSelectAppointmentAmbulatoryTypeOk.setEnabled(false);
		btnSelectAppointmentAmbulatoryTypeOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wantedAmbulatory = (String) cbAmbulatory.getSelectedItem();

				for (Enumeration<AbstractButton> buttons = groupType.getElements(); buttons.hasMoreElements();) {
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
				
				DefaultTableModel model = (DefaultTableModel) tableSelectTimeSlot.getModel();
				model.setRowCount(0);
				cbSelectAppointmentDate.removeAll();
				cbSelectAppointmentDate.addItem("");
				cbSelectAppointmentSanitarian.removeAll();
				cbSelectAppointmentSanitarian.addItem("");
			}
		});
		btnSelectAppointmentAmbulatoryTypeOk.setBounds(199, 273, 85, 21);
		panelSelectAppointmentAmbulatoryType.add(btnSelectAppointmentAmbulatoryTypeOk);

		btnSelectAppointmentAmbulatoryTypeCancel = new JButton("Cancelar");
		btnSelectAppointmentAmbulatoryTypeCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPatient.setVisible(true);
				panelSelectAppointmentAmbulatoryType.setVisible(false);
			}
		});
		btnSelectAppointmentAmbulatoryTypeCancel.setBounds(315, 273, 85, 21);
		panelSelectAppointmentAmbulatoryType.add(btnSelectAppointmentAmbulatoryTypeCancel);
		panelSelectAppointmentAmbulatoryType.setVisible(false);

		// PANEL | SELECT APPOINTMENT : DATE & TIMESLOT
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
						Ambulatory ambulatory = appointmentSelectionManager.selectAmbulatory(wantedAmbulatory);
						sanitarians = appointmentSelectionManager.showAvailableSanitarianByDate(wantedSanitarian,
								dateString, ambulatory);
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
					appointmentSelectionManager.insertAppointmentWorkingDayTimeSlot(appointment, workingDay, timeSlot);

					JOptionPane.showMessageDialog(null, "Cita seleccionada", "Info", 1);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}
				panelPatient.setVisible(true);
				panelSelectAppointmentDateTimeSlot.setVisible(false);
			}
		});
		btnSelectAppointmentDateTimeSlotOk.setBounds(207, 292, 89, 23);
		panelSelectAppointmentDateTimeSlot.add(btnSelectAppointmentDateTimeSlotOk);

		JButton btnSelectAppointmentDateTimeSlotCancel = new JButton("Cancelar");
		btnSelectAppointmentDateTimeSlotCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectAppointmentDateTimeSlot.setVisible(false);
				panelSelectAppointmentAmbulatoryType.setVisible(true);
			}
		});
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

								if ((null == timeSlots) || (timeSlots.size() == 0)) {
									JOptionPane.showMessageDialog(null, "No quedan citas disponibles con " + name,
											"Error", 0);
								} else {
									for (TimeSlot timeSlot : timeSlots) {
										model.addRow(new String[] { surname + ", " + name, wantedAmbulatory,
												cbSelectAppointmentDate.getSelectedItem().toString(),
												timeSlotManager.select(timeSlot.getId()).getStartTime().toString() });
									}
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
			JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
		}
		cbSelectAppointmentSanitarian.setBounds(319, 76, 138, 22);
		panelSelectAppointmentDateTimeSlot.add(cbSelectAppointmentSanitarian);

		// PANEL | BLOCK OR UNLOCK PATIENTS
		panelBlockPatient = new JPanel();
		panelBlockPatient.setBackground(new Color(16, 169, 121));
		panelBlockPatient.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelBlockPatient);
		panelBlockPatient.setLayout(null);
		panelBlockPatient.setVisible(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 77, 443, 173);
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
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}
			}
		});

		btnBlockPatientOk = new JButton("Bloquear");
		btnBlockPatientOk.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBlockPatientOk.setForeground(new Color(16, 169, 121));
		btnBlockPatientOk.setBackground(new Color(255, 255, 255));
		btnBlockPatientOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tableBlockPatients.getSelectedRow();
				String patientDni = (String) tableBlockPatients.getValueAt(fila, 0);
				Patient patient = null;
				try {
					patient = userDataModificationManager.selectPatient(patientDni);
					if (false == patient.isBlocked()) {
						blockUnlockPatientManager.blockPatient(patient);
						JOptionPane.showMessageDialog(null, "Paciente bloqueado/a", "Confirmación", 1);
					} else {
						blockUnlockPatientManager.unlockPatient(patient);
						JOptionPane.showMessageDialog(null, "Paciente desbloqueado/a", "Confirmación", 1);
					}

					ArrayList<Patient> patients = blockUnlockPatientManager.showPatientByAmbulatoryId(
							userDataModificationManager.selectDoctor(userDNI).getAmbulatory().getId());

					DefaultTableModel model = (DefaultTableModel) tableBlockPatients.getModel();
					model.setRowCount(0);

					for (Patient Onepatient : patients) {
						model.addRow(new String[] { Onepatient.getDni(), Onepatient.getName(), Onepatient.getSurname(),
								blockUnlockPatientManager.patientState(Onepatient.isBlocked()) });
					}
					tableBlockPatients.repaint();

				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la BBDD.", "Error", 0);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);

				}
			}
		});
		btnBlockPatientOk.setBounds(265, 261, 85, 21);
		panelBlockPatient.add(btnBlockPatientOk);

		JButton btnBlockPatienCancel = new JButton("Cancelar");
		btnBlockPatienCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBlockPatienCancel.setForeground(new Color(16, 169, 121));
		btnBlockPatienCancel.setBackground(new Color(255, 255, 255));
		btnBlockPatienCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBlockPatient.setVisible(false);
				panelSanitarian.setVisible(true);
			}
		});
		btnBlockPatienCancel.setBounds(265, 289, 85, 21);
		panelBlockPatient.add(btnBlockPatienCancel);
		panelBlockPatient.setVisible(false);
		panelSelectAppointmentAmbulatoryType.setVisible(false);
		tablePatientData.setDefaultEditor(Object.class, null);

	}
}
