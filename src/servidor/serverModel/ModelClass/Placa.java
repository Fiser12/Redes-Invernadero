package servidor.serverModel.ModelClass;
import java.util.LinkedList;

public class Placa {
	private int id;
	private String estado;
	private String foto;
	private LinkedList<String>sensores;
	
	public Placa(int id, String estado, String foto, LinkedList<String> sensores) {
		this.id = id;
		this.estado = estado;
		this.foto = foto;
		this.sensores = sensores;
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public LinkedList<String> getSensores() {
		return sensores;
	}
	public void setSensores(LinkedList<String> sensores) {
		this.sensores = sensores;
	}
	
}
