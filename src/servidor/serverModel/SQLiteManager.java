package servidor.serverModel;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import util.Util;
import util.excepciones.SearchException;

public class SQLiteManager
{
	private Connection connection;
	private Statement query;
	private String dir;
	private boolean conectado;
	private static SQLiteManager instance = null;
	private ResultSet resultadoDeConsulta;

	public SQLiteManager()
	{
		this.dir = Util.SQLITE_NOMBRE_BBDD;
		connect();
	}
	private synchronized static void createInstance()
	{
		if (instance == null) { 
			instance = new SQLiteManager();
		}
	}

	public static SQLiteManager getInstance()
	{
		createInstance();
		return instance;
	}
	/**
	 * Conecta con la base de datos SQLite establecida en la direción Util
	 */
	private void connect()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+dir);
				query = connection.createStatement();
				conectado = true;
			}catch (SQLException e1) {
				conectado = false;
			}
		}catch (ClassNotFoundException e) {
			conectado = false;
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Desconecta la base de datos
	 */
	public void disconnet()
	{
		try{
			query.close();
			connection.close();
			conectado = false;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Retorna el resultado de la consulta SQL última que se ha realizado
	 * @return
	 */
	public ResultSet getResultSet(){
		return resultadoDeConsulta;
	}
	/**
	 * Método preparado para enviar cualquier tipo de comando y ajusta la llamada para decidir si devuelve algún valor o no
	 * @param comando
	 * @return
	 */
	public synchronized boolean enviarComando(String comando)
	{
		try {
			if(conectado){
				if(comando.toLowerCase().startsWith("insert") 
						|| comando.toLowerCase().startsWith("update")
						|| comando.toLowerCase().startsWith("delete")
						|| comando.toLowerCase().startsWith("create")
						|| comando.toLowerCase().startsWith("alter")
						|| comando.toLowerCase().startsWith("drop")
						|| comando.toLowerCase().startsWith("pragma")
						){
					System.out.println(comando);
					query.executeUpdate(comando);
				}else{
					resultadoDeConsulta = query.executeQuery(comando);
				}
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}               
	}
	public byte[] getImagen(int codigo) throws SearchException
	{
		String query="SELECT Foto FROM Placa WHERE Id="+codigo;
		Statement stmt=null;
		byte[] imgArr = new byte[1];
		try{
			stmt=connection.createStatement();
			ResultSet rslt=stmt.executeQuery(query);
			if(rslt.next()){
				imgArr= rslt.getBytes("Foto");
			}
			else
				throw new SearchException();
		}catch(Exception e){
			throw new SearchException();
		}

		return imgArr;
	}
	public byte[] getImagenVariable(String codigo) throws SearchException
	{
		String query="SELECT Foto FROM Variable WHERE Nombre_Variable='"+codigo+"'";
		Statement stmt=null;
		byte[] imgArr = new byte[1];
		try{
			stmt=connection.createStatement();
			ResultSet rslt=stmt.executeQuery(query);
			if(rslt.next()){
				imgArr= rslt.getBytes("Foto");
			}
			else
				throw new SearchException();
		}catch(Exception e){
			throw new SearchException();
		}

		return imgArr;
	}
	public byte[] getImagenSensor(int sensor) throws SearchException
	{
		String query="SELECT Foto FROM Sensor WHERE Id_Sensor="+sensor;
		Statement stmt=null;
		byte[] imgArr = new byte[1];
		try{
			stmt=connection.createStatement();
			ResultSet rslt=stmt.executeQuery(query);
			if(rslt.next()){
				imgArr= rslt.getBytes("Foto");
			}
			else
				throw new SearchException();
		}catch(Exception e){
			throw new SearchException();
		}

		return imgArr;
	}
	public boolean setImagen(String sql, Image image)
	{
		boolean funciona = true; 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        try {
                ImageIO.write((RenderedImage)image, "png", baos);
        } catch (IOException e) {
                e.printStackTrace();
        }  
        byte[] data = baos.toByteArray(); 
		try {
			PreparedStatement consultaPreparada = connection.prepareStatement(sql);
			consultaPreparada.setBytes(1, data);
			consultaPreparada.executeUpdate();
		} catch (SQLException e) {
			funciona = false;
		}
		return funciona;
	}
}