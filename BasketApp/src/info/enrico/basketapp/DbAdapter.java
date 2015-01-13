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
 * la BBDD. Aquí meteremos todas las operaciones CRUD sobre
 * los datos
 * @author Pello Altadill
 * @modify Enrico Martínez
 */
public class DbAdapter {

	// Este objeto nos permite meterle mano a SQLite
	private SQLiteDatabase db;

	// Aquí tenemos nuestro SqliteHelper
	// que se encarga de crear y actualizar
	private SqLiteHelper dbHelper;
	
	// El contexto nos servirá para referirnos a la Activity
	// en la que estamos
	private final Context contexto;


	/**
	 * DbAdapter
	 * Constructor de la clase
	 * @param contexto Será la activity que usa esta clase
	 */
	public DbAdapter(Context contexto) {
		this.contexto = contexto;
	}


	/**
	 * open
	 * Usa el SqLiteHelper para encargase de abrir la conexión.
	 * El SqLiteHelper lo primero que hará es crear la BD si no existe.
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
	 * @param String nombreEquipo
	 * @return Devuelve el número de registro insertado 0 -1 en caso de error
	 */
	public long insertarEquipo(String nombre) {
		// Creamos un registro
		ContentValues registro = new ContentValues();

		// Agrega los datos.
		registro.put("nombreEquipo", nombre);

		// Inserta el registro y devuelve el resultado.
		return db.insert("equipo", null, registro);
	}

	/**
	 * borrarEquipo
	 * Borra el registro que tiene el id especificado.
	 * ACTUALIZAR EL idEquipo DE TODOS LOS JUGADORES DE ESE EQUIPO Y PONERLO A 0
	 * @param idEquipo id del registro a borrar
	 * @return Devuelve el nº de registros afectados.
	 */
	public int borrarEquipo(long idEquipo) {
		return db.delete("equipo",  "idEquipo = "
				+ idEquipo, null);
	}

	/**
	 * obtenerEquipos
	 * Obtiene todos los equipos de la tabla equipo.
	 * 
	 * @return Cursor Devuelve un cursor con los registros obtenidos.
	 */
	public Cursor obtenerEquipos() {
		return db.query("equipo", new String[] {"idEquipo","nombreEquipo"}, null, null, null, null, null);
	}

	/**
	 * obtenerEquipo
	 * Obtiene el equipo que tiene el id especificado.
	 * 
	 * @param idEquipo id del registro que se quiere obtener.
	 * @return Cursor un cursor con el registro obtenido.
	 * @throws SQLException
	 */
	public Cursor obtenerEquipo (long idEquipo) throws SQLException {
		Cursor equipo = db.query(true, "equipo",new String[] { "idEquipo","nombreEquipo"}, 
									"idEquipo =" + idEquipo, null, null, null, null, null);

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
	public int actualizarEquipo(long idEquipo, String nombre) {
		// Creamos un registro de equipo
		ContentValues equipo = new ContentValues();

		// Agrega los datos.
		equipo.put("nombre", nombre);

		// Inserta el equipo y devuelve el resultado.
		return db.update("equipo", equipo,
				 "idEquipo=" + idEquipo, null);
	}
	
}