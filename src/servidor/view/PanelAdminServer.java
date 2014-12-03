package servidor.view;

import javax.swing.JPanel;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import servidor.mainServidor;
import servidor.serverController.Request;
import util.SocketManager;
import util.Util;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAdminServer extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tUsuario;
	private JSpinner spinnerConexiones;
	private DefaultTableModel mUsuario;
	private JPanel panelPrincipal;
	private JPanel central;
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

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atras();
			}});



		panelPrincipal = new JPanel();
		add(panelPrincipal, "name_116325924696087");
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		mUsuario=new DefaultTableModel();
		mUsuario.setColumnIdentifiers(new String[]{"USERNAME" , "Nº USUARIO", "DIRECCION", "NOMBRE DEL HOST", "PUERTO", "NOMBRE CANONICO"});
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
				cerrarConexion();
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

		SpinnerNumberModel model = new SpinnerNumberModel(Util.getUsuariosMaximos(), 1, Integer.MAX_VALUE, 1);
		spinnerConexiones = new JSpinner(model);
		panelBotonera.add(spinnerConexiones);
		spinnerConexiones.setValue(Util.getUsuariosMaximos());
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
		mUsuario.setColumnIdentifiers(new String[]{"USERNAME" , "Nº USUARIO", "DIRECCION", "NOMBRE DEL HOST", "PUERTO"});
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
	public void desconectarUsuario(int usuario){
		SocketManager temp1 = Util.listaSockets.get(usuario);
		Request temp2 = Util.listaHilos.get(usuario);
		try {
			temp1.CerrarSocket();
			temp1.CerrarStreams();
		} catch (Exception ignored) {

		}
		borrar(temp1, temp2);
	}
	public void cambioMaximoUser(){
		Util.setUsuariosMaximos((Integer) spinnerConexiones.getValue());
		mainServidor.userMax = (Util.listaHilos.size() >= Util.getUsuariosMaximos());
	}
	public void cerrarConexion()
	{
		int rowIndex = tUsuario.getSelectedRow();
		int posicion = Integer.parseInt((String) tUsuario.getValueAt(rowIndex, 1));
		desconectarUsuario(posicion-1);
		rellenarTablaUsuario();
	}
	public void borrar(SocketManager add1, Request add2)
	{
		Util.listaSockets.remove(add1);
		Util.listaHilos.remove(add2);
		actualizarLabel();
		if((Util.listaHilos.size()< Util.getUsuariosMaximos()))
		{
			mainServidor.userMax = false;
		}
	}

}
