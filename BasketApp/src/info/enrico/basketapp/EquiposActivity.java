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
	
	 // Defined Array values to show in ListView
    String[] values = new String[] { "Burlada", 
                                     "Mendillorri",
                                     "San Cernin",
                                     "Larraona"
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
        setContentView(R.layout.equipos);
        
        lstJugadores = (ListView) findViewById(R.id.lstEquipos);
        lstJugadores.setAdapter(adapter);      
    }
    
    
}