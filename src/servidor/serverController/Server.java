package servidor.serverController;
import java.io.IOException;
import java.net.* ;
import java.util.LinkedList;

import util.*;

public final class Server
{
	private static ServerSocket wellcomeSocket;
	public static int usuariosConectados;
	public static LinkedList<SocketManager> lista;
	public static void main(String argv[]) throws Exception
	{
		// Set the port number.
		lista = new LinkedList<SocketManager>();
		int port = 3000; //(new Integer(argv[0])).intValue();
		wellcomeSocket = new ServerSocket(port);
		usuariosConectados = 0;
		while (true)
		{
			if(usuariosConectados<Util.usuariosMaximos)
			{
				SocketManager sockManager = new SocketManager(wellcomeSocket.accept());
				Request request = new Request(sockManager);
				Thread thre = new Thread(request);
				thre.start();
				if(thre.isAlive())
				{
					lista.add(sockManager);
					mostrarUsuario(getUsuariosConectados());
					usuariosConectados++;
				}
			}
		}
	}
	public static void mostrarUsuario(int usuario){
		System.out.println("NºUSUARIO: " + (usuario+1));
		System.out.println("DIRECCIÓN: " + lista.get(usuario).getMySocket().getInetAddress().getHostAddress());
		System.out.println("NOMBRE DEL HOST: " + lista.get(usuario).getMySocket().getInetAddress().getHostName());
		System.out.println("PUERTO: "+ lista.get(usuario).getMySocket().getPort());
		System.out.println("NOMBRE CANONICO: " + lista.get(usuario).getMySocket().getInetAddress().getCanonicalHostName());
	}
	public static void desconectarUsuario(int usuario){
		try
		{
			lista.get(usuario).CerrarStreams();
			lista.get(usuario).CerrarSocket();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int getUsuariosConectados()
	{
		return usuariosConectados;
	}
}
