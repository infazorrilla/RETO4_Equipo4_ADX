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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import manager.AmbulatoryManager;
import manager.AppointmentSelectionManager;
import manager.BlockUnlockPatientManager;
import manager.PatientManager;
import manager.RegistrationManager;
import manager.TimeSlotManager;
import manager.UserDataModificationManager;
import model.pojos.Ambulatory;
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

	private BlockUnlockPatientManager blockUnlockPatientManager;
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
	private AmbulatoryManager ambulatoryManager;
	private RegistrationManager registrationManager;
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
	private JComboBox comboBoxGenderDoctor;
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
	private JComboBox comboBoxAmbulatoryNurse;
	private JComboBox comboBoxAmbulatoryDoctor;
	private JPanel panelBlockPatient;
	private JTable tableBlockPatients;
	private JButton btnBlockPatientOk;

	/**
	 * Create the application.
	 */
	public View() {
//		patientManager = new PatientManager();
//		doctorManager = new DoctorManager();
//		nurseManager = new NurseManager();
		userDataModificationManager = new UserDataModificationManager();
		appointmentSelectionManager = new AppointmentSelectionManager();
		timeSlotManager = new TimeSlotManager();
		blockUnlockPatientManager = new BlockUnlockPatientManager();
		patientManager = new PatientManager();
		doctorManager = new DoctorManager();
		nurseManager = new NurseManager();
		ambulatoryManager = new AmbulatoryManager();
		registrationManager = new RegistrationManager();

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

//		LOGIN | PANEL
		panelLogin = new JPanel();
		panelLogin.setBackground(new Color(16, 169, 121));
		panelLogin.setBounds(0, 0, 344, 601);
		frame.getContentPane().add(panelLogin);
		panelLogin.setLayout(null);
		panelLogin.setVisible(true);

		JLabel lblLogin = new JLabel("Inicio de sesión");
		panelLogin.setBounds(0, 0, 616, 351);
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
					tfModifyPatientDNI.setText(userDNI);
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
					try {
						patients = blockUnlockPatientManager.showPatientByAmbulatoryId(
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
								blockUnlockPatientManager.patientState(patient.isBlocked()) });
					}
				}
			}
		});
		btnLoginOk.setBounds(204, 245, 89, 23);
		panelLogin.add(btnLoginOk);

		btnLoginCancel = new JButton("Cancelar");
		btnLoginCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfLoginUser.setText("");
				tfLoginPassword.setText("");
			}
		});
		btnLoginCancel.setBounds(346, 245, 89, 23);
		panelLogin.add(btnLoginCancel);

		JButton btnLoginResgistrationPatient = new JButton("Registro Paciente");
		btnLoginResgistrationPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(false);
				panelRegistrationPatient.setVisible(true);
			}
		});
		btnLoginResgistrationPatient.setBounds(442, 10, 154, 37);
		panelLogin.add(btnLoginResgistrationPatient);

		btnRegistroSanitarian = new JButton("Registro Sanitario");
		btnRegistroSanitarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(false);
				panelRegistrationPatient.setVisible(false);
				panelNurseOrDoctor.setVisible(true);
				panelRegistrationDoctor.setVisible(false);
				panelRegistrationNurse.setVisible(false);
			}
		});
		btnRegistroSanitarian.setBounds(442, 62, 154, 37);
		panelLogin.add(btnRegistroSanitarian);

		lblCross = new JLabel("");
		lblCross.setIcon(new ImageIcon(View.class.getResource("/view/images/OsasunbideCross.jpg")));
		lblCross.setBounds(476, 105, 140, 140);
		panelLogin.add(lblCross);

		JLabel lblOsasunbide = new JLabel("");
		lblOsasunbide.setIcon(new ImageIcon(View.class.getResource("/view/images/Letters.jpg")));
		lblOsasunbide.setBounds(0, 105, 140, 140);
		panelLogin.add(lblOsasunbide);

