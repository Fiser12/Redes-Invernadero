package servidor.serverController;
import java.io.IOException;
import java.net.* ;
import java.util.LinkedList;

import util.*;

public final class Server
{
	private static ServerSocket wellcomeSocket;
	public static LinkedList<SocketManager> listaSockets;
	public static LinkedList<Request> listaHilos;

	public static void main(String argv[]) throws Exception
	{
		// Set the port number.
		listaSockets = new LinkedList<SocketManager>();
		listaHilos = new LinkedList<Request>();
		int port = 3000; //(new Integer(argv[0])).intValue();
		wellcomeSocket = new ServerSocket(port);
		while (true)
		{
			if(getUsuariosConectados()<Util.usuariosMaximos)
			{
				SocketManager sockManager = new SocketManager(wellcomeSocket.accept());
				Request request = new Request(sockManager);
				Thread thre = new Thread(request);
				thre.start();
				if(thre.isAlive())
				{
					listaSockets.add(sockManager);
					listaHilos.add(request);
					mostrarUsuario(getUsuariosConectados());
				}
			}
		}
	}
	public static void mostrarUsuario(int usuario)
	{
		String usuarioRegistrado = listaHilos.get(usuario).getUsuario();
		if(usuarioRegistrado.equals(""))
			usuarioRegistrado = "DESCONECTADO";			
		System.out.println("USERNAME: " + usuarioRegistrado);
		System.out.println("NºUSUARIO: " + (usuario+1));
		System.out.println("DIRECCIÓN: " + listaSockets.get(usuario).getMySocket().getInetAddress().getHostAddress());
		System.out.println("NOMBRE DEL HOST: " + listaSockets.get(usuario).getMySocket().getInetAddress().getHostName());
		System.out.println("PUERTO: "+ listaSockets.get(usuario).getMySocket().getPort());
		System.out.println("NOMBRE CANONICO: " + listaSockets.get(usuario).getMySocket().getInetAddress().getCanonicalHostName());
	}
	public static void desconectarUsuario(int usuario){
		try
		{
			listaSockets.get(usuario).CerrarStreams();
			listaSockets.get(usuario).CerrarSocket();
			listaSockets.remove(usuario);
			listaHilos.remove(usuario);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int getUsuariosConectados()
	{
		return listaSockets.size();
	}
}
