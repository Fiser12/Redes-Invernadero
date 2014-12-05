package servidor.view;

import servidor.serverModel.InteraccionDB;
import servidor.serverModel.ModelClass.Placa;
import servidor.serverModel.ModelClass.Sensor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class PanelSensores extends JPanel{

	private static final long serialVersionUID = 1L;

	private final JTable tPlaca;
	private final JTable tSensor;
	private final JTable tVariable;
	private final VentanaPrincipal central;
	private DefaultTableModel mPlaca;
	private DefaultTableModel mVariable;
	public PanelSensores(VentanaPrincipal central)
	{
		this.central = central;
		this.setLayout(new BorderLayout());
		JPanel panelInferior = new JPanel();
		JPanel panelCentral = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		add(panelCentral, BorderLayout.CENTER);

		JPanel panelTablas = new JPanel();
		panelCentral.add(panelTablas);
		panelTablas.setLayout(new FlowLayout());
		
		mPlaca=new DefaultTableModel();
		mPlaca.setColumnIdentifiers(new String[]{"ID Placa"});
		tPlaca=new JTable(mPlaca);
		tPlaca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tPlaca.setModel(mPlaca);
		tPlaca.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPanePlaca = new JScrollPane(tPlaca);
		scrollPanePlaca.setPreferredSize(new Dimension(100, 400));

		tSensor=new JTable();
		tSensor.getTableHeader().setReorderingAllowed(false);
		tSensor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPaneSensor = new JScrollPane(tSensor);
		scrollPaneSensor.setPreferredSize(new Dimension(466, 400));
		panelTablas.add(scrollPaneSensor);
		
		mVariable=new DefaultTableModel();
		mVariable.setColumnIdentifiers(new String[]{"Variable"});
		tVariable=new JTable(mVariable);
		tVariable.getTableHeader().setReorderingAllowed(false);
		tVariable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tVariable.setModel(mVariable);
		JScrollPane scrollPaneVariable = new JScrollPane(tVariable);
		scrollPaneVariable.setPreferredSize(new Dimension(100, 400));
		panelTablas.add(scrollPanePlaca);
		panelTablas.add(scrollPaneVariable);
		
		/**
		 * El panel inferior con todos los botones y sus metodos
		 */
		panelInferior.setLayout(new GridLayout(1, 0, 0, 0));

		JButton atras = new JButton("Atras");
		atras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atras();
			}
		});
		panelInferior.add(atras);

		JButton asociarVariable = new JButton("Asociar Variable");
		asociarVariable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelInferior.add(asociarVariable);
		asociarVariable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				asociarVariable();
			}
		});

		JButton asociarPlaca = new JButton("Asociar Placa");
		asociarPlaca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelInferior.add(asociarPlaca);
		asociarPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				asociarPlaca();
			}
		});


		JButton crear = new JButton("Crear");
		crear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelInferior.add(crear);
		crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});
		JButton borrar = new JButton("Borrar");
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

	void asociarVariable()
	{
		int rowIndexSensor = tSensor.getSelectedRow();
		int sensor = Integer.parseInt((String) tSensor.getValueAt(rowIndexSensor, 0));
		int rowIndexVariable = tVariable.getSelectedRow();
		String variable = ((String) tVariable.getValueAt(rowIndexVariable, 0));
		InteraccionDB.actualizarVariable(sensor, variable);
		rellenarTablaSensor();
	}

	void asociarPlaca()
	{
		int rowIndexSensor = tSensor.getSelectedRow();
		int sensor = Integer.parseInt((String) tSensor.getValueAt(rowIndexSensor, 0));
		int rowIndexPlaca = tPlaca.getSelectedRow();
		int placa = Integer.parseInt((String) tPlaca.getValueAt(rowIndexPlaca, 0));
		InteraccionDB.actualizarPlaca(sensor, placa);
		rellenarTablaSensor();
	}

	void crearUsuario()
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

	void atras()
	{
		central.getPanelcentral().setVisible(true);
		this.setVisible(false);
	}

	void borrarUsuario()
	{
		int id=Integer.parseInt((String) tSensor.getValueAt(tSensor.getSelectedRow(), 0));
		InteraccionDB.eliminarSensor(id);
		rellenarTablaSensor();
	}
	public void rellenarTablaSensor()
	{
		DefaultTableModel mSensor = new DefaultTableModel();
		mSensor.setColumnIdentifiers(new String[]{"ID Sensor", "ID Placa", "Estado", "Variable", "Accion", "Funcion"});
		LinkedList<Sensor> devolver = InteraccionDB.ListadoSensor();
		for (Sensor s : devolver) {
			mSensor.addRow(new String[]{"" + s.getId_sensor(), "" + s.getId_Placa(), s.getEstadoVariable(), s.getVariable(), s.getUltimaAccion(), s.getFuncionPrincipal()});

		}
		mSensor.fireTableDataChanged();
		tSensor.setModel(mSensor);
	}

	void rellenarVariables() {
		LinkedList<String> devolver = InteraccionDB.listadoVariable();
		mVariable=new DefaultTableModel();
		mVariable.setColumnIdentifiers(new String[]{"Nombre Variable"});
		for (String aDevolver : devolver) {

			mVariable.addRow(new String[]{aDevolver});
		}
		mVariable.fireTableDataChanged();
		tVariable.setModel(mVariable);
	}
	public void rellenarPlacas()
	{
		mPlaca=new DefaultTableModel();
		mPlaca.setColumnIdentifiers(new String[]{"ID Placa"});	
		LinkedList<Placa> devolver = InteraccionDB.listadoPlacas();
		for (Placa p : devolver) {
			mPlaca.addRow(new String[]{"" + p.getId()});
		}
		mPlaca.fireTableDataChanged();
		tPlaca.setModel(mPlaca);
	}
}
