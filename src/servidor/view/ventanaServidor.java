package servidor.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ventanaServidor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public ventanaServidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSuperior = new JPanel();
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblS = new JLabel("Seleccionar una opcion:");
		panelSuperior.add(lblS);
		
		JPanel panelcentral = new JPanel();
		contentPane.add(panelcentral, BorderLayout.CENTER);
		panelcentral.setLayout(new CardLayout(0, 0));
		
		JPanel botoneraInicial = new JPanel();
		panelcentral.add(botoneraInicial, "name_117604164092399");
		botoneraInicial.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panelAU = new JPanel();
		botoneraInicial.add(panelAU);
		panelAU.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAUsuarios = new JButton("Administrar usuarios");
		panelAU.add(btnAUsuarios);
		
		JPanel panelAP = new JPanel();
		botoneraInicial.add(panelAP);
		
		JButton btnPlacas = new JButton("Administrar Placas");
		panelAP.add(btnPlacas);
		
		JPanel panelAs = new JPanel();
		botoneraInicial.add(panelAs);
		
		JButton btnASensores = new JButton("Administrar Sensores");
		panelAs.add(btnASensores);
		
		JPanel panelAd = new JPanel();
		botoneraInicial.add(panelAd);
		
		JButton btnAVariables = new JButton("Administrar Variables");
		btnAVariables.setHorizontalAlignment(SwingConstants.RIGHT);
		panelAd.add(btnAVariables);
		
		JPanel panelUsuarios = new JPanel();
		panelcentral.add(panelUsuarios, "name_117609091157597");
		panelUsuarios.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotonesUsuario = new JPanel();
		panelUsuarios.add(panelBotonesUsuario);
		
		JButton btnAUsuarioPlaca = new JButton("Asociciar Usuarios a placas");
		
		JButton btnFUsuario = new JButton("Funcionalidades de usuario");
		panelBotonesUsuario.setLayout(new GridLayout(3, 1, 0, 0));
		panelBotonesUsuario.add(btnAUsuarioPlaca);
		panelBotonesUsuario.add(btnFUsuario);
		
		JButton btnCUsuario = new JButton("Crear Usuario");
		panelBotonesUsuario.add(btnCUsuario);
		
		JButton btnAtras = new JButton("Atras");
		panelUsuarios.add(btnAtras, BorderLayout.SOUTH);
		
		JPanel panelPlacas = new JPanel();
		panelcentral.add(panelPlacas, "name_119314737616723");
		panelPlacas.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCPlacas = new JPanel();
		panelPlacas.add(panelCPlacas, BorderLayout.CENTER);
		panelCPlacas.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnLPlaca = new JButton("Lista de Placas");
		panelCPlacas.add(btnLPlaca);
		
		JButton btnCPlaca = new JButton("Crear placa");
		panelCPlacas.add(btnCPlaca);
		
		JPanel panelAtras = new JPanel();
		panelPlacas.add(panelAtras, BorderLayout.SOUTH);
		panelAtras.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnAtras1 = new JButton("Atras");
		panelAtras.add(btnAtras1);
		
		JPanel panelSensores = new JPanel();
		panelcentral.add(panelSensores, "name_120084261205515");
		panelSensores.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCSensores = new JPanel();
		panelSensores.add(panelCSensores, BorderLayout.CENTER);
		panelCSensores.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnLSensores = new JButton("Lista de sensores");
		panelCSensores.add(btnLSensores);
		
		JButton btnCSensor = new JButton("Crear Sensor");
		panelCSensores.add(btnCSensor);
		
		JButton btnNewButton_1 = new JButton("Asocciar sensores a placas");
		panelCSensores.add(btnNewButton_1);
		
		JPanel panelAtras2 = new JPanel();
		panelSensores.add(panelAtras2, BorderLayout.SOUTH);
		panelAtras2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnAtras2 = new JButton("Atras");
		panelAtras2.add(btnAtras2);
		
		JPanel panelVariables = new JPanel();
		panelcentral.add(panelVariables, "name_120457173488400");
		panelVariables.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCVariables = new JPanel();
		panelVariables.add(panelCVariables, BorderLayout.CENTER);
		panelCVariables.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton btnLVariables = new JButton("Lista de Variables");
		panelCVariables.add(btnLVariables);
		
		JButton btnAVPlacas = new JButton("Asocciar variables a las placas");
		panelCVariables.add(btnAVPlacas);
		
		JButton btnCVariable = new JButton("Crear Variable");
		panelCVariables.add(btnCVariable);
		
		JPanel panelAtras3 = new JPanel();
		panelVariables.add(panelAtras3, BorderLayout.SOUTH);
		panelAtras3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnAtras3 = new JButton("Atras");
		panelAtras3.add(btnAtras3);
		
		JPanel panelApagar = new JPanel();
		contentPane.add(panelApagar, BorderLayout.SOUTH);
		
		JButton btnConexiones = new JButton("Administrar conexiones");
		panelApagar.add(btnConexiones);
		
		JButton btnAServidor = new JButton("Apagar servidor");
		panelApagar.add(btnAServidor);
	}

}
