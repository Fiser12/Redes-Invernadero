package servidor.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperior;
	private JLabel lblS;
	private JPanel panelcentral;
	private JPanel botoneraInicial;
	private JButton btnAUsuarios;
	private JButton btnPlacas;
	private JButton btnASensores;
	private JPanel panelApagar;
	private JButton btnConexiones;
	private JButton btnAServidor;
	private PanelPlacas pPlacas;
	private PanelUsuarios pUsuarios;
	private PanelSensores pSensores;
	private PanelAdminServer pPanelAdmin;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setResizable(false);


					setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					setBounds(100, 100, 700, 400);
					contentPane = new JPanel();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					setContentPane(contentPane);

					panelSuperior = new JPanel();
					contentPane.add(panelSuperior, BorderLayout.NORTH);
					panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

					lblS = new JLabel("Administracion del Servidor");
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

					btnASensores = new JButton("Administrar Sensores");
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
					botoneraInicial.add(btnASensores);

					panelApagar = new JPanel();
					contentPane.add(panelApagar, BorderLayout.SOUTH);

					btnConexiones = new JButton("Administrar conexiones");
					btnConexiones.setFont(new Font("Tahoma", Font.PLAIN, 16));
					botoneraInicial.add(btnConexiones);
					btnConexiones.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							administrarGestion();
						}
					});

					btnAServidor = new JButton("Apagar servidor");
					btnAServidor.setFont(new Font("Tahoma", Font.PLAIN, 16));
					btnAServidor.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							desconectar();
						}
					});
					panelApagar.add(btnAServidor);
					pPlacas = new PanelPlacas(VentanaPrincipal.this);
					pUsuarios = new PanelUsuarios(VentanaPrincipal.this);
					pSensores = new PanelSensores(VentanaPrincipal.this);
					pPanelAdmin = new PanelAdminServer(VentanaPrincipal.this);
					pPlacas.setVisible(false);
					pUsuarios.setVisible(false);
					pSensores.setVisible(false);
					pPanelAdmin.setVisible(false);

					panelcentral.add(pUsuarios);
					panelcentral.add(pSensores);
					panelcentral.add(pPlacas);
					panelcentral.add(pPanelAdmin);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void desconectar() {
		System.exit(0);
	}

	public PanelAdminServer getPanelServer()
	{
		return pPanelAdmin;
	}

	void administrarSensores() {
		pSensores.setVisible(true);
		botoneraInicial.setVisible(false);
	}

	void administrarUsuarios() {
		pUsuarios.setVisible(true);
		botoneraInicial.setVisible(false);
	}

	void administrarPlacas() {
		pPlacas.setVisible(true);
		botoneraInicial.setVisible(false);
	}

	void administrarGestion() {
		pPanelAdmin.rellenarTablaUsuario();
		pPanelAdmin.setVisible(true);
		botoneraInicial.setVisible(false);
	}
	public PanelUsuarios getpUsuarios() {
		return pUsuarios;
	}

	public PanelSensores getpSensores() {
		return pSensores;
	}
	public JPanel getPanelcentral() {
		return panelcentral;
	}
}
