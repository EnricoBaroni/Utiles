package info.enrico.basketapp;

import info.enrico.basketapp.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class EquiposActivity extends ListActivity {
	Button botonanadir;
	Button botoneliminar;
	Button botoneditar;
	ListView lstEquipos;
	
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
        String[] items = new String[] { "Burlada", 
                                         "Mendillorri",
                                         "San Cernin",
                                         "Larraona"
                                        };
        
        
        botonanadir = (Button) this.findViewById(R.id.botonanadir);
        botoneliminar = (Button) this.findViewById(R.id.botoneliminar);
        botoneditar = (Button) this.findViewById(R.id.botoneditar);
        lstEquipos = getListView();  
        
      //ArrayList<String> asd = new ArrayList<String>(); //USO BIDIMENSIONAL	
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
        	    R.layout.menu_item, items);
        lstEquipos.setAdapter(adaptador);
        
        
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
}