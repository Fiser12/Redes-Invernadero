package servidor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import servidor.serverModel.InteraccionDB;
import util.excepciones.RepetElement;

public class VentanaCrearVariables extends JDialog{
	private JTextField textFieldVariable;
	private Image image;
	private JButton btnInsertar;
	private static final long serialVersionUID = 1L;
	public VentanaCrearVariables()
	{
	    setSize(new Dimension(250,135));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		
		JPanel panelNombre = new JPanel();
		panelCentral.add(panelNombre);
		
		JLabel lblVariable = new JLabel("Variable:");
		panelNombre.add(lblVariable);
		
		textFieldVariable = new JTextField();
		panelNombre.add(textFieldVariable);
		textFieldVariable.setColumns(10);
		
		JPanel panelContraseña = new JPanel();
		panelCentral.add(panelContraseña);
		
		JButton btnSeleccionarFoto = new JButton("Seleccionar Foto");
		btnSeleccionarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sacarFoto();
			}
		});
		panelContraseña.add(btnSeleccionarFoto);

		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior, BorderLayout.SOUTH);

		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertar();
			}
		});
        btnInsertar.setEnabled(false);
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
		String var = textFieldVariable.getText();
	    try {
	    	InteraccionDB.insertarVariable(var, image);
	    }catch(RepetElement E){
	    	JOptionPane.showMessageDialog(null,"El Usuario ya esta insertado","Error",JOptionPane.ERROR_MESSAGE);
	    }
		dispose();
	}
	public void sacarFoto()
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
	public void cancelar()
	{
		dispose();
	}
}