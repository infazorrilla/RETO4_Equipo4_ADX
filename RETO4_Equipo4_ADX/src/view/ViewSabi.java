package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import manager.AmbulatoryManager;
import manager.DoctorManager;
import manager.NurseManager;
import manager.PatientManager;
import model.pojos.Ambulatory;
import model.pojos.Doctor;
import model.pojos.Nurse;
import model.pojos.Patient;
import java.awt.Font;

public class ViewSabi {

	private PatientManager patientManager;
	private DoctorManager doctorManager;
	private NurseManager nurseManager;
	private AmbulatoryManager ambulatoryManager;

	public JFrame frame;
	private JPanel panelLogin;
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

	/**
	 * Create the application.
	 */
	public ViewSabi() {
//		inicializar gestores
		patientManager = new PatientManager();
		doctorManager = new DoctorManager();
		nurseManager = new NurseManager();
		ambulatoryManager = new AmbulatoryManager();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();

		frame.setBounds(100, 100, 632, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Login panel
		panelLogin = new JPanel();
		panelLogin.setBounds(0, 0, 616, 351);
		panelLogin.setBackground(new Color(0, 128, 192));
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

		JButton btnLoginOk = new JButton("Aceptar");
		btnLoginOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnLoginOk.setBounds(204, 245, 89, 23);
		panelLogin.add(btnLoginOk);

		JButton btnLoginCancel = new JButton("Cancelar");
		btnLoginCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnLoginCancel.setBounds(346, 245, 89, 23);
		panelLogin.add(btnLoginCancel);

		JButton btnLoginResgistrationPatient = new JButton("Registro Paciente");
		btnLoginResgistrationPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(false);
				panelRegistrationPatient.setVisible(true);
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationDoctor.setVisible(false);
				panelRegistrationNurse.setVisible(false);
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

		// Registration panel
		// XABI pon los nombres en inglés
		panelRegistrationPatient = new JPanel();
		panelRegistrationPatient.setBounds(0, 0, 626, 351);
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
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				patient.setBirthDate(date1);
				String password = new String(textFieldPasswordPatient.getPassword());
				patient.setPassword(password);
				patient.setPhoneNumber(textFieldPhoneNumberPatient.getText().trim());
				patient.setAddress(textFieldAddressPatient.getText());

				try {
					patientManager.insert(patient);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(btnAceptarRegistro, "Error Base De Datos", "Aviso", 2);
					e1.printStackTrace();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(btnAceptarRegistro, "Errorª", "Aviso", 2);
					e1.printStackTrace();
				}

				panelLogin.setVisible(true);
				panelRegistrationPatient.setVisible(false);
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationDoctor.setVisible(false);
				panelRegistrationNurse.setVisible(false);
			}

		});
		btnAceptarRegistro.setBounds(200, 271, 89, 23);
		panelRegistrationPatient.add(btnAceptarRegistro);

		JButton btnCancelarRegistro = new JButton("Cancelar");
		btnCancelarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(true);
				panelRegistrationPatient.setVisible(false);
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationDoctor.setVisible(false);
				panelRegistrationNurse.setVisible(false);
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
				panelRegistrationPatient.setVisible(false);
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationDoctor.setVisible(true);
				panelRegistrationNurse.setVisible(false);
			}
		});
		btnSelectDoctor.setBounds(110, 128, 149, 51);
		panelNurseOrDoctor.add(btnSelectDoctor);

		btnSelectNurse = new JButton("Enfermero");
		btnSelectNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(false);
				panelRegistrationPatient.setVisible(false);
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationDoctor.setVisible(false);
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
				panelRegistrationPatient.setVisible(false);
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationDoctor.setVisible(false);
				panelRegistrationNurse.setVisible(false);
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
				ambulatoryManager.select();
				doctor.setAmbulatory((String) comboBoxAmbulatoryDoctor.getSelectedItem());

				try {
					doctorManager.insert(doctor);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(btnAceptarRegistro, "Error Base De Datos", "Aviso", 2);
					e1.printStackTrace();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(btnAceptarRegistro, "Errorª", "Aviso", 2);
					e1.printStackTrace();
				}

				panelLogin.setVisible(true);
				panelRegistrationPatient.setVisible(false);
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationDoctor.setVisible(false);
				panelRegistrationNurse.setVisible(false);

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
			ambulatories = (ArrayList<Ambulatory>) ambulatoryManager.select();
		} catch (SQLException e3) {
			JOptionPane.showMessageDialog(btnAceptarRegistro, "Error Base De Datos", "Aviso", 2);
			e3.printStackTrace();
		} catch (Exception e3) {
			JOptionPane.showMessageDialog(btnAceptarRegistro, "Errorª", "Aviso", 2);
			e3.printStackTrace();
		}
		for(Ambulatory ambulatory: ambulatories) {
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
					// TODO Auto-generated catch block
					e2.printStackTrace();
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
					JOptionPane.showMessageDialog(btnAceptarRegistro, "Error Base De Datos", "Aviso", 2);
					e1.printStackTrace();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(btnAceptarRegistro, "Errorª", "Aviso", 2);
					e1.printStackTrace();
				}

				panelLogin.setVisible(true);
				panelRegistrationPatient.setVisible(false);
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationDoctor.setVisible(false);
				panelRegistrationNurse.setVisible(false);
			}
		});
		btnAceptNurse.setBounds(95, 280, 171, 47);
		panelRegistrationNurse.add(btnAceptNurse);

		btnCancelNurse = new JButton("Cancelar");
		btnCancelNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLogin.setVisible(true);
				panelRegistrationPatient.setVisible(false);
				panelNurseOrDoctor.setVisible(false);
				panelRegistrationDoctor.setVisible(false);
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
		comboBoxAmbulatoryNurse.addItem("1");
		comboBoxAmbulatoryNurse.addItem("2");
		comboBoxAmbulatoryNurse.addItem("3");
		comboBoxAmbulatoryNurse.addItem("4");
		comboBoxAmbulatoryNurse.addItem("5");

	}
}
