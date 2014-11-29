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
	private JButton btnAVPlacas;
	private JPanel panelApagar;
	private JButton btnConexiones;
	private JButton btnAServidor;

	private static ServerSocket wellcomeSocket;
	public static LinkedList<SocketManager> listaSockets;
	public static LinkedList<Request> listaHilos;
	private JPanel panelCPlaca;
	private JPanel panelAtras8;
	private JButton btnAtras8;
	private JButton btnMenuInicio4;
	private JPanel panelCreplaca;
	private JButton btnCreaccionPlaca;
	private JPanel panelCreaccionPla;
	private JLabel lblPNombre;
	private JTextField textID;
	private JLabel lbNombrep;
	private JTextField textNombre;
	private JPanel panelCSensor;
	private JPanel panelCrSensor;
	private JPanel panelAtras10;
	private JButton btnAtras10;
	private JButton btnInicio;
	private JPanel panelCreSensor;
	private JButton btnCrSensor;
	private JTextField textFSensor;
	private JTextField textNSensor;
	private JTextField textIDSensor;
	private JComboBox comboBoxVariable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaServidor frame = new ventanaServidor();
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
		/*
		 * Crear Placa
		 * 
		 */
		panelCPlaca = new JPanel();
		panelcentral.add(panelCPlaca, "name_24645888711459");
		panelCPlaca.setLayout(new BorderLayout(0, 0));

		panelAtras8 = new JPanel();
		panelCPlaca.add(panelAtras8, BorderLayout.SOUTH);
		panelAtras8.setLayout(new GridLayout(0, 3, 0, 0));

		btnAtras8 = new JButton("Atras");
		btnAtras8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCPlaca.setVisible(false);
				lblS.setText("Seleccionar una Opcion:");
			}
		});
		panelAtras8.add(btnAtras8);

		btnMenuInicio4 = new JButton("Menu Inicio");
		btnMenuInicio4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMenuInicio4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCPlaca.setVisible(false);
				botoneraInicial.setVisible(true);
				lblS.setText("Seleccionar una Opcion:");
			}
		});

		btnCreaccionPlaca = new JButton("Creaccion Placa");
		btnCreaccionPlaca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelAtras8.add(btnCreaccionPlaca);
		btnCreaccionPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//textID
				//textNombre
				// rdbtnEstadoinicial
			}
		});
		panelAtras8.add(btnMenuInicio4);

		panelCreplaca = new JPanel();
		panelCPlaca.add(panelCreplaca, BorderLayout.CENTER);
		panelCreplaca.setLayout(new BorderLayout(0, 0));

		panelCreaccionPla = new JPanel();
		panelCreplaca.add(panelCreaccionPla, BorderLayout.CENTER);
		panelCreaccionPla.setLayout(null);

		lblPNombre = new JLabel("ID:");
		lblPNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPNombre.setBounds(88, 33, 51, 14);
		panelCreaccionPla.add(lblPNombre);

		textID = new JTextField();
		textID.setBounds(192, 30, 158, 20);
		panelCreaccionPla.add(textID);
		textID.setColumns(10);

		lbNombrep = new JLabel("Nombre:");
		lbNombrep.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbNombrep.setBounds(88, 95, 94, 14);
		panelCreaccionPla.add(lbNombrep);

		textNombre = new JTextField();
		textNombre.setBounds(202, 94, 158, 20);
		panelCreaccionPla.add(textNombre);
		textNombre.setColumns(10);

		JRadioButton rdbtnEstadoinicial = new JRadioButton("Encendido");
		rdbtnEstadoinicial.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnEstadoinicial.setBounds(165, 141, 109, 23);
		panelCreaccionPla.add(rdbtnEstadoinicial);
		/*
		 * Crear sensor
		 * Sensor se relacciona con una variable y  placa
		 */
		panelCSensor = new JPanel();
		panelcentral.add(panelCSensor, "name_27780962298831");
		panelCSensor.setLayout(new BorderLayout(0, 0));

		panelCrSensor = new JPanel();
		panelCSensor.add(panelCrSensor);
		panelCrSensor.setLayout(new BorderLayout(0, 0));

		panelCreSensor = new JPanel();
		panelCrSensor.add(panelCreSensor, BorderLayout.CENTER);
		panelCreSensor.setLayout(null);

		JLabel lblSensorId = new JLabel("Sensor ID:");
		lblSensorId.setBounds(10, 27, 73, 14);
		panelCreSensor.add(lblSensorId);

		JLabel lblNSensor = new JLabel("Sensor Nombre:");
		lblNSensor.setBounds(10, 64, 94, 14);
		panelCreSensor.add(lblNSensor);

		JLabel lblFuncionPrincipal = new JLabel("Funcion Principal:");
		lblFuncionPrincipal.setBounds(10, 89, 114, 14);
		panelCreSensor.add(lblFuncionPrincipal);

		JRadioButton rdbtnEISensor = new JRadioButton("Estodo inicial activado");
		rdbtnEISensor.setBounds(318, 163, 157, 23);
		panelCreSensor.add(rdbtnEISensor);

		textFSensor = new JTextField();
		textFSensor.setBounds(208, 86, 172, 20);
		panelCreSensor.add(textFSensor);
		textFSensor.setColumns(10);

		textNSensor = new JTextField();
		textNSensor.setBounds(207, 55, 172, 20);
		panelCreSensor.add(textNSensor);
		textNSensor.setColumns(10);

		textIDSensor = new JTextField();
		textIDSensor.setBounds(207, 24, 86, 20);
		panelCreSensor.add(textIDSensor);
		textIDSensor.setColumns(10);

		comboBoxVariable = new JComboBox();
		comboBoxVariable.setModel(new DefaultComboBoxModel(rellenarCombobox()));
		comboBoxVariable.setBounds(80, 164, 157, 20);
		panelCreSensor.add(comboBoxVariable);

		panelAtras10 = new JPanel();
		panelCSensor.add(panelAtras10, BorderLayout.SOUTH);
		panelAtras10.setLayout(new GridLayout(0, 3, 0, 0));

		btnAtras10 = new JButton("Atras");
		btnAtras10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCSensor.setVisible(false);
				lblS.setText("Seleccionar opccion");

			}
		});
		panelAtras10.add(btnAtras10);

		btnInicio = new JButton("Menu inicio");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCSensor.setVisible(false);
				botoneraInicial.setVisible(true);
				lblS.setText("Seleccionar opccion");
			}
		});

		btnCrSensor = new JButton("Crear Sensor");
		panelAtras10.add(btnCrSensor);
		btnCrSensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * rdbtnEISensor
				 * textFSensor textNSensor textIDSensor comboBoxVariable 
				 */
			}
		});
		panelAtras10.add(btnInicio);

		panelApagar = new JPanel();
		contentPane.add(panelApagar, BorderLayout.SOUTH);

		btnConexiones = new JButton("Administrar conexiones");
		btnConexiones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelApagar.add(btnConexiones);
		//Inicializando las tablas
		btnAServidor = new JButton("Apagar servidor");
		btnAServidor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desconectar();
			}
		});
		panelApagar.add(btnAServidor);	
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
	public Image buscarImage(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
		chooser.setFileFilter(filter);
		//    int returnVal = chooser.showOpenDialog();
		//    if(returnVal == JFileChooser.APPROVE_OPTION) {
		//       System.out.println("You chose to open this file: " +
		//           chooser.getSelectedFile().getName());
		//    }
		return null;
	}

	public String[]rellenarCombobox(){
		LinkedList<String> devolver = new LinkedList<String>();
		devolver=InteraccionDB.listadoVariable();
		String[]var=new String[devolver.size()+1];
		var[0]="Variable";
		for(int i=0;i<devolver.size();i++){
			var[i+1]=devolver.get(i);
		}
		return var;
	}
	public void administrarVariables(){
		
	}
	public void administrarSensores(){
		
	}
	public void administrarUsuarios(){
		
	}
	public void administrarPlacas(){
		
	}
}
