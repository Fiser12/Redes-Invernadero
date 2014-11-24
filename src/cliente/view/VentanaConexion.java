package cliente.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import java.awt.GridLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.NumberFormat;

public class VentanaConexion extends JFrame {

	private JPanel contentPane;
	private JTextField tFIP;
	private JFormattedTextField tFpuerto;

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
		setTitle("Conexion");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(3, 0, 0, 0));
		
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
		tFIP.setText(Util.servidor);
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
		
	    NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(65535);
	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);
	    tFpuerto = new JFormattedTextField(formatter);

		tFpuerto.setText(""+Util.puerto);
		panel_5.add(tFpuerto);
		tFpuerto.setColumns(10);
		
		JPanel Botones = new JPanel();
		panelCentral.add(Botones);
		Botones.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel = new JPanel();
		Botones.add(panel);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Util.puerto = Integer.parseInt(tFpuerto.getText().replace(".", ""));
				Util.servidor = tFIP.getText();
				try {
					Util.claseSocketCliente = new SocketManager("127.0.0.1", 3000);
					VentanaInicioSesion ventana = new VentanaInicioSesion();
					ventana.setVisible(true);
					dispose();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error, el servidor no está encendido", "Error de conexión", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		Botones.add(panel_1);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panel_1.add(btnSalir);
	}

}
