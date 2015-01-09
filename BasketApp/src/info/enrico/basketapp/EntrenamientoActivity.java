package info.enrico.basketapp;

import info.enrico.basketapp.R;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class EntrenamientoActivity extends PreferenceActivity {
	Button botonbuscar;
	Button botoneliminar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d("ENRICO","onCreate Entrenamiento");
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
    	
        super.onCreate(savedInstanceState);
        
        addPreferencesFromResource(R.xml.preferences);
        Log.d("ENRICO","Cargando layout");
        setContentView(R.layout.entrenamientos);   
        
        Log.d("ENRICO","Cargando botones");        
        botonbuscar = (Button) this.findViewById(R.id.botonbuscar_entrenamiento);
        botoneliminar = (Button) this.findViewById(R.id.botoneliminar_entrenamiento);
        
        Log.d("ENRICO","OnClick Buscar");
        botonbuscar.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Intent miIntent = new Intent(EntrenamientoActivity.this, EquiposActivity.class);
    	    	startActivity(miIntent);
    	    	EntrenamientoActivity.this.finish();
    	    }
    	});
        
        Log.d("ENRICO","OnClick Eliminar");
        botoneliminar.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Intent miIntent = new Intent(EntrenamientoActivity.this, EquiposActivity.class);
    	    	startActivity(miIntent);
    	    	EntrenamientoActivity.this.finish();
    	    }
    	});
    }
}