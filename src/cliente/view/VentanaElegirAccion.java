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
import java.util.LinkedList;

import javax.swing.JRadioButton;

public class VentanaElegirAccion extends JFrame implements ActionListener {

	private JPanel contentPane;
    private LinkedList<JRadioButton> radioButtonGroup;
    private ButtonGroup group = new ButtonGroup();
    private Sensor accionSobre;
    private String seleccion;
    private JPanel opciones;
    /**
	 * Launch the application.
	 */

	public VentanaElegirAccion(Sensor s) {
		accionSobre = s;
		opciones = new JPanel();
		radioButtonGroup = new LinkedList<JRadioButton>();
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		cargarRadioButtons();
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
		btnContinuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accionSobre.setUltimaAccion(seleccion);
				VentanaIncrementar nuevaVentana = new VentanaIncrementar(accionSobre);
				nuevaVentana.setVisible(true);
				dispose();
			}
		});
		botones.add(btnContinuar);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botones.add(btnRegresar);
		
		contentPane.add(opciones, BorderLayout.CENTER);
		opciones.setLayout(new GridLayout(4, 1, 0, 0));
	
	}
	public void cargarRadioButtons(){
		if(accionSobre.getVariable().equals("Temperatura"))
		{
			JRadioButton uno = new JRadioButton("Subir a.c");
			uno.setSelected(true);
			JRadioButton dos = new JRadioButton("Bajar a.c");
			uno.addActionListener(this);
			dos.addActionListener(this);
			uno.setActionCommand("Subir a.c");
			dos.setActionCommand("Bajar a.c");
			radioButtonGroup.add(uno);
			radioButtonGroup.add(dos);
			group.add(uno);
			group.add(dos);
			opciones.add(uno);
			opciones.add(dos);
			seleccion = uno.getActionCommand();
		}
		else if(accionSobre.getVariable().equals("Humedad"))
		{
			JRadioButton uno = new JRadioButton("Activar sistemas de riego");
			uno.addActionListener(this);
			uno.setSelected(true);
			uno.setActionCommand("Activar sistemas de riego");
			radioButtonGroup.add(uno);
			group.add(uno);
			opciones.add(uno);
			seleccion = uno.getActionCommand();
		}
		else if(accionSobre.getVariable().equals("Zoom"))
		{
			JRadioButton uno = new JRadioButton("Aumentar zoom");
			JRadioButton dos = new JRadioButton("Disminuir zoom");
			uno.addActionListener(this);
			uno.setSelected(true);
			dos.addActionListener(this);
			uno.setActionCommand("Aumentar zoom");
			dos.setActionCommand("Disminuir zoom");
			radioButtonGroup.add(uno);
			radioButtonGroup.add(dos);
			group.add(uno);
			group.add(dos);
			opciones.add(uno);
			opciones.add(dos);
			seleccion = uno.getActionCommand();
		}
		else if(accionSobre.getVariable().equals("Imagen"))
		{
			JRadioButton uno = new JRadioButton("Capturar imagen a color");
			uno.setSelected(true);
			JRadioButton dos = new JRadioButton("Capturar imagen blanco y negro");
			JRadioButton tres = new JRadioButton("Aumentar intensidad de la luz");
			JRadioButton cuatro = new JRadioButton("Disminuir intensidad de la luz");
			uno.addActionListener(this);
			dos.addActionListener(this);
			tres.addActionListener(this);
			cuatro.addActionListener(this);
			uno.setActionCommand("Capturar imagen a color");
			dos.setActionCommand("Capturar imagen blanco y negro");
			tres.setActionCommand("Aumentar intensidad de la luz");		
			cuatro.setActionCommand("Disminuir intensidad de la luz");
			radioButtonGroup.add(uno);
			radioButtonGroup.add(dos);
			radioButtonGroup.add(tres);
			radioButtonGroup.add(cuatro);
			group.add(uno);
			group.add(dos);
			group.add(tres);
			group.add(cuatro);
			opciones.add(uno);
			opciones.add(dos);
			opciones.add(tres);
			opciones.add(cuatro);
			seleccion = uno.getActionCommand();
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		seleccion = e.getActionCommand();
	}
	

}
