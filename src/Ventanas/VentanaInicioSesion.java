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
import java.awt.Button;
public class VentanaInicioSesion extends JFrame implements FocusListener {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
    private JPanel inicioSesion;
    private JPanel Inicio;
    private JPanel Interno;
    private JLabel lblUsuario;
    private JLabel lblContraseña;
    private JPanel Botones;
    private JButton btnLogin;
    private JButton btnClear;
    private JPanel TextoInicial;
    private JLabel lblDatos;
    private SocketManager sm ;
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
	public VentanaInicioSesion() throws IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		 lblContraseña = new JLabel("Contrase\u00F1a:");
		lblContraseña.setBounds(21, 56, 74, 14);
		Interno.add(lblContraseña);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(195, 8, 86, 20);
		Interno.add(textUsuario);
		textUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
	
		passwordField.setBounds(195, 53, 86, 20);
		Interno.add(passwordField);
	    Botones = new JPanel();
		inicioSesion.add(Botones, BorderLayout.SOUTH);
		GridBagLayout gbl_Botones = new GridBagLayout();
		gbl_Botones.columnWidths = new int[]{220, 0, 220, 0};
		gbl_Botones.rowHeights = new int[]{23, 0};
		gbl_Botones.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_Botones.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		Botones.setLayout(gbl_Botones);
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
	   
		 if(respuesta.equals("201 OK Bienvenido al sistema.")){
			 JOptionPane.showMessageDialog(null,respuesta,"Correcto",JOptionPane.ALLBITS);
			 //abrir ventana de funciones
		 }
		 else{
			 JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR); 
		 }
			}
		});
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
	   
		if(respuesta.equals("200 OK Bienvenido "+textUsuario.getText())){
			 JOptionPane.showMessageDialog(null,respuesta,"Correcto",JOptionPane.ALLBITS);
			 btnClear.setEnabled(true);
		}
		else{
			 JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR);
		}
			}
		});
		btnLogin.setEnabled(false);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.BOTH;
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 0;
		Botones.add(btnLogin, gbc_btnLogin);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}
		});
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalir.gridx = 1;
		gbc_btnSalir.gridy = 0;
		Botones.add(btnSalir, gbc_btnSalir);
		btnClear.setEnabled(false);
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.fill = GridBagConstraints.BOTH;
		gbc_btnClear.gridx = 2;
		gbc_btnClear.gridy = 0;
		Botones.add(btnClear, gbc_btnClear);
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
	    else if(!textUsuario.getText().equals("")){
	    	btnLogin.setEnabled(true);
	    }
	    else if(passwordField.getText().equals("")){
	       btnClear.setEnabled(false);
	    
	    }
	    
	}
}
