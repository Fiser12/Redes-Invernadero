package util;
import java.net.*;
import java.io.*;

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
	//Estos métodos los he conseguido de aquí http://stackoverflow.com/questions/2878867/how-to-send-an-array-of-bytes-over-a-tcp-connection-java-programming
	public void sendBytes(byte[] myByteArray) throws IOException {
		sendBytes(myByteArray, 0, myByteArray.length);
	}
	public void sendBytes(byte[] myByteArray, int start, int len) throws IOException {
		if (len < 0)
			throw new IllegalArgumentException("Negative length not allowed");
		if (start < 0 || start >= myByteArray.length)
			throw new IndexOutOfBoundsException("Out of bounds: " + start);
		// Other checks if needed.

		// May be better to save the streams in the support class;
		// just like the socket variable.
		OutputStream out = mySocket.getOutputStream(); 
		DataOutputStream dos = new DataOutputStream(out);

		dos.writeInt(len);
		if (len > 0) {
			dos.write(myByteArray, start, len);
		}
	}
	public byte[] readBytes() throws IOException {
		// Again, probably better to store these objects references in the support class
		InputStream in = mySocket.getInputStream();
		DataInputStream dis = new DataInputStream(in);

		int len = dis.readInt();
		byte[] data = new byte[len];
		if (len > 0) {
			dis.readFully(data);
		}
		return data;
	}
}
