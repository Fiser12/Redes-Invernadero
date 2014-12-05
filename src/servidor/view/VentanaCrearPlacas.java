package servidor.view;

import servidor.serverModel.InteraccionDB;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaCrearPlacas extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JButton btnInsertar;
	private Image image;
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

	void insertar()
	{
		InteraccionDB.insertarPlaca(image);
		dispose();
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
		dispose();
	}
}