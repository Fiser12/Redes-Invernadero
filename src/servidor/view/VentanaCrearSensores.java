package servidor.view;

import servidor.serverModel.InteraccionDB;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.LinkedList;

public class VentanaCrearSensores extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JButton btnInsertar;
	private final JTextField tFFuncionPrincipal;
	private final JTextField tFUltimaAccion;
	private final JCheckBox chckbxNewCheckBox;
	private final JComboBox<String> comboBoxVariable;
	private Image image;
	public VentanaCrearSensores()
	{
	    setSize(new Dimension(400,300));
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
		
		final JButton btnImagen = new JButton("Imagen");
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
		btnImagen.setEnabled(false);

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
		comboBoxVariable.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxVariable.getSelectedItem().equals("Imagen"))
					btnImagen.setEnabled(true);
				else
					btnImagen.setEnabled(false);
			}
		});
	}

	void insertar()
	{
		String funcion = tFFuncionPrincipal.getText();
		String variable = (String) comboBoxVariable.getSelectedItem();
		boolean estado = chckbxNewCheckBox.isSelected();
		String accion = tFUltimaAccion.getText();
		InteraccionDB.insertarSensor(funcion, variable, accion, estado, image);
		dispose();
		image = null;
	}

	void sacarFoto()
	{
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
				image = ImageIO.read(fc.getSelectedFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
            btnInsertar.setEnabled(true);  
        }
	}

	void cancelar()
	{
		image = null;
		dispose();
	}

	String[] rellenarCombobox() {
		LinkedList<String> devolver;
		devolver=InteraccionDB.listadoVariable();
		String[]var=new String[devolver.size()];
		for(int i=0;i<devolver.size();i++){
			var[i]=devolver.get(i);
		}
		return var;
	}
}
