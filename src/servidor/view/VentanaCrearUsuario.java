package servidor.view;

import servidor.serverModel.InteraccionDB;
import util.excepciones.RepetElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearUsuario extends JDialog{

	private static final long serialVersionUID = 1L;
	private final JTextField textFieldUser;
	private final JPasswordField textFieldPass;
	public VentanaCrearUsuario()
	{
	    setSize(new Dimension(250,135));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		
		JPanel panelUser = new JPanel();
		panelCentral.add(panelUser);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		panelUser.add(lblUsuario);
		
		textFieldUser = new JTextField();
		panelUser.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		JPanel panelContrasena = new JPanel();
		panelCentral.add(panelContrasena);
		
		JLabel lblPass = new JLabel("Pass:");
		panelContrasena.add(lblPass);
		
		textFieldPass = new JPasswordField();
		panelContrasena.add(textFieldPass);
		textFieldPass.setColumns(10);
		
		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior, BorderLayout.SOUTH);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertar();
			}
		});
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
		String user = textFieldUser.getText();
	    String pass=new String(textFieldPass.getPassword());
	    try {
	    	InteraccionDB.insertarUser(user, pass);
	    }catch(RepetElement E){
	    	JOptionPane.showMessageDialog(null,"El Usuario ya esta insertado","Error",JOptionPane.ERROR_MESSAGE);
	    }
		dispose();
	}

	void cancelar()
	{
		dispose();
	}
}
