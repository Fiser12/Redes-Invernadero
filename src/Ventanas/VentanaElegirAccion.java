package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import servidor.serverModel.ModelClass.Placa;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class VentanaElegirAccion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	public VentanaElegirAccion(Placa p) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel opcion = new JPanel();
		contentPane.add(opcion, BorderLayout.NORTH);
		opcion.setLayout(new BoxLayout(opcion, BoxLayout.X_AXIS));
		
		JLabel Variable = new JLabel("Selecionar una opcion:");
		Variable.setVerticalAlignment(SwingConstants.TOP);
		opcion.add(Variable);
		
		JPanel botones = new JPanel();
		contentPane.add(botones, BorderLayout.SOUTH);
		botones.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		botones.add(btnContinuar);
		
		JButton btnRegresar = new JButton("Regresar");
		botones.add(btnRegresar);
		
		JPanel opciones = new JPanel();
		contentPane.add(opciones, BorderLayout.CENTER);
		opciones.setLayout(new GridLayout(4, 1, 0, 0));
		
		JRadioButton radioIColor = new JRadioButton("Capturar imagen a color");
		opciones.add(radioIColor);
		
		JRadioButton rdbtnCIBLN = new JRadioButton("Capturar imagen a blanco y negro");
		opciones.add(rdbtnCIBLN);
		
		JRadioButton rdbtnDIntensidad = new JRadioButton("Aumentar intensidad de la luz de la imagen");
		opciones.add(rdbtnDIntensidad);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Disminuir intensidad de la luz de la imagen");
		opciones.add(rdbtnNewRadioButton_2);
	}

}
