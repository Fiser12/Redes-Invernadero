package servidor.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import servidor.serverModel.InteraccionDB;
import servidor.serverModel.ModelClass.Usuario;

public class PanelUsuarios extends JPanel{

	private static final long serialVersionUID = 1L;
	
	JPanel panelCentral;
	JPanel panelInferior;
	JPanel panelTablas;
	JButton atras;
	JButton asociar;
	JButton crear;
	JButton borrar;
	JTable tPlaca;
	JTable tUsuario;
	DefaultTableModel mPlaca;
	DefaultTableModel mUsuario;
	JPanel central;
	
	JScrollPane scrollPanePlaca;
	JScrollPane scrollPaneUsuario;

	public PanelUsuarios(JPanel central)
	{
		this.central = central;
		this.setLayout(new BorderLayout());
		panelInferior = new JPanel();
		panelCentral = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		add(panelCentral, BorderLayout.CENTER);
		panelTablas = new JPanel();
		panelCentral.add(panelTablas);
		panelTablas.setLayout(new GridLayout(0, 2, 0, 0));
		
		mPlaca=new DefaultTableModel();
		mPlaca.setColumnIdentifiers(new String[]{"ID Placa"});
		tPlaca=new JTable(mPlaca);
		tPlaca.setRowSelectionAllowed(false);
		tPlaca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tPlaca.setModel(mPlaca);
		tPlaca.getTableHeader().setReorderingAllowed(false);
		scrollPanePlaca = new JScrollPane(tPlaca);
		panelTablas.add(scrollPanePlaca);

		mUsuario=new DefaultTableModel();
		mUsuario.setColumnIdentifiers(new String[]{"Nombre Usuario"});
		tUsuario=new JTable(mUsuario);
		tUsuario.getTableHeader().setReorderingAllowed(false);
		tUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tUsuario.setModel(mUsuario);
		scrollPaneUsuario = new JScrollPane(tUsuario);
		panelTablas.add(scrollPaneUsuario);
		
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
		borrar = new JButton("Borrar");
		borrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelInferior.add(borrar);
		borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrarUsuario();
			}
		});
		rellenarTablaUsuario();
	}
	public void asociar()
	{

	}
	public void crearUsuario()
	{

	}
	public void atras()
	{
		central.setVisible(true);
		this.setVisible(false);
	}
	public void borrarUsuario()
	{
		String Nombre= (String) tUsuario.getValueAt(tUsuario.getSelectedRow(), 0);
		InteraccionDB.eliminarUser(Nombre);
		rellenarTablaUsuario();
	}
	public void rellenarTablaUsuario()
	{
		mUsuario=new DefaultTableModel();
		mUsuario.setColumnIdentifiers(new String[]{"Nombre Usuario"});
		LinkedList<Usuario> devolver = new LinkedList<Usuario>();
		devolver=InteraccionDB.listadoUsuario();
		for(int i=0;i<devolver.size();i++)		
		{
			Usuario u= devolver.get(i);
			mUsuario.addRow(new String[]{u.getUsuario()});	
		}
		mUsuario.fireTableDataChanged();
		tUsuario.setModel(mUsuario);
		tUsuario.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

}