//		PANEL | REGISTRATION: PATIENT
		panelRegistrationPatient = new JPanel();
		panelRegistrationPatient.setBounds(0, 0, 616, 351);
		panelRegistrationPatient.setBackground(new Color(0, 128, 192));
		frame.getContentPane().add(panelRegistrationPatient);
		panelRegistrationPatient.setLayout(null);
		panelRegistrationPatient.setVisible(false);

		JLabel lblRegistroUsuario = new JLabel("Registro de usuario Paciente");
		lblRegistroUsuario.setBounds(227, 34, 165, 14);
		panelRegistrationPatient.add(lblRegistroUsuario);

		JLabel lblDniRegistro = new JLabel("DNI:");
		lblDniRegistro.setBounds(29, 93, 119, 14);
		panelRegistrationPatient.add(lblDniRegistro);

		JLabel lblNameRegister = new JLabel("Nombre:");
		lblNameRegister.setBounds(29, 118, 127, 14);
		panelRegistrationPatient.add(lblNameRegister);

		JLabel lblSurnamePatient = new JLabel("Apellido:");
		lblSurnamePatient.setBounds(29, 143, 119, 14);
		panelRegistrationPatient.add(lblSurnamePatient);

		JLabel lblGenderPatient = new JLabel("Sexo:");
		lblGenderPatient.setBounds(29, 168, 127, 14);
		panelRegistrationPatient.add(lblGenderPatient);

		JLabel lblPasswordPatient = new JLabel("Contraseña:");
		lblPasswordPatient.setBounds(29, 193, 119, 14);
		panelRegistrationPatient.add(lblPasswordPatient);

		JTextField textFieldDNIPatient = new JTextField();
		textFieldDNIPatient.setBounds(101, 90, 119, 20);
		panelRegistrationPatient.add(textFieldDNIPatient);
		textFieldDNIPatient.setColumns(10);

		JTextField textFieldNamePatient = new JTextField();
		textFieldNamePatient.setBounds(101, 118, 119, 20);
		panelRegistrationPatient.add(textFieldNamePatient);
		textFieldNamePatient.setColumns(10);

		JTextField textFieldSurnamePatient = new JTextField();
		textFieldSurnamePatient.setBounds(101, 143, 119, 20);
		panelRegistrationPatient.add(textFieldSurnamePatient);
		textFieldSurnamePatient.setColumns(10);

		JPasswordField textFieldPasswordPatient = new JPasswordField();
		textFieldPasswordPatient.setBounds(101, 193, 119, 20);
		panelRegistrationPatient.add(textFieldPasswordPatient);

		JComboBox<String> comboBoxGenderPatient = new JComboBox<String>();
		comboBoxGenderPatient.setBounds(101, 168, 119, 18);
		panelRegistrationPatient.add(comboBoxGenderPatient);
		comboBoxGenderPatient.addItem("Hombre");
		comboBoxGenderPatient.addItem("Mujer");

		JButton btnAceptarRegistro = new JButton("Aceptar");
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
			}

		});
		btnAceptarRegistro.setBounds(200, 271, 89, 23);
		panelRegistrationPatient.add(btnAceptarRegistro);

		JButton btnCancelarRegistro = new JButton("Cancelar");
		btnCancelarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(true);
				panelRegistrationPatient.setVisible(false);
			}
		});
		btnCancelarRegistro.setBounds(350, 271, 89, 23);
		panelRegistrationPatient.add(btnCancelarRegistro);

		JLabel lblBirthDatePatient = new JLabel("Fecha Nacimiento:");
		lblBirthDatePatient.setBounds(283, 93, 119, 14);
		panelRegistrationPatient.add(lblBirthDatePatient);

		textFieldBirthDatePatient = new JTextField();
		textFieldBirthDatePatient.setColumns(10);
		textFieldBirthDatePatient.setBounds(397, 90, 119, 20);
		panelRegistrationPatient.add(textFieldBirthDatePatient);

		JLabel lblNumberPhonePatient = new JLabel("Numero Telefono:");
		lblNumberPhonePatient.setBounds(283, 118, 119, 14);
		panelRegistrationPatient.add(lblNumberPhonePatient);

		textFieldPhoneNumberPatient = new JTextField();
		textFieldPhoneNumberPatient.setColumns(10);
		textFieldPhoneNumberPatient.setBounds(397, 115, 119, 20);
		panelRegistrationPatient.add(textFieldPhoneNumberPatient);

		JLabel lblAddressPatient = new JLabel("Direccion:");
		lblAddressPatient.setBounds(283, 143, 119, 14);
		panelRegistrationPatient.add(lblAddressPatient);

		textFieldAddressPatient = new JTextField();
		textFieldAddressPatient.setColumns(10);
		textFieldAddressPatient.setBounds(397, 140, 119, 20);
		panelRegistrationPatient.add(textFieldAddressPatient);

