package cliente.view;

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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;

import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import util.SocketManager;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

public class VentanaControl  extends JFrame implements FocusListener {

	private JPanel contentPane;
	private JTextField tFVariable;
    private JButton btnBuscar;
	private JButton btnClear;
    private JComboBox comboBoxOpciones;
    private SocketManager sm ;
    private String respuesta;
    
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
		try {
			sm = new SocketManager("127.0.0.1", 3000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//despues lo cambiaremos para relaccionar a la otra
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,300, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new GridLayout(0, 3, 0, 0));
		
	    btnBuscar = new JButton("Buscar");
	    btnBuscar.setEnabled(false);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				sm.Escribir("Buscar "+tFVariable.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				respuesta=sm.Leer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*No posiblidad de error
			 * Abrir ventana variables
			 */
			}
		});
		panelBotones.add(btnBuscar);
		
		btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				sm.Escribir(""+comboBoxOpciones.getSelectedItem()+tFVariable.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				respuesta=sm.Leer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int numero=Integer.parseInt(respuesta.substring(0, 2));//coje los 3 primeros numeros asi no tengo que hacer un if gigante
            if(numero<399){
            	 JOptionPane.showMessageDialog(null,respuesta,"Correcto",JOptionPane.INFORMATION_MESSAGE);
			if(numero==205){
				//Ventana accion
			}
            }
            else{
            	JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR); 
            }
			}
			
		});
		panelBotones.add(btnClear);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}
		});
		panelBotones.add(btnSalir);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelopcion = new JPanel();
		panelCentral.add(panelopcion);
		
		JLabel lblOpcion1 = new JLabel("Opcion:");
		lblOpcion1.setVerticalAlignment(SwingConstants.BOTTOM);
		panelopcion.add(lblOpcion1);
		lblOpcion1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOpcion1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panelCentral.add(panel_1);
		
		comboBoxOpciones = new JComboBox();
		panel_1.add(comboBoxOpciones);
		comboBoxOpciones.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar opcion", "ON", " OFF ", "ACCION", "OBTENER_FOTO"}));
		comboBoxOpciones.setToolTipText("");
		
		JPanel panel_2 = new JPanel();
		panelCentral.add(panel_2);
		
		JLabel labelOpcion = new JLabel("Variable:");
		panel_2.add(labelOpcion);
		labelOpcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelOpcion.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_3 = new JPanel();
		panelCentral.add(panel_3);
		
		tFVariable = new JTextField();
		panel_3.add(tFVariable);
		tFVariable.setForeground(Color.BLACK);
		tFVariable.setColumns(10);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(!tFVariable.getText().equals("")&&!(comboBoxOpciones.getSelectedIndex()==0)){
			btnClear.setEnabled(true);
			btnBuscar.setEnabled(true);
			
		}
	}
}
