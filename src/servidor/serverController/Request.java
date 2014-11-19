package servidor.serverController;

import java.io.*;
import java.net.*;
import java.util.*;
import util.*;
import servidor.serverModel.InteraccionDB;
final class Request implements Runnable {

	final static String CRLF = "\r\n";
	SocketManager sockManager;
	int estado;
	String usuario;
	// Constructor
	public Request(SocketManager sockMan) throws Exception {
		sockManager = sockMan;
	}

	// Implement the run() method of the Runnable interface.
	public void run() {
		try {
			processRequest();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	private void processRequest() throws Exception {
	    String requestLine = sockManager.Leer();
	    System.out.println("RequestLine: " + requestLine);

		switch (estado) {
		case 0:
			if (requestLine.contains("USER"))
			{
				usuario = requestLine.substring(5);
				System.out.println(usuario);
				int respuesta = InteraccionDB.metodoUser(usuario);
				System.out.println(respuesta);
				if(respuesta == 200){
					sockManager.Escribir("200 OK Bienvenido " + usuario);
					estado = 1;
				}
				else if(respuesta == 400)
					sockManager.Escribir("400 ERR Falta el nombre de usuario");
				else if(respuesta == 401)
					sockManager.Escribir("401 ERR Usuario desconocido");
			}
			else{}
			break; 
		case 1:
			if (requestLine.contains("PASS"))
			{
				String pass = requestLine.substring(5, requestLine.length()-1);
				int respuesta = InteraccionDB.metodoPass(usuario, pass);
				if(respuesta == 201)
					sockManager.Escribir("200 OK Bienvenido al sistema");
				else if(respuesta == 402)
					sockManager.Escribir("402 ERR La clave es incorrecta");
				else if(respuesta == 403)
					sockManager.Escribir("403 ERR Falta la clave");
				estado = 2;
			}
			else{}
			break;
		case 2:
			if (requestLine.contains("ON"))
			{

			}
			else if(requestLine.contains("OFF"))
			{

			}
			else if(requestLine.contains("ACCION"))
			{

			}
			else if(requestLine.contains("LISTADO"))
			{

			}
			else if(requestLine.contains("BUSCAR"))
			{

			}
			else if(requestLine.contains("OBTENER_FOTO"))
			{

			}
			else
			{

			}
			break;
		case 3:
			if(requestLine.contains("CONFIRMAR_ACCION"))
			{

			}
			else if(requestLine.contains("RECHAZAR_ACCION"))
			{

			}
			else{}
			break;

		case 4:
			sockManager.CerrarStreams();
			sockManager.CerrarSocket();
			break;
		}
	}
}
