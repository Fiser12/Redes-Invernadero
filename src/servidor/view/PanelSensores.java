package servidor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import servidor.serverModel.InteraccionDB;
import servidor.serverModel.ModelClass.Placa;
import servidor.serverModel.ModelClass.Sensor;

public class PanelSensores extends JPanel{

	private static final long serialVersionUID = 1L;
	
	JPanel panelCentral;
	JPanel panelInferior;
	JPanel panelTablas;
	JButton atras;
	JButton asociarVariable;
	JButton asociarPlaca;

	JButton crear;
	JButton borrar;
	JTable tPlaca;
	JTable tSensor;
	JTable tVariable;

	DefaultTableModel mPlaca;
	DefaultTableModel mSensor;
	DefaultTableModel mVariable;

	JScrollPane scrollPanePlaca;
	JScrollPane scrollPaneSensor;
	JScrollPane scrollPaneVariable;
	JPanel central;
	public PanelSensores(JPanel central)
	{
		this.central = central;
		this.setLayout(new BorderLayout());
		panelInferior = new JPanel();
		panelCentral = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		add(panelCentral, BorderLayout.CENTER);

		panelTablas = new JPanel();
		panelCentral.add(panelTablas);
		panelTablas.setLayout(new FlowLayout());
		
		mPlaca=new DefaultTableModel();
		mPlaca.setColumnIdentifiers(new String[]{"ID Placa"});
		tPlaca=new JTable(mPlaca);
		tPlaca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tPlaca.setModel(mPlaca);
		tPlaca.getTableHeader().setReorderingAllowed(false);
		scrollPanePlaca = new JScrollPane(tPlaca);
		scrollPanePlaca.setPreferredSize(new Dimension(122, 400));

		tSensor=new JTable();
		tSensor.getTableHeader().setReorderingAllowed(false);
		tSensor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneSensor = new JScrollPane(tSensor);
		scrollPaneSensor.setPreferredSize(new Dimension(422, 400));
		panelTablas.add(scrollPaneSensor);
		
		mVariable=new DefaultTableModel();
		mVariable.setColumnIdentifiers(new String[]{"Variable"});
		tVariable=new JTable(mVariable);
		tVariable.getTableHeader().setReorderingAllowed(false);
		tVariable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tVariable.setModel(mVariable);
		scrollPaneVariable = new JScrollPane(tVariable);
		scrollPaneVariable.setPreferredSize(new Dimension(122, 400));
		panelTablas.add(scrollPanePlaca);
		panelTablas.add(scrollPaneVariable);
		
		/**
		 * El panel inferior con todos los botones y sus métodos
		 */
		panelInferior.setLayout(new GridLayout(1, 0, 0, 0));
		
		atras = new JButton("Atras");
		atras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atras();
			}
		});
		panelInferior.add(atras);
			
		asociarVariable = new JButton("Asociar Variable");
		asociarVariable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelInferior.add(asociarVariable);
		asociarVariable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				asociarVariable();
			}
		});

		asociarPlaca = new JButton("Asociar Placa");
		asociarPlaca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelInferior.add(asociarPlaca);
		asociarPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				asociarPlaca();
			}
		});

		
		crear = new JButton("Crear");
		crear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelInferior.add(crear);
		crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});
		borrar = new JButton("Borrar");
		borrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelInferior.add(borrar);
		borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrarUsuario();
			}
		});
		rellenarTablaSensor();
		rellenarVariables();
		rellenarPlacas();
	}
	public void asociarVariable()
	{

	}
	public void asociarPlaca()
	{
		
	}
	public void crearUsuario()
	{
		VentanaCrearSensores nueva = new VentanaCrearSensores();
		nueva.setVisible(true);
		nueva.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e)
			{
				rellenarTablaSensor();
			}
		});
	}
	public void atras()
	{
		central.setVisible(true);
		this.setVisible(false);
	}
	public void borrarUsuario()
	{
		int id=Integer.parseInt((String) tSensor.getValueAt(tSensor.getSelectedRow(), 0));
		InteraccionDB.eliminarSensor(id);
		rellenarTablaSensor();
	}
	public void rellenarTablaSensor()
	{
		mSensor=new DefaultTableModel();
		mSensor.setColumnIdentifiers(new String[]{"ID Sensor","Estado","Variable","Accion","Funcion"});
		LinkedList<Sensor> devolver = new LinkedList<Sensor>();
		devolver=InteraccionDB.ListadoSensor();
		for(int i=0;i<devolver.size();i++){
			Sensor s=devolver.get(i);
			System.out.println(s.toString());
			mSensor.addRow(new String[]{""+s.getId_sensor(),s.getEstadoVariable(),s.getVariable(),s.getUltimaAccion(),s.getFuncionPrincipal()});

		}
		mSensor.fireTableDataChanged();
		tSensor.setModel(mSensor);
	}
	public void rellenarVariables(){
		LinkedList<String> devolver = new LinkedList<String>();
		devolver=InteraccionDB.listadoVariable();
		mVariable=new DefaultTableModel();
		mVariable.setColumnIdentifiers(new String[]{"Nombre Variable"});
		for(int i=0;i<devolver.size();i++){

			mVariable.addRow(new String[]{	devolver.get(i)});
		}
		mVariable.fireTableDataChanged();
		tVariable.setModel(mVariable);
	}
	public void rellenarPlacas()
	{
		mPlaca=new DefaultTableModel();
		mPlaca.setColumnIdentifiers(new String[]{"ID Placa"});	
		LinkedList<Placa> devolver = new LinkedList<Placa>();
		devolver=InteraccionDB.listadoPlacas();
		for(int i=0;i<devolver.size();i++){
			Placa p=devolver.get(i);
			mPlaca.addRow(new String[]{""+p.getId()});
		}
		mPlaca.fireTableDataChanged();
		tPlaca.setModel(mPlaca);
		tPlaca.setModel(mPlaca);
	}
}
