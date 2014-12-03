package cliente.view;

import servidor.serverModel.ModelClass.Sensor;
import util.Util;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.NumberFormat;

public class VentanaSeleccionarValor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String respuesta = "";
	private JPanel contentPane;
	private JFormattedTextField txIncremento;

	/**
	 * Create the frame.
	 */
	public VentanaSeleccionarValor(final Sensor s, final VentanaTabla control) {
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
		final String accion = s.getUltimaAccion();
		JLabel lblParametro = new JLabel("Indique el valor de la variable:");
		lblParametro.setHorizontalAlignment(SwingConstants.CENTER);
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
			lblNewLabel.setVisible(false);
		}

		panel_1.add(lblParametro);
		
		JPanel incremento = new JPanel();
		panel.add(incremento);
		incremento.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_5 = new JPanel();
		incremento.add(panel_5);
				 
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
					if(!accion.contains("imagen"))
					{
						Util.claseSocketCliente.Escribir("CONFIRMAR_ACCION "+ txIncremento.getText() +"\n");
						respuesta = Util.claseSocketCliente.Leer();
						JOptionPane.showMessageDialog(null,respuesta);
						control.recargarTabla();
						dispose();
					}
					else
					{
						Util.claseSocketCliente.Escribir("CONFIRMAR_ACCION "+ s.getId_sensor() +"\n");
						respuesta = Util.claseSocketCliente.Leer();
						if(respuesta.contains("ERR"))
							JOptionPane.showMessageDialog(null,respuesta,"Error",JOptionPane.ERROR_MESSAGE);
						else
						{
							byte[] imagen = Util.claseSocketCliente.readBytes();
							Image temp = ImageIO.read(new ByteArrayInputStream(imagen));
							VentanaCamara ventana = new VentanaCamara(temp, s.getId_sensor()+"");
							ventana.setVisible(true);
						}
						dispose();
					}
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
