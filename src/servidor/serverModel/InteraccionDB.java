package servidor.serverModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import servidor.serverModel.ModelClass.Sensor;

/**
 * Clase dedicada a métodos estaticos que haciendo uso de SQLiteManager enmascara el modelo al controlador
 * @author Fiser
 *
 */

public class InteraccionDB {
	static SQLiteManager gestor = SQLiteManager.getInstance();
	public static void crearBaseDeDatos()
	{
		gestor.enviarComando("PRAGMA foreign_keys = ON");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Usuario (Nombre VARCHAR(50) NOT NULL ,Pass VARCHAR(50) NOT NULL, PRIMARY KEY(Nombre));");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Placa (Id INTEGER PRIMARY KEY AUTOINCREMENT, Estado VARCHAR(250) NOT NULL, Foto VARCHAR(250));");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Usuario_Placa(Nombre VARCHAR(50) NOT NULL, Id_Placa INT(10) NOT NULL, CONSTRAINT Nombre FOREIGN KEY (Nombre) REFERENCES Usuario (Nombre) ON DELETE CASCADE, CONSTRAINT Id_Placa FOREIGN KEY (Id_Placa) REFERENCES Placa(Id) ON DELETE CASCADE)");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Sensor (Nombre_Variable VARCHAR(250) NOT NULL, Funcion_Principal VARCHAR(500) NOT NULL, Estado_la_variable VARCHAR(250) NOT NULL,Ultima_Accion VARCHAR(500) NOT NULL, id_Placa INT(10), CONSTRAINT id_Placa FOREIGN KEY (id_Placa) REFERENCES Placa (Id) ON DELETE CASCADE ON UPDATE CASCADE, PRIMARY KEY(id_Placa, Nombre_Variable));");
	}
	private static void borrarBaseDeDatos()
	{
		gestor.enviarComando("DROP TABLE IF EXISTS Sensor");
		gestor.enviarComando("DROP TABLE IF EXISTS Usuario_Placa");
		gestor.enviarComando("DROP TABLE IF EXISTS Variable");
		gestor.enviarComando("DROP TABLE IF EXISTS Placa");
		gestor.enviarComando("DROP TABLE IF EXISTS Usuario");
	}
	public static void reiniciarBase()
	{
		borrarBaseDeDatos();
		crearBaseDeDatos();
		cargarDatosIniciales();
	}
	public static void cargarDatosIniciales()
	{
		gestor.enviarComando("INSERT INTO Usuario VALUES('Fiser', '1234')");
	}
	public static int metodoUser(String nombre){
		gestor.enviarComando("SELECT Nombre FROM Usuario WHERE Nombre = '" + nombre + "';");
		ResultSet resultado = gestor.getResultSet();
		try {
			if(resultado.next())
				return 200;
			else
				if(nombre.equals(""))
					return 400;
				else
					return 401;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static int metodoPass(String nombre, String pass){
		gestor.enviarComando("SELECT * FROM Usuario WHERE Nombre = '" + nombre + "' AND Pass = '"+ pass + "';");
		ResultSet resultado = gestor.getResultSet();
		try {
			if(resultado.next())
				return 201;
			else
				if(pass.equals(""))
					return 403;
				else
					return 402;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	private static LinkedList<Sensor> listadoList(String nombre)
	{
		LinkedList<Sensor> lista = new LinkedList<Sensor>();
		gestor.enviarComando("SELECT * FROM Usuario_Placa U Sensor S WHERE Nombre = '" + nombre +"' AND U.Id_Placa=S.id_Placa;");
		ResultSet resultado = gestor.getResultSet();
		try {
			while(resultado.next())
				lista.add(new Sensor(resultado.getString("Nombre_Variable"), resultado.getString("Funcion_Principal"), resultado.getString("Estado_la_variable"), resultado.getString("Ultima_Accion"), resultado.getInt("id_Placa")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
		
	}
	public static String listado(String nombre){
		String texto = "";
		LinkedList<Sensor> lista = listadoList(nombre);
		for(Sensor temp: lista){
			String temporal = "ELEM " + temp.getId_sensor() + "Placa" + temp.getId_Placa() + "; " + temp.getVariable() + "; " + temp.getFuncionPrincipal() + "; " + temp.getEstadoVariable() + "; " + temp.getUltimaAccion() + "\n";
			texto = texto + temporal;
		}
		texto = texto + "\n\n202 FINLISTA";
		return texto;
	}
	public static void main(String []argv)
	{
		reiniciarBase();
	}
}
