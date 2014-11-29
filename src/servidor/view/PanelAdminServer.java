package servidor.view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.JTextField;

import java.awt.CardLayout;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import servidor.serverModel.InteraccionDB;
import servidor.serverModel.ModelClass.Usuario;
import util.Util;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.LinkedList;

public class PanelAdminServer extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tUsuario;
	private JTextField textFieldNConexiones;
	private DefaultTableModel mUsuario;
	private JPanel  panelCentral;
	private JPanel panelCerrar;
	JPanel central;
	
	public PanelAdminServer(JPanel central)
	{
		this.central = central;
		setLayout(new CardLayout(0, 0));
		panelCentral = new JPanel();
		add(panelCentral, "name_116325883497167");
		panelCentral.setLayout(new BorderLayout(0, 0));

		tUsuario=new JTable(mUsuario);
		tUsuario.getTableHeader().setReorderingAllowed(false);
		tUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneTablaconetados = new JScrollPane(tUsuario);
		panelCentral.add(scrollPaneTablaconetados);

		JLabel lblUsuariosconectados = new JLabel("Conexiones actuales:");
		lblUsuariosconectados.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelCentral.add(lblUsuariosconectados, BorderLayout.NORTH);

		JPanel panelOpciones = new JPanel();
		panelCentral.add(panelOpciones, BorderLayout.SOUTH);
		panelOpciones.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panelCerrarconexion = new JPanel();
		panelOpciones.add(panelCerrarconexion);

		JButton btnCerrarConexion = new JButton("Cerrar conexion");
		panelCerrarconexion.add(btnCerrarConexion);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atras();
			}});
		panelCerrarconexion.add(btnAtras);
		btnCerrarConexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int seleccion=JOptionPane.showOptionDialog(null,"Esta Seguro de que desea cerrar la conexion con "+tUsuario.getValueAt(tUsuario.getSelectedRow(), 0),"Cierre de conexion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, "Si");
				if(seleccion==0){
					/*
					 * cerrar la conexion con el Usuario
					 */
				}
			}
		});

		JPanel panelAConexines = new JPanel();
		panelOpciones.add(panelAConexines);

		JLabel lblConexiones = new JLabel("Conexiones Actuales");
		panelAConexines.add(lblConexiones);

		textFieldNConexiones = new JTextField();
		panelAConexines.add(textFieldNConexiones);
		textFieldNConexiones.setColumns(10);
		textFieldNConexiones.setText(""+Util.usuariosMaximos);
		JButton btnConexiones = new JButton("Actualizar conexiones");
		btnConexiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				NumberFormat format = NumberFormat.getInstance();
				NumberFormatter formatter = new NumberFormatter(format);
				formatter.setValueClass(Integer.class);
				formatter.setMinimum(0);
				formatter.setMaximum(10);

				formatter.setCommitsOnValidEdit(true);
				textFieldNConexiones = new JFormattedTextField(formatter);
				String str=textFieldNConexiones.getText() ;
				int numeroU;
				numeroU=Integer.parseInt(str);
				if(Util.usuariosMaximos<numeroU) {	

					panelCentral.setVisible(false);
					panelCerrar.setVisible(true);
				}
				else{
					int seleccion=JOptionPane.showOptionDialog(null,"Esta Seguro de que desea reducir las conexiones a "+numeroU ,"Cierre de conexion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, "Si");
					if(seleccion==0){
						Util.cambiarNUsuarios(numeroU);
					}}
			}
		});
		panelAConexines.add(btnConexiones);

		panelCerrar = new JPanel();
		add(panelCerrar, "name_116325924696087");
		panelCerrar.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneTabla = new JScrollPane(tUsuario);
		panelCerrar.add(scrollPaneTabla);

		JPanel panelBotonera = new JPanel();
		panelCerrar.add(panelBotonera, BorderLayout.SOUTH);

		JButton btnCerrar = new JButton("Cerrar conexion");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion=JOptionPane.showOptionDialog(null,"Esta Seguro de que desea cerrar la conexion con "+tUsuario.getValueAt(tUsuario.getSelectedRow(), 0),"Cierre de conexion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, "Si");
				if(seleccion==0){
					/*
					 * cerrar la conexion con el Usuario
					 */
				}
			}
		});
		panelBotonera.add(btnCerrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCerrar.setVisible(false);
				panelCentral.setVisible(true);
			}
		});
		panelBotonera.add(btnCancelar);

		JLabel lblNewLabel = new JLabel("Seleccione el Usuario a cerrar conexion ");
		panelCerrar.add(lblNewLabel, BorderLayout.NORTH);

	}
	public void rellenarTablaUsuario()
	{
		mUsuario=new DefaultTableModel();
		mUsuario.setColumnIdentifiers(new String[]{"Nombre Usuario"});
		LinkedList<Usuario> devolver = new LinkedList<Usuario>();
		devolver=InteraccionDB.listadoUsuario();
		for(int i=0;i<devolver.size();i++)		
		{
			/*
			 *if(u.get) Los que estan conectados
			 */

			Usuario u= devolver.get(i);
			mUsuario.addRow(new String[]{u.getUsuario()});	
		}
		mUsuario.fireTableDataChanged();
		tUsuario.setModel(mUsuario);
		tUsuario.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
	public void atras()
	{
		central.setVisible(true);
		this.setVisible(false);
	}
}