//		PANEL | REGISTRATION: SANITARIAN
		panelNurseOrDoctor = new JPanel();
		panelNurseOrDoctor.setBounds(0, 0, 616, 351);
		panelNurseOrDoctor.setBackground(new Color(51, 153, 204));
		frame.getContentPane().add(panelNurseOrDoctor);
		panelNurseOrDoctor.setLayout(null);
		panelNurseOrDoctor.setVisible(false);

		btnSelectDoctor = new JButton("Medico");
		btnSelectDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(false);
				panelNurseOrDoctor.setVisible(true);
			}
		});
		btnSelectDoctor.setBounds(110, 128, 149, 51);
		panelNurseOrDoctor.add(btnSelectDoctor);

		btnSelectNurse = new JButton("Enfermero");
		btnSelectNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationNurse.setVisible(true);
			}
		});
		btnSelectNurse.setBounds(367, 128, 149, 51);
		panelNurseOrDoctor.add(btnSelectNurse);

		JLabel lblNewLabel = new JLabel("Selecciona Sanitario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblNewLabel.setBounds(163, 35, 354, 40);
		panelNurseOrDoctor.add(lblNewLabel);

		panelRegistrationDoctor = new JPanel();
		panelRegistrationDoctor.setBackground(new Color(0, 153, 255));
		panelRegistrationDoctor.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelRegistrationDoctor);
		panelRegistrationDoctor.setLayout(null);
		panelRegistrationDoctor.setVisible(false);

		JLabel lblNewLabel_1 = new JLabel("Registro de usuario Medico");
		lblNewLabel_1.setBounds(249, 27, 158, 28);
		panelRegistrationDoctor.add(lblNewLabel_1);

		JLabel lblDniDoctor = new JLabel("DNI:");
		lblDniDoctor.setBounds(20, 77, 46, 14);
		panelRegistrationDoctor.add(lblDniDoctor);

		textFieldDniDoctor = new JTextField();
		textFieldDniDoctor.setBounds(153, 74, 103, 20);
		panelRegistrationDoctor.add(textFieldDniDoctor);
		textFieldDniDoctor.setColumns(10);

		JLabel lblNameDoctor = new JLabel("Nombre:");
		lblNameDoctor.setBounds(20, 120, 46, 14);
		panelRegistrationDoctor.add(lblNameDoctor);

		textFieldNameDoctor = new JTextField();
		textFieldNameDoctor.setColumns(10);
		textFieldNameDoctor.setBounds(153, 117, 103, 20);
		panelRegistrationDoctor.add(textFieldNameDoctor);

		JLabel lblSurnameDoctor = new JLabel("Apellido:");
		lblSurnameDoctor.setBounds(20, 157, 46, 14);
		panelRegistrationDoctor.add(lblSurnameDoctor);

		textFieldSurnameDoctor = new JTextField();
		textFieldSurnameDoctor.setColumns(10);
		textFieldSurnameDoctor.setBounds(153, 154, 103, 20);
		panelRegistrationDoctor.add(textFieldSurnameDoctor);

		JLabel lblGenderDoctor = new JLabel("Sexo:");
		lblGenderDoctor.setBounds(20, 199, 46, 14);
		panelRegistrationDoctor.add(lblGenderDoctor);

		JComboBox<String> comboBoxGenderDoctor = new JComboBox<String>();
		comboBoxGenderDoctor.setBounds(153, 196, 103, 20);
		panelRegistrationDoctor.add(comboBoxGenderDoctor);
		comboBoxGenderDoctor.addItem("Hombre");
		comboBoxGenderDoctor.addItem("Mujer");

		JLabel lblBirthDateDoctor = new JLabel("Fecha Nacimiento:");
		lblBirthDateDoctor.setBounds(20, 230, 121, 14);
		panelRegistrationDoctor.add(lblBirthDateDoctor);

		textFieldBirthDateDoctor = new JTextField();
		textFieldBirthDateDoctor.setColumns(10);
		textFieldBirthDateDoctor.setBounds(153, 227, 103, 20);
		panelRegistrationDoctor.add(textFieldBirthDateDoctor);

		passwordFieldDoctor = new JPasswordField();
		passwordFieldDoctor.setBounds(417, 74, 103, 20);
		panelRegistrationDoctor.add(passwordFieldDoctor);

		JLabel lblContrasenaDoctor = new JLabel("Contrasena:");
		lblContrasenaDoctor.setBounds(314, 74, 121, 20);
		panelRegistrationDoctor.add(lblContrasenaDoctor);

		JLabel lblStaffNumDoctor = new JLabel("Numero Personal:");
		lblStaffNumDoctor.setBounds(314, 117, 121, 20);
		panelRegistrationDoctor.add(lblStaffNumDoctor);

		textFieldStaffNumDoctor = new JTextField();
		textFieldStaffNumDoctor.setColumns(10);
		textFieldStaffNumDoctor.setBounds(417, 117, 103, 20);
		panelRegistrationDoctor.add(textFieldStaffNumDoctor);

		JLabel lblSalaryDoctor = new JLabel("Salario:");
		lblSalaryDoctor.setBounds(314, 157, 121, 20);
		panelRegistrationDoctor.add(lblSalaryDoctor);

		textFieldSalaryDoctor = new JTextField();
		textFieldSalaryDoctor.setColumns(10);
		textFieldSalaryDoctor.setBounds(417, 154, 103, 20);
		panelRegistrationDoctor.add(textFieldSalaryDoctor);

		JLabel lblSpecialityDoctor = new JLabel("Especialidad:");
		lblSpecialityDoctor.setBounds(314, 196, 121, 20);
		panelRegistrationDoctor.add(lblSpecialityDoctor);

		textFieldSpecialityDoctor = new JTextField();
		textFieldSpecialityDoctor.setColumns(10);
		textFieldSpecialityDoctor.setBounds(417, 196, 103, 20);
		panelRegistrationDoctor.add(textFieldSpecialityDoctor);

		btnCancelDoctor = new JButton("Cancelar");
		btnCancelDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(true);
				panelRegistrationDoctor.setVisible(false);
			}
		});
		btnCancelDoctor.setBounds(380, 270, 142, 38);
		panelRegistrationDoctor.add(btnCancelDoctor);

		btnAceptDoctor = new JButton("Aceptar");
		btnAceptDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doctor doctor = new Doctor();
				doctor.setDni(textFieldDniDoctor.getText());
				doctor.setName(textFieldNameDoctor.getText());
				doctor.setSurname(textFieldSurnameDoctor.getText());
				doctor.setGender((String) comboBoxGenderDoctor.getSelectedItem());
				String sDate1 = textFieldBirthDateDoctor.getText();
				Date date1 = null;
				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				doctor.setBirthDate(date1);
				String password = new String(passwordFieldDoctor.getPassword());
				doctor.setPassword(password);
				doctor.setStaffNum(Integer.parseInt(textFieldStaffNumDoctor.getText()));
				doctor.setSalary(Integer.parseInt(textFieldSalaryDoctor.getText()));
				doctor.setMir(true);
				doctor.setType("Medicina");
				doctor.setSpeciality(textFieldSpecialityDoctor.getText());
				try {
					registrationManager.select();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}
				// TODO
