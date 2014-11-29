package servidor.serverModel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import servidor.serverModel.ModelClass.Placa;
import servidor.serverModel.ModelClass.Sensor;
import servidor.serverModel.ModelClass.Usuario;
import util.excepciones.RepetElement;
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
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Placa (Id INTEGER PRIMARY KEY AUTOINCREMENT, Estado VARCHAR(250) NOT NULL, Foto BLOB);");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Usuario_Placa(Nombre VARCHAR(50) NOT NULL, Id_Placa INT(10) NOT NULL, CONSTRAINT Nombre FOREIGN KEY (Nombre) REFERENCES Usuario (Nombre) ON DELETE CASCADE, CONSTRAINT Id_Placa FOREIGN KEY (Id_Placa) REFERENCES Placa(Id) ON DELETE CASCADE)");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Sensor (Id_Sensor INTEGER PRIMARY KEY AUTOINCREMENT, Nombre_Variable VARCHAR(250) NOT NULL, Funcion_Principal VARCHAR(500) NOT NULL, Estado_la_variable VARCHAR(250) NOT NULL,Ultima_Accion VARCHAR(500) NOT NULL, id_Placa INT(10), Foto BLOB, CONSTRAINT id_Placa FOREIGN KEY (id_Placa) REFERENCES Placa (Id) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT Nombre_Variable FOREIGN KEY (Nombre_Variable) REFERENCES Variable (Nombre_variable) ON DELETE CASCADE ON UPDATE CASCADE, UNIQUE(id_Placa, Nombre_Variable));");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Variable (Nombre_Variable VARCHAR(250) PRIMARY KEY, Foto BLOB)");
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
		gestor.enviarComando("INSERT INTO Placa(Id, Estado) VALUES(1, 'ON')");
		gestor.enviarComando("INSERT INTO Placa(Id, Estado) VALUES(2, 'ON')");
		gestor.enviarComando("INSERT INTO Placa(Id, Estado) VALUES(3, 'ON')");
		gestor.enviarComando("INSERT INTO Variable(Nombre_Variable) VALUES('Temperatura')");
		gestor.enviarComando("INSERT INTO Variable(Nombre_Variable) VALUES('Imagen')");
		gestor.enviarComando("INSERT INTO Variable(Nombre_Variable) VALUES('Zoom')");
		gestor.enviarComando("INSERT INTO Variable(Nombre_Variable) VALUES('Humedad')");

		gestor.enviarComando("INSERT INTO Usuario_Placa VALUES('Fiser', 1)");
		gestor.enviarComando("INSERT INTO Usuario_Placa VALUES('Fiser', 2)");
		gestor.enviarComando("INSERT INTO Usuario_Placa VALUES('Fiser', 3)");
		gestor.enviarComando("INSERT INTO Sensor(Id_Sensor, Nombre_Variable, Funcion_Principal, Estado_la_variable, Ultima_Accion, id_Placa) VALUES(1, 'Temperatura', 'Regulacion climatizacion', 'ON', 'Subir a.c.', 1)");
		gestor.enviarComando("INSERT INTO Sensor(Id_Sensor, Nombre_Variable, Funcion_Principal, Estado_la_variable, Ultima_Accion, id_Placa) VALUES(2, 'Humedad', 'Sistema de Riego', 'ON', 'Activar sistema de riego', 1)");
		gestor.enviarComando("INSERT INTO Sensor(Id_Sensor, Nombre_Variable, Funcion_Principal, Estado_la_variable, Ultima_Accion, id_Placa) VALUES(3, 'Temperatura', 'Regulacion climatizacion', 'OFF', 'Subir a.c.', 2)");
		gestor.enviarComando("INSERT INTO Sensor(Id_Sensor, Nombre_Variable, Funcion_Principal, Estado_la_variable, Ultima_Accion, id_Placa) VALUES(4, 'Humedad', 'Sistema de riego', 'OFF', 'Activar sistema de riego', 2)");
		gestor.enviarComando("INSERT INTO Sensor(Id_Sensor, Nombre_Variable, Funcion_Principal, Estado_la_variable, Ultima_Accion, id_Placa) VALUES(5, 'Zoom', 'Controlar el zoom de la camara', 'ON', 'Aumentar zoom', 3)");
		gestor.enviarComando("INSERT INTO Sensor(Id_Sensor, Nombre_Variable, Funcion_Principal, Estado_la_variable, Ultima_Accion, id_Placa) VALUES(6, 'Imagen', 'Vigilancia', 'OFF', 'Capturar imagen color', 2)");
		try {
			gestor.setImagen("UPDATE Placa SET Foto=? WHERE Id=1", ImageIO.read(new File("photos/foto1.jpg")));
			gestor.setImagen("UPDATE Placa SET Foto=? WHERE Id=2", ImageIO.read(new File("photos/foto2.png")));
			gestor.setImagen("UPDATE Placa SET Foto=? WHERE Id=3", ImageIO.read(new File("photos/foto3.jpg")));
			gestor.setImagen("UPDATE Variable SET Foto=? WHERE Nombre_Variable='Temperatura'", ImageIO.read(new File("photos/temperatura.png")));
			gestor.setImagen("UPDATE Variable SET Foto=? WHERE Nombre_Variable='Imagen'", ImageIO.read(new File("photos/image.png")));
			gestor.setImagen("UPDATE Variable SET Foto=? WHERE Nombre_Variable='Zoom'", ImageIO.read(new File("photos/zoom.png")));
			gestor.setImagen("UPDATE Variable SET Foto=? WHERE Nombre_Variable='Humedad'", ImageIO.read(new File("photos/humedad.png")));
			gestor.setImagen("UPDATE Sensor SET Foto=? WHERE Id_Sensor = 6", ImageIO.read(new File("photos/invernadero.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

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
	public static void insertarUser(String usuario, String pass) throws RepetElement{
		if(metodoUser(usuario)!=200)
			gestor.enviarComando("INSERT INTO Usuario VALUES('"+usuario+"','"+pass+"');");
		else
			throw new RepetElement();
	}
	public static void insertarVariable(String nombre, Image imagen) throws RepetElement{

		gestor.enviarComando("Select * FROM Variable WHERE Nombre='"+nombre+"');");
		ResultSet resultado=gestor.getResultSet();

		try {
			if(!resultado.isFirst()){
				gestor.enviarComando("INSERT INTO Variable(Nombre_Variable) VALUES('"+nombre+"');");
			}
			else{
				throw new RepetElement();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gestor.setImagen("UPDATE Variable SET Foto=? WHERE Nombre_Variable='"+nombre+"'", imagen);

	}
	public static void insertarPlaca(Image imagen) throws RepetElement{
		gestor.setImagen("INSERT INTO(Estado, Foto) Placa VALUES('ON', ?) ", imagen);
	}
	public static void insertarSensor(String funcion, String variable, String accion, boolean estado, Image imagen) throws RepetElement{
		String estadoStr = "ON";
		if(!estado)
			estadoStr = "OFF";
		gestor.setImagen("INSERT INTO(Funcion_Principal, Nombre_Variable, Ultima_Accion, Estado_la_variable, Foto) Placa VALUES('"+funcion+"','"+variable+"','"+accion+"','"+estadoStr+"', ?) ", imagen);
	}

	public static void eliminarUser(String usuario){
		gestor.enviarComando("DELETE FROM Usuario WHERE (Nombre='"+usuario+"');");	
	}
	public static void eliminarSensor(int ID){
		gestor.enviarComando("DELETE FROM Usuario WHERE (Id_Sensor'"+ID+"');");	
	}
	public static void eliminarPlaca(int ID){
		gestor.enviarComando("DELETE FROM Usuario WHERE (Id_Placa='"+ID+"');");	
	}
	public static void eliminarVariable(String Nombre){
		gestor.enviarComando("DELETE FROM Usuario WHERE (Nombre='"+Nombre+"');");	
	}
	public static LinkedList<Sensor>ListadoSensor(){
		LinkedList<Sensor> sensores=new LinkedList<Sensor>();
		gestor.enviarComando("SELECT * FROM Sensor");
		ResultSet resultado=gestor.getResultSet();
		try {
			while(resultado.next()){
				sensores.add(new Sensor(resultado.getInt("Id_Sensor"), resultado.getString("Nombre_Variable"), resultado.getString("Funcion_Principal"), resultado.getString("Estado_la_variable"), resultado.getString("Ultima_Accion"), resultado.getInt("id_Placa")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sensores;
	}
	public static LinkedList<Placa>listadoPlacas(){
		LinkedList<Placa> placas=new LinkedList<Placa>();
		gestor.enviarComando("Select * FROM Placa");
		ResultSet respuesta=gestor.getResultSet();
		try {
			while(respuesta.next()){
			placas.add(new Placa(respuesta.getInt(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return placas;
	}
	public static LinkedList<String>listadoVariable(){
		LinkedList<String> variables=new LinkedList<String>();
		gestor.enviarComando("SELECT * FROM Variable;");
		ResultSet respuesta=gestor.getResultSet();
		try {
			while(respuesta.next()){
				variables.add(respuesta.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return variables;
	}
	public static  LinkedList<Usuario>listadoUsuario(){
		LinkedList<Usuario> usuarios=new LinkedList<Usuario>();
		gestor.enviarComando("SELECT * FROM Usuario;");
		ResultSet respuesta=gestor.getResultSet();
		try {
			while(respuesta.next()){
				
				usuarios.add(new Usuario(respuesta.getString("Nombre"),""));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
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
	private static LinkedList<Sensor> buscarSacar(String nombre, String patron, String columna){
		LinkedList<Sensor> lista = new LinkedList<Sensor>();
		gestor.enviarComando("SELECT S.* FROM Sensor S, Usuario_Placa P WHERE S.Id_Placa=P.id_placa AND P.Nombre = '"+nombre+"' AND S."+columna+" Like '"+patron+"' ORDER BY S.Id_Placa, S.Id_Sensor;");
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
	public static String buscar(String nombre, String patron, String columna){
		String texto = "";
		LinkedList<Sensor> lista = buscarSacar(nombre, patron, columna);

		for(Sensor temp: lista){
			String temporal = "ELEM" + temp.getId_sensor() + " Placa" + temp.getId_Placa() + "; " + temp.getVariable() + "; " + temp.getFuncionPrincipal() + "; " + temp.getEstadoVariable() + "; " + temp.getUltimaAccion() + "/n";
			texto = texto + temporal;
		}
		texto = texto + "/n202 FINLISTA\n";
		System.out.println(texto);
		return texto;
	}
	public static boolean comprobarEstado(String sensor, String placa) throws SearchException{
		gestor.enviarComando("SELECT * FROM Sensor WHERE id_Placa="+placa+" AND Nombre_Variable = '"+sensor+"';");
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
				throw new SearchException();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}
	public static byte[] getImagen(int i) throws SearchException
	{
		return gestor.getImagen(i);
	}
	public static byte[] getImagenVariable(String i) throws SearchException
	{
		return gestor.getImagenVariable(i);
	}
	public static byte[] getImagenSensor(int sensor) throws SearchException
	{
		return gestor.getImagenSensor(sensor);
	}
	public static void actualizarEstado(String sensor, String placa, String estado) {
		gestor.enviarComando("UPDATE Sensor SET Estado_la_variable='"+estado+"' WHERE Id_Placa='"+placa+"' AND Nombre_Variable='"+sensor+"';");
	}
	public static void actualizarAccion(String sensor, String placa, String accion) {
		gestor.enviarComando("UPDATE Sensor SET Ultima_Accion='"+accion+"' WHERE Id_Placa='"+placa+"' AND Nombre_Variable='"+sensor+"';");
	}
	public static void main(String []argv)
	{
		reiniciarBase();
		System.out.println(listado("Fiser").replaceAll("/n", "\n"));
		JFrame ventana = new JFrame();
		ventana.setVisible(true);
	}
}
