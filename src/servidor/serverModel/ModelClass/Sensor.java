package servidor.serverModel.ModelClass;

public class Sensor {
	private String estadoVariable;
	private String ultimaAcción;
	private Variable variable;
	public Sensor(String estadoVariable, String ultimaAcción, Variable variable) {
		super();
		this.estadoVariable = estadoVariable;
		this.ultimaAcción = ultimaAcción;
		this.variable = variable;
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
	public Variable getVariable() {
		return variable;
	}
	public void setVariable(Variable variable) {
		this.variable = variable;
	}
	
	
}