//				doctor.setAmbulatory((String) comboBoxAmbulatoryDoctor.getSelectedItem()); 

				try {
					doctorManager.insert(doctor);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}

				panelLogin.setVisible(true);
				panelRegistrationDoctor.setVisible(false);
			}
		});
		btnAceptDoctor.setBounds(99, 270, 142, 38);
		panelRegistrationDoctor.add(btnAceptDoctor);

		JLabel lblAmbulatoryDoctor = new JLabel("Ambulatorio:");
		lblAmbulatoryDoctor.setBounds(314, 230, 80, 20);
		panelRegistrationDoctor.add(lblAmbulatoryDoctor);

		JComboBox<String> comboBoxAmbulatoryDoctor = new JComboBox<String>();
		comboBoxAmbulatoryDoctor.setBounds(417, 226, 108, 22);
		panelRegistrationDoctor.add(comboBoxAmbulatoryDoctor);
		ArrayList<Ambulatory> ambulatories = new ArrayList<Ambulatory>();

		try {
			ambulatories = (ArrayList<Ambulatory>) registrationManager.select();
		} catch (SQLException e3) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
		} catch (Exception e3) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
		}
		for (Ambulatory ambulatory : ambulatories) {
			comboBoxAmbulatoryDoctor.addItem(ambulatory.getName());

		}

		panelRegistrationNurse = new JPanel();
		panelRegistrationNurse.setBackground(new Color(51, 153, 204));
		panelRegistrationNurse.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelRegistrationNurse);
		panelRegistrationNurse.setLayout(null);
		panelRegistrationNurse.setVisible(false);

		JLabel lblNewLabel_2 = new JLabel("Registro de usuario Enfermero");
		lblNewLabel_2.setBounds(210, 11, 207, 47);
		panelRegistrationNurse.add(lblNewLabel_2);

		JLabel lblNameNurse = new JLabel("Nombre:");
		lblNameNurse.setBounds(29, 120, 46, 14);
		panelRegistrationNurse.add(lblNameNurse);

		JLabel lblSurnameNurse = new JLabel("Apellido:");
		lblSurnameNurse.setBounds(29, 154, 46, 14);
		panelRegistrationNurse.add(lblSurnameNurse);

		JLabel lblGenderNurse = new JLabel("Sexo:");
		lblGenderNurse.setBounds(29, 193, 46, 14);
		panelRegistrationNurse.add(lblGenderNurse);

		JLabel lblBirthDateNurse = new JLabel("Fecha Nacimiento:");
		lblBirthDateNurse.setBounds(29, 235, 98, 14);
		panelRegistrationNurse.add(lblBirthDateNurse);

		JLabel lblDniNurse = new JLabel("Dni:");
		lblDniNurse.setBounds(29, 81, 46, 14);
		panelRegistrationNurse.add(lblDniNurse);

		JComboBox<String> comboBoxGenderNurse = new JComboBox<String>();
		comboBoxGenderNurse.setBounds(138, 189, 122, 22);
		panelRegistrationNurse.add(comboBoxGenderNurse);
		comboBoxGenderNurse.addItem("Hombre");
		comboBoxGenderNurse.addItem("Mujer");

		textFieldDniNurse = new JTextField();
		textFieldDniNurse.setText("");
		textFieldDniNurse.setBounds(138, 78, 122, 20);
		panelRegistrationNurse.add(textFieldDniNurse);
		textFieldDniNurse.setColumns(10);

		textFieldNameNurse = new JTextField();
		textFieldNameNurse.setText("");
		textFieldNameNurse.setColumns(10);
		textFieldNameNurse.setBounds(138, 117, 122, 20);
		panelRegistrationNurse.add(textFieldNameNurse);

		textFieldSurnameNurse = new JTextField();
		textFieldSurnameNurse.setText("");
		textFieldSurnameNurse.setColumns(10);
		textFieldSurnameNurse.setBounds(138, 151, 122, 20);
		panelRegistrationNurse.add(textFieldSurnameNurse);

		textFieldBirthDateNurse = new JTextField();
		textFieldBirthDateNurse.setText("");
		textFieldBirthDateNurse.setColumns(10);
		textFieldBirthDateNurse.setBounds(137, 232, 122, 20);
		panelRegistrationNurse.add(textFieldBirthDateNurse);

		JLabel lblPasswordNurse = new JLabel("Contrasena:");
		lblPasswordNurse.setBounds(366, 81, 73, 14);
		panelRegistrationNurse.add(lblPasswordNurse);

		JLabel lblStaffNumberNurse = new JLabel("Numero Personal:");
		lblStaffNumberNurse.setBounds(366, 120, 108, 14);
		panelRegistrationNurse.add(lblStaffNumberNurse);

		JLabel lblSalaryNurse = new JLabel("Salario:");
		lblSalaryNurse.setBounds(366, 154, 108, 14);
		panelRegistrationNurse.add(lblSalaryNurse);

		JLabel lblCategoryNurse = new JLabel("Categoria:");
		lblCategoryNurse.setBounds(366, 193, 108, 14);
		panelRegistrationNurse.add(lblCategoryNurse);

		textFieldStaffNumberNurse = new JTextField();
		textFieldStaffNumberNurse.setText("");
		textFieldStaffNumberNurse.setColumns(10);
		textFieldStaffNumberNurse.setBounds(472, 117, 122, 20);
		panelRegistrationNurse.add(textFieldStaffNumberNurse);

		textFieldSalaryNurse = new JTextField();
		textFieldSalaryNurse.setText("");
		textFieldSalaryNurse.setColumns(10);
		textFieldSalaryNurse.setBounds(472, 151, 122, 20);
		panelRegistrationNurse.add(textFieldSalaryNurse);

		textFieldCategoryNurse = new JTextField();
		textFieldCategoryNurse.setText("");
		textFieldCategoryNurse.setColumns(10);
		textFieldCategoryNurse.setBounds(472, 190, 122, 20);
		panelRegistrationNurse.add(textFieldCategoryNurse);

		btnAceptNurse = new JButton("Aceptar");
		btnAceptNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Nurse nurse = new Nurse();
				nurse.setDni(textFieldDniDoctor.getText());
				nurse.setName(textFieldNameDoctor.getText());
				nurse.setSurname(textFieldSurnameDoctor.getText());
				nurse.setGender((String) comboBoxGenderNurse.getSelectedItem());
				String sDate1 = textFieldBirthDateDoctor.getText();
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

				try {
					nurseManager.insert(nurse);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}

				panelLogin.setVisible(true);
				panelRegistrationNurse.setVisible(false);
			}
		});
		btnAceptNurse.setBounds(95, 280, 171, 47);
		panelRegistrationNurse.add(btnAceptNurse);

		btnCancelNurse = new JButton("Cancelar");
		btnCancelNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(true);
				panelRegistrationNurse.setVisible(false);
			}
		});
		btnCancelNurse.setBounds(398, 280, 171, 47);
		panelRegistrationNurse.add(btnCancelNurse);

		passwordFieldNurse = new JPasswordField();
		passwordFieldNurse.setBounds(472, 78, 108, 20);
		panelRegistrationNurse.add(passwordFieldNurse);

		JLabel lblAmbulatoryNurse = new JLabel("Ambulatorio:");
		lblAmbulatoryNurse.setBounds(366, 229, 80, 20);
		panelRegistrationNurse.add(lblAmbulatoryNurse);

		JComboBox<String> comboBoxAmbulatoryNurse = new JComboBox<String>();
		comboBoxAmbulatoryNurse.setBounds(461, 231, 108, 22);
		panelRegistrationNurse.add(comboBoxAmbulatoryNurse);
		try {
			ambulatories = (ArrayList<Ambulatory>) registrationManager.select();
		} catch (SQLException e3) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
		} catch (Exception e3) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
		}
		for (Ambulatory ambulatory : ambulatories) {
			comboBoxAmbulatoryNurse.addItem(ambulatory.getName());

		}

