package info.enrico.basketapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * DBAdapter
 * Esta es una clase intermediaria entre nuestro Activity y 
 * la BBDD. Aqu� meteremos todas las operaciones CRUD sobre
 * los datos
 * @author Pello Altadill
 * @modify Enrico Mart�nez
 */
public class DbAdapter {

	// Este objeto nos permite meterle mano a SQLite
	private SQLiteDatabase db;

	// Aqu� tenemos nuestro SqliteHelper
	// que se encarga de crear y actualizar
	private SqLiteHelper dbHelper;
	
	// El contexto nos servir� para referirnos a la Activity
	// en la que estamos
	private final Context contexto;


	/**
	 * DbAdapter
	 * Constructor de la clase
	 * @param contexto Ser� la activity que usa esta clase
	 */
	public DbAdapter(Context contexto) {
		this.contexto = contexto;
	}


	/**
	 * open
	 * Usa el SqLiteHelper para encargase de abrir la conexi�n.
	 * El SqLiteHelper lo primero que har� es crear la BD si no existe.
	 * @return Devuelve un objeto de clase SQLiteDatabase para gestionar la BD
	 * @throws SQLException
	 */
	public SQLiteDatabase open() throws SQLException {
		// Crea un objeto asistente de base de datos de clase SqLiteHelper.
		dbHelper = new SqLiteHelper(contexto);
		
		// Abre la base de datos en modo escritura (lectura permitida).
		db = dbHelper.getWritableDatabase();
		
		Log.d("DEBUG","BD obtenida: " + db.toString());
		
		// Devuelve el objeto de tipo SQLiteDatabase.
		return db;
	}

	/**
	 * close
	 * Cierra la base de datos mediante el dbHelper
	 */
	public void close() {
		dbHelper.close();
	}

	/**
	 * insertarEquipo
	 * Inserta un registro con el nombre del equipo.
	 * 
	 * @param String nombre
	 * @return Devuelve el n�mero de registro insertado 0 -1 en caso de error
	 */
	public long insertarEquipo(String nombre) {
		// Creamos un registro
		ContentValues registro = new ContentValues();

		// Agrega los datos.
		registro.put("nombre", nombre);

		// Inserta el registro y devuelve el resultado.
		return db.insert("equipo", null, registro);
	}

	/**
	 * borrarEquipo
	 * Borra el registro que tiene el id especificado.
	 * ACTUALIZAR EL idEquipo DE TODOS LOS JUGADORES DE ESE EQUIPO Y PONERLO A 0
	 * @param idEquipo id del registro a borrar
	 * @return Devuelve el n� de registros afectados.
	 */
	public int borrarEquipo(long idEquipo) {
		return db.delete("equipo",  "id = "
				+ idEquipo, null);
	}

	/**
	 * obtenerEquipos
	 * Obtiene todos los equipos de la tabla equipo.
	 * 
	 * @return Cursor Devuelve un cursor con los registros obtenidos.
	 */
	public Cursor obtenerEquipos() {
		return db.query("equipo", new String[] {"idEquipo","nombre"}, null, null, null, null, null);
	}

	/**
	 * obtenerEquipo
	 * Obtiene el equipo que tiene el id especificado.
	 * 
	 * @param idEquipo id del registro que se quiere obtener.
	 * @return Cursor un cursor con el registro obtenido.
	 * @throws SQLException
	 */
	public Cursor obtenerTarea (long idEquipo) throws SQLException {
		Cursor equipo = db.query(true, "equipo",new String[] { "id","nombre"}, 
									"id =" + idEquipo, null, null, null, null, null);

		// Si lo ha encontrado, apunta al inicio del cursor.
		if (equipo != null) {
			equipo.moveToFirst();
		}
		return equipo;
	}

	/**
	 * actualizarEquipo
	 * Hace un UPDATE de los valores del registro cuyo id es idEquipo.
	 * 
	 * @param int idEquipo id del equipo que se quiere modificar.
	 * @param String nombre
	 * @return int cantidad registros han sido afectados.
	 */
	public int actualizarTarea(long idEquipo, String nombre) {
		// Creamos un registro de equipo
		ContentValues equipo = new ContentValues();

		// Agrega los datos.
		equipo.put("nombre", nombre);

		// Inserta el equipo y devuelve el resultado.
		return db.update("equipo", equipo,
				 "id=" + idEquipo, null);
	}
	
}