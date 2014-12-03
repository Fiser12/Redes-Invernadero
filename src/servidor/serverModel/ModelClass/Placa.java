package servidor.serverModel.ModelClass;

import java.awt.*;
import java.util.LinkedList;

public class Placa {
	private int id;
	private String estado;
	private Image foto;
	private LinkedList<Integer>sensores;
	private LinkedList<String>usuarios;
	private String nombreVariable;
	
	public Placa(int id, String estado, Image foto, LinkedList<Integer> sensores, LinkedList<String> usuarios, String variable) {
		this.id = id;
		this.estado = estado;
		this.foto = foto;
		this.sensores = sensores;
		this.usuarios = usuarios;
	}
	public Placa(int id, String estado, Image temp) {
		this.id = id;
		this.estado = estado;
		this.foto = temp;
	}
	public Placa(int id){
	this.id=id;
	}
	public Placa() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Image getFoto() {
		return foto;
	}
	public void setFoto(Image foto) {
		this.foto = foto;
	}
	public LinkedList<Integer> getSensores() {
		return sensores;
	}
	public void setSensores(LinkedList<Integer> sensores) {
		this.sensores = sensores;
	}
	public LinkedList<String> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(LinkedList<String> usuarios) {
		this.usuarios = usuarios;
	}
	public String getNombreVariable() {
		return nombreVariable;
	}
	public void setNombreVariable(String nombreVariable) {
		this.nombreVariable = nombreVariable;
	}
	
	
}
