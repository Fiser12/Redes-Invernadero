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
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Usuario (Nombre VARCHAR(50) NOT NULL ,Pass VARCHAR(50) NOT NULL, PRIMARY KEY(Nombre));");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Placa (Id INT(10) NOT NULL AUTOINCREMENT,Estado VARCHAR(250) NOT NULL, Foto VARCHAR(250), PRIMARY KEY (Id));");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Usuario_Placa(Nombre VARCHAR(50) NOT NULL, Id_Placa INT(10) NOT NULL, CONSTRAINT Nombre FOREIGN KEY (Nombre) REFERENCES Usuario (Nombre) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT Id_Placa FOREIGN KEY (Id_Placa) REFERENCES Placa(Id) ON DELETE CASCADE ON UPDATE CASCADE");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Sensor (Id INT(10) NOT NULL AUTOINCREMENT, Nombre_Variable VARCHAR(250) NOT NULL, Estado_la_variable VARCHAR(250) NOT NULL,Ultima_Accion VARCHAR(500) NOT NULL, id_Placa INT(10), PRIMARY KEY(Id, id_Placa), CONSTRAINT id_Placa FOREIGN KEY (id_Placa) REFERENCES Placa (Id) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT Nombre_Variable FOREIGN KEY (Nombre_Variable) REFERENCES Variable(Nombre) ON DELETE CASCADE ON UPDATE CASCADE);");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Variable (Nombre VARCHAR(250) NOT NULL,Funcion_Principal VARCHAR(500) NOT NULL, PRIMARY KEY(Nombre));");
	}
}
