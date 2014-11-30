package util;

import java.util.LinkedList;

import servidor.serverController.Request;

/**
 * Clase dedicada a guardar métodos o atributos genéricos que puedan ser accedidos desde cualquier parte del sistema
 * @author Fiser
 *
 */
public class Util {
	public static String SQLITE_NOMBRE_BBDD = "InvernaderoSQL.sqlite";
	public static int usuariosMaximos = 3;
	public static String servidor = "127.0.0.1";
	public static int puerto = 3000;
	public static SocketManager claseSocketCliente;
	public static LinkedList<SocketManager> listaSockets;
	public static LinkedList<Request> listaHilos;
	public  static void cambiarNUsuarios(int i){
		usuariosMaximos=i;
	}
}
