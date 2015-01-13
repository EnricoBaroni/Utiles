package info.enrico.basketapp.Calendar;

import info.enrico.basketapp.MenuActivity;
import info.enrico.basketapp.R;
import info.enrico.basketapp.R.id;
import info.enrico.basketapp.R.layout;
import info.enrico.basketapp.R.xml;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class CalendarioActivity extends PreferenceActivity {
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
    	    	Intent miIntent = new Intent(CalendarioActivity.this, MenuActivity.class);
    	    	startActivity(miIntent);
    	    	CalendarioActivity.this.finish();
    	    }
    	});
    }
}