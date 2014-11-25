package cliente.view.componentes;

import javax.swing.table.DefaultTableModel;
import servidor.serverModel.ModelClass.Sensor;

/**
 * Clase creada para hacer no editable una tabla
 *
 */
public class ModeloSensor extends DefaultTableModel
{

	private static final long serialVersionUID = 1L;
	public ModeloSensor(){
		setColumnIdentifiers(new String[]{"Sensor","Placa","Variable","Estado","Funcion","Ultima Accion"});
	}
	public boolean isCellEditable (int row, int column)
	{
		return false;
	}
	public void addRow(Sensor anadir){
		String idSensor = ""+anadir.getId_sensor();
		String idPlaca = ""+anadir.getId_Placa();
		String variable = anadir.getVariable();
		String estado = anadir.getEstadoVariable();
		String funcion = anadir.getFuncionPrincipal();
		String accion = anadir.getUltimaAccion();
		Object [] add = {idSensor, idPlaca, variable, estado, funcion, accion};
		this.addRow(add);
	}
}
