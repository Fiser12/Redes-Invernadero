package servidor.view;

import java.awt.BorderLayout;
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
		panelTablas.setLayout(new GridLayout(0, 3, 0, 0));
		
		mPlaca=new DefaultTableModel();
		mPlaca.setColumnIdentifiers(new String[]{"ID Placa"});
		tPlaca=new JTable(mPlaca);
		tPlaca.setRowSelectionAllowed(false);
		tPlaca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tPlaca.setModel(mPlaca);
		tPlaca.getTableHeader().setReorderingAllowed(false);
		scrollPanePlaca = new JScrollPane(tPlaca);
		panelTablas.add(scrollPanePlaca);

		mSensor=new DefaultTableModel();
		mSensor.setColumnIdentifiers(new String[]{"ID Sensor"});
		tSensor=new JTable(mSensor);
		tSensor.getTableHeader().setReorderingAllowed(false);
		tSensor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tSensor.setModel(mSensor);
		scrollPaneSensor = new JScrollPane(tSensor);
		panelTablas.add(scrollPaneSensor);
		
		mVariable=new DefaultTableModel();
		mVariable.setColumnIdentifiers(new String[]{"Variable"});
		tVariable=new JTable(mVariable);
		tVariable.getTableHeader().setReorderingAllowed(false);
		tVariable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tVariable.setModel(mVariable);
		scrollPaneVariable = new JScrollPane(tVariable);
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
		mSensor.setColumnIdentifiers(new String[]{"ID Sensor","EstadoV","Variable","Ul Accion","Fun principal"});
		LinkedList<Sensor> devolver = new LinkedList<Sensor>();
		devolver=InteraccionDB.ListadoSensor();
		for(int i=0;i<devolver.size();i++){
			Sensor s=devolver.get(i);

			mSensor.addRow(new String[]{""+s.getId_sensor(),s.getEstadoVariable(),s.getVariable(),s.getUltimaAccion(),s.getFuncionPrincipal()});

		}
	}
}
