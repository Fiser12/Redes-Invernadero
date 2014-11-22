package servidor.serverController;
import java.net.* ;
import util.*;

public final class Server
{
	private static ServerSocket wellcomeSocket;

	public static void main(String argv[]) throws Exception
	{
		// Set the port number.
		int port = 3000; //(new Integer(argv[0])).intValue();

		wellcomeSocket = new ServerSocket(port);

		while (true)
		{

			SocketManager sockManager = new SocketManager(wellcomeSocket.accept());

			Request request = new Request(sockManager);

			Thread thre = new Thread(request);

			thre.start();
		}
	}
}