//		PANEL | SANITARIAN
		panelSanitarian = new JPanel();
		panelSanitarian.setBackground(new Color(16, 169, 121));
		panelSanitarian.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelSanitarian);
		panelSanitarian.setLayout(null);
		panelSanitarian.setVisible(false);

		JLabel lblCrossPanelSanitarian = new JLabel("");
		lblCrossPanelSanitarian
				.setIcon(new ImageIcon(ViewDaniel.class.getResource("/view/images/OsasunbideCross.jpg")));
		lblCrossPanelSanitarian.setBounds(476, 105, 140, 140);
		panelSanitarian.add(lblCrossPanelSanitarian);

		JLabel lblLettersPanelSanitarian = new JLabel("");
		lblLettersPanelSanitarian.setIcon(new ImageIcon(ViewDaniel.class.getResource("/view/images/Letters.jpg")));
		lblLettersPanelSanitarian.setBounds(0, 105, 140, 140);
		panelSanitarian.add(lblLettersPanelSanitarian);

		JLabel lblPatient = new JLabel("EMPLEADO");
		lblPatient.setForeground(new Color(255, 255, 255));
		lblPatient.setFont(new Font("Verdana", Font.BOLD, 18));
		lblPatient.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatient.setBounds(187, 168, 242, 14);
		panelSanitarian.add(lblPatient);

		JButton btnLogOutPanelSanitarian = new JButton("LOGOUT");
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
		btnBlockAmbulatoryPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBlockPatient.setVisible(true);
				panelSanitarian.setVisible(false);
			}
		});
		btnBlockAmbulatoryPatient.setBounds(249, 105, 118, 21);
		panelSanitarian.add(btnBlockAmbulatoryPatient);

		JButton btntodo2 = new JButton("TO DO 2");
		btntodo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btntodo2.setBounds(245, 183, 122, 21);
		panelSanitarian.add(btntodo2);

		JButton btntodo1 = new JButton("TO DO");
		btntodo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btntodo1.setBounds(249, 143, 118, 21);
		panelSanitarian.add(btntodo1);

		JButton btnModifySanitarianData = new JButton("Modificar contraseña");
		btnModifySanitarianData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModifySanitarian.setVisible(true);
				panelSanitarian.setVisible(false);
			}
		});
		btnModifySanitarianData.setBounds(249, 224, 118, 21);
		panelSanitarian.add(btnModifySanitarianData);

