package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import manager.PatientManager;
import model.pojos.Patient;

public class ViewSabi {
	
	private PatientManager patientManager;

	public JFrame frame;
	private JPanel panelLogin;
	private JPanel panelRegistrationPatient;
	private JTextField textFieldBirthDatePatient;
	private JTextField textFieldPhoneNumberPatient;
	private JTextField textFieldAddressPatient;

	/**
	 * Create the application.
	 */
	public ViewSabi() {
//		inicializar gestores
		patientManager = new PatientManager();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();

		frame.setBounds(100, 100, 632, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Login panel
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
			}
		});
		btnLoginResgistrationPatient.setBounds(442, 10, 154, 37);
		panelLogin.add(btnLoginResgistrationPatient);

		JButton btnRegistroSanitarian = new JButton("Registro Sanitario");
		btnRegistroSanitarian.setBounds(442, 62, 154, 37);
		panelLogin.add(btnRegistroSanitarian);

		// Registration panel
		// XABI pon los nombres en inglés
		panelRegistrationPatient = new JPanel();
		panelRegistrationPatient.setBackground(new Color(0, 128, 192));
		panelRegistrationPatient.setBounds(0, 0, 626, 351);
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
		textFieldDNIPatient.setBounds(91, 90, 119, 20);
		panelRegistrationPatient.add(textFieldDNIPatient);
		textFieldDNIPatient.setColumns(10);

		JTextField textFieldNamePatient = new JTextField();
		textFieldNamePatient.setBounds(91, 115, 119, 20);
		panelRegistrationPatient.add(textFieldNamePatient);
		textFieldNamePatient.setColumns(10);

		JTextField textFieldSurnamePatient = new JTextField();
		textFieldSurnamePatient.setBounds(91, 143, 119, 20);
		panelRegistrationPatient.add(textFieldSurnamePatient);
		textFieldSurnamePatient.setColumns(10);

		JPasswordField textFieldPasswordPatient = new JPasswordField();
		textFieldPasswordPatient.setBounds(91, 190, 119, 20);
		panelRegistrationPatient.add(textFieldPasswordPatient);

		JComboBox<String> comboBoxGenderPatient = new JComboBox<String>();
		comboBoxGenderPatient.setBounds(91, 168, 119, 18);
		panelRegistrationPatient.add(comboBoxGenderPatient);
		comboBoxGenderPatient.addItem("Hombre");
		comboBoxGenderPatient.addItem("Mujer");

		JButton btnAceptarRegistro = new JButton("Aceptar");
		btnAceptarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient patient = new Patient();
				patient.setDni(textFieldDNIPatient.getText());
				patient.setName(textFieldNamePatient.getText());
				patient.setSurname(textFieldSurnamePatient.getText());
				patient.setGender(comboBoxGenderPatient.getToolTipText());
			
				String password = new String(textFieldPasswordPatient.getPassword());
				patient.setPassword(password);
				patient.setPhoneNumber(textFieldPhoneNumberPatient.getText());
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
			}
			
			
		});
		btnAceptarRegistro.setBounds(200, 271, 89, 23);
		panelRegistrationPatient.add(btnAceptarRegistro);

		JButton btnCancelarRegistro = new JButton("Cancelar");
		btnCancelarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

	}
}
