package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import util.SocketManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class VentanaInicioSesion extends JFrame implements FocusListener {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
    private JPanel inicioSesion;
    private JPanel Inicio;
    private JPanel Interno;
    private JLabel lblUsuario;
    private JLabel lblContrase�a;
    private JPanel Botones;
    private JButton btnLogin;
    private JButton btnClear;
    private JPanel TextoInicial;
    private JLabel lblDatos;
    private boolean UsuarioCorrecto;
    private SocketManager sm ;
    private String respuesta;
	
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
	public VentanaInicioSesion() throws IOException {
		
		setResizable(false);
		setUndecorated(true);
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
		
		sm = new SocketManager("127.0.0.1", 3000);
		
		Interno = new JPanel();
		Inicio.add(Interno);
		Interno.setLayout(null);
		
		 lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(23, 11, 87, 14);
		Interno.add(lblUsuario);
		
		 lblContrase�a = new JLabel("Contrase\u00F1a:");
		lblContrase�a.setBounds(21, 56, 74, 14);
		Interno.add(lblContrase�a);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(195, 8, 86, 20);
		Interno.add(textUsuario);
		textUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
	
		passwordField.setBounds(195, 53, 86, 20);
		Interno.add(passwordField);
	    Botones = new JPanel();
		inicioSesion.add(Botones, BorderLayout.SOUTH);
		Botones.setLayout(new GridLayout(0, 2, 0, 0));
	    btnLogin = new JButton("Login");
	    btnLogin.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    try {
			sm.Escribir("USER"+textUsuario.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			respuesta=sm.Leer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JOptionPane.showMessageDialog(null,respuesta);
	    if(respuesta.equals("200 OK Bienvenido "+textUsuario.getText())){
	    	UsuarioCorrecto=true;
	    }
	    	}
	    });
		btnLogin.setEnabled(false);
		Botones.add(btnLogin);
	    btnClear = new JButton("Clear");
	    btnClear.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	try {
				sm.Escribir(passwordField.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     try {
			respuesta=sm.Leer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     JOptionPane.showMessageDialog(null,respuesta);
	     if(respuesta.equals("201 OK Bienvenido al sistema.")){
	    	 //abrir ventana de funciones
	     }
	    	}
	    });
		btnClear.setEnabled(false);
		Botones.add(btnClear);
		TextoInicial = new JPanel();
		inicioSesion.add(TextoInicial, BorderLayout.NORTH);
		lblDatos = new JLabel("Introduzca los datos indicados:");
		GridBagConstraints gbc_lblDatos = new GridBagConstraints();
		gbc_lblDatos.insets = new Insets(0, 0, 0, 5);
		gbc_lblDatos.fill = GridBagConstraints.BOTH;
		gbc_lblDatos.gridx = 0;
		gbc_lblDatos.gridy = 0;
		TextoInicial.add(lblDatos, gbc_lblDatos);
		textUsuario.addFocusListener( (FocusListener) this);
		passwordField.addFocusListener((FocusListener) this);
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
	
	    if(textUsuario.getText().equals("")){
	    	btnLogin.setEnabled(false);
	    }
	    else if(!textUsuario.getText().equals("")||!UsuarioCorrecto){
	    	btnLogin.setEnabled(true);
	    }
	    else if(!textUsuario.getText().equals("")||UsuarioCorrecto||!passwordField.getText().equals("")){
	    btnClear.setEnabled(true);
	    
	    }
	    
	}
	
}
