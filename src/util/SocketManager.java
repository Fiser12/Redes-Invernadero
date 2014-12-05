package util;

import java.io.*;
import java.net.Socket;

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
	 * @throws IOException
	 */
	public SocketManager() throws IOException {
		mySocket = new Socket("127.0.0.1", 3000);
		InicializaStreams();
	}

	/**
	 * Inicializacion de los bufferes de lectura y escritura del socket
	 * @throws IOException
	 */
	void InicializaStreams() throws IOException {
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

	public Socket getMySocket() {
		return mySocket;
	}

	//Estos metodos los he conseguido de aqui http://stackoverflow.com/questions/2878867/how-to-send-an-array-of-bytes-over-a-tcp-connection-java-programming
	public void sendBytes(byte[] myByteArray) throws IOException {
		sendBytes(myByteArray, myByteArray.length);
	}

	void sendBytes(byte[] myByteArray, int len) throws IOException {
		if (len < 0)
			throw new IllegalArgumentException("Negative length not allowed");
		if (0 >= myByteArray.length)
			throw new IndexOutOfBoundsException("Out of bounds: " + 0);
		// Other checks if needed.

		// just like the socket variable.
		// May be better to save the streams in the support class;
		OutputStream out = mySocket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		dos.writeInt(len);
		if (len > 0) {
			dos.write(myByteArray, 0, len);
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
