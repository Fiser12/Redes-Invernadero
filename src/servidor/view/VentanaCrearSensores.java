package servidor.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import servidor.serverModel.InteraccionDB;
import util.excepciones.RepetElement;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class VentanaCrearSensores extends JDialog{
	private Image image;
	private JButton btnInsertar;
	private static final long serialVersionUID = 1L;
	private JTextField tFFuncionPrincipal;
	private JTextField tFUltimaAccion;
	private JCheckBox chckbxNewCheckBox;
	private JComboBox<String> comboBoxVariable;
	public VentanaCrearSensores()
	{
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		
		JPanel panelNombre = new JPanel();
		panelCentral.add(panelNombre);
		
		JLabel lblFuncionPrincipal = new JLabel("Funcion Principal");
		panelNombre.add(lblFuncionPrincipal);
		
		tFFuncionPrincipal = new JTextField();
		panelNombre.add(tFFuncionPrincipal);
		tFFuncionPrincipal.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panelCentral.add(panel_1);
		
		JLabel lblVariable = new JLabel("Variable");
		panel_1.add(lblVariable);
		
		comboBoxVariable = new JComboBox<String>();
		comboBoxVariable.setModel(new DefaultComboBoxModel<String>(rellenarCombobox()));
		comboBoxVariable.setBounds(80, 164, 157, 20);
		panel_1.add(comboBoxVariable);
		
		chckbxNewCheckBox = new JCheckBox("Estado");
		panel_1.add(chckbxNewCheckBox);
		
		JButton btnImagen = new JButton("Imagen");
		btnImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sacarFoto();
			}
		});
		panel_1.add(btnImagen);
		
		JPanel panel = new JPanel();
		panelCentral.add(panel);
		
		JLabel lblUltimaAccion = new JLabel("Ultima Accion");
		panel.add(lblUltimaAccion);
		
		tFUltimaAccion = new JTextField();
		panel.add(tFUltimaAccion);
		tFUltimaAccion.setColumns(10);

		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior, BorderLayout.SOUTH);

		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertar();
			}
		});
		panelInferior.add(btnInsertar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		panelInferior.add(btnCancelar);
		
	}
	public void insertar()
	{
	    try {
	    	String funcion = tFFuncionPrincipal.getSelectedText();
	    	String variable = (String)comboBoxVariable.getSelectedItem();
	    	boolean estado = chckbxNewCheckBox.isSelected();
	    	String accion = tFUltimaAccion.getSelectedText();
	    	InteraccionDB.insertarSensor(funcion, variable, accion, estado, image);
			dispose();
	    }catch(RepetElement E){
	    	JOptionPane.showMessageDialog(null,"El Usuario ya esta insertado","Error",JOptionPane.ERROR_MESSAGE);
	    }
	}
	public void sacarFoto()
	{
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String sname = file.getName();
            image = new ImageIcon(sname).getImage();
            btnInsertar.setEnabled(true);  
        }
	}
	public void cancelar()
	{
		dispose();
	}
	public String[]rellenarCombobox(){
		LinkedList<String> devolver = new LinkedList<String>();
		devolver=InteraccionDB.listadoVariable();
		String[]var=new String[devolver.size()+1];
		var[0]="Variable";
		for(int i=0;i<devolver.size();i++){
			var[i+1]=devolver.get(i);
		}
		return var;
	}
}
