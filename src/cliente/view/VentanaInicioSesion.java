package cliente.view;

import util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaInicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JTextField textUsuario;
	private final JPasswordField passwordField;
	private String respuesta;

	public VentanaInicioSesion() {
		
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel inicioSesion = new JPanel();
		contentPane.add(inicioSesion, BorderLayout.CENTER);
		inicioSesion.setLayout(new BorderLayout(0, 0));

		JPanel inicio = new JPanel();
		inicioSesion.add(inicio, BorderLayout.CENTER);
		inicio.setLayout(new GridLayout(1, 0, 0, 0));


		JPanel interno = new JPanel();
		inicio.add(interno);
		interno.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(23, 11, 87, 14);
		interno.add(lblUsuario);

		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setBounds(23, 56, 74, 14);
		interno.add(lblContrasena);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(156, 8, 195, 20);
		interno.add(textUsuario);
		textUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
	
		passwordField.setBounds(156, 53, 195, 20);
		interno.add(passwordField);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Recordar");
		chckbxNewCheckBox.setBounds(154, 80, 97, 23);
		interno.add(chckbxNewCheckBox);
		JPanel botones = new JPanel();
		inicioSesion.add(botones, BorderLayout.SOUTH);
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mandarUsuarioPass();
			}
		});

		botones.setLayout(new GridLayout(0, 2, 0, 0));
		btnContinuar.setEnabled(true);
		botones.add(btnContinuar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desconexion();
			}
		});
		botones.add(btnSalir);
		JPanel textoInicial = new JPanel();
		inicioSesion.add(textoInicial, BorderLayout.NORTH);
		JLabel lblDatos = new JLabel("Introduzca los datos indicados:");
		GridBagConstraints gbc_lblDatos = new GridBagConstraints();
		gbc_lblDatos.insets = new Insets(0, 0, 0, 5);
		gbc_lblDatos.fill = GridBagConstraints.BOTH;
		gbc_lblDatos.gridx = 0;
		gbc_lblDatos.gridy = 0;
		textoInicial.add(lblDatos, gbc_lblDatos);

	}

	void mandarUsuarioPass()
	{
		try {
			Util.claseSocketCliente.Escribir("USER " + textUsuario.getText()+"\n");
			respuesta = Util.claseSocketCliente.Leer();
			if(respuesta.contains("200"))
			{
				Util.claseSocketCliente.Escribir("PASS " + new String(passwordField.getPassword())+"\n");
				respuesta = Util.claseSocketCliente.Leer();
				if(respuesta.contains("201"))
				{
					VentanaTabla nueva = new VentanaTabla();
					nueva.setVisible(true);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE); 
			}
			else
				JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE); 
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error, el servidor no esta encendido", "Error de conexion", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
	}

	void desconexion()
	{
		try {
			Util.claseSocketCliente.Escribir("SALIR\n");
			respuesta = Util.claseSocketCliente.Leer();
			Util.claseSocketCliente.CerrarStreams();
			Util.claseSocketCliente.CerrarSocket();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE); 
		}

		dispose();
	}
}