//		PANEL | PATIENT
		panelPatient = new JPanel();
		panelPatient.setBackground(new Color(16, 169, 121));
		panelPatient.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelPatient);
		panelPatient.setLayout(null);
		panelPatient.setVisible(false);

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
		lblPatientTitule.setFont(new Font("Verdana", Font.BOLD, 18));
		lblPatientTitule.setBounds(187, 168, 242, 14);
		panelPatient.add(lblPatientTitule);

		btnLogOutPanelPatient = new JButton("LOGOUT");
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
		btnSelectAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectAppointmentAmbulatoryType.setVisible(true);
				panelPatient.setVisible(false);
			}
		});
		btnSelectAppointment.setBounds(249, 105, 118, 21);
		panelPatient.add(btnSelectAppointment);

		JButton btnCancelAppointment = new JButton("Cancelar cita");
		btnCancelAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btnCancelAppointment.setBounds(245, 183, 122, 21);
		panelPatient.add(btnCancelAppointment);

		JButton btnShowAppointments = new JButton("Ver citas");
		btnShowAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btnShowAppointments.setBounds(249, 143, 118, 21);
		panelPatient.add(btnShowAppointments);

		JButton btnModifyPatientData = new JButton("Modificar datos");
		btnModifyPatientData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModifyPatient.setVisible(true);
				panelPatient.setVisible(false);
			}
		});
		btnModifyPatientData.setBounds(249, 224, 118, 21);
		panelPatient.add(btnModifyPatientData);

