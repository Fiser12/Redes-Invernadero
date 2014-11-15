package servidor.serverModel.ModelClass;

public class Variable {
	private String nombreVariable;
	private String funcionPrincipal;
	public Variable(String nombreVariable, String funcionPrincipal) {
		this.nombreVariable = nombreVariable;
		this.funcionPrincipal = funcionPrincipal;
	}
	public Variable() {

	}
	public String getNombreVariable() {
		return nombreVariable;
	}
	public void setNombreVariable(String nombreVariable) {
		this.nombreVariable = nombreVariable;
	}
	public String getFuncionPrincipal() {
		return funcionPrincipal;
	}
	public void setFuncionPrincipal(String funcionPrincipal) {
		this.funcionPrincipal = funcionPrincipal;
	}
}
