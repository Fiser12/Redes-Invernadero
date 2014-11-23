package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;

import servidor.serverModel.ModelClass.Sensor;
import servidor.serverModel.ModelClass.Placa;
import servidor.serverModel.ModelClass.Usuario;
public class VentanTabla extends JFrame implements FocusListener{

	private JPanel contentPane;
	private JTextField textIDPlaca;
	private JTextField textIdSensor;
    private JButton btnActuar;
    private JButton btnImagen;
	private JButton btnActivar;
	private JButton btnDesactivar;
	private JButton btnActua;
	private JTextField textVariable;
	private JComboBox comboBoxBusqueda;
	private JButton btnBuscar;
	private JButton btnListar;
	private jTable modeloTabla;
	private JTable tabla;
	private ArrayList<Placa> Placas=new ArrayList();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanTabla frame = new VentanTabla();
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
	public VentanTabla() {
		/*
		 * Rellenar el array list De placas
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel BotonesArriba = new JPanel();
		contentPane.add(BotonesArriba, BorderLayout.NORTH);
		BotonesArriba.setLayout(new GridLayout(0, 6, 0, 0));
		modeloTabla=new jTable();
		modeloTabla.setColumnIdentifiers(new String[]{"SENSOR","PLACA","VARIABLE","ESTADO","FUNCION","ULTIMA ACCION"});
		
		for(int i=0;i<Placas.size();i++){
			Placa p=Placas.get(i);
			for(int j=0;j<p.getSensores().size();j++)
		/*
		 * Rellenar tabla
		 */
			modeloTabla.addRow(new String[] {""+p.getId()});
		}
		
	    btnImagen = new JButton("Imajen Placa");
		BotonesArriba.add(btnImagen);
		
		textIDPlaca = new JTextField();
		textIDPlaca.setText("IDPlaca");
		BotonesArriba.add(textIDPlaca);
		textIDPlaca.setColumns(10);
		
		textIdSensor = new JTextField();
		textIdSensor.setText("IDSensor");
		BotonesArriba.add(textIdSensor);
		textIdSensor.setColumns(10);
		
		btnActivar = new JButton("Activar");
		BotonesArriba.add(btnActivar);
		
		btnDesactivar = new JButton("Desactivar");
		BotonesArriba.add(btnDesactivar);
		
		JButton btnActuar = new JButton("Actuar\r\n");
		BotonesArriba.add(btnActuar);
		
		JPanel BotonesAbajo = new JPanel();
		contentPane.add(BotonesAbajo, BorderLayout.SOUTH);
		BotonesAbajo.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		BotonesAbajo.add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		textVariable = new JTextField();
		panel.add(textVariable);
		textVariable.setColumns(10);
		
		comboBoxBusqueda = new JComboBox();
		comboBoxBusqueda.setModel(new DefaultComboBoxModel(new String[] {"Elegir Opcion De Busqueda", "Todas", "IDPlaca", "Variable", "IDPLaca", "En OFF", "EN ON", ""}));
		comboBoxBusqueda.setToolTipText("Elegir opcion\r\n");
		panel.add(comboBoxBusqueda);
		
		 btnBuscar = new JButton("Buscar");
		panel.add(btnBuscar);
		
		JPanel Busqueda = new JPanel();
		BotonesAbajo.add(Busqueda);
		Busqueda.setLayout(new GridLayout(0, 2, 0, 0));
		
		 btnListar = new JButton("Listar");
		Busqueda.add(btnListar);
		
		JButton btnSalir = new JButton("Salir");
		Busqueda.add(btnSalir);
		JScrollPane sPTabla = new JScrollPane();
		tabla=new JTable(modeloTabla);
		tabla.setModel(modeloTabla);
		tabla.addFocusListener(this);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sPTabla.add(tabla);
		contentPane.add(sPTabla, BorderLayout.CENTER);
	}
	/**
	 * Clase creada para hacer no editable una tabla
	 *
	 */
	public class jTable extends DefaultTableModel
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
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
