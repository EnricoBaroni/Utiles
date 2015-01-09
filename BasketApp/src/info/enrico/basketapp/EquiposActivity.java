package info.enrico.basketapp;

import info.enrico.basketapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EquiposActivity extends Activity {
	TextView game_title;
	String mode;
	boolean confirm;
	static boolean yesOrNo = false;
	ListView lstJugadores;
    /** Called when the activity is first created. */
	
	 // Defined Array values to show in ListView
    String[] values = new String[] { "Android List View", 
                                     "Adapter implementation",
                                     "Simple List View In Android",
                                     "Create List View Android", 
                                     "Android Example", 
                                     "List View Source Code", 
                                     "List View Array Adapter", 
                                     "Android Example List View" 
                                    };
    //ArrayList<String> asd = new ArrayList<String>(); //USO BIDIMENSIONAL	
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, android.R.id.text1, values);
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    	
               
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipo);
        
        lstJugadores = (ListView) findViewById(R.id.lstEquipos);
        lstJugadores.setAdapter(adapter);      
    }
    
    
}