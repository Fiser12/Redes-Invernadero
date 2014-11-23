package util;
import java.net.*;
import java.io.*;
import servidor.serverController.Server;

public class SocketManager {
	private Socket mySocket;
	private DataOutputStream bufferEscritura;
	private BufferedReader bufferLectura;

	public SocketManager(Socket sock) throws IOException {
		this.mySocket = sock;
		InicializaStreams();
	}
	/**
	 *
	 * @param address InetAddress
	 * @param port int numero de puerto
	 * @throws IOException
	 */
	public SocketManager(InetAddress address, int port) throws IOException {
		mySocket = new Socket(address, port);
		InicializaStreams();
	}

	/**
	 *
	 * @param host String nombre del servidor al que se conecta
	 * @param port int puerto de conexion
	 * @throws IOException
	 */
	public SocketManager(String host, int port) throws IOException {
		mySocket = new Socket(host, port);
		InicializaStreams();
	}

	/**
	 * Inicializacion de los bufferes de lectura y escritura del socket
	 * @throws IOException
	 */
	public void InicializaStreams() throws IOException {
		bufferEscritura = new DataOutputStream(mySocket.getOutputStream());
		bufferLectura = new BufferedReader(new InputStreamReader(mySocket.
				getInputStream()));
	}

	public void CerrarStreams() throws IOException {
		bufferEscritura.close();
		bufferLectura.close();
	}

	public void CerrarSocket() throws IOException {
		mySocket.close();
		Server.listaHilos.remove(Server.listaSockets.indexOf(this));
		Server.listaSockets.remove(this);
	}

	/**
	 *
	 * @return String
	 * @throws IOException
	 */
	public String Leer() throws IOException {
		return (bufferLectura.readLine());
	}

	public void Escribir(String contenido) throws IOException {
		bufferEscritura.writeBytes(contenido);
	}

	public void Escribir(byte[] buffer, int bytes) throws IOException {
		bufferEscritura.write(buffer, 0, bytes);
	}

	public Socket getMySocket() {
		return mySocket;
	}

	public void setMySocket(Socket mySocket) {
		this.mySocket = mySocket;
	}
}
