package servidor.serverModel.ModelClass;

public class Asociacion {
	private String user;
	private int placa;
	
	public Asociacion(String user, int placa) {
		this.user = user;
		this.placa = placa;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getPlaca() {
		return placa;
	}
	public void setPlaca(int placa) {
		this.placa = placa;
	}
	
}
