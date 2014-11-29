package servidor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import servidor.serverModel.InteraccionDB;
import util.excepciones.RepetElement;

public class VentanaCrearPlacas extends JDialog{
	private Image image;
	private JButton btnInsertar;
	private static final long serialVersionUID = 1L;
	public VentanaCrearPlacas()
	{
	    setSize(new Dimension(250,135));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
			
		JPanel panelContrasena = new JPanel();
		getContentPane().add(panelContrasena);
		
		JButton btnSeleccionarFoto = new JButton("Seleccionar Foto");
		btnSeleccionarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sacarFoto();
			}
		});
		panelContrasena.add(btnSeleccionarFoto);

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
	    try {
	    	InteraccionDB.insertarPlaca(image);
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