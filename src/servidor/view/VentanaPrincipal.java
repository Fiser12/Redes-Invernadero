package servidor.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.CardLayout;

import servidor.serverController.*;
import servidor.serverModel.InteraccionDB;
import servidor.serverModel.ModelClass.Placa;
import servidor.serverModel.ModelClass.Sensor;
import servidor.serverModel.ModelClass.Usuario;
import util.SocketManager;
import util.Util;
import util.excepciones.RepetElement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperior;
	private JLabel lblS;
	private JPanel panelcentral;
	private JPanel botoneraInicial;
	private JButton btnAUsuarios;
	private JButton btnPlacas;
	private JButton btnASensores;
	private JButton btnAVariables;
	private JPanel panelApagar;
	private JButton btnConexiones;
	private JButton btnAServidor;
	private PanelPlacas pPlacas;
	private PanelUsuarios pUsuarios;
	private PanelSensores pSensores;
	private PanelVariables pVariables;
	private static ServerSocket wellcomeSocket;
	public static LinkedList<SocketManager> listaSockets;
	public static LinkedList<Request> listaHilos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		listaSockets = new LinkedList<SocketManager>();
		listaHilos = new LinkedList<Request>();
		int port = 3000; //(new Integer(argv[0])).intValue();
		wellcomeSocket = new ServerSocket(port);
		while (true)
		{
			if(getUsuariosConectados()<Util.usuariosMaximos)
			{
				SocketManager sockManager = new SocketManager(wellcomeSocket.accept());
				Request request = new Request(sockManager);
				Thread thre = new Thread(request);
				thre.start();
				if(thre.isAlive())
				{
					listaSockets.add(sockManager);
					listaHilos.add(request);
					mostrarUsuario(getUsuariosConectados());
				}
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() throws Exception{
		setResizable(false);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		/*
		 * Inicializaccion de modelos de tabla
		 */

		panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblS = new JLabel("Seleccionar una opcion:");
		lblS.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSuperior.add(lblS);

		panelcentral = new JPanel();
		contentPane.add(panelcentral, BorderLayout.CENTER);
		panelcentral.setLayout(new CardLayout(0, 0));
		/*
		 * Botonera inicial
		 */
		botoneraInicial = new JPanel();
		panelcentral.add(botoneraInicial, "name_117604164092399");
		botoneraInicial.setLayout(new GridLayout(2, 2, 0, 0));

		btnASensores = 
				new JButton("Administrar Sensores");
		btnASensores.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnASensores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administrarSensores();
				botoneraInicial.setVisible(false);
			}
		});

		btnAUsuarios = new JButton("Administrar Usuarios");
		btnAUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		botoneraInicial.add(btnAUsuarios);
		btnAUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administrarUsuarios();
				botoneraInicial.setVisible(false);
			}
		});

		btnPlacas = new JButton("Administrar Placas");
		btnPlacas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPlacas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administrarPlacas();
				botoneraInicial.setVisible(false);
			}
		});
		botoneraInicial.add(btnPlacas);

		btnAVariables = new JButton("Administrar Variables");
		btnAVariables.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAVariables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				administrarVariables();
				botoneraInicial.setVisible(false);
			}
		});
		botoneraInicial.add(btnAVariables);
		botoneraInicial.add(btnASensores);

		panelApagar = new JPanel();
		contentPane.add(panelApagar, BorderLayout.SOUTH);

		btnConexiones = new JButton("Administrar conexiones");
		btnConexiones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelApagar.add(btnConexiones);
		btnAServidor = new JButton("Apagar servidor");
		btnAServidor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desconectar();
			}
		});
		panelApagar.add(btnAServidor);
		pPlacas = new PanelPlacas();
		pUsuarios = new PanelUsuarios();
		pSensores = new PanelSensores();
		pVariables = new PanelVariables();
		pPlacas.setVisible(false);
		pUsuarios.setVisible(false);
		pSensores.setVisible(false);
		pVariables.setVisible(false);
		panelcentral.add(pVariables);
		panelcentral.add(pUsuarios);
		panelcentral.add(pSensores);
		panelcentral.add(pPlacas);

	}
	public static void mostrarUsuario(int usuario)
	{
		String usuarioRegistrado = "DESCONECTADO";
		try{
			usuarioRegistrado = listaHilos.get(usuario-1).getUsuario();
		}catch(IndexOutOfBoundsException E){
			usuarioRegistrado = "DESCONECTADO";
		}

		System.out.println("USERNAME: " + usuarioRegistrado);
		System.out.println("NºUSUARIO: " + (usuario));
		System.out.println("DIRECCIÓN: " + listaSockets.get(usuario-1).getMySocket().getInetAddress().getHostAddress());
		System.out.println("NOMBRE DEL HOST: " + listaSockets.get(usuario-1).getMySocket().getInetAddress().getHostName());
		System.out.println("PUERTO: "+ listaSockets.get(usuario-1).getMySocket().getPort());
		System.out.println("NOMBRE CANONICO: " + listaSockets.get(usuario-1).getMySocket().getInetAddress().getCanonicalHostName());
	}
	public static void desconectarUsuario(int usuario){
		try
		{
			listaSockets.get(usuario).CerrarStreams();
			listaSockets.get(usuario).CerrarSocket();
			listaSockets.remove(usuario);
			listaHilos.remove(usuario);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int getUsuariosConectados()
	{
		return listaSockets.size();
	}
	public static void desconectar(){
		System.exit(0);
	}
	public void administrarVariables(){
		pVariables.setVisible(true);
		botoneraInicial.setVisible(false);

	}
	public void administrarSensores(){
		pSensores.setVisible(true);
		botoneraInicial.setVisible(false);
	}
	public void administrarUsuarios(){
		pUsuarios.setVisible(true);
		botoneraInicial.setVisible(false);

	}
	public void administrarPlacas(){
		pPlacas.setVisible(true);
		botoneraInicial.setVisible(false);
	}
}
