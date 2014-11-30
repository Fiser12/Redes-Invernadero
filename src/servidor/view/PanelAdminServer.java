package servidor.view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Font;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import util.Util;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class PanelAdminServer extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tUsuario;
	private JSpinner spinnerConexiones;
	private DefaultTableModel mUsuario;
	private JPanel panelPrincipal;
	JPanel central;
	private JScrollPane scrollPaneTablaconetados;
	private JLabel lblNewLabel;
	
	public PanelAdminServer(JPanel central)
	{
		this.central = central;

		JLabel lblUsuariosconectados = new JLabel("Conexiones actuales:");
		lblUsuariosconectados.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JPanel panelOpciones = new JPanel();
		central.add(panelOpciones, BorderLayout.SOUTH);
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


		panelPrincipal = new JPanel();
		add(panelPrincipal, "name_116325924696087");
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		mUsuario=new DefaultTableModel();
		mUsuario.setColumnIdentifiers(new String[]{"USERNAME" , "Nº USUARIO", "DIRECCIÓN", "NOMBRE DEL HOST", "PUERTO", "NOMBRE CANONICO"});
		tUsuario=new JTable(mUsuario);
		tUsuario.getTableHeader().setReorderingAllowed(false);
		tUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tUsuario.setModel(mUsuario);
		scrollPaneTablaconetados = new JScrollPane(tUsuario);
		scrollPaneTablaconetados.setPreferredSize(new Dimension(650, 230));
		panelPrincipal.add(scrollPaneTablaconetados, BorderLayout.CENTER);

		JPanel panelBotonera = new JPanel();
		panelPrincipal.add(panelBotonera, BorderLayout.SOUTH);

		JButton btnCerrar = new JButton("Cerrar conexion");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panelBotonera.add(btnCerrar);

		JButton btnCancelar = new JButton("Atras");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atras();
			}
		});
		panelBotonera.add(btnCancelar);

		JLabel lblConexiones = new JLabel("Conexiones Actuales");
		panelBotonera.add(lblConexiones);

		SpinnerNumberModel model = new SpinnerNumberModel(Util.usuariosMaximos, 1, Integer.MAX_VALUE, 1);
		spinnerConexiones = new JSpinner(model);
		panelBotonera.add(spinnerConexiones);
		spinnerConexiones.setValue(Util.usuariosMaximos);
		spinnerConexiones.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				cambioMaximoUser();
			}
	    });

		
		lblNewLabel = new JLabel("Usuarios Conectados: " + (Util.listaHilos.size()));
		panelPrincipal.add(lblNewLabel, BorderLayout.NORTH);
		rellenarTablaUsuario();
	}
	public void rellenarTablaUsuario()
	{
		mUsuario=new DefaultTableModel();
		mUsuario.setColumnIdentifiers(new String[]{"USERNAME" , "Nº USUARIO", "DIRECCIÓN", "NOMBRE DEL HOST", "PUERTO"});
		for(int i = 0; i<Util.listaHilos.size(); i++)
			mUsuario.addRow(obtenerUsuario(i));
		mUsuario.fireTableDataChanged();
		tUsuario.setModel(mUsuario);
	}
	public void actualizarLabel()
	{
		lblNewLabel.setText("Usuarios Conectados: " + (Util.listaHilos.size()));
	}
	public void atras()
	{
		central.setVisible(true);
		this.setVisible(false);
	}
	public static String[] obtenerUsuario(int usuario)
	{
		String [] devolver = new String[6];
		String usuarioRegistrado = Util.listaHilos.get(usuario).getUsuario();
		if(usuarioRegistrado == null)
			usuarioRegistrado = "DESCONECTADO";

		devolver[0] = usuarioRegistrado;
		devolver[1] = (usuario+1)+"";
		devolver[2] = Util.listaSockets.get(usuario).getMySocket().getInetAddress().getHostAddress();
		devolver[3] = Util.listaSockets.get(usuario).getMySocket().getInetAddress().getHostName();
		devolver[4] = Util.listaSockets.get(usuario).getMySocket().getPort()+"";
		return devolver;
	}
	public static void desconectarUsuario(int usuario){
		try
		{
			Util.listaHilos.get(usuario).salir();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void cambioMaximoUser(){
		Util.usuariosMaximos = new Integer((Integer)spinnerConexiones.getValue()).intValue();
		System.out.println(Util.usuariosMaximos);
		if((Util.listaHilos.size()<Util.usuariosMaximos))
		{
			System.out.println(Util.listaHilos.size() + "<REST" + Util.usuariosMaximos);
			VentanaPrincipal.userMax = false;
		}
		else
			VentanaPrincipal.userMax = true;
	}
	
}
