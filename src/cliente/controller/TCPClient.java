package cliente.controller;
import util.*;
import cliente.view.*;

public class TCPClient {
	public static void main(String[] args) throws Exception {
		String sentence=""; //Variable dnd se almacena la frase introducida por el usuario
		String modifiedSentence=""; //Variable dnd se recibe la frase capitalizada
		
/*		try {
			SocketManager sm = new SocketManager("127.0.0.1", 3000);
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			while (!modifiedSentence.startsWith("209")) {
				System.out.print("String a enviar: ");
				sentence = inFromUser.readLine();
				//El m�todo Escribir, pone en el socket lo introducido por teclado
				sm.Escribir(sentence + '\n');
				//El m�todo Leer, lee del socket lo enviado por el Servidor
				if(!sentence.startsWith("OBTENER"))
					modifiedSentence = sm.Leer().replaceAll("/n", "\n");//Dado que pide en algunos casos mandar \n pero supone que no se envie la linea correcta utilizamos /n como código auxiliar que consideramos así del lado del cliente para convertirlo en el \n real
				else
				{
					modifiedSentence = sm.Leer();
					System.out.println(modifiedSentence);
					if(modifiedSentence.startsWith("206")){
						byte[] temporal = sm.readBytes();
						Image temp = ImageIO.read(new ByteArrayInputStream(temporal));
						JFrame ventana = new JFrame();
						ventana.setVisible(true);
						try{
						ventana.add(new JLabel(new ImageIcon(temp)));
						}catch(Exception E)
						{
							
						}
					}
				}
				//Saca por consola la frase modificada enviada por el servidor
				System.out.println("Desde el servidor: " + modifiedSentence);
			}
			System.out.println("Fin de la practica");
			sm.CerrarSocket();
		} catch (Exception e) {
			System.err.println("main: " + e);
			e.printStackTrace();
		}
*/
	}

}
