package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JPasswordField;
import java.awt.TextArea;
import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Container;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;

import javax.swing.UIManager;

import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;


public class VentanaUsuario extends JFrame implements FocusListener {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
    private JPanel inicioSesion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuario frame = new VentanaUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaUsuario() {
		
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
		
		JPanel Inicio = new JPanel();
		inicioSesion.add(Inicio, BorderLayout.CENTER);
		Inicio.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel Interno = new JPanel();
		Inicio.add(Interno);
		Interno.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(23, 11, 87, 14);
		Interno.add(lblUsuario);
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a:");
		lblContraseña.setBounds(21, 56, 74, 14);
		Interno.add(lblContraseña);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(195, 8, 86, 20);
		Interno.add(textUsuario);
		textUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
	
		passwordField.setBounds(195, 53, 86, 20);
		Interno.add(passwordField);
		JPanel Botones = new JPanel();
		inicioSesion.add(Botones, BorderLayout.SOUTH);
		Botones.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setEnabled(false);
		Botones.add(btnLogin);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		Botones.add(btnClear);
		
		JPanel TextoInicial = new JPanel();
		inicioSesion.add(TextoInicial, BorderLayout.NORTH);
		GridBagLayout gbl_TextoInicial = new GridBagLayout();
		gbl_TextoInicial.columnWidths = new int[]{212, 212, 0};
		gbl_TextoInicial.rowHeights = new int[]{14, 0};
		gbl_TextoInicial.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_TextoInicial.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		TextoInicial.setLayout(gbl_TextoInicial);
		
		JLabel lblDatos = new JLabel("Introduzca los datos indicados");
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
		
	}
	
}
