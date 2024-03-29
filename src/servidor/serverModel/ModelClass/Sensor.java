package servidor.serverModel.ModelClass;

public class Sensor {
	private int id_sensor;
	private String estadoVariable;
	private String ultimaAccion;
	private String nombreVariable;
	private String funcionPrincipal;
	private int id_Placa;
	public Sensor(int id_Sensor, String variable, String funcionPrincipal, String estadoVariable, String Ultima_Accion, int idPlaca) {
		this.estadoVariable = estadoVariable;
		this.ultimaAccion = Ultima_Accion;
		this.nombreVariable = variable;
		this.funcionPrincipal = funcionPrincipal;
		this.id_Placa = idPlaca;
		this.id_sensor = id_Sensor;
	}
	public Sensor() {

	}
	
	@Override
	public String toString() {
		return "Sensor [id_sensor=" + id_sensor + ", estadoVariable="
				+ estadoVariable + ", ultimaAccion=" + ultimaAccion
				+ ", nombreVariable=" + nombreVariable + ", funcionPrincipal="
				+ funcionPrincipal + ", id_Placa=" + id_Placa + "]";
	}
	public String getEstadoVariable() {
		return estadoVariable;
	}
	public void setEstadoVariable(String estadoVariable) {
		this.estadoVariable = estadoVariable;
	}
	public String getUltimaAccion() {
		return ultimaAccion;
	}
	public void setUltimaAccion(String ultimaAccion) {
		this.ultimaAccion = ultimaAccion;
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
	public String getFuncionPrincipal() {
		return funcionPrincipal;
	}
	public void setFuncionPrincipal(String funcionPrincipal) {
		this.funcionPrincipal = funcionPrincipal;
	}
	public int getId_Placa() {
		return id_Placa;
	}
	public void setId_Placa(int id_Placa) {
		this.id_Placa = id_Placa;
	}
}
