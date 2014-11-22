package servidor.serverModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import servidor.serverModel.ModelClass.Sensor;
import util.excepciones.SearchException;

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
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Placa (Id INTEGER PRIMARY KEY, Estado VARCHAR(250) NOT NULL, Foto VARCHAR(250));");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Usuario_Placa(Nombre VARCHAR(50) NOT NULL, Id_Placa INT(10) NOT NULL, CONSTRAINT Nombre FOREIGN KEY (Nombre) REFERENCES Usuario (Nombre) ON DELETE CASCADE, CONSTRAINT Id_Placa FOREIGN KEY (Id_Placa) REFERENCES Placa(Id) ON DELETE CASCADE)");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Sensor (Id_Sensor INTEGER PRIMARY KEY, Nombre_Variable VARCHAR(250) NOT NULL, Funcion_Principal VARCHAR(500) NOT NULL, Estado_la_variable VARCHAR(250) NOT NULL,Ultima_Accion VARCHAR(500) NOT NULL, id_Placa INT(10), CONSTRAINT id_Placa FOREIGN KEY (id_Placa) REFERENCES Placa (Id) ON DELETE CASCADE ON UPDATE CASCADE, UNIQUE(id_Placa, Nombre_Variable));");
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
		gestor.enviarComando("INSERT INTO Placa VALUES(1, 'ON', 'photos/foto1.jpg')");
		gestor.enviarComando("INSERT INTO Placa VALUES(2, 'ON', 'photos/foto2.png')");
		gestor.enviarComando("INSERT INTO Placa VALUES(3, 'ON', 'photos/foto3.jpg')");
		gestor.enviarComando("INSERT INTO Usuario_Placa VALUES('Fiser', 1)");
		gestor.enviarComando("INSERT INTO Usuario_Placa VALUES('Fiser', 2)");
		gestor.enviarComando("INSERT INTO Sensor VALUES(1, 'Temperatura', 'Regulacion climatizacion', 'ON', 'subir a.c.', 1)");
		gestor.enviarComando("INSERT INTO Sensor VALUES(2, 'Humedad', 'Sistema de Riego', 'ON', 'activar sistema de riego', 1)");
		gestor.enviarComando("INSERT INTO Sensor VALUES(3, 'Temperatura', 'Regulacion climatizacion', 'OFF', 'subir a.c.', 2)");
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
		gestor.enviarComando("SELECT S.* FROM Sensor S, Usuario_Placa P WHERE S.Id_Placa=P.id_placa AND P.Nombre = '"+nombre+"' ORDER BY S.Id_Placa, S.Id_Sensor;");
		ResultSet resultado = gestor.getResultSet();
		try {
			while(resultado.next())
				lista.add(new Sensor(resultado.getInt("Id_Sensor"), resultado.getString("Nombre_Variable"), resultado.getString("Funcion_Principal"), resultado.getString("Estado_la_variable"), resultado.getString("Ultima_Accion"), resultado.getInt("id_Placa")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
		
	}
	public static String listado(String nombre){
		String texto = "";
		LinkedList<Sensor> lista = listadoList(nombre);
		for(Sensor temp: lista){
			String temporal = "ELEM" + temp.getId_sensor() + " Placa" + temp.getId_Placa() + "; " + temp.getVariable() + "; " + temp.getFuncionPrincipal() + "; " + temp.getEstadoVariable() + "; " + temp.getUltimaAccion() + "/n";
			texto = texto + temporal;
		}
		texto = texto + "/n202 FINLISTA\n";
		return texto;
	}
	public static boolean comprobarEstado(String sensor, String placa) throws SearchException{
		gestor.enviarComando("SELECT * FROM Sensor WHERE Id_Placa="+placa+" AND Nombre_Variable = '"+sensor+"';");
		ResultSet resultado = gestor.getResultSet();
		try {
			if(resultado.next())
			{
				String estado = resultado.getString("Estado_la_variable");
				System.out.println(estado);
				if(estado.equals("ON"))
					return true;
				else return false;
			}
			else
				System.out.println("asdasdf");
				throw new SearchException();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}
	public static void actualizarEstado(String sensor, String placa, String estado) {
		gestor.enviarComando("UPDATE Sensor SET Estado_la_variable='"+estado+"';");
	}

	public static void main(String []argv)
	{
		reiniciarBase();
		System.out.println(listado("Fiser").replaceAll("/n", "\n"));
	}
}
