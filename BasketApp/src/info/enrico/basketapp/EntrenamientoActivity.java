package info.enrico.basketapp;

import info.enrico.basketapp.R;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class EntrenamientoActivity extends PreferenceActivity {
	Button botonmenu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
    	
        super.onCreate(savedInstanceState);
 
        addPreferencesFromResource(R.xml.preferences);
        setContentView(R.layout.preferences_layout);
        botonmenu = (Button) this.findViewById(R.id.botonmenu);
        botonmenu.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	Intent miIntent = new Intent(EntrenamientoActivity.this, MenuActivity.class);
    	    	startActivity(miIntent);
    	    	EntrenamientoActivity.this.finish();
    	    }
    	});
    }
}