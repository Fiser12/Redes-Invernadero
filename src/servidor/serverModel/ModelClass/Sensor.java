package servidor.serverModel.ModelClass;

public class Sensor {
	private int id_sensor;
	private String estadoVariable;
	private String ultimaAcción;
	private String nombreVariable;
	public Sensor(String estadoVariable, String ultimaAcción, String variable) {
		this.estadoVariable = estadoVariable;
		this.ultimaAcción = ultimaAcción;
		this.nombreVariable = variable;
	}
	public Sensor() {
		super();
	}
	public String getEstadoVariable() {
		return estadoVariable;
	}
	public void setEstadoVariable(String estadoVariable) {
		this.estadoVariable = estadoVariable;
	}
	public String getUltimaAcción() {
		return ultimaAcción;
	}
	public void setUltimaAcción(String ultimaAcción) {
		this.ultimaAcción = ultimaAcción;
	}
	public String getVariable() {
		return nombreVariable;
	}
	public void setVariable(String variable) {
		this.nombreVariable = variable;
	}
	public int getId_sensor() {
		return id_sensor;
	}
	public void setId_sensor(int id_sensor) {
		this.id_sensor = id_sensor;
	}
	
	
}
