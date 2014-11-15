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
            gestor.enviarComando("CREATE TABLE IF NOT EXISTS Usuario ();");           
            gestor.enviarComando("CREATE TABLE IF NOT EXISTS Placa ();");
            gestor.enviarComando("CREATE TABLE IF NOT EXISTS Sensor ();");
    }
}
