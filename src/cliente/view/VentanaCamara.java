package cliente.view;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class VentanaCamara extends JFrame {

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
	public VentanaCamara(Image i, String texto) {
		setBounds(100, 100, new ImageIcon(i).getIconWidth(), new ImageIcon(i).getIconHeight()+70);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel Central = new JPanel();
		contentPane.add(Central, BorderLayout.CENTER);
		Central.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		Central.add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel(new ImageIcon(i));
		label.setSize(300, 300);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblIdPlaca = new JLabel("Camara de sensor:");
		panel_1.add(lblIdPlaca);
		
		JLabel label_1 = new JLabel(""+texto);
		panel_1.add(label_1);
	}

}
