package info.enrico.basketapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * SqLiteHelper
 * Digamos que en lugar de meter mano a SQLite a nuestra bola con jdbc
 * lo hacemos usando el SQLiteOpenHelper que es lo que le gusta a Android
 * Esta clase se requiere por otra de tipo DbAdapter.
 * La clase se encarga de inicializar la BBDD -si no existe-, de actualizarla
 * 
 * @author pello xabier altadill
 * @modify Enrico Mart�nez
 *
 */
class SqLiteHelper extends SQLiteOpenHelper {
	
	// Nombre de BBDD
	public static final String NOMBRE_BD = "basket.db";
	
	// N�mero de versi�n, n�mero arbitrario 
	// que decidimos nosotros
	public static final int VERSION_BD = 1;

	// String Sql de creaci�n de la tabla
	// se ejecutar� si no existe la BD, o sea, la primera vez
	public final String SQLCREAR = "create table equipo "+
			 " (idEquipo integer primary key autoincrement, " +
			 " nombreEquipo text not null);";

	/**
	 * Constructor
	 * llama al constructor padre
	 * Le pasa el contexto, que se refiere al activity actual
	 * @param contexto 
	 */
	SqLiteHelper(Context contexto) {
		super(contexto, NOMBRE_BD, null, VERSION_BD);
	}

	/**
	 * onCreate
	 * Se ejecuta en caso de que la BD no exista
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se ejecuta la sentencia de creaci�n de la tabla equipo.
		db.execSQL("DROP TABLE IF EXISTS equipo");
		db.execSQL(SQLCREAR);
		Log.d("DEBUG","Ok, BD creada");
	}

	/**
	 * onUpgrade
	 * Se ejecuta de forma autom�ticamente en caso de que
	 * estemos en una nueva versi�n.
	 *  
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Sacamos un log para contar lo que ha pasado
		Log.w("SqLiteHelper", "Actualizando desde versi�n " + oldVersion
				+ " a " + newVersion + ", los datos ser�n eliminados");

		// En este caso en el upgrade realmente
		// lo que hacemos es cargarnos lo que hay...
		db.execSQL("DROP TABLE IF EXISTS equipo");

		// ... y lo volvemos a generar
		onCreate(db);
		Log.d("DEBUG","Ok, BD regenerada");
	}
	
}