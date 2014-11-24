package cliente.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConexion extends JFrame {

	private JPanel contentPane;
	private JTextField tFIP;
	private JTextField tFpuerto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConexion frame = new VentanaConexion();
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
	public VentanaConexion() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panelIP = new JPanel();
		panelCentral.add(panelIP);
		panelIP.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panelIP.add(panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Direccion IP:");
		panel_4.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panelIP.add(panel_2);
		
		tFIP = new JTextField();
		panel_2.add(tFIP);
		tFIP.setColumns(10);
		
		JPanel panelPuerto = new JPanel();
		panelCentral.add(panelPuerto);
		panelPuerto.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panelPuerto.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("Puerto:");
		panel_3.add(lblNewLabel);
		
		JPanel panel_5 = new JPanel();
		panelPuerto.add(panel_5);
		
		tFpuerto = new JTextField();
		panel_5.add(tFpuerto);
		tFpuerto.setColumns(10);
		
		JPanel Recordar = new JPanel();
		panelCentral.add(Recordar);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		Recordar.add(chckbxNewCheckBox);
		
		JPanel Botones = new JPanel();
		panelCentral.add(Botones);
		Botones.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel = new JPanel();
		Botones.add(panel);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		Botones.add(panel_1);
		
		JButton btnSalir = new JButton("Salir");
		panel_1.add(btnSalir);
	}

}
