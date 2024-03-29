package cliente.view;

import util.SocketManager;
import util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;

public class VentanaConexion extends JFrame {

	private static final long serialVersionUID = 4392006415802003983L;
	private final JTextField tFIP;
	private final JFormattedTextField tFpuerto;

	public VentanaConexion() {
		setTitle("Conexion");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 170);
		JPanel contentPane = new JPanel();
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
					Util.claseSocketCliente = new SocketManager();
					VentanaInicioSesion ventana = new VentanaInicioSesion();
					ventana.setVisible(true);
					dispose();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error, el servidor no esta encendido", "Error de conexion", JOptionPane.ERROR_MESSAGE);
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
