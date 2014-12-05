package servidor;

import servidor.serverController.Request;
import servidor.view.VentanaPrincipal;
import util.SocketManager;

import java.net.ServerSocket;

public class mainServidor {
	public static boolean userMax = false;

	public static void main(String[]args) throws Exception
	{
		VentanaPrincipal frame = new VentanaPrincipal();
		frame.setVisible(true);
		int port = 3000;
		ServerSocket wellcomeSocket = new ServerSocket(port);
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
