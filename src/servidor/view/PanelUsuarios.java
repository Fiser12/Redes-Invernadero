package servidor.view;

import servidor.serverModel.InteraccionDB;
import servidor.serverModel.ModelClass.Asociacion;
import servidor.serverModel.ModelClass.Placa;
import servidor.serverModel.ModelClass.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class PanelUsuarios extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JPanel panelCentral;
	private JPanel panelInferior;
	private JPanel panelTablas;
	private JButton atras;
	private JButton asociar;
	private JButton crear;
	private JButton borrar;
	private JTable tPlaca;
	private JTable tUsuario;
	private DefaultTableModel mPlaca;
	private DefaultTableModel mUsuario;
	private VentanaPrincipal principal;
	private JScrollPane scrollPanePlaca;
	private JScrollPane scrollPaneUsuario;
	private DefaultTableModel mAsociacion;

	private JTable tAsociacion;

	private JScrollPane scrollPaneAsociacion;

	private JButton borrarAsociacion;

	public PanelUsuarios(VentanaPrincipal prin)
	{
		this.principal = prin;
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
		scrollPanePlaca.setPreferredSize(new Dimension(222, 400));
		panelTablas.add(scrollPanePlaca);

		mUsuario=new DefaultTableModel();
		mUsuario.setColumnIdentifiers(new String[]{"Nombre Usuario"});
		tUsuario=new JTable(mUsuario);
		tUsuario.getTableHeader().setReorderingAllowed(false);
		tUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tUsuario.setModel(mUsuario);
		scrollPaneUsuario = new JScrollPane(tUsuario);
		scrollPaneUsuario.setPreferredSize(new Dimension(222, 400));
		panelTablas.add(scrollPaneUsuario);
		
		mAsociacion = new DefaultTableModel();
		mAsociacion.setColumnIdentifiers(new String[]{"Nombre Usuario", "ID Placa"});
		tAsociacion = new JTable(mAsociacion);
		tAsociacion.getTableHeader().setReorderingAllowed(false);
		tAsociacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tAsociacion.setModel(mUsuario);
		scrollPaneAsociacion = new JScrollPane(tAsociacion);
		scrollPaneAsociacion.setPreferredSize(new Dimension(222, 400));
		panelTablas.add(scrollPaneAsociacion);
		
		/**
		 * El panel inferior con todos los botones y sus metodos
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
			
		asociar = new JButton("Asociar");
		asociar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelInferior.add(asociar);
		asociar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				asociar();
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
		borrar = new JButton("Borrar Usuario");
		borrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelInferior.add(borrar);
		borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrarUsuario();
			}
		});
		borrarAsociacion = new JButton("Borrar Asociacion");
		borrarAsociacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelInferior.add(borrarAsociacion);
		borrarAsociacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrarAsociacion();
			}
		});
		rellenarPlacas();
		rellenarTablaUsuario();
		rellenarAsociacion();
	}
	public void asociar()
	{
		String Nombre= (String) tUsuario.getValueAt(tUsuario.getSelectedRow(), 0);
		int placa = Integer.parseInt((String)tPlaca.getValueAt(tPlaca.getSelectedRow(), 0));
		InteraccionDB.asociarUserPlaca(Nombre, placa);
		rellenarAsociacion();
	}
	public void crearUsuario()
	{
		VentanaCrearUsuario nuevo = new VentanaCrearUsuario();
		nuevo.setVisible(true);
		nuevo.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e)
			{
				rellenarTablaUsuario();
			}
		});
	}
	public void atras()
	{
		principal.getPanelcentral().setVisible(true);
		this.setVisible(false);
	}
	public void borrarUsuario()
	{
		String Nombre= (String) tUsuario.getValueAt(tUsuario.getSelectedRow(), 0);
		InteraccionDB.eliminarUser(Nombre);
		rellenarTablaUsuario();
		rellenarAsociacion();
	}
	public void borrarAsociacion()
	{
		String Nombre= (String) tAsociacion.getValueAt(tAsociacion.getSelectedRow(), 0);
		int placa = Integer.parseInt((String)tAsociacion.getValueAt(tAsociacion.getSelectedRow(), 1));
		InteraccionDB.desasociarUserPlaca(Nombre, placa);
		rellenarTablaUsuario();
		rellenarAsociacion();
	}
	public void rellenarTablaUsuario()
	{
		mUsuario=new DefaultTableModel();
		mUsuario.setColumnIdentifiers(new String[]{"Nombre Usuario"});
		LinkedList<Usuario> devolver = InteraccionDB.listadoUsuario();
		for (Usuario u : devolver) {
			mUsuario.addRow(new String[]{u.getUsuario()});
		}
		mUsuario.fireTableDataChanged();
		tUsuario.setModel(mUsuario);
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
	public void rellenarAsociacion()
	{
		mAsociacion=new DefaultTableModel();
		mAsociacion.setColumnIdentifiers(new String[]{"Nombre Usuario", "ID Placa"});	
		LinkedList<Asociacion> devolver = InteraccionDB.getUserPlaca();
		for (Asociacion p : devolver) {
			mAsociacion.addRow(new String[]{p.getUser(), p.getPlaca() + ""});
		}
		mAsociacion.fireTableDataChanged();
		tAsociacion.setModel(mAsociacion);
	}
}
