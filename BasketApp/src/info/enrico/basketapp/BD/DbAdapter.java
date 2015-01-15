package info.enrico.basketapp.BD;

import info.enrico.basketapp.Class.Equipo;
import info.enrico.basketapp.Class.Jugador;

import java.util.ArrayList;


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
	 * @param nombreEquipoInsertar
	 * @return 0 = Se ha insertado correctamente. -1 = No se ha insertado nada.
	 */
	public int insertarEquipo(String nombreEquipoInsertar) {
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		String insertQuery = "";
			  		 
		insertQuery = "INSERT INTO equipos (nombreEquipo) VALUES ('" + nombreEquipoInsertar + "');";

		Log.e("ENRICO", "Query insertar equipo realizada");
		final Cursor a = db.rawQuery(insertQuery, null);
		if (a != null) {
			if (a.moveToFirst()) {
				do {
					
				} while (a.moveToNext());
					   
				a.close();
				return 0; //Todo correcto
			}
		}
		a.close();
		return -1; //No se inserta nada
	}
	
	/**
	 * insertarJugador
	 * Inserta un registro con los datos del jugador
	 * 
	 * @param todos los del jugador
	 * @return 0 = Se ha insertado correctamente. -1 = No se ha insertado nada.
	 */
	public int insertarJugador(String nombreJugadorInsertar, String idEquipoJugadorInsertar, 
			String fechNacJugadorInsertar, int pesoJugadorInsertar, int alturaJugadorInsertar, 
			int tfnJugadorInsertar, String imagenJugadorInsertar, String detallesJugadorInsertar) {
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		String insertQuery = "";
			  		 
		insertQuery = "INSERT INTO jugadores (nombreJugador, idEquipoJugador, fechNacJugador, " +
				"pesoJugador, alturaJugador, tfnJugador, imagenJugador, detallesJugador) VALUES ('" + 
				nombreJugadorInsertar + "','" + 
				idEquipoJugadorInsertar + "','" + 
				fechNacJugadorInsertar + "','" + 
				pesoJugadorInsertar + "','" + 
				alturaJugadorInsertar + "','" + 
				tfnJugadorInsertar + "','" + 
				imagenJugadorInsertar + "','" + 
				detallesJugadorInsertar  
				+ "');";

		Log.e("ENRICO", "Query insertar jugador realizada");
		final Cursor a = db.rawQuery(insertQuery, null);
		if (a != null) {
			if (a.moveToFirst()) {
				do {
					
				} while (a.moveToNext());
					   
				a.close();
				return 0; //Todo correcto
			}
		}
		a.close();
		return -1; //No se inserta nada
	}

	/**
	 * borrarEquipo
	 * Borra el registro que tiene el id especificado.
	 * Y actualiza el idEquipoJugador de los jugadores afectados
	 * @param idEquipoBorrar id del registro a borrar
	 * @return Devuelve el nº de registros afectados.
	 * @return 0 = No se han borrado equipos. -1 = No se han actualizado jugadores.
	 */
	public int borrarEquipo(int idEquipoBorrar) {
		int numEquipos = 0; //Numero de equipos borrados, simplemente por si me interesa indicarlo
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		String deleteQuery = "";
		String updateQuery = "";
			  		 
		deleteQuery = "DELETE FROM equipos WHERE idEquipo='" + idEquipoBorrar + "';";

		Log.e("ENRICO", "Query delete equipo realizada");
		final Cursor a = db.rawQuery(deleteQuery, null);
		if (a != null) {
			if (a.moveToFirst()) {
				do {
					numEquipos+=1;     					
				} while (a.moveToNext());
				a.close();
				//Una vez borrado el equipo con ese id, cambiamos los idEquipo de los jugadores a 0
				updateQuery = "UPDATE jugadores SET idEquipoJugador='0' WHERE idEquipoJugador='" + idEquipoBorrar + "';";

				Log.e("ENRICO", "Query update jugadores realizada");
				final Cursor b = db.rawQuery(updateQuery, null);
				if (b != null) {
					if (b.moveToFirst()) {
						do {
							//Si en un futuro quiero informar de los jugadores afectados aumentar aqui el valor
							//numJugadores +=1;
						} while (b.moveToNext());
						b.close();
					}
				}else{
					return -1;//No se ha actualizado ningún jugador
				}
				return numEquipos;
			}
		}
		a.close();
		return 0; //No se ha eliminado ningun equipo
	}
	
	/**
	 * borrarJugador
	 * Borra el registro que tiene el id especificado.
	 * @param idEquipoBorrar id del registro a borrar
	 * @return Devuelve el nº de registros afectados.
	 * @return 0 = No se han borrado jugadores.
	 */
	public int borrarJugador(int idJugadorBorrar) {
		int numJugadores = 0; //Numero de jugadores borrados, simplemente por si me interesa indicarlo
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		String deleteQuery = "";
			  		 
		deleteQuery = "DELETE FROM jugadores WHERE idJugador='" + idJugadorBorrar + "';";

		Log.e("ENRICO", "Query delete jugador realizada");
		final Cursor a = db.rawQuery(deleteQuery, null);
		if (a != null) {
			if (a.moveToFirst()) {
				do {
					numJugadores+=1;     					
				} while (a.moveToNext());
				a.close();				
				return numJugadores;
			}
		}
		a.close();
		return 0; //No se ha eliminado ningun jugador
	}
		
	/**
	 * obtenerEquipo
	 * Obtiene el equipo que tiene el id especificado
	 * 
	 * @param idEquipoObtener id del registro que se quiere obtener.
	 * @return equipoObtenido
	 */
	public Equipo obtenerEquipo(int idEquipoObtener) {
		Equipo equipoObtenido;
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		String selectQuery = "";
			  		 
		selectQuery = "SELECT * FROM equipos WHERE idEquipo='" + idEquipoObtener + "';";

		Log.e("ENRICO", "Query select equipo realizada");
		final Cursor a = db.rawQuery(selectQuery, null);
		if (a != null) {
			if (a.moveToFirst()) {
				do {
					final int idEquipo = a.getInt(a.getColumnIndex("idEquipo"));
					final String nombreEquipo = a.getString(a.getColumnIndex("nombreEquipo"));     
			      		      
					equipoObtenido = new Equipo(idEquipo,nombreEquipo);			       
					
				} while (a.moveToNext());
					   
				a.close();
				return equipoObtenido;
			}
		}
		a.close();
		return null;
	}
	
	/**
	 * obtenerJugador
	 * Obtiene el jugador que tiene el id especificado
	 * 
	 * @param idJugadorObtener id del registro que se quiere obtener.
	 * @return jugadorObtenido
	 */
	public Jugador obtenerJugador(int idJugadorObtener) {
		Jugador jugadorObtenido;
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		String selectQuery = "";
			  		 
		selectQuery = "SELECT * FROM jugadores WHERE idJugador='" + idJugadorObtener + "';";

		Log.e("ENRICO", "Query select jugador realizada");
		final Cursor a = db.rawQuery(selectQuery, null);
		if (a != null) {
			if (a.moveToFirst()) {
				do {
					final int idJugador = a.getInt(a.getColumnIndex("idJugador"));
					final String nombreJugador = a.getString(a.getColumnIndex("nombreJugador"));
					final int idEquipoJugador = a.getInt(a.getColumnIndex("idEquipoJugador")); 
					final String fechNacJugador = a.getString(a.getColumnIndex("fechNacJugador")); 
					final int pesoJugador = a.getInt(a.getColumnIndex("pesoJugador")); 
					final int alturaJugador = a.getInt(a.getColumnIndex("alturaJugador")); 
					final int tfnJugador = a.getInt(a.getColumnIndex("tfnJugador")); 
					final String imagenJugador = a.getString(a.getColumnIndex("imagenJugador")); 
					final String detallesJugador = a.getString(a.getColumnIndex("detallesJugador")); 
			      		      
					jugadorObtenido = new Jugador(idJugador,nombreJugador,idEquipoJugador,fechNacJugador,pesoJugador,alturaJugador,tfnJugador,imagenJugador,detallesJugador);			       
					
				} while (a.moveToNext());
					   
				a.close();
				return jugadorObtenido;
			}
		}
		a.close();
		return null;
	}
	
	/**
	 * obtenerEquipos
	 * Obtiene todos los equipos de la tabla equipos.
	 * 
	 * @return Devuelve un array con todos los equipos
	 */
	public ArrayList<Equipo> obtenerEquipos() {
		final ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		String selectQuery = "";
			  		 
		selectQuery = "SELECT * FROM equipos;";

		Log.e("ENRICO", "Query select equipos realizada");
		final Cursor a = db.rawQuery(selectQuery, null);
		if (a != null) {
			if (a.moveToFirst()) {
				do {
					final int idEquipo = a.getInt(a.getColumnIndex("idEquipo"));
					final String nombreEquipo = a.getString(a.getColumnIndex("nombreEquipo"));     
			      		      
					final Equipo e = new Equipo(idEquipo,nombreEquipo);			       
					equipos.add(e);
				} while (a.moveToNext());
					   
				a.close();
				return equipos;
			}
		}
		a.close();
		return null;
	}
	
	/**
	 * obtenerJugadores
	 * Obtiene todos los jugadores de la tabla jugadores.
	 * 
	 * @return Devuelve un array con todos los jugadores
	 */
	public ArrayList<Jugador> obtenerJugadores() {
		final ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		String selectQuery = "";
			  		 
		selectQuery = "SELECT * FROM equipos;";

		Log.e("ENRICO", "Query select jugadores realizada");
		final Cursor a = db.rawQuery(selectQuery, null);
		if (a != null) {
			if (a.moveToFirst()) {
				do {
					final int idJugador = a.getInt(a.getColumnIndex("idJugador"));
					final String nombreJugador = a.getString(a.getColumnIndex("nombreJugador"));
					final int idEquipoJugador = a.getInt(a.getColumnIndex("idEquipoJugador")); 
					final String fechNacJugador = a.getString(a.getColumnIndex("fechNacJugador")); 
					final int pesoJugador = a.getInt(a.getColumnIndex("pesoJugador")); 
					final int alturaJugador = a.getInt(a.getColumnIndex("alturaJugador")); 
					final int tfnJugador = a.getInt(a.getColumnIndex("tfnJugador")); 
					final String imagenJugador = a.getString(a.getColumnIndex("imagenJugador")); 
					final String detallesJugador = a.getString(a.getColumnIndex("detallesJugador")); 
			      		      
					final Jugador e = new Jugador(idJugador,nombreJugador,idEquipoJugador,fechNacJugador,pesoJugador,alturaJugador,tfnJugador,imagenJugador,detallesJugador);			       
								       
					jugadores.add(e);
				} while (a.moveToNext());
					   
				a.close();
				return jugadores;
			}
		}
		a.close();
		return null;
	}
	
	/**
	 * actualizarEquipo
	 * Hace un UPDATE de los valores del registro cuyo id es idEquipoActualizar.
	 * 
	 * @param int idEquipoActualizar id del equipo que se quiere modificar.
	 * @param nombreEquipoActualizar
	 * @return 0 = No se ha actualizado ningun equipo. 
	 * @return num = Numero de equipos actualizados. Deberia ser 1 ya que el id es unico.
	 */
	public int actualizarEquipo(int idEquipoActualizar, String nombreEquipoActualizar) {
		int num = 0; //Numero de equipos actualizados
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		String updateQuery = "";
			  		 
		updateQuery = "UPDATE equipos SET nombreEquipo='" + nombreEquipoActualizar 
				+ "' WHERE idEquipo='" + idEquipoActualizar + "';";

		Log.e("ENRICO", "Query update equipo realizada");
		final Cursor a = db.rawQuery(updateQuery, null);
		if (a != null) {
			if (a.moveToFirst()) {
				do {
					num +=1;
				} while (a.moveToNext());
					   
				a.close();
				return num; //Numero de equipos actualizados. Deberia ser 1
			}
		}
		a.close();
		return 0; //No se ha actualizado ningun equipo
	}
	
	/**
	 * actualizarJugador
	 * Hace un UPDATE de los valores del registro cuyo id es idJugadorActualizar.
	 * 
	 * @param Todos los registros del jugador
	 * @return 0 = No se ha actualizado ningun jugador. 
	 * @return num = Numero de jugadores actualizados. Deberia ser 1 ya que el id es unico.
	 */
	public int actualizarJugador(int idJugadorActualizar, String nombreJugadorActualizar, 
			String idEquipoJugadorActualizar, String fechNacJugadorActualizar, 
			int pesoJugadorActualizar, int alturaJugadorActualizar, int tfnJugadorActualizar, 
			String imagenJugadorActualizar, String detallesJugadorActualizar) {
		int num = 0; //Numero de jugadores actualizados
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		String updateQuery = "";
			  		 
		updateQuery = "UPDATE jugadores SET nombreJugador='" + nombreJugadorActualizar 
				+ "', idEquipoJugador='" + idEquipoJugadorActualizar 
				+ "', fechNacJugador='" + fechNacJugadorActualizar 
				+ "', pesoJugador='" + pesoJugadorActualizar 
				+ "', alturaJugador='" + alturaJugadorActualizar 
				+ "', tfnJugador='" + tfnJugadorActualizar 
				+ "', imagenJugador='" + imagenJugadorActualizar 
				+ "', detallesJugador='" + detallesJugadorActualizar
				+ "' WHERE idJugador='" + idJugadorActualizar + "';";

		Log.e("ENRICO", "Query update jugador realizada");
		final Cursor a = db.rawQuery(updateQuery, null);
		if (a != null) {
			if (a.moveToFirst()) {
				do {
					num +=1;
				} while (a.moveToNext());
					   
				a.close();
				return num; //Numero de jugadores actualizados. Deberia ser 1
			}
		}
		a.close();
		return 0; //No se ha actualizado ningun jugador
	}
}