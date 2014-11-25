package cliente.view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;

import servidor.serverModel.ModelClass.Placa;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaImagen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public VentanaImagen(Placa p) {
		setBounds(100, 100, new ImageIcon(p.getFoto()).getIconWidth(), new ImageIcon(p.getFoto()).getIconHeight()+70);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel Central = new JPanel();
		contentPane.add(Central, BorderLayout.CENTER);
		Central.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		Central.add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel(new ImageIcon(p.getFoto()));
		label.setSize(300, 300);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblIdPlaca = new JLabel("ID Placa:");
		panel_1.add(lblIdPlaca);
		
		JLabel label_1 = new JLabel(""+p.getId());
		panel_1.add(label_1);
	}

}
