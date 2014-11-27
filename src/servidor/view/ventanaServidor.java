package servidor.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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
import util.SocketManager;
import util.Util;

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
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class ventanaServidor extends JFrame implements FocusListener {

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
	private JPanel panelUsuarios;
	private JPanel panelBotonesUsuario ;
	private JButton btnAUsuarioPlaca;
	private JButton btnFUsuario;
	private JButton btnCUsuario;
	private JButton btnAtras;
	private JPanel panelPlacas;
	private JPanel panelCPlacas;
	private JButton btnLPlaca;
	private JButton btnCPlaca;
	private JPanel panelAtras;
	private JButton btnAtras1;
	private JPanel panelSensores;
	private JPanel panelCSensores;
	private JButton btnLSensores;
	private JButton btnCSensor;
	private JButton btnASPlacas;
	private JPanel panelAtras2;
	private JButton btnAtras2;
	private JPanel panelVariables;
	private JPanel panelCVariables;
	private JButton btnLVariables;
	private JButton btnAVPlacas;
	private JButton btnCVariable;
	private JPanel panelAtras3;
	private JButton btnAtras3;
	private JPanel panelApagar;
	private JButton btnConexiones;
	private JButton btnAServidor;
	private JModel mUsuario;
	private JModel mPlaca;
	private JModel mVariable;
	private JModel mSensor;
	private JTable tUsuario;
	private JTable tPlaca;
	private JTable tSensor;
	private JTable tVariable;
	private JTable tUsuario1;
	private JTable tPlaca1;
	private JTable tSensor1;
	private JTable tPlaca2;
	private JTable tVariable1;
	private static ServerSocket wellcomeSocket;
	public static LinkedList<SocketManager> listaSockets;
	public static LinkedList<Request> listaHilos;
	private JPanel panelAUsuarioPlacas;
	private JPanel panelTablas;
	private JPanel panelAtras4;
	private JButton btnAtras4;
	private JButton btnInicio1;
	private JScrollPane scrollPaneTUsuario;
	private JScrollPane scrollPanePlaca;
	private JPanel panelBotonesU;
	private JPanel panelCombinar;
	private JButton btnAUPlacas;
	private JPanel panelLUsuario;
	private JPanel panelCUsuario;
	private JPanel panelAtras5;
	private JButton btnAtras5;
	private JButton btnInicio2;
	private JButton btnUBorrar;
	private JPanel panelCrUsuario;
	private JPanel panelCreaccionU;
	private JPanel panelAtras6;
	private JPanel panelCamposuser;
	private JButton btnInicio3;
	private JButton btnAtras6;
	private JButton btnCrearU;
	private JPanel panelcamposUser;
	private JLabel lblNombre;
	private JLabel lblPass;
	private JTextField tFNombre;
	private JPasswordField pFUser;
	private JPanel panelLPlaca;
	private JPanel panelAtras7;
	private JPanel panelLplaca;
	private JButton btnAtras7;
	private JButton btnInicio4;
	private JPanel panelBPlaca;
	private JButton btnBPlaca;
	private JScrollPane scrollPane;
	private JPanel panelCPlaca;
	private JPanel panelAtras8;
	private JButton btnAtras8;
	private JButton btnMenuInicio4;
	private JPanel panelCreplaca;
	private JPanel panelCreaccionP;
	private JButton btnCreaccionPlaca;
	private JPanel panelCreaccionPla;
	private JLabel lblPNombre;
	private JTextField textID;
	private JLabel lbNombrep;
	private JTextField textNombre;
	private JPanel panelLSensores;
	private JPanel panelListac;
	private JPanel panelAtras9;
	private JButton btnAtras9;
	private JButton btnInicio5;
	private JPanel panelBSensor;
	private JButton btnBSensor;
	private JScrollPane scrollLSensor;
	private JPanel panelCSensor;
	private JPanel panelCrSensor;
	private JPanel panelAtras10;
	private JButton btnAtras10;
	private JButton btnInicio;
	private JPanel panelBCSe;
	private JPanel panelCreSensor;
	private JButton btnCrSensor;
	private JTextField textFSensor;
	private JTextField textNSensor;
	private JTextField textIDSensor;
	private JPanel panelSensoresPlaca;
	private JPanel panelAtras11;
	private JPanel panelTVarPla;
	private JButton btnAtras11;
	private JButton btnMenuInicio;
	private JPanel panelASENSPLAca;
	private JPanel panelAsociarSP;
	private JButton btnAsociarSP;
	private JScrollPane scrollUTablaPlaca;
	private JScrollPane scrolltablaSensor;

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
	public ventanaServidor() throws Exception{
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		/*
		 * Inicializaccion de modelos de tabla
		 */
			JModel mUsuario=new JModel();
			mUsuario.setColumnIdentifiers(new String[]{"Nombre Usuario"});
			JModel mPlaca=new JModel();
			mPlaca.setColumnIdentifiers(new String[]{"ID","Estado"});
			JModel mSensor=new JModel();
			mSensor.setColumnIdentifiers(new String[]{"ID","Estado variable","Nombre Variable","Ultima Accion","Funcion principal"});
			JModel mVariable=new JModel();
			mVariable.setColumnIdentifiers(new String[]{"Nombre"});
			/*
			 * Inicializaccion de las tablas
			 */
			//tabla variable
			tVariable=new JTable(mVariable);
			tVariable.addFocusListener(this);
			tVariable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tVariable.setModel(mVariable);
			//variable2
			tVariable1=new JTable(mVariable);
			tVariable1.addFocusListener(this);
			tVariable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tVariable1.setModel(mVariable);
			//tabla Sensor
			tSensor=new JTable(mSensor);
			tSensor.addFocusListener(this);
			tSensor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tSensor.setModel(mSensor);
			//sensor 2
			tSensor1=new JTable(mSensor);
			tSensor1.addFocusListener(this);
			tSensor1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tSensor1.setModel(mSensor);
		
		panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblS = new JLabel("Seleccionar una opcion:");
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
	    btnASensores.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		botoneraInicial.setVisible(false);
	    		panelSensores.setVisible(true);
	    	}
	    });
	    
	    btnAUsuarios = new JButton("Administrar usuarios");
	    botoneraInicial.add(btnAUsuarios);
	    btnAUsuarios.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		botoneraInicial.setVisible(false);
	    	    panelUsuarios.setVisible(true);	
	    	 
	    	}
	    });
	    
	    btnPlacas = new JButton("Administrar Placas");
	    btnPlacas.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		botoneraInicial.setVisible(false);
	    		panelPlacas.setVisible(true);
	    	}
	    });
	    botoneraInicial.add(btnPlacas);
	    
	    btnAVariables = new JButton("Administrar Variables");
	    btnAVariables.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		botoneraInicial.setVisible(false);
	    		panelVariables.setVisible(true);
	    	}
	    });
	    botoneraInicial.add(btnAVariables);
	    botoneraInicial.add(btnASensores);
		/*
		 * Opciones Usuario
		 */
		panelUsuarios = new JPanel();
		panelcentral.add(panelUsuarios, "name_117609091157597");
		panelUsuarios.setLayout(new BorderLayout(0, 0));
		
		panelBotonesUsuario = new JPanel();
		panelUsuarios.add(panelBotonesUsuario);
		
		btnAUsuarioPlaca = new JButton("Asociciar Usuarios a placas");
		btnAUsuarioPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			     panelAUsuarioPlacas.setVisible(true);
			     /*
			      * Cargar los datos de la tabla placa y usuario
			      */
				panelUsuarios.setVisible(false);
				lblS.setText("Seleccionar ambas tablas para poder relaccionar:");	
			}
		});
		
		btnFUsuario = new JButton("Funcionalidades de usuario");
		btnFUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panelUsuarios.setVisible(false);
			panelLUsuario.setVisible(true);
			lblS.setText("Seleccionar una Usuario a borrar");
			/*
			 * Rellenar tabla usuario
			 */
			}
		});
		panelBotonesUsuario.setLayout(new GridLayout(3, 1, 0, 0));
		panelBotonesUsuario.add(btnAUsuarioPlaca);
		panelBotonesUsuario.add(btnFUsuario);
		
		btnCUsuario = new JButton("Crear Usuario");
		btnCUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCrUsuario.setVisible(true);
				panelUsuarios.setVisible(false);
				lblS.setText("Rellenar campos");
			}
		});
		panelBotonesUsuario.add(btnCUsuario);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUsuarios.setVisible(false);
				botoneraInicial.setVisible(true);
			}
		});
		panelUsuarios.add(btnAtras, BorderLayout.SOUTH);
		/*
		 * Opciones de placas
		 */
		panelPlacas = new JPanel();
		panelcentral.add(panelPlacas, "name_119314737616723");
		panelPlacas.setLayout(new BorderLayout(0, 0));
		
		panelCPlacas = new JPanel();
		panelPlacas.add(panelCPlacas, BorderLayout.CENTER);
		panelCPlacas.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnLPlaca = new JButton("Lista de Placas");
		btnLPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panelPlacas.setVisible(false);
			panelLPlaca.setVisible(true);
			lblS.setText("Seleccionar el usuario a borrar:");
			}
		});
		panelCPlacas.add(btnLPlaca);
		
		btnCPlaca = new JButton("Crear placa");
		btnCPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCPlaca.setVisible(true);
				panelPlacas.setVisible(false);
				lblS.setText("Rellenar campos para crear una placa:");
			}
		});
		panelCPlacas.add(btnCPlaca);
		
		panelAtras = new JPanel();
		panelPlacas.add(panelAtras, BorderLayout.SOUTH);
		panelAtras.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnAtras1 = new JButton("Atras");
		btnAtras1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panelPlacas.setVisible(false);
			botoneraInicial.setVisible(true);
			}
		});
		panelAtras.add(btnAtras1);
		
		panelSensores = new JPanel();
		panelcentral.add(panelSensores, "name_120084261205515");
		panelSensores.setLayout(new BorderLayout(0, 0));
		
		panelCSensores = new JPanel();
		panelSensores.add(panelCSensores, BorderLayout.CENTER);
		panelCSensores.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnLSensores = new JButton("Lista de sensores");
		btnLSensores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panelLSensores.setVisible(true);
			panelSensores.setVisible(false);
			lblS.setText("Seleccionar sensor a borrar:");
			
			}
		});
		panelCSensores.add(btnLSensores);
		
		btnCSensor = new JButton("Crear Sensor");
		btnCSensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCSensor.setVisible(true);
				panelSensores.setVisible(false);
				lblS.setText("Rellenar campos para la creaccion del sensor");
				}
		});
		panelCSensores.add(btnCSensor);
		
		btnASPlacas = new JButton("Asociar sensores a placas");
		btnASPlacas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSensoresPlaca.setVisible(true);
				panelSensores.setVisible(false);
			lblS.setText("Seleccionar Ambas tablas para realizar la asociaciom de sensores con placas:");	}
		});
		panelCSensores.add(btnASPlacas);
		
		 panelAtras2 = new JPanel();
		panelSensores.add(panelAtras2, BorderLayout.SOUTH);
		panelAtras2.setLayout(new GridLayout(1, 0, 0, 0));
		
	    btnAtras2 = new JButton("Atras");
	    btnAtras2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	panelSensores.setVisible(false);
	    	botoneraInicial.setVisible(true);
	    	}
	    });
		panelAtras2.add(btnAtras2);
		/*
		 * Panel opciones de las variables
		 */
		panelVariables = new JPanel();
		panelcentral.add(panelVariables, "name_120457173488400");
		panelVariables.setLayout(new BorderLayout(0, 0));
		
		 panelCVariables = new JPanel();
		panelVariables.add(panelCVariables, BorderLayout.CENTER);
		panelCVariables.setLayout(new GridLayout(3, 1, 0, 0));
		
		btnLVariables = new JButton("Lista de Variables");
		panelCVariables.add(btnLVariables);
		
		btnCVariable = new JButton("Crear Variable");
		panelCVariables.add(btnCVariable);
		
		btnAVPlacas = new JButton("Asocciar variables a las placas");
		panelCVariables.add(btnAVPlacas);
		
		panelAtras3 = new JPanel();
		panelVariables.add(panelAtras3, BorderLayout.SOUTH);
		panelAtras3.setLayout(new GridLayout(0, 1, 0, 0));
		
		 btnAtras3 = new JButton("Atras");
		btnAtras3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			panelVariables.setVisible(false);
			botoneraInicial.setVisible(true);
						}
		});
		panelAtras3.add(btnAtras3);
		/*
		 * Panel Asocciaccion Placas Usuario No carga la tabla usuario
		 */
		panelAUsuarioPlacas = new JPanel();
		panelcentral.add(panelAUsuarioPlacas, "name_5095721302242");
		panelAUsuarioPlacas.setLayout(new BorderLayout(0, 0));
		
		panelAtras4 = new JPanel();
		panelAUsuarioPlacas.add(panelAtras4, BorderLayout.SOUTH);
		panelAtras4.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnAtras4 = new JButton("Atras");
		btnAtras4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				panelAUsuarioPlacas.setVisible(false);
				panelUsuarios.setVisible(true);
				lblS.setText("Seleccionar una opcion");
			}
		});
		panelAtras4.add(btnAtras4);
		
		btnInicio1 = new JButton("Volver al inicio");
		btnInicio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAUsuarioPlacas.setVisible(false);
				botoneraInicial.setVisible(true);
				lblS.setText("Seleccionar una opcion");
			}
		});
		panelAtras4.add(btnInicio1);
		
		panelBotonesU = new JPanel();
		panelAUsuarioPlacas.add(panelBotonesU, BorderLayout.CENTER);
		panelBotonesU.setLayout(new BorderLayout(0, 0));
		
		panelTablas = new JPanel();
		panelBotonesU.add(panelTablas);
		panelTablas.setLayout(new GridLayout(0, 2, 0, 0));
		
		panelCombinar = new JPanel();
		panelBotonesU.add(panelCombinar, BorderLayout.SOUTH);
		panelCombinar.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnAUPlacas = new JButton("Asocciar usuarios a placa");
		btnAUPlacas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panelCombinar.add(btnAUPlacas);
		/*
		 * Lista Usuario
		 */
		panelLUsuario = new JPanel();
		panelcentral.add(panelLUsuario, "name_10638615482548");
		panelLUsuario.setLayout(new BorderLayout(0, 0));
		
		panelCUsuario = new JPanel();
		panelLUsuario.add(panelCUsuario, BorderLayout.CENTER);
		panelCUsuario.setLayout(new BorderLayout(0, 0));
		
		btnUBorrar = new JButton("Borrar Usuario");
		panelCUsuario.add(btnUBorrar, BorderLayout.SOUTH);
		//tabla Placa
		tPlaca=new JTable(mPlaca);
		tPlaca.addFocusListener(this);
		tPlaca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tPlaca.setModel(mPlaca);
		//tabla PLaca
		tPlaca1=new JTable(mPlaca);
		tPlaca1.addFocusListener(this);
		tPlaca1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tPlaca1.setModel(mPlaca);
		
		scrollPanePlaca = new JScrollPane(tPlaca1);
		panelTablas.add(scrollPanePlaca);
		//tabla Usuario
		tUsuario=new JTable(mUsuario);
		tUsuario.addFocusListener(this);
		tUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tUsuario.setModel(mUsuario);
		tUsuario1=new JTable(mUsuario);
		tUsuario1.addFocusListener(this);
		tUsuario1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tUsuario1.setModel(mUsuario);
		
		scrollPaneTUsuario = new JScrollPane(tUsuario1);
		panelTablas.add(scrollPaneTUsuario);
		
		JScrollPane scroLUsuario = new JScrollPane(tUsuario);
		panelCUsuario.add(scroLUsuario, BorderLayout.CENTER);
		
		panelAtras5 = new JPanel();
		panelLUsuario.add(panelAtras5, BorderLayout.SOUTH);
		panelAtras5.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnAtras5 = new JButton("Atras");
		btnAtras5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panelLUsuario.setVisible(false);
			panelUsuarios.setVisible(true);
			lblS.setText("Seleccionar una opcion: ");
			}
		});
		panelAtras5.add(btnAtras5);
		
		btnInicio2 = new JButton("Menu inicio");
		btnInicio2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLUsuario.setVisible(false);
				botoneraInicial.setVisible(true);
				lblS.setText("Seleccionar una opcion: ");
			}
		});
		panelAtras5.add(btnInicio2);
		/*
		 * Creaccion de usuario
		 */
		panelCrUsuario = new JPanel();
		panelcentral.add(panelCrUsuario, "name_22220760681955");
		panelCrUsuario.setLayout(new BorderLayout(0, 0));
		
		panelCreaccionU = new JPanel();
		panelCrUsuario.add(panelCreaccionU, BorderLayout.CENTER);
		panelCreaccionU.setLayout(new BorderLayout(0, 0));
		
		panelCamposuser = new JPanel();
		panelCreaccionU.add(panelCamposuser, BorderLayout.SOUTH);
		panelCamposuser.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnCrearU = new JButton("Crear Usuario");
		btnCrearU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//			tFNombre
				//pFUser
			}
		});
		panelCamposuser.add(btnCrearU);
		
		panelcamposUser = new JPanel();
		panelCreaccionU.add(panelcamposUser, BorderLayout.CENTER);
		GridBagLayout gbl_panelcamposUser = new GridBagLayout();
		gbl_panelcamposUser.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelcamposUser.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelcamposUser.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelcamposUser.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelcamposUser.setLayout(gbl_panelcamposUser);
		
		lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 2;
		gbc_lblNombre.gridy = 1;
		panelcamposUser.add(lblNombre, gbc_lblNombre);
		
		tFNombre = new JTextField();
		GridBagConstraints gbc_tFNombre = new GridBagConstraints();
		gbc_tFNombre.insets = new Insets(0, 0, 5, 0);
		gbc_tFNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFNombre.gridx = 7;
		gbc_tFNombre.gridy = 1;
		panelcamposUser.add(tFNombre, gbc_tFNombre);
		tFNombre.setColumns(10);
		
		lblPass = new JLabel("Contrase\u00F1a");
		GridBagConstraints gbc_lblPass = new GridBagConstraints();
		gbc_lblPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblPass.gridx = 2;
		gbc_lblPass.gridy = 2;
		panelcamposUser.add(lblPass, gbc_lblPass);
		
		pFUser = new JPasswordField();
		GridBagConstraints gbc_pFUser = new GridBagConstraints();
		gbc_pFUser.insets = new Insets(0, 0, 5, 0);
		gbc_pFUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_pFUser.gridx = 7;
		gbc_pFUser.gridy = 2;
		panelcamposUser.add(pFUser, gbc_pFUser);
		
		panelAtras6 = new JPanel();
		panelCrUsuario.add(panelAtras6, BorderLayout.SOUTH);
		panelAtras6.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnInicio3 = new JButton("Atras");
		btnInicio3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panelCrUsuario.setVisible(false);
			panelUsuarios.setVisible(true);
			lblS.setText("Seleccionar una opccion");
			}
		});
		panelAtras6.add(btnInicio3);
		
		btnAtras6 = new JButton("Menu inicio");
		btnAtras6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCrUsuario.setVisible(false);
				botoneraInicial.setVisible(true);
				lblS.setText("Seleccionar una opccion");
			}
		});
		panelAtras6.add(btnAtras6);
		/*
		 * Listado Placas
		 */
		panelLPlaca = new JPanel();
		panelcentral.add(panelLPlaca, "name_23593973357549");
		panelLPlaca.setLayout(new BorderLayout(0, 0));
		
		panelAtras7 = new JPanel();
		panelLPlaca.add(panelAtras7, BorderLayout.SOUTH);
		panelAtras7.setLayout(new GridLayout(1, 1, 0, 0));
		
		btnAtras7 = new JButton("Atras");
		btnAtras7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panelLPlaca.setVisible(false);
			panelPlacas.setVisible(true);
			lblS.setText("Seleccionar una opcion");
			}
		});
		panelAtras7.add(btnAtras7);
		
		btnInicio4 = new JButton("Menu inicio");
		btnInicio4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLPlaca.setVisible(false);
				botoneraInicial.setVisible(true);
				lblS.setText("Seleccionar una opcion");
			}
		});
		panelAtras7.add(btnInicio4);
		
		panelLplaca = new JPanel();
		panelLPlaca.add(panelLplaca, BorderLayout.CENTER);
		panelLplaca.setLayout(new BorderLayout(0, 0));
		
		panelBPlaca = new JPanel();
		panelLplaca.add(panelBPlaca, BorderLayout.SOUTH);
		panelBPlaca.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnBPlaca = new JButton("Borrar placa");
		btnBPlaca.addActionListener(new ActionListener() {
			
			/*borrar placa
			 */
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelBPlaca.add(btnBPlaca);
		
		scrollPane = new JScrollPane(tPlaca);
		panelLplaca.add(scrollPane, BorderLayout.CENTER);
		/*
		 * Crear Placa
		 */
		panelCPlaca = new JPanel();
		panelcentral.add(panelCPlaca, "name_24645888711459");
		panelCPlaca.setLayout(new BorderLayout(0, 0));
		
		panelAtras8 = new JPanel();
		panelCPlaca.add(panelAtras8, BorderLayout.SOUTH);
		panelAtras8.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnAtras8 = new JButton("Atras");
		btnAtras8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCPlaca.setVisible(false);
				panelPlacas.setVisible(true);
				lblS.setText("Seleccionar una Opcion:");
			}
		});
		panelAtras8.add(btnAtras8);
		
		btnMenuInicio4 = new JButton("Menu Inicio");
		btnMenuInicio4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCPlaca.setVisible(false);
				botoneraInicial.setVisible(true);
				lblS.setText("Seleccionar una Opcion:");
				}
		});
		panelAtras8.add(btnMenuInicio4);
	
		panelCreplaca = new JPanel();
		panelCPlaca.add(panelCreplaca, BorderLayout.CENTER);
		panelCreplaca.setLayout(new BorderLayout(0, 0));
		
		panelCreaccionP = new JPanel();
		panelCreplaca.add(panelCreaccionP, BorderLayout.SOUTH);
		panelCreaccionP.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnCreaccionPlaca = new JButton("Creaccion Placa");
		btnCreaccionPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//textID
		    //textNombre
			// rdbtnEstadoinicial
			}
		});
		panelCreaccionP.add(btnCreaccionPlaca);
		
		panelCreaccionPla = new JPanel();
		panelCreplaca.add(panelCreaccionPla, BorderLayout.CENTER);
		panelCreaccionPla.setLayout(null);
		
		lblPNombre = new JLabel("ID:");
		lblPNombre.setBounds(88, 33, 15, 14);
		panelCreaccionPla.add(lblPNombre);
		
		textID = new JTextField();
		textID.setBounds(192, 30, 158, 20);
		panelCreaccionPla.add(textID);
		textID.setColumns(10);
		
		lbNombrep = new JLabel("Nombre:");
		lbNombrep.setBounds(88, 58, 94, 14);
		panelCreaccionPla.add(lbNombrep);
		
		textNombre = new JTextField();
		textNombre.setBounds(192, 55, 158, 20);
		panelCreaccionPla.add(textNombre);
		textNombre.setColumns(10);
		
		JRadioButton rdbtnEstadoinicial = new JRadioButton("Encendido");
		rdbtnEstadoinicial.setBounds(139, 79, 109, 23);
		panelCreaccionPla.add(rdbtnEstadoinicial);
		/*
		 * Lista de sensores
		 */
		panelLSensores = new JPanel();
		panelcentral.add(panelLSensores, "name_26661603695533");
		panelLSensores.setLayout(new BorderLayout(0, 0));
		
		panelListac = new JPanel();
		panelLSensores.add(panelListac);
		panelListac.setLayout(new BorderLayout(0, 0));
		
		panelBSensor = new JPanel();
		panelListac.add(panelBSensor, BorderLayout.SOUTH);
		panelBSensor.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnBSensor = new JButton("Borrar Sensor");
		panelBSensor.add(btnBSensor);
		
		scrollLSensor = new JScrollPane(tSensor);
		panelListac.add(scrollLSensor, BorderLayout.CENTER);
		
		panelAtras9 = new JPanel();
		panelLSensores.add(panelAtras9, BorderLayout.SOUTH);
		panelAtras9.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnAtras9 = new JButton("Atras");
		btnAtras9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLSensores.setVisible(false);
				panelSensores.setVisible(true);
				lblS.setText("Seleccionar opcion:");
			}
		});
		panelAtras9.add(btnAtras9);
		
		btnInicio5 = new JButton("Menu inicio");
		btnInicio5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelLSensores.setVisible(false);
				botoneraInicial.setVisible(true);
				lblS.setText("Seleccionar opcion:");
				}
		});
		panelAtras9.add(btnInicio5);
		/*
		 * Crear sensor
		 */
		panelCSensor = new JPanel();
		panelcentral.add(panelCSensor, "name_27780962298831");
		panelCSensor.setLayout(new BorderLayout(0, 0));
		
		panelCrSensor = new JPanel();
		panelCSensor.add(panelCrSensor);
		panelCrSensor.setLayout(new BorderLayout(0, 0));
		
		panelBCSe = new JPanel();
		panelCrSensor.add(panelBCSe, BorderLayout.SOUTH);
		panelBCSe.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnCrSensor = new JButton("Crear Sensor");
		btnCrSensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			/*
			 * rdbtnEISensor
			 * textFSensor textNSensor textIDSensor
			 */
			}
		});
		panelBCSe.add(btnCrSensor);
		
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
		rdbtnEISensor.setBounds(121, 118, 157, 23);
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
		
		panelAtras10 = new JPanel();
		panelCSensor.add(panelAtras10, BorderLayout.SOUTH);
		panelAtras10.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnAtras10 = new JButton("Atras");
		btnAtras10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCSensor.setVisible(false);
				panelSensores.setVisible(true);
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
		panelAtras10.add(btnInicio);
		/*
		 * Asociar Sensores a placa
		 */
		panelSensoresPlaca = new JPanel();
		panelcentral.add(panelSensoresPlaca, "name_46903230953549");
		panelSensoresPlaca.setLayout(new BorderLayout(0, 0));
		
		panelTVarPla = new JPanel();
		panelSensoresPlaca.add(panelTVarPla, BorderLayout.CENTER);
		panelTVarPla.setLayout(new BorderLayout(0, 0));
		
		panelASENSPLAca = new JPanel();
		panelTVarPla.add(panelASENSPLAca, BorderLayout.CENTER);
		panelASENSPLAca.setLayout(new GridLayout(1, 0, 0, 0));
		tPlaca2=new JTable(mPlaca);
		tPlaca2.addFocusListener(this);
		tPlaca2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tPlaca2.setModel(mPlaca);
		scrollUTablaPlaca = new JScrollPane(tPlaca2);
		panelASENSPLAca.add(scrollUTablaPlaca);
		
		scrolltablaSensor = new JScrollPane(tSensor1);
		panelASENSPLAca.add(scrolltablaSensor);
		
		panelAsociarSP = new JPanel();
		panelTVarPla.add(panelAsociarSP, BorderLayout.SOUTH);
		panelAsociarSP.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnAsociarSP = new JButton("Asociar sensor a placa");
		btnAsociarSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelAsociarSP.add(btnAsociarSP);
		
		panelAtras11 = new JPanel();
		panelSensoresPlaca.add(panelAtras11, BorderLayout.SOUTH);
		panelAtras11.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnAtras11 = new JButton("Atras");
		btnAtras11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSensoresPlaca.setVisible(false);
				panelSensores.setVisible(true);
			lblS.setText("Seleccionar opccion");	
			}
		});
		panelAtras11.add(btnAtras11);
		
		btnMenuInicio = new JButton("Menu inicio");
		btnMenuInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSensoresPlaca.setVisible(false);
				botoneraInicial.setVisible(true);
			lblS.setText("Seleccionar opccion");	}
		});
		panelAtras11.add(btnMenuInicio);
		
	      panelApagar = new JPanel();
		contentPane.add(panelApagar, BorderLayout.SOUTH);
		
		btnConexiones = new JButton("Administrar conexiones");
		panelApagar.add(btnConexiones);
		
		btnAServidor = new JButton("Apagar servidor");
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
		System.out.println("NÂºUSUARIO: " + (usuario));
		System.out.println("DIRECCIÃ“N: " + listaSockets.get(usuario-1).getMySocket().getInetAddress().getHostAddress());
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
	public class JModel extends DefaultTableModel
	{

		/**
		 * Metodo utilizado para no editar la tabla
		 */
		public boolean isCellEditable (int row, int column)
		{
			// Aquí devolvemos true o false según queramos que una celda
			// identificada por fila,columna (row,column), sea o no editable
			if (column >=0)
				return false;
			return true;
		}
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
