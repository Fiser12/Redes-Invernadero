package servidor.view;

import javax.swing.JDialog;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import servidor.serverModel.InteraccionDB;
import util.excepciones.RepetElement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCrearUsuario extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUser;
	private JPasswordField textFieldPass;
	public VentanaCrearUsuario()
	{
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
		
		JPanel panelContraseña = new JPanel();
		panelCentral.add(panelContraseña);
		
		JLabel lblPass = new JLabel("Pass:");
		panelContraseña.add(lblPass);
		
		textFieldPass = new JPasswordField();
		panelContraseña.add(textFieldPass);
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
	public void insertar()
	{
		String user = textFieldUser.getText();
	    String pass=new String(textFieldPass.getPassword());
	    try {
	    	InteraccionDB.insertarUser(user, pass);
	    }catch(RepetElement E){
	    	JOptionPane.showMessageDialog(null,"El Usuario ya esta insertado","Error",JOptionPane.ERROR_MESSAGE);
	    }
	}
	public void cancelar()
	{
		dispose();
	}
}