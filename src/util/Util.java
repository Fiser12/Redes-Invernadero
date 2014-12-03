package util;

import java.util.LinkedList;

import servidor.serverController.Request;

/**
 * Clase dedicada a guardar metodos o atributos genericos que puedan ser accedidos desde cualquier parte del sistema
 * @author Fiser
 *
 */
public class Util {
	public static String SQLITE_NOMBRE_BBDD = "InvernaderoSQL.sqlite";
	public static int usuariosMaximos = 3;
	public static String servidor = "127.0.0.1";
	public static int puerto = 3000;
	public static SocketManager claseSocketCliente;
	public static LinkedList<SocketManager> listaSockets = new LinkedList<SocketManager>();;
	public static LinkedList<Request> listaHilos = new LinkedList<Request>();
}
