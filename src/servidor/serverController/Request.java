package servidor.serverController;

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
		boolean stop = false;
		while(!stop)
		{
			switch (estado) {
			case 0:
				if (requestLine.startsWith("USER"))
				{
					usuario = requestLine.substring(5);
					int respuesta = InteraccionDB.metodoUser(usuario);
					System.out.println("USER: " + respuesta);
					if(respuesta == 200){
						sockManager.Escribir("200 OK Bienvenido " + usuario + "\n");
						estado = 1;
					}
					else if(respuesta == 400)
						sockManager.Escribir("400 ERR Falta el nombre de usuario\n");
					else if(respuesta == 401)
						sockManager.Escribir("401 ERR Usuario desconocido\n");
					requestLine = sockManager.Leer();
					System.out.println("0. RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("SALIR"))
				{
					sockManager.Escribir("209 OK Adios\n");
					estado = 4;
				}
				else{
					sockManager.Escribir("\n");
					requestLine = sockManager.Leer();
					System.out.println("0. RequestLine: " + requestLine);

				}
				break; 
			case 1:
				if (requestLine.startsWith("PASS"))
				{
					String pass = requestLine.substring(5);
					int respuesta = InteraccionDB.metodoPass(usuario, pass);
					System.out.println("PASS: " + pass + "resptuesta= " + respuesta);
					if(respuesta == 201){
						sockManager.Escribir("200 OK Bienvenido al sistema\n");
						estado = 2;
					}
					else if(respuesta == 402)
						sockManager.Escribir("402 ERR La clave es incorrecta\n");
					else if(respuesta == 403)
						sockManager.Escribir("403 ERR Falta la clave\n");
					requestLine = sockManager.Leer();
					System.out.println("1. RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("SALIR"))
				{
					sockManager.Escribir("209 OK Adios\n");
					estado = 4;
				}
				else{
					sockManager.Escribir("\n");
					requestLine = sockManager.Leer();
					System.out.println("1. RequestLine: " + requestLine);
				}
				break;
			case 2:
				if (requestLine.startsWith("ON"))
				{
					requestLine = sockManager.Leer();
					System.out.println("2. RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("OFF"))
				{
					requestLine = sockManager.Leer();
					System.out.println("2. RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("ACCION"))
				{
					estado = 3;
					requestLine = sockManager.Leer();
					System.out.println("2. RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("LISTADO"))
				{
					requestLine = sockManager.Leer();
					System.out.println("2. RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("BUSCAR"))
				{
					requestLine = sockManager.Leer();
					System.out.println("2. RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("OBTENER_FOTO"))
				{
					requestLine = sockManager.Leer();
					System.out.println("2. RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("SALIR"))
				{
					sockManager.Escribir("209 OK Adios\n");
					estado = 4;
				}
				else
				{
					sockManager.Escribir("\n");
					requestLine = sockManager.Leer();
					System.out.println("2. RequestLine: " + requestLine);
				}
				break;
			case 3:
				if(requestLine.startsWith("CONFIRMAR_ACCION"))
				{
					estado = 2;
					requestLine = sockManager.Leer();
					System.out.println("3. RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("RECHAZAR_ACCION"))
				{
					estado = 2;
					requestLine = sockManager.Leer();
					System.out.println("0. RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("SALIR"))
				{
					sockManager.Escribir("209 OK Adios\n");
					estado = 4;
				}
				else{
					sockManager.Escribir("\n");
					requestLine = sockManager.Leer();
					System.out.println("0. RequestLine: " + requestLine);
				}
				break;

			case 4:
				sockManager.CerrarStreams();
				sockManager.CerrarSocket();
				System.out.println("Cerrando");
				stop = true;
				break;
			}
		}
	}
}
