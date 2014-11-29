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

public class PanelVariables extends JPanel{

	private static final long serialVersionUID = 1L;
	JPanel panelInferior;
	JPanel panelCentral;
	JButton btnAtras;
	JButton btnAñadir;
	JButton borrar;
	JScrollPane scrollPane;
	JTable tVariable;
	DefaultTableModel mVariable;
	JPanel central;
	public PanelVariables(JPanel central)
	{
		this.central = central;
		mVariable=new DefaultTableModel();
		mVariable.setColumnIdentifiers(new String[]{"Nombre"});
		
		setLayout(new BorderLayout(0, 0));

		panelInferior = new JPanel();
		panelCentral = new JPanel();

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
		
		tVariable=new JTable(mVariable);
		tVariable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tVariable.setModel(mVariable);
		tVariable.getTableHeader().setReorderingAllowed(false);
		scrollPane = new JScrollPane(tVariable);
		borrar = new JButton("Borrar placa");
		borrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrar();
			}
		});
		panelInferior.add(borrar);
		
		btnAñadir = new JButton("Añadir variable");
		btnAñadir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadir();
			}
		});
		panelInferior.add(btnAñadir);

		panelCentral.setLayout(new BorderLayout(0, 0));
		panelCentral.add(scrollPane, BorderLayout.CENTER);
		rellenarVariables();
	}
	public void atras()
	{
		central.setVisible(true);
		this.setVisible(false);
	}
	public void borrar()
	{
		String Nombre= (String) tVariable.getValueAt(tVariable.getSelectedRow(), 0);
		InteraccionDB.eliminarVariable(Nombre);
		rellenarVariables();
	}
	public void añadir()
	{
		VentanaCrearVariables nueva = new VentanaCrearVariables();
		nueva.setVisible(true);
		nueva.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e)
			{
				rellenarVariables();
			}
		});
	}
	public void rellenarVariables(){
		LinkedList<String> devolver = new LinkedList<String>();
		devolver=InteraccionDB.listadoVariable();
		mVariable=new DefaultTableModel();
		mVariable.setColumnIdentifiers(new String[]{"Nombre Variable"});
		for(int i=0;i<devolver.size();i++){
		
			mVariable.addRow(new String[]{	devolver.get(i)});
		}
		mVariable.fireTableDataChanged();
		tVariable.setModel(mVariable);
	}
}
