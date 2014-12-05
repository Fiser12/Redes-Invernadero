package cliente.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaImagenVariable extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaImagenVariable(Image i, String variable) {
		setBounds(100, 100, new ImageIcon(i).getIconWidth(), new ImageIcon(i).getIconHeight()+70);
		JPanel contentPane = new JPanel();
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
		
		JLabel lblIdPlaca = new JLabel("Variable:");
		panel_1.add(lblIdPlaca);
		
		JLabel label_1 = new JLabel(""+variable);
		panel_1.add(label_1);
	}

}
