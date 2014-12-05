package servidor.view;

import servidor.mainServidor;
import servidor.serverController.Request;
import util.SocketManager;
import util.Util;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdminServer extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JTable tUsuario;
	private final JSpinner spinnerConexiones;
	private final VentanaPrincipal central;
	private final JLabel lblNewLabel;
	private DefaultTableModel mUsuario;
	
	public PanelAdminServer(VentanaPrincipal central)
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


		JPanel panelPrincipal = new JPanel();
		add(panelPrincipal, "name_116325924696087");
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		mUsuario=new DefaultTableModel();
		mUsuario.setColumnIdentifiers(new String[]{"USERNAME" , "Nº USUARIO", "DIRECCION", "NOMBRE DEL HOST", "PUERTO", "NOMBRE CANONICO"});
		tUsuario=new JTable(mUsuario);
		tUsuario.getTableHeader().setReorderingAllowed(false);
		tUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tUsuario.setModel(mUsuario);
		JScrollPane scrollPaneTablaconetados = new JScrollPane(tUsuario);
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

	private static String[] obtenerUsuario(int usuario) {
		String[] devolver = new String[6];
		String usuarioRegistrado = Util.listaHilos.get(usuario).getUsuario();
		if (usuarioRegistrado == null)
			usuarioRegistrado = "DESCONECTADO";

		devolver[0] = usuarioRegistrado;
		devolver[1] = (usuario + 1) + "";
		devolver[2] = Util.listaSockets.get(usuario).getMySocket().getInetAddress().getHostAddress();
		devolver[3] = Util.listaSockets.get(usuario).getMySocket().getInetAddress().getHostName();
		devolver[4] = Util.listaSockets.get(usuario).getMySocket().getPort() + "";
		return devolver;
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

	void atras()
	{
		central.getPanelcentral().setVisible(true);
		this.setVisible(false);
	}

	void desconectarUsuario(int usuario) {
		SocketManager temp1 = Util.listaSockets.get(usuario);
		Request temp2 = Util.listaHilos.get(usuario);
		try {
			temp1.CerrarSocket();
			temp1.CerrarStreams();
		} catch (Exception ignored) {

		}
		borrar(temp1, temp2);
	}

	void cambioMaximoUser() {
		Util.setUsuariosMaximos((Integer) spinnerConexiones.getValue());
		mainServidor.userMax = (Util.listaHilos.size() >= Util.getUsuariosMaximos());
	}

	void cerrarConexion()
	{
		int rowIndex = tUsuario.getSelectedRow();
		int posicion = Integer.parseInt((String) tUsuario.getValueAt(rowIndex, 1));
		desconectarUsuario(posicion-1);
		rellenarTablaUsuario();
	}

	void borrar(SocketManager add1, Request add2)
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
