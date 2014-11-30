package servidor;

import java.net.ServerSocket;

import servidor.serverController.Request;
import servidor.view.VentanaPrincipal;
import util.SocketManager;

public class mainServidor {
	public static int usuariosConectados = 0;
	public static boolean userMax = false;
	public static ServerSocket wellcomeSocket;
	
	public static void main(String[]args) throws Exception
	{
		VentanaPrincipal frame = new VentanaPrincipal();
		frame.setVisible(true);
		int port = 3000;
		wellcomeSocket = new ServerSocket(port);
		while (true)
		{
			SocketManager sockManager = new SocketManager(wellcomeSocket.accept());
			if(!userMax)
			{
				Request request = new Request(sockManager, frame.getPanelServer());
				Thread thre = new Thread(request);
				thre.start();
			}
		}
	}
}
