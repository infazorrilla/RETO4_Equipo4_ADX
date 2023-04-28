package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import manager.PatientManager;

public class ViewAmaia {
	
	private PatientManager patientManager;
	private String userDNI;
	
	private JPanel panelLogin;
	private JPanel panelRegistration;
	private JPanel panelModifyUser;
	private JTextField tfModifyUserAdress;
	private JTextField tfModifyUserPhoneNumber;
	
	/**
	 * Create the application.
	 */
	public ViewAmaia() {
//		inicializar gestores
		patientManager = new PatientManager();
		initialize();
	}
	
	private void initialize() {
		JFrame frame = new JFrame();

		frame.setBounds(100, 100, 632, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Login panel
		panelLogin = new JPanel();
		panelLogin.setBackground(new Color(0, 128, 192));
		panelLogin.setBounds(583, 0, 33, 351);
		frame.getContentPane().add(panelLogin);
		panelLogin.setLayout(null);
		panelLogin.setVisible(false);

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
				userDNI = tfLoginUser.getText();
				
				
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

		JButton btnLoginResgistration = new JButton("Registro");
		btnLoginResgistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnLoginResgistration.setBounds(442, 10, 154, 37);
		panelLogin.add(btnLoginResgistration);
		
		//Registration panel
		//XABI pon los nombres en inglés
		panelRegistration = new JPanel();
		panelRegistration.setBackground(new Color(0, 128, 192));
		panelRegistration.setBounds(538, 0, 78, 351);
		frame.getContentPane().add(panelRegistration);
		panelRegistration.setLayout(null);
		panelRegistration.setVisible(false);

		JLabel lbl = new JLabel("Registro de usuario");
		lbl.setBounds(227, 34, 165, 14);
		panelRegistration.add(lbl);

		JLabel lblDniRegistro = new JLabel("DNI:");
		lblDniRegistro.setBounds(106, 93, 119, 14);
		panelRegistration.add(lblDniRegistro);

		JLabel lblNombreRegistro = new JLabel("Nombre:");
		lblNombreRegistro.setBounds(106, 118, 127, 14);
		panelRegistration.add(lblNombreRegistro);

		JLabel lblApellidoRegistro = new JLabel("Apellido:");
		lblApellidoRegistro.setBounds(104, 143, 119, 14);
		panelRegistration.add(lblApellidoRegistro);

		JLabel lblSexoRegistro = new JLabel("Sexo:");
		lblSexoRegistro.setBounds(106, 168, 127, 14);
		panelRegistration.add(lblSexoRegistro);

		JLabel lblContrasenaRegistro = new JLabel("Contraseña:");
		lblContrasenaRegistro.setBounds(106, 193, 119, 14);
		panelRegistration.add(lblContrasenaRegistro);

		JTextField textFieldDNI = new JTextField();
		textFieldDNI.setEditable(false);
		textFieldDNI.setBounds(233, 90, 183, 20);
		panelRegistration.add(textFieldDNI);
		textFieldDNI.setColumns(10);

		JTextField textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setBounds(233, 115, 183, 20);
		panelRegistration.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JTextField textFieldApellido = new JTextField();
		textFieldApellido.setEditable(false);
		textFieldApellido.setBounds(233, 140, 183, 20);
		panelRegistration.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		JPasswordField textFieldPasswd = new JPasswordField();
		textFieldPasswd.setBounds(233, 190, 183, 20);
		panelRegistration.add(textFieldPasswd);

		JComboBox<String> comboBoxSexo = new JComboBox<String>();
		comboBoxSexo.setBounds(233, 164, 183, 22);
		panelRegistration.add(comboBoxSexo);
		comboBoxSexo.addItem("Hombre");
		comboBoxSexo.addItem("Mujer");
		comboBoxSexo.addItem("Otro");

		JButton btnAceptarRegistro = new JButton("Aceptar");
		btnAceptarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnAceptarRegistro.setBounds(200, 271, 89, 23);
		panelRegistration.add(btnAceptarRegistro);

		JButton btnCancelarRegistro = new JButton("Cancelar");
		btnCancelarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCancelarRegistro.setBounds(350, 271, 89, 23);
		panelRegistration.add(btnCancelarRegistro);
		
		//Update users' information panel
		panelModifyUser = new JPanel();
		panelModifyUser.setBackground(new Color(0, 128, 192));
		panelModifyUser.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelModifyUser);
		panelModifyUser.setLayout(null);
		panelModifyUser.setVisible(true);
		
		JLabel lblModifyUser = new JLabel("Modificación de datos");
		lblModifyUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifyUser.setBounds(225, 32, 165, 14);
		panelModifyUser.add(lblModifyUser);

		JLabel lblModifyUserDNI = new JLabel("DNI:");
		lblModifyUserDNI.setSize(94, 23);
		lblModifyUserDNI.setLocation(106, 102);
		lblModifyUserDNI.setBounds(106, 93, 119, 14);
		panelModifyUser.add(lblModifyUserDNI);

		JLabel lblModifyUserAddress = new JLabel("Dirección:");
		lblModifyUserAddress.setBounds(106, 118, 127, 14);
		panelModifyUser.add(lblModifyUserAddress);

		JLabel lblModifyUserPhoneNumber = new JLabel("Teléfono:");
		lblModifyUserPhoneNumber.setBounds(104, 143, 119, 14);
		panelModifyUser.add(lblModifyUserPhoneNumber);

		JLabel lblModifyUserPassword = new JLabel("Contraseña:");
		lblModifyUserPassword.setBounds(106, 168, 119, 14);
		panelModifyUser.add(lblModifyUserPassword);
		
		JTextField tfModifyUserDNI = new JTextField();
		tfModifyUserDNI.setEditable(false);
		tfModifyUserDNI.setBounds(225, 90, 183, 20);
		panelModifyUser.add(tfModifyUserDNI);
		tfModifyUserDNI.setColumns(10);


		JPasswordField tfModifyUserPassword = new JPasswordField();
		tfModifyUserPassword.setBounds(225, 165, 183, 20);
		panelModifyUser.add(tfModifyUserPassword);
		

		JButton btnModifyUserOk = new JButton("Guardar");
		btnModifyUserOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnModifyUserOk.setBounds(200, 271, 89, 23);
		panelModifyUser.add(btnModifyUserOk);

		JButton btnModifyUserCancel= new JButton("Cancelar");
		btnModifyUserCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnModifyUserCancel.setBounds(350, 271, 89, 23);
		panelModifyUser.add(btnModifyUserCancel);
		
		tfModifyUserAdress = new JTextField();
		tfModifyUserAdress.setColumns(10);
		tfModifyUserAdress.setBounds(225, 115, 183, 20);
		panelModifyUser.add(tfModifyUserAdress);
		
		tfModifyUserPhoneNumber = new JTextField();
		tfModifyUserPhoneNumber.setColumns(10);
		tfModifyUserPhoneNumber.setBounds(225, 140, 183, 20);
		panelModifyUser.add(tfModifyUserPhoneNumber);
		
		JButton btnModifyUserUnsubscribe = new JButton("Eliminar cuenta");
		btnModifyUserUnsubscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					patientManager.delete(userDNI);
				} catch (SQLException sqle) {
					JOptionPane.showMessageDialog(btnAceptarRegistro, sqle.getMessage(), "Aviso", 0);
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(btnAceptarRegistro, e1.getMessage(), "Aviso", 0);
				}
			}
		});
		btnModifyUserUnsubscribe.setBounds(260, 206, 130, 23);
		panelModifyUser.add(btnModifyUserUnsubscribe);

	
		
	}
}
