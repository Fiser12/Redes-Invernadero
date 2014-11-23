package servidor.serverController;
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
					usuariosConectados++;
					System.out.println(usuariosConectados);
				}
			}
		}
	}
}
