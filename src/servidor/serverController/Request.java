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
			if (requestLine.startsWith("USER"))
			{
				usuario = requestLine.substring(5);
				int respuesta = InteraccionDB.metodoUser(usuario);
				System.out.println(respuesta);
				if(respuesta == 200){
					sockManager.Escribir("200 OK Bienvenido " + usuario + "\n");
					estado = 1;
				}
				else if(respuesta == 400)
					sockManager.Escribir("400 ERR Falta el nombre de usuario\n");
				else if(respuesta == 401)
					sockManager.Escribir("401 ERR Usuario desconocido\n");
			}
			else{
				sockManager.Escribir("0 ERR Generico");
			}
			break; 
		case 1:
			if (requestLine.startsWith("PASS"))
			{
				String pass = requestLine.substring(5, requestLine.length()-1);
				int respuesta = InteraccionDB.metodoPass(usuario, pass);
				if(respuesta == 201)
					sockManager.Escribir("200 OK Bienvenido al sistema\n");
				else if(respuesta == 402)
					sockManager.Escribir("402 ERR La clave es incorrecta\n");
				else if(respuesta == 403)
					sockManager.Escribir("403 ERR Falta la clave\n");
				estado = 2;
			}
			else{
				sockManager.Escribir("0 ERR Generico");
			}
			break;
		case 2:
			if (requestLine.startsWith("ON"))
			{

			}
			else if(requestLine.startsWith("OFF"))
			{

			}
			else if(requestLine.startsWith("ACCION"))
			{

			}
			else if(requestLine.startsWith("LISTADO"))
			{

			}
			else if(requestLine.startsWith("BUSCAR"))
			{

			}
			else if(requestLine.startsWith("OBTENER_FOTO"))
			{

			}
			else
			{

			}
			break;
		case 3:
			if(requestLine.startsWith("CONFIRMAR_ACCION"))
			{

			}
			else if(requestLine.startsWith("RECHAZAR_ACCION"))
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
