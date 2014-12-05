package util;

import servidor.serverController.Request;

import java.util.LinkedList;

/**
 * Clase dedicada a guardar metodos o atributos genericos que puedan ser accedidos desde cualquier parte del sistema
 * @author Fiser
 *
 */
public class Util {
	public static final String SQLITE_NOMBRE_BBDD = "InvernaderoSQL.sqlite";
	public static final LinkedList<SocketManager> listaSockets = new LinkedList<SocketManager>();
	public static final LinkedList<Request> listaHilos = new LinkedList<Request>();
	public static String servidor = "127.0.0.1";
	public static int puerto = 3000;
	public static SocketManager claseSocketCliente;
	private static int usuariosMaximos = 3;

	public static int getUsuariosMaximos() {
		return usuariosMaximos;
	}

	public static void setUsuariosMaximos(int usuariosMaximos) {
		Util.usuariosMaximos = usuariosMaximos;
	}
}