//		PANEL | MODIFICATION: PATIENT
		panelModifyPatient = new JPanel();
		panelModifyPatient.setBackground(new Color(0, 128, 192));
		panelModifyPatient.setBounds(0, 0, 616, 351);
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

				panelModifyPatient.setVisible(false);
				panelPatient.setVisible(true);
			}
		});
		btnModifyPatientOk.setBounds(200, 271, 89, 23);
		panelModifyPatient.add(btnModifyPatientOk);

		btnModifyPatientCancel = new JButton("Cancelar");
		btnModifyPatientCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModifyPatient.setVisible(false);
				panelPatient.setVisible(true);
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

				panelModifyPatient.setVisible(false);
				panelPatient.setVisible(true);
			}
		});
		btnModifyPatientUnsubscribe.setBounds(260, 206, 130, 23);
		panelModifyPatient.add(btnModifyPatientUnsubscribe);

//		PANEL | MODIFICATION: SANITARIAN
		panelModifySanitarian = new JPanel();
		panelModifySanitarian.setBackground(new Color(0, 128, 192));
		panelModifySanitarian.setBounds(0, 0, 616, 351);
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

				panelModifySanitarian.setVisible(false);
				panelSanitarian.setVisible(true);
			}
		});
		btnModifySanitarianOk.setBounds(200, 271, 89, 23);
		panelModifySanitarian.add(btnModifySanitarianOk);

		btnModifySanitarianCancel = new JButton("Cancelar");
		btnModifySanitarianCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModifySanitarian.setVisible(false);
				panelSanitarian.setVisible(true);
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

				panelModifySanitarian.setVisible(false);
				panelSanitarian.setVisible(true);
			}
		});
		btnModifySanitarianUnsubscribe.setBounds(260, 195, 130, 23);
		panelModifySanitarian.add(btnModifySanitarianUnsubscribe);

