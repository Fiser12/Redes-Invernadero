package servidor.serverModel.ModelClass;

import java.util.LinkedList;
public class Variable {
	private String nombreVariable;
	private String funcionPrincipal;
	private LinkedList<Integer> id_Placa;
	public Variable(String nombreVariable, String funcionPrincipal) {
		this.nombreVariable = nombreVariable;
		this.funcionPrincipal = funcionPrincipal;
	}
	public Variable(String nombreVariable, String funcionPrincipal, LinkedList<Integer> id_Placa) {
		this.nombreVariable = nombreVariable;
		this.funcionPrincipal = funcionPrincipal;
		this.setId_Placa(id_Placa);
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
	public LinkedList<Integer> getId_Placa() {
		return id_Placa;
	}
	public void setId_Placa(LinkedList<Integer> id_Placa) {
		this.id_Placa = id_Placa;
	}
}
