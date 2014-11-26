package servidor.serverController;

import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.*;
import util.excepciones.SearchException;
import servidor.serverModel.InteraccionDB;
public final class Request implements Runnable {

	final static String CRLF = "\r\n";
	SocketManager sockManager;
	int estado;
	private String usuario;
	private String idPlacaAccion;
	private String variableAccion;
	private String accion;
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
	@SuppressWarnings("unused")
	private void sendBytes(FileInputStream fis) throws Exception {
		// Construct a 1K buffer to hold bytes on their way to the socket.
		byte[] buffer = new byte[1024];
		int bytes = 0;

		// Copy requested file into the socket's output stream.
		while ( (bytes = fis.read(buffer)) != -1) {
			sockManager.Escribir(buffer, bytes);
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
					System.out.println("RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("SALIR"))
				{
					sockManager.Escribir("209 OK Adios\n");
					estado = 4;
				}
				else{
					sockManager.Escribir("\n");
					requestLine = sockManager.Leer();
					System.out.println("RequestLine: " + requestLine);

				}
				break; 
			case 1:
				if (requestLine.startsWith("PASS"))
				{
					String pass = requestLine.substring(5);
					int respuesta = InteraccionDB.metodoPass(usuario, pass);
					System.out.println("PASS: " + pass + "resptuesta= " + respuesta);
					if(respuesta == 201){
						sockManager.Escribir("201 OK Bienvenido al sistema\n");
						estado = 2;
					}
					else if(respuesta == 402){
						sockManager.Escribir("402 ERR La clave es incorrecta\n");
						estado = 0;
					}
					else if(respuesta == 403){
						sockManager.Escribir("403 ERR Falta la clave\n");
						estado = 0;
					}
					requestLine = sockManager.Leer();
					System.out.println("RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("SALIR"))
				{
					sockManager.Escribir("209 OK Adios\n");
					estado = 4;
				}
				else{
					sockManager.Escribir("\n");
					requestLine = sockManager.Leer();
					System.out.println("RequestLine: " + requestLine);
				}
				break;
			case 2:
				if (requestLine.startsWith("ON"))
				{
					String variables = requestLine.substring(requestLine.indexOf(" ") + 1);
					String idPlaca = variables.substring(0, variables.indexOf(" "));
					String Variable = variables.substring(variables.indexOf(" ")+1, variables.length());
					// Comprobar si está encendido y mandar error 405 y si no existe el 404 sino poner a ON
					try
					{
						if(InteraccionDB.comprobarEstado(Variable, idPlaca))
						{
							sockManager.Escribir("405 ERR "+Variable+" en estado ON \n");
						}
						else
						{//Continuar con el proceso de poner a ON
							InteraccionDB.actualizarEstado(Variable, idPlaca, "ON");
							sockManager.Escribir("203 OK "+Variable+" activo \n");
						}
					}
					catch(SearchException E)
					{
						sockManager.Escribir("404 ERR "+Variable+" no existe \n");
					}
					requestLine = sockManager.Leer();
					System.out.println("RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("OFF"))
				{
					String variables = requestLine.substring(requestLine.indexOf(" ") + 1);
					String idPlaca = variables.substring(0, variables.indexOf(" "));
					String Variable = variables.substring(variables.indexOf(" ")+1, variables.length());
					// Comprobar si está apagado y mandar error 407 y si no existe el 406 sino poner a ON
					try
					{
						if(InteraccionDB.comprobarEstado(Variable, idPlaca))
						{//Continuar con el proceso de poner a OFF
							InteraccionDB.actualizarEstado(Variable, idPlaca, "OFF");
							sockManager.Escribir("204 OK "+Variable+" desactivo \n");
						}
						else
						{
							sockManager.Escribir("405 ERR "+Variable+" en estado OFF \n");
						}
					}
					catch(SearchException E)
					{
						sockManager.Escribir("404 ERR "+Variable+" no existe\n");
					}
					requestLine = sockManager.Leer();
					System.out.println("RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("ACCION"))
				{
					String variables = requestLine.substring(requestLine.indexOf(" ") + 1);
					String idPlaca = variables.substring(0, variables.indexOf(" "));
					String accionyVariable = variables.substring(variables.indexOf(" ")+1, variables.length());
					String variable = accionyVariable.substring(0, accionyVariable.indexOf(" "));
					String accion = accionyVariable.substring(accionyVariable.indexOf(" ")+1, accionyVariable.length());
					try
					{
						if(InteraccionDB.comprobarEstado(variable, idPlaca))
						{
							idPlacaAccion = idPlaca;
							variableAccion = variable;
							this.accion = accion;
							estado = 3;
							sockManager.Escribir("205 OK Esperando confirmación\n");
						}
						else
						{
							sockManager.Escribir("406 ERROR "+variable+" en estado OFF\n");
						}
					}
					catch(SearchException E)
					{
						sockManager.Escribir("404 ERROR "+variable+" no existe\n");
					}

					requestLine = sockManager.Leer();
					System.out.println("RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("LISTADO"))
				{
					sockManager.Escribir(InteraccionDB.listado(usuario)); //Cuidado con los \n
					requestLine = sockManager.Leer(); 
					System.out.println("RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("BUSCAR"))
				{
					requestLine = sockManager.Leer();
					System.out.println("RequestLine: " + requestLine);
				}
				else if(requestLine.startsWith("OBTENER_FOTO"))
				{
					String tipo = requestLine.substring(13,18);
					if(tipo.equals("Placa"))
					{
						int id = Integer.parseInt(requestLine.substring(18));
						System.out.println("ID: "+id);
						try{
							byte[] imagen = InteraccionDB.getImagen(id);
							sockManager.Escribir("206 OK Transmitiendo "+ imagen.length + " bytes\n");
							System.out.println(imagen.length);
							sockManager.sendBytes(imagen);		
						}catch(SearchException E){
							sockManager.Escribir("403 ERR Identificador no existe\n");
						}
					}
					else if(tipo.equals("Varia"))
					{//Método de extracción de la imagen del sensor
						String id = requestLine.substring(21);
						System.out.println("ID: "+id);
						try{
							byte[] imagen = InteraccionDB.getImagenVariable(id);
							sockManager.Escribir("206 OK Transmitiendo "+ imagen.length + " bytes\n");
							System.out.println(imagen.length);
							sockManager.sendBytes(imagen);		
						}catch(SearchException E){
							sockManager.Escribir("403 ERR Identificador no existe\n");
						}
					}
					else if(tipo.equals("Senso"))
					{

					}
					requestLine = sockManager.Leer();
					System.out.println("RequestLine: " + requestLine);
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
					System.out.println("RequestLine: " + requestLine);
				}
				break;
			case 3:
				if(requestLine.startsWith("CONFIRMAR_ACCION"))
				{
					try
					{
						String variableUsar = requestLine.substring(17);
						System.out.println("VAR: "+ variableUsar);
						if(variableUsar.equals(" ")||variableUsar.equals("")&&!accion.contains("imagen")){
							sockManager.Escribir("407 ERR 1 Faltan Datos\n");
							requestLine = sockManager.Leer();
							System.out.println("RequestLine: " + requestLine + "prueba");
						}
						else
						{
							String texto = "";
							if(accion.equals("Subir a.c")||accion.equals("Bajar a.c")){
								texto = "("+accion + " en " + variableUsar + " grados)";
								sockManager.Escribir("206 OK Accion sobre el sensor confirmada " + texto + " \n");	
							}
							else if(accion.equals("Activar sistemas de riego")){
								texto = "("+accion + " durante " + variableUsar + " minutos)";
								sockManager.Escribir("206 OK Accion sobre el sensor confirmada " + texto + " \n");
							}
							else if(accion.equals("Aumentar zoom")||accion.equals("Aumentar intensidad de la luz")){
								texto = "("+accion + " en X" + variableUsar + " veces)";
								sockManager.Escribir("206 OK Accion sobre el sensor confirmada " + texto + " \n");
							}
							else if(accion.equals("Disminuir zoom")||accion.equals("Disminuir intensidad de la luz")){
								texto = "("+accion + " entre " + variableUsar + ")";
								sockManager.Escribir("206 OK Accion sobre el sensor confirmada " + texto + " \n");
							}
							else if(accion.contains("imagen"))
							{
								int id = Integer.parseInt(requestLine.substring(17));
								try{
									byte[] imagen = InteraccionDB.getImagenSensor(id);
									sockManager.Escribir("206 OK Transmitiendo "+ imagen.length + " bytes\n");
									if(!accion.contains("color"))//método para convertir la foto en blanco y negro una vez salida de la BD
									{
										BufferedImage temp = ImageIO.read(new ByteArrayInputStream(imagen));
										ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);  
										ColorConvertOp op = new ColorConvertOp(cs, null);  
										Image image = op.filter(temp, null);  
										ByteArrayOutputStream baos = new ByteArrayOutputStream();  
										try {
											ImageIO.write((RenderedImage)image, "png", baos);
										} catch (IOException e) {
											e.printStackTrace();
										}  
										imagen = baos.toByteArray();
									}
									sockManager.sendBytes(imagen);		
								}catch(SearchException E){
									sockManager.Escribir("403 ERR Identificador no existe\n");
								}
							}
							InteraccionDB.actualizarAccion(variableAccion, idPlacaAccion, accion);

							estado = 2;
							requestLine = sockManager.Leer();
							System.out.println("RequestLine: " + requestLine);
						}
					}
					catch(Exception E)
					{
						sockManager.Escribir("407 ERR Faltan Datos "+E.getMessage()+"\n");
						requestLine = sockManager.Leer();
						System.out.println("RequestLine: " + requestLine);
					}
				}
				else if(requestLine.startsWith("RECHAZAR_ACCION"))
				{
					sockManager.Escribir("207 OK ACCION CANCELADA\n");
					estado = 2;
					requestLine = sockManager.Leer();
					System.out.println("RequestLine: " + requestLine);
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
					System.out.println("RequestLine: " + requestLine);
				}
				idPlacaAccion = "";
				variableAccion = "";
				accion = "";
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
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
