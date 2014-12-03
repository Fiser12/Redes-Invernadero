package servidor.view;

import servidor.serverModel.InteraccionDB;
import servidor.serverModel.ModelClass.Placa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class PanelPlacas extends JPanel {
	private static final long serialVersionUID = 1L;

	JPanel panelInferior;
	JPanel panelCentral;
	JButton btnAtras;
	JButton btnAnadir;
	JButton borrarPlaca;
	JScrollPane scrollPane;
	JTable tPlaca;
	DefaultTableModel mPlaca;
	VentanaPrincipal principal;
	public PanelPlacas(VentanaPrincipal principal)
	{
		this.principal = principal;
		mPlaca=new DefaultTableModel();
		mPlaca.setColumnIdentifiers(new String[]{"ID Placa"});
		panelCentral = new JPanel();

		setLayout(new BorderLayout(0, 0));

		panelInferior = new JPanel();
		panelInferior.setLayout(new GridLayout(1, 1, 0, 0));
		add(panelInferior, BorderLayout.SOUTH);
		add(panelCentral, BorderLayout.CENTER);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				atras();
			}
		});
		panelInferior.add(btnAtras);

		tPlaca=new JTable(mPlaca);
		tPlaca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tPlaca.setModel(mPlaca);
		tPlaca.getTableHeader().setReorderingAllowed(false);
		scrollPane = new JScrollPane(tPlaca);
		borrarPlaca = new JButton("Borrar placa");
		borrarPlaca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		borrarPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrar();
			}
		});
		panelInferior.add(borrarPlaca);

		btnAnadir = new JButton("Anadir placa");
		btnAnadir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadir();
			}
		});
		panelInferior.add(btnAnadir);

		panelCentral.setLayout(new BorderLayout(0, 0));
		panelCentral.add(scrollPane, BorderLayout.CENTER);
		rellenarPlacas();
	}
	public void atras()
	{
		principal.getPanelcentral().setVisible(true);
		this.setVisible(false);
	}
	public void borrar()
	{
		int id=Integer.parseInt((String) tPlaca.getValueAt(tPlaca.getSelectedRow(), 0));
		InteraccionDB.eliminarPlaca(id);
		rellenarPlacas();
		principal.getpSensores().rellenarPlacas();
		principal.getpSensores().rellenarTablaSensor();
		principal.getpUsuarios().rellenarPlacas();
		principal.getpUsuarios().rellenarAsociacion();
	}
	public void anadir()
	{
		VentanaCrearPlacas nueva = new VentanaCrearPlacas();
		nueva.setVisible(true);
		nueva.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				rellenarPlacas();
				principal.getpSensores().rellenarPlacas();
				principal.getpSensores().rellenarTablaSensor();
				principal.getpUsuarios().rellenarPlacas();
				principal.getpUsuarios().rellenarAsociacion();
			}
		});
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
		tPlaca.setModel(mPlaca);
	}
}