//		PANEL | SELECT APPOINTMENT : AMBULATORY & SANITARIAN TYPE
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
		ArrayList<String> ambulatoriesList = null;
		try {
			ambulatoriesList = appointmentSelectionManager.selectAmbulatoryNames();
			for (String ambulatory : ambulatoriesList) {
				cbAmbulatory.addItem(ambulatory);
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
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
					for (Date date : dates) {
						cbSelectAppointmentDate.addItem(date.toString());
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
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
				panelSelectAppointmentAmbulatoryType.setVisible(false);
				panelPatient.setVisible(true);
			}
		});
		btnSelectAppointmentAmbulatoryTypeCancel.setBounds(315, 273, 85, 21);
		panelSelectAppointmentAmbulatoryType.add(btnSelectAppointmentAmbulatoryTypeCancel);
		panelSelectAppointmentAmbulatoryType.setVisible(false);

//		PANEL | SELECT APPOINTMENT : DATE & TIMESLOT
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
		btnSelectAppointmentDateTimeSlotOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		btnSelectAppointmentDateTimeSlotOk.setBounds(207, 292, 89, 23);
		panelSelectAppointmentDateTimeSlot.add(btnSelectAppointmentDateTimeSlotOk);

		JButton btnSelectAppointmentDateTimeSlotCancel = new JButton("Cancelar");
		btnSelectAppointmentDateTimeSlotCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSelectAppointmentAmbulatoryType.setVisible(false);
				panelSelectAppointmentDateTimeSlot.setVisible(true);
			}
		});
		btnSelectAppointmentDateTimeSlotCancel.setBounds(323, 292, 89, 23);
		panelSelectAppointmentDateTimeSlot.add(btnSelectAppointmentDateTimeSlotCancel);

		JLabel lblSelectAppontmentSanitarian = new JLabel("Seleccione un profesional");
		lblSelectAppontmentSanitarian.setBounds(174, 99, 129, 14);
		panelSelectAppointmentDateTimeSlot.add(lblSelectAppontmentSanitarian);

		cbSelectAppointmentSanitarian = new JComboBox<String>();
		cbSelectAppointmentSanitarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
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
				DefaultTableModel model = (DefaultTableModel) tableSelectTimeSlot.getModel();
				model.setRowCount(0);
				try {
					timeSlotIds = appointmentSelectionManager.showAvailableTimeSlots(wantedSanitarian, dateString);
					for (Integer timeSlot : timeSlotIds) {
						model.addRow(new String[] { wantedSanitarian, wantedAmbulatory,
								cbSelectAppointmentDate.getSelectedItem().toString(),
								timeSlotManager.select(timeSlot).toString() });
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
				}

				// TODO
//				"Profesional", "Ambulatorio", "Fecha", "Hora"
			}
		});
		btnNewButton.setBounds(49, 91, 89, 23);
		panelSelectAppointmentDateTimeSlot.add(btnNewButton);

//		PANEL | BLOCK OR UNLOCK PATIENTS
		panelBlockPatient = new JPanel();
		panelBlockPatient.setBackground(new Color(0, 128, 192));
		panelBlockPatient.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelBlockPatient);
		panelBlockPatient.setLayout(null);
		panelBlockPatient.setVisible(false);

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
					JOptionPane.showMessageDialog(null, "Se ha producido un error con la Base de Datos.", "Error", 0);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error.", "Error", 0);
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
						blockUnlockPatientManager.blockPatient(patient);
						JOptionPane.showMessageDialog(null, "Paciente bloqueado/a", "Confirmación", 1);
					} else {
						blockUnlockPatientManager.unlockPatient(patient);
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
		btnBlockPatienCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBlockPatient.setVisible(false);
				panelSanitarian.setVisible(true);
			}
		});
		btnBlockPatienCancel.setBounds(332, 279, 85, 21);
		panelBlockPatient.add(btnBlockPatienCancel);
		panelBlockPatient.setVisible(false);

	}

}
