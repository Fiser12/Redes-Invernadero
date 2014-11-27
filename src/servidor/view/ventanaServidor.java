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
			//tabla Usuario
			tUsuario=new JTable(mUsuario);
			tUsuario.addFocusListener(this);
			tUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tUsuario.setModel(mUsuario);
			//tabla variable
			tVariable=new JTable(mVariable);
			tVariable.addFocusListener(this);
			tVariable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tVariable.setModel(mVariable);
			//tabla Sensor
			tSensor=new JTable(mSensor);
			tSensor.addFocusListener(this);
			tSensor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tSensor.setModel(mSensor);
			//tabla Placa
			tPlaca=new JTable(mPlaca);
			tPlaca.addFocusListener(this);
			tPlaca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tPlaca.setModel(mPlaca);
			
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
		panelCPlacas.add(btnLPlaca);
		
		btnCPlaca = new JButton("Crear placa");
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
		panelCSensores.add(btnLSensores);
		
		btnCSensor = new JButton("Crear Sensor");
		panelCSensores.add(btnCSensor);
		
		btnASPlacas = new JButton("Asocciar sensores a placas");
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
		
		btnAVPlacas = new JButton("Asocciar variables a las placas");
		panelCVariables.add(btnAVPlacas);
		
		btnCVariable = new JButton("Crear Variable");
		panelCVariables.add(btnCVariable);
		
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
		 * Panel Asocciaccion Placas Usuario
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
		
		scrollPaneTUsuario = new JScrollPane(tUsuario);
		panelTablas.add(scrollPaneTUsuario);
		
		scrollPanePlaca = new JScrollPane(tPlaca);
		panelTablas.add(scrollPanePlaca);
		
		panelCombinar = new JPanel();
		panelBotonesU.add(panelCombinar, BorderLayout.SOUTH);
		panelCombinar.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnAUPlacas = new JButton("Asocciar usuarios a placa");
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
