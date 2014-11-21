package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class VentanaControl extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaControl frame = new VentanaControl();
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
	public VentanaControl() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panelBotones.add(btnBuscar);
		
		JButton btnClear = new JButton("Clear");
		panelBotones.add(btnClear);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblOpcion1 = new JLabel("Opcion:");
		lblOpcion1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOpcion1.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(lblOpcion1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar opcion", "ON", " OFF ", "ACCION", "OBTENER_FOTO"}));
		comboBox.setToolTipText("");
		panelCentral.add(comboBox);
		
		JLabel labelOpcion = new JLabel("Opcion: ");
		labelOpcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelOpcion.setHorizontalAlignment(SwingConstants.CENTER);
		panelCentral.add(labelOpcion);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		panelCentral.add(textField);
		textField.setColumns(10);
	}
}
