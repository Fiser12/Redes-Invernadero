package cliente.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import servidor.serverModel.ModelClass.Placa;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VetanaImagen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public VetanaImagen(Placa p,boolean color) {
		/*
		 * Si es en blanco y negro false si
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel Central = new JPanel();
		contentPane.add(Central, BorderLayout.CENTER);
		Central.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel Atributos = new JPanel();
		Central.add(Atributos);
		Atributos.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel Id = new JPanel();
		Atributos.add(Id);
		Id.setLayout(new BorderLayout(0, 0));
		
		JLabel lblestado = new JLabel("ID Placa"+p.getId());
		Id.add(lblestado);
		lblestado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JPanel Estado = new JPanel();
		Atributos.add(Estado);
		Estado.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel(p.getEstado()+"   Estado");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Estado.add(lblNewLabel);
		
		JPanel Boton = new JPanel();
		Atributos.add(Boton);
		Boton.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			VentanaTabla nueva = new VentanaTabla();
			nueva.setVisible(true);
			dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		Boton.add(btnNewButton);
		
		JPanel imagen = new JPanel();
		Central.add(imagen);
		imagen.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblimagen = new JLabel("New label");
		imagen.add(lblimagen);
	}

}
