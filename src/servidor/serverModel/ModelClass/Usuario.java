package servidor.serverModel.ModelClass;

import java.util.LinkedList;

public class Usuario {
	private String usuario;
	private String pass;
	private LinkedList<Integer> idPlaca;
	
	public Usuario(String usuario, String pass, LinkedList<Integer> idPlaca) {
		this.usuario = usuario;
		this.pass = pass;
		this.idPlaca = idPlaca;
	}
	public Usuario() {
		
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public LinkedList<Integer> getIdPlaca() {
		return idPlaca;
	}
	public void setIdPlaca(LinkedList<Integer> idPlaca) {
		this.idPlaca = idPlaca;
	}
	
	
}
