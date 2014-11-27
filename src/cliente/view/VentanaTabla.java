package cliente.view;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;

import servidor.serverModel.ModelClass.Placa;
import servidor.serverModel.ModelClass.Sensor;
import cliente.view.componentes.*;
import util.Util;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
public class VentanaTabla extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JButton btnImagen;
    private JButton btnImagenVar;
	private JButton btnActivar;
	private JButton btnDesactivar;
	private JTextField textVariable;
	private JComboBox<String> comboBoxBusqueda;
	private JButton btnBuscar;
	private JButton btnListar;
	private JButton btnActuar;
	private ModeloSensor modeloTabla;
	private JTable tabla;
	private int ultimaCarga = 0;
    private String respuesta;

	public VentanaTabla() {
		/*
		 * Rellenar el array list De TodasPlacas
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel BotonesArriba = new JPanel();
		contentPane.add(BotonesArriba, BorderLayout.EAST);
	    BotonesArriba.setLayout(new GridLayout(14, 1, 0, 0));
	    btnActivar = new JButton("Activar");
	    BotonesArriba.add(btnActivar);
	    btnActivar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		activar();
	    	}
	    });
	    
	    btnDesactivar = new JButton("Desactivar");
	    btnDesactivar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		desactivar();
	    	}
	    });
	    BotonesArriba.add(btnDesactivar);
			
	    btnImagen = new JButton("Imagen Placa");
	    btnImagen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerFoto();
			}
		});
		BotonesArriba.add(btnImagen);
	    btnImagenVar = new JButton("Imagen Variable");
	    btnImagenVar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerFotoVariable();
			}
		});
		BotonesArriba.add(btnImagenVar);
		btnActuar = new JButton("Actuar");
		BotonesArriba.add(btnActuar);
		
		JPanel BotonesAbajo = new JPanel();
		contentPane.add(BotonesAbajo, BorderLayout.SOUTH);
		BotonesAbajo.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel Busqueda = new JPanel();
		BotonesAbajo.add(Busqueda);
		Busqueda.setLayout(new GridLayout(0, 3, 0, 0));

		textVariable = new JTextField();
		Busqueda.add(textVariable);
		textVariable.setColumns(10);

		comboBoxBusqueda = new JComboBox<String>();
		comboBoxBusqueda.setModel(new DefaultComboBoxModel<String>(new String[] {"Placa", "Variable", "Estado", "Funcion", "Accion"}));
		comboBoxBusqueda.setToolTipText("Elegir opcion\r\n");
		Busqueda.add(comboBoxBusqueda);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar();
			}
		});

		Busqueda.add(btnBuscar);

		JPanel Listar = new JPanel();
		BotonesAbajo.add(Listar);
		Listar.setLayout(new GridLayout(0, 2, 0, 0));

		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listar();
			}
		});
		Listar.add(btnListar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}
		});
		btnActuar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actuar();
				
			}
		});
		Listar.add(btnSalir);
		modeloTabla=new ModeloSensor();
		tabla=new JTable(modeloTabla);
	    tabla.setCellSelectionEnabled(true);
	    tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scroll = new JScrollPane(tabla);
		contentPane.add(scroll, BorderLayout.CENTER);
	}
	public  void buscar(){
		try{
			Util.claseSocketCliente.Escribir("BUSCAR "+ comboBoxBusqueda.getSelectedItem().toString() + " " + textVariable.getText() + "\n");
			respuesta = Util.claseSocketCliente.Leer().replace("/n", "\n");
			LinkedList<Sensor> sensores = convertirASensor(sacarRespuesta(respuesta));
			for(Sensor temp: sensores){
				modeloTabla.addRow(temp);
			}
			modeloTabla.fireTableDataChanged();
			tabla.setModel(modeloTabla);
			ultimaCarga = 1;
			repaint();
		    tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		} catch(IOException e){
			JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE); 
		}
	}
	public void salir(){
		try {
			Util.claseSocketCliente.Escribir("SALIR\n");
			respuesta = Util.claseSocketCliente.Leer();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE); 
		}
		dispose();
	}
	public void activar(){
		int rowIndex = tabla.getSelectedRow();
		Sensor seleccionado;
		try
		{
			seleccionado = new Sensor(Integer.parseInt((String) tabla.getValueAt(rowIndex, 0)), (String)tabla.getValueAt(rowIndex, 2), (String)tabla.getValueAt(rowIndex, 4), (String)tabla.getValueAt(rowIndex, 3), (String)tabla.getValueAt(rowIndex, 5), Integer.parseInt((String) tabla.getValueAt(rowIndex, 1)));
		}
		catch(ArrayIndexOutOfBoundsException E)
		{
			seleccionado = new Sensor();
		}
		try
		{
			Util.claseSocketCliente.Escribir("ON " + seleccionado.getId_Placa() + " " + seleccionado.getVariable()+"\n");
			respuesta = Util.claseSocketCliente.Leer();
			System.out.println(respuesta);
			if(respuesta.contains("ERR"))
				JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE);
			else
			{
				recargarTabla();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE); 
		}
		recargarTabla();
	}
	public void desactivar(){
		int rowIndex = tabla.getSelectedRow();
		Sensor seleccionado;
		try
		{
			seleccionado = new Sensor(Integer.parseInt((String) tabla.getValueAt(rowIndex, 0)), (String)tabla.getValueAt(rowIndex, 2), (String)tabla.getValueAt(rowIndex, 4), (String)tabla.getValueAt(rowIndex, 3), (String)tabla.getValueAt(rowIndex, 5), Integer.parseInt((String) tabla.getValueAt(rowIndex, 1)));
		}
		catch(ArrayIndexOutOfBoundsException E)
		{
			seleccionado = new Sensor();
		}
		try
		{
			Util.claseSocketCliente.Escribir("OFF " + seleccionado.getId_Placa() + " " + seleccionado.getVariable()+"\n");
			respuesta = Util.claseSocketCliente.Leer();
			System.out.println(respuesta);
			if(respuesta.contains("ERR"))
				JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE);
			else
			{
				recargarTabla();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE); 
		}
	}
	public void actuar(){
		int rowIndex = tabla.getSelectedRow();
		Sensor seleccionado;
		try
		{
			seleccionado = new Sensor(Integer.parseInt((String) tabla.getValueAt(rowIndex, 0)), (String)tabla.getValueAt(rowIndex, 2), (String)tabla.getValueAt(rowIndex, 4), (String)tabla.getValueAt(rowIndex, 3), (String)tabla.getValueAt(rowIndex, 5), Integer.parseInt((String) tabla.getValueAt(rowIndex, 1)));
		}
		catch(ArrayIndexOutOfBoundsException E)
		{
			seleccionado = new Sensor();
		}
		VentanaElegirAccion nuevaVentana = new VentanaElegirAccion(seleccionado, this);
		nuevaVentana.setVisible(true);
	}
	public void listar(){
		try {
			Util.claseSocketCliente.Escribir("LISTADO\n");
			respuesta = Util.claseSocketCliente.Leer().replace("/n", "\n");
			LinkedList<Sensor> sensores = convertirASensor(sacarRespuesta(respuesta));
			for(Sensor temp: sensores){
				modeloTabla.addRow(temp);
			}
			modeloTabla.fireTableDataChanged();
			tabla.setModel(modeloTabla);
			ultimaCarga = 1;
			repaint();
		    tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE); 
		}

	}
	private LinkedList<String> sacarRespuesta(String respuesta)
	{
		LinkedList<String>sacadas = new LinkedList<String>();
		modeloTabla = new ModeloSensor();
		String temporal = respuesta.substring(0, respuesta.indexOf("\n"));
		respuesta = respuesta.substring(respuesta.indexOf("\n")+1);

		while(respuesta.contains("\n"))
		{
			sacadas.add(temporal);
			temporal = respuesta.substring(0, respuesta.indexOf("\n"));
			respuesta = respuesta.substring(respuesta.indexOf("\n")+1);
		}
		return sacadas;
	}
	private LinkedList<Sensor> convertirASensor(LinkedList<String> temp)
	{
		LinkedList<Sensor> devolver = new LinkedList<Sensor>();
		
		for(String tratar: temp)
		{
			System.out.println(tratar);
			Sensor nuevo = new Sensor();
			nuevo.setId_Placa(Character.getNumericValue(tratar.charAt(11)));
			nuevo.setId_sensor(Character.getNumericValue(tratar.charAt(4)));
			nuevo.setUltimaAccion(tratar.substring(tratar.lastIndexOf(";")+2));
			nuevo.setVariable(tratar.substring(14, tratar.indexOf(";", 14)));
			nuevo.setFuncionPrincipal(tratar.substring(tratar.indexOf(";", tratar.indexOf(";", 14))+2,tratar.indexOf(";", tratar.indexOf(";", 14)+2)));
			nuevo.setEstadoVariable(tratar.substring(tratar.lastIndexOf(";", tratar.lastIndexOf(";")-1)+2,tratar.lastIndexOf(";")));
			devolver.add(nuevo);
		}
		return devolver;
	}
	public void obtenerFoto()
	{
		int rowIndex = tabla.getSelectedRow();
		Sensor seleccionado;
		try {
			seleccionado = new Sensor(Integer.parseInt((String) tabla.getValueAt(rowIndex, 0)), (String)tabla.getValueAt(rowIndex, 2), (String)tabla.getValueAt(rowIndex, 4), (String)tabla.getValueAt(rowIndex, 3), (String)tabla.getValueAt(rowIndex, 5), Integer.parseInt((String) tabla.getValueAt(rowIndex, 1)));
		}
		catch(ArrayIndexOutOfBoundsException E)
		{
			seleccionado = new Sensor();
		}
		try {
			Util.claseSocketCliente.Escribir("OBTENER_FOTO Placa" + seleccionado.getId_Placa()+"\n");
			respuesta = Util.claseSocketCliente.Leer();

			if(respuesta.contains("ERR"))
				JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE);
			else
			{
				byte[] imagen = Util.claseSocketCliente.readBytes();
				Image temp = ImageIO.read(new ByteArrayInputStream(imagen));
				Placa visualizar = new Placa(seleccionado.getId_Placa(), "", temp);
				VentanaImagen ventana = new VentanaImagen(visualizar);
				ventana.setVisible(true);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE); 
		}
	}
	public void obtenerFotoVariable()
	{
		int rowIndex = tabla.getSelectedRow();
		Sensor seleccionado;
		try {
			seleccionado = new Sensor(Integer.parseInt((String) tabla.getValueAt(rowIndex, 0)), (String)tabla.getValueAt(rowIndex, 2), (String)tabla.getValueAt(rowIndex, 4), (String)tabla.getValueAt(rowIndex, 3), (String)tabla.getValueAt(rowIndex, 5), Integer.parseInt((String) tabla.getValueAt(rowIndex, 1)));
		}
		catch(ArrayIndexOutOfBoundsException E)
		{
			seleccionado = new Sensor();
		}
		try {
			Util.claseSocketCliente.Escribir("OBTENER_FOTO Variable" + seleccionado.getVariable()+"\n");
			respuesta = Util.claseSocketCliente.Leer();
			System.out.println(respuesta);
			if(respuesta.contains("ERR"))
				JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE);
			else
			{
				byte[] imagen = Util.claseSocketCliente.readBytes();
				Image temp = ImageIO.read(new ByteArrayInputStream(imagen));
				VentanaImagenVariable ventana = new VentanaImagenVariable(temp, seleccionado.getVariable());
				ventana.setVisible(true);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE); 
		}

	}
	/**
	 * Permite recargar la tabla teniendo en cuenta el último patron con el que se ha cargado, para así actualizarlo con el
	 */
	public void recargarTabla()
	{
		if(ultimaCarga==1)
			listar();
	}
}
