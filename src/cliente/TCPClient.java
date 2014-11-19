package cliente;
import util.*;
import java.io.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        String sentence=""; //Variable dnd se almacena la frase introducida por el usuario
        String modifiedSentence=""; //Variable dnd se recibe la frase capitalizada
        try {
            //Se crea el socket, pasando el nombre del servidor y el puerto de conexi�n
            SocketManager sm = new SocketManager("127.0.0.1", 3000);
            //Se inicializan los streams de lectura y escritura del socket

            //Se declara un buffer de lectura del
            //dato escrito por el usuario por teclado
            //es necesario pq no es un buffer propio de los sockets
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            //Se almacena en "sentence" la linea introducida por teclado

            while (!modifiedSentence.startsWith("209")) {
                System.out.print("String a enviar: ");
                sentence = inFromUser.readLine();
                //El m�todo Escribir, pone en el socket lo introducido por teclado
                sm.Escribir(sentence + '\n');
                //El m�todo Leer, lee del socket lo enviado por el Servidor
                modifiedSentence = sm.Leer();
                //Saca por consola la frase modificada enviada por el servidor
                System.out.println("Desde el servidor: " + modifiedSentence);
            }
            System.out.println("Fin de la practica");
            sm.CerrarSocket();
        } catch (Exception e) {
			System.err.println("main: " + e);
			e.printStackTrace();
        }

    }
    }
