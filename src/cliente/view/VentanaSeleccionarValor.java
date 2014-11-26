package cliente.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import servidor.serverModel.ModelClass.Sensor;
import util.Util;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.NumberFormat;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class VentanaSeleccionarValor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	String respuesta = "";
	private JFormattedTextField txIncremento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//	VentanaIncrementar frame = new VentanaIncrementar(true);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaSeleccionarValor(Sensor s, final VentanaTabla control) {
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		String accion = s.getUltimaAccion();
		JLabel lblParametro = new JLabel("Indique el valor de la variable:");
		lblParametro.setHorizontalAlignment(SwingConstants.CENTER);
		if(accion.equals("Subir a.c")||accion.equals("Bajar a.c"))
			lblParametro.setText("Seleccionar temperatura a " + accion);
		else if(accion.equals("Activar sistemas de riego"))
			lblParametro.setText("Accionar sistemas de riego durante ");
		else if(accion.equals("Aumentar zoom")||accion.equals("Aumentar intensidad de la luz"))
			lblParametro.setText(accion + " en las veces que se indique");
		else if(accion.equals("Disminuir zoom")||accion.equals("Disminuir intensidad de la luz"))
			lblParametro.setText(accion + " las veces que se indique");
		else{
			lblParametro.setText("Continuar para descargar imagen");
			txIncremento.setVisible(false);
		}

		panel_1.add(lblParametro);
		
		JPanel incremento = new JPanel();
		panel.add(incremento);
		incremento.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		 
		 JLabel lblNewLabel = new JLabel("Valor");
		 panel_5.add(lblNewLabel);
		 panel_5.add(txIncremento);
		JPanel botones = new JPanel();
		panel.add(botones);
		botones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnContinuar = new JButton("Continuar");
		botones.add(btnContinuar);
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Util.claseSocketCliente.Escribir("CONFIRMAR_ACCION "+ txIncremento.getText() +"\n");
					respuesta = Util.claseSocketCliente.Leer();
					JOptionPane.showMessageDialog(null,respuesta);
					control.recargarTabla();
					dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnRegresar = new JButton("Regresar");
		botones.add(btnRegresar);
		btnRegresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Util.claseSocketCliente.Escribir("RECHAZAR_ACCION\n");
					respuesta = Util.claseSocketCliente.Leer();
					dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
