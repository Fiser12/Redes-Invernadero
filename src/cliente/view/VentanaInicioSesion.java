package cliente.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import util.Util;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JCheckBox;

public class VentanaInicioSesion extends JFrame implements FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
    private JPanel inicioSesion;
    private JPanel Inicio;
    private JPanel Interno;
    private JLabel lblUsuario;
    private JLabel lblContrasena;
    private JPanel Botones;
    private JButton btnContinuar;
    private JPanel TextoInicial;
    private JLabel lblDatos;
    private String respuesta;
    private JButton btnSalir;
	
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicioSesion frame = new VentanaInicioSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public VentanaInicioSesion() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		 inicioSesion = new JPanel();
		contentPane.add(inicioSesion, BorderLayout.CENTER);
		inicioSesion.setLayout(new BorderLayout(0, 0));
		
		 Inicio = new JPanel();
		inicioSesion.add(Inicio, BorderLayout.CENTER);
		Inicio.setLayout(new GridLayout(1, 0, 0, 0));
		
	
		Interno = new JPanel();
		Inicio.add(Interno);
		Interno.setLayout(null);
		
		 lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(23, 11, 87, 14);
		Interno.add(lblUsuario);
		
		 lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setBounds(23, 56, 74, 14);
		Interno.add(lblContrasena);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(156, 8, 195, 20);
		Interno.add(textUsuario);
		textUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
	
		passwordField.setBounds(156, 53, 195, 20);
		Interno.add(passwordField);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Recordar");
		chckbxNewCheckBox.setBounds(154, 80, 97, 23);
		Interno.add(chckbxNewCheckBox);
		Botones = new JPanel();
		inicioSesion.add(Botones, BorderLayout.SOUTH);
		btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mandarUsuarioPass();
			}
		});
		
		Botones.setLayout(new GridLayout(0, 2, 0, 0));
		btnContinuar.setEnabled(true);
		Botones.add(btnContinuar);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desconexion();
			}
		});
		Botones.add(btnSalir);
		TextoInicial = new JPanel();
		inicioSesion.add(TextoInicial, BorderLayout.NORTH);
		lblDatos = new JLabel("Introduzca los datos indicados:");
		GridBagConstraints gbc_lblDatos = new GridBagConstraints();
		gbc_lblDatos.insets = new Insets(0, 0, 0, 5);
		gbc_lblDatos.fill = GridBagConstraints.BOTH;
		gbc_lblDatos.gridx = 0;
		gbc_lblDatos.gridy = 0;
		TextoInicial.add(lblDatos, gbc_lblDatos);
		textUsuario.addFocusListener( this);
		passwordField.addFocusListener( this);
		
	}
	public void mandarUsuarioPass()
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
			JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE); 
		}
	}
	public void desconexion()
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
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
}
