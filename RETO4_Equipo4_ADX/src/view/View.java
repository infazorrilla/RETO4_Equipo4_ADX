package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class View {
	
	public JFrame frame;
	private JPanel panelLogin;
	private JPanel panelRegistration;
	private JPanel panelUpdateUser;
	
	/**
	 * Create the application.
	 */
	public View() {
//		inicializar gestores

		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();

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
		panelRegistration.setBounds(0, 0, 616, 351);
		frame.getContentPane().add(panelRegistration);
		panelRegistration.setLayout(null);
		panelRegistration.setVisible(false);

		JLabel lblRegistroUsuario = new JLabel("Registro de usuario");
		lblRegistroUsuario.setBounds(227, 34, 165, 14);
		panelRegistration.add(lblRegistroUsuario);

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
		textFieldDNI.setBounds(233, 90, 183, 20);
		panelRegistration.add(textFieldDNI);
		textFieldDNI.setColumns(10);

		JTextField textFieldNombre = new JTextField();
		textFieldNombre.setBounds(233, 115, 183, 20);
		panelRegistration.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JTextField textFieldApellido = new JTextField();
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
		panelUpdateUser = new JPanel();
		panelUpdateUser.setBackground(new Color(0, 128, 192));
		panelUpdateUser.setBounds(479, 0, 137, 351);
		frame.getContentPane().add(panelUpdateUser);
		panelUpdateUser.setLayout(null);
		panelUpdateUser.setVisible(false);

	
		
	}
	
	
	
	
	
	

}
