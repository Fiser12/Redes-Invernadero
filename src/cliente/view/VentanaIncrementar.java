package cliente.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

public class VentanaIncrementar extends JFrame {

	private JPanel contentPane;
	
	private JFormattedTextField txIncremento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIncrementar frame = new VentanaIncrementar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaIncrementar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblParametro = new JLabel("Indique el incremento o decremento de la variable");
		panel_1.add(lblParametro);
		
		JPanel incremento = new JPanel();
		panel.add(incremento);
		incremento.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		incremento.add(panel_4);
		
		JLabel lblNewLabel = new JLabel("Incremento");
		panel_4.add(lblNewLabel);
		
		JPanel panel_5 = new JPanel();
		incremento.add(panel_5);
		
		
		
		
		NumberFormat format = NumberFormat.getInstance();
		 NumberFormatter formatter = new NumberFormatter(format);
		 formatter.setValueClass(Integer.class);
		 txIncremento = new JFormattedTextField(formatter);
		 txIncremento.setColumns(10);
		 formatter.setMinimum(0);
		 formatter.setMaximum(Integer.MAX_VALUE);
		 // If you want the value to be committed on each keystroke instead of focus lost
		 formatter.setCommitsOnValidEdit(true);
		 panel_5.add(txIncremento);
		JPanel botones = new JPanel();
		panel.add(botones);
		botones.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		botones.add(panel_2);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_2.add(btnContinuar);
		
		JPanel panel_3 = new JPanel();
		botones.add(panel_3);
		
		JButton btnRegresar = new JButton("Regresar");
		panel_3.add(btnRegresar);
	}

}
