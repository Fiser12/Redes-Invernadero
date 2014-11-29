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
import servidor.serverModel.ModelClass.Placa;

public class PanelPlacas extends JPanel {
	private static final long serialVersionUID = 1L;

	JPanel panelInferior;
	JPanel panelCentral;
	JButton btnAtras;
	JButton btnAñadir;
	JButton borrarPlaca;
	JScrollPane scrollPane;
	JTable tPlaca;
	DefaultTableModel mPlaca;
	JPanel central;
	public PanelPlacas(JPanel central)
	{
		this.central = central;
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

		btnAñadir = new JButton("Añadir placa");
		btnAñadir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadir();
			}
		});
		panelInferior.add(btnAñadir);

		panelCentral.setLayout(new BorderLayout(0, 0));
		panelCentral.add(scrollPane, BorderLayout.CENTER);
		rellenarPlacas();
	}
	public void atras()
	{
		central.setVisible(true);
		this.setVisible(false);
	}
	public void borrar()
	{
		int id=Integer.parseInt((String) tPlaca.getValueAt(tPlaca.getSelectedRow(), 0));
		InteraccionDB.eliminarPlaca(id);
		rellenarPlacas();
	}
	public void añadir()
	{
		VentanaCrearPlacas nueva = new VentanaCrearPlacas();
		nueva.setVisible(true);
		nueva.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e)
			{
				rellenarPlacas();
			}
		});
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
