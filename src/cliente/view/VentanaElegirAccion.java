package cliente.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import servidor.serverModel.ModelClass.Placa;
import servidor.serverModel.ModelClass.Sensor;

import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JRadioButton;

public class VentanaElegirAccion extends JFrame {

	private JPanel contentPane;
    private JRadioButton radioIColor;
    private JRadioButton rdbtnCIBLN;
	private JRadioButton rdbtnAIntensidad;
    private JRadioButton rdbtnDInsensidad;
    private ButtonGroup b;
    /**
	 * Launch the application.
	 */

	public VentanaElegirAccion(final Sensor s) {
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
			if(radioIColor.isSelected()){
			dispose();
			/*
				 * La imagen a de ser de la placa pero aqui pasamos el sensor
				 */
			}
			if(rdbtnCIBLN.isSelected()){
				dispose();	
			}
			if(rdbtnAIntensidad.isSelected()){
				VentanaIncrementar i=new VentanaIncrementar(s,true);//sumar 
				i.setVisible(true);
				dispose();
			}
			if(rdbtnDInsensidad.isSelected()){
				VentanaIncrementar i=new VentanaIncrementar(s,false);//restar
				i.setVisible(true);
				dispose();
				
			}
			}
			
		});
		botones.add(btnContinuar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaTabla nueva = new VentanaTabla();
				nueva.setVisible(true);
				dispose();}
		});
		botones.add(btnRegresar);
		
		JPanel opciones = new JPanel();
		contentPane.add(opciones, BorderLayout.CENTER);
		opciones.setLayout(new GridLayout(4, 1, 0, 0));
		
		 radioIColor = new JRadioButton("Capturar imagen a color");
		opciones.add(radioIColor);
		 rdbtnCIBLN = new JRadioButton("Capturar imagen a blanco y negro");
		opciones.add(rdbtnCIBLN);
		rdbtnAIntensidad = new JRadioButton("Aumentar intensidad de la luz de la imagen");
		opciones.add(rdbtnAIntensidad);
		rdbtnDInsensidad = new JRadioButton("Disminuir intensidad de la luz de la imagen");
		opciones.add(rdbtnDInsensidad);
	    b=new ButtonGroup();
		b.add(radioIColor);
		b.add(rdbtnCIBLN);
		b.add(rdbtnAIntensidad);
		b.add(rdbtnDInsensidad );
	
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//	VentanaElegirAccion frame = new VentanaElegirAccion();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
