package info.enrico.basketapp;

import info.enrico.basketapp.R;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EquiposActivity extends ListActivity {
	Button botonanadir;
	Button botoneliminar;
	Button botoneditar;
	ListView lstEquipos;
	
	// Variable para manejar la base de datos.
		private DbAdapter db;
		private EditText nuevoEquipo;
		private TextView txtSeleccionado;
	    private Cursor cursor = null;
	    private int seleccionado = -1;
	
	TextView game_title;
	String mode;
	boolean confirm;
	static boolean yesOrNo = false;
	ListView lstJugadores;
	    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    	
               
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipos);
        
       // Defined Array values to show in ListView
       // String[] items = new String[] { "Burlada",
       //                                  "Mendillorri",
       //                                  "San Cernin",
       //                                  "Larraona"
       //                                 };
        
        // En esta caja de texto meteremos nuevos equipos
     	nuevoEquipo = (EditText) findViewById(R.id.etNuevoEquipo);

     	// Un textview que informa del equipo que se encuentra seleccionado actualmente
     	txtSeleccionado = (TextView) findViewById(R.id.txtseleccionado);
     	
     	// Crea un objeto manipulador de base de datos y abre una conexiï¿½n.
     	db = new DbAdapter(this);
     	db.open();

     	// Rellena el elemento ListView con los registros de la tabla equipos.
     	this.rellenarListView();
     	
     	
        botonanadir = (Button) this.findViewById(R.id.botonanadir);
        botoneliminar = (Button) this.findViewById(R.id.botoneliminar);
        botoneditar = (Button) this.findViewById(R.id.botoneditar);
        lstEquipos = getListView();  
        
        //ArrayList<String> asd = new ArrayList<String>(); //USO BIDIMENSIONAL	
        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
        //	    R.layout.menu_item, items);
        //lstEquipos.setAdapter(adaptador);
        
        
        Log.d("ENRICO","OnClick Añadir");
        botonanadir.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Log.d("ENRICO","Dentro OnClick Añadir");
    	    	//Intent miIntent = new Intent(EntrenamientoActivity.this, EquiposActivity.class);
    	    	//startActivity(miIntent);
    	    	//EntrenamientoActivity.this.finish();
    	    }
    	});
        Log.d("ENRICO","OnClick Eliminar");
        botoneliminar.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Log.d("ENRICO","Dentro OnClick Eliminar");
    	    	//Intent miIntent = new Intent(EntrenamientoActivity.this, EquiposActivity.class);
    	    	//startActivity(miIntent);
    	    	//EntrenamientoActivity.this.finish();
    	    }
    	});
        Log.d("ENRICO","OnClick Editar");
        botoneditar.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Log.d("ENRICO","Dentro OnClick Editar");
    	    	//Intent miIntent = new Intent(EntrenamientoActivity.this, EquiposActivity.class);
    	    	//startActivity(miIntent);
    	    	//EntrenamientoActivity.this.finish();
    	    }
    	});
    }
    
    /**
	 * rellenarListView
	 * Método con el que rellenamos el ListView con los registros
	 * de la BD
	 */
	void rellenarListView() {
		// Hacemos la consulta y obtenemos el cursor
		// que apunta a los datos
		cursor = db.obtenerEquipos();

		// Prepara el cursor para su uso.
		startManagingCursor(cursor);

		// Decimos qué campos queremos sacar
		String[] campos = new String[] {"idEquipo","nombreEquipo"};

		// Decimos dónde cargaremos los datos en cada item de la lista
		int[] dondeMostrarCampos = new int[] {R.id.jugador_num, R.id.jugador_nom };

		// Crea un adaptador para poder mostrar los datos en el ListView.
		SimpleCursorAdapter tareas = new SimpleCursorAdapter(this,R.layout.jugador_item, cursor, campos,dondeMostrarCampos);

		// Asigna el adaptador al ListView.
		lstEquipos.setAdapter(tareas);
		
		// Le asociamos un listener para saber cuál clickamos
		lstEquipos.setOnItemClickListener(new OnItemClickListener() {

		    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		    	// 	Sacamos el registro de la posición que han seleccionado (arg2)
		    	Cursor elementoSeleccionado = (Cursor) arg0.getItemAtPosition(arg2);

		    	// Nos guardamos el ID del registro
		    	seleccionado = elementoSeleccionado.getInt(0);
		    	
		    	// Sacamos info por el textview
		    	txtSeleccionado.setText("Has seleccionado: " + seleccionado);
		    	
		    	Log.d("DEBUG","Clickado el elemento con el identificador: " + seleccionado);
		    }
		});
	
		Log.d("DEBUG","Lista cargada desde BD");
	}


	/**
	 * insertarRegistro
	 * Toma la información de la caja de texto y la inserta
	 * como nuevo registro
	 * @param v
	 */
	public void insertarRegistro (View v) {
		String texto = nuevoEquipo.getText().toString();
		// Inserta los valores de las cajas de texto en la tabla notas.
		db.insertarEquipo(texto);

		// Actualiza los datos del elemento ListView.
		actualizarLista();

		// Notificamos al usuario
		Toast.makeText(getApplicationContext(), "Registro insertado: " + texto, Toast.LENGTH_SHORT).show();
		
		// Vacíamos la caja de texto
		nuevoEquipo.setText("");
	}
	
	/**
	 * eliminarRegistro
	 * Recupera el id del registro que habiamos seleccionado 
	 * y el manda a la BD que lo elimine
	 * @param v
	 */
	public void eliminarRegistro (View v) {
		
		// Si no tenemos nada seleccionado nos vamos.
		if (seleccionado == -1) {return;}
		
		// nos cargamos el registro de la BD
		db.borrarEquipo(seleccionado);

		// Hasta que no vuelvan a seleccionar algún elemento
		// de la lista no podremos entrar aquí
		seleccionado = -1;
		
    	// Sacamos info por el textview
    	txtSeleccionado.setText("Elemento eliminado");

		
		// Actualiza los datos del elemento ListView.
		actualizarLista();

		// Notificamos al usuario
		Toast.makeText(getApplicationContext(), "Registro eliminado: " + seleccionado, Toast.LENGTH_SHORT).show();

	}
	
	/**
	 * actualizarLista
	 * La lista tipo ListView tiene asociado un Cursor,
	 * nos basta con hacer un requery para que se refresque
	 */
	private void actualizarLista () {
		cursor.requery();
	}
}