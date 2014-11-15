package servidor.serverModel;
/**
 * Clase dedicada a métodos estaticos que haciendo uso de SQLiteManager enmascara el modelo al controlador
 * @author Fiser
 *
 */

public class InteraccionDB {
	static SQLiteManager gestor = SQLiteManager.getInstance();
	public static void crearBaseDeDatos()
	{
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Usuario (Nombre VARCHAR (50) NOT NULL ,Pass VARCHAR(50) NOT NULL,KEY(Nombre));");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Placa (Id INT(4)NOT NULL AUTO_INCREMENT,Estado VARCHAR(250) NOT NULL, Foto VARCHAR(250),KEY (Id));");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Sensor (Estado_la_variable VARCHAR(250) NOT NULL,Ultima_Accion VARCHAR(500) NOT NULL);");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Variable (Nombre VARCHAR(250) NOT NULL,Funcion_Principal VARCHAR(500) NOT NULL,KEY(Nombre));");
	}
}
