package info.enrico.basketapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        String[] items = { getResources().getString(R.string.entrenamiento_title),
                getResources().getString(R.string.equipo_title),
                getResources().getString(R.string.calendario_title),
                getResources().getString(R.string.ayuda_title) };

        ListView menuLista = (ListView) findViewById(R.id.ListView_Menu);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
        	    R.layout.menu_item, items);
        
        menuLista.setAdapter(adaptador);
        
        menuLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View itemClicked,
                int position, long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();
                if (strText.equalsIgnoreCase(getResources().getString(
                        R.string.entrenamiento_title))) {

                    startActivity(new Intent(MenuActivity.this,
                    		EntrenamientoActivity.class));
                } else if (strText.equalsIgnoreCase(getResources().getString(
                        R.string.equipo_title))) {
 
                    startActivity(new Intent(MenuActivity.this,
                    		EquipoActivity.class));
                } else if (strText.equalsIgnoreCase(getResources().getString(
                        R.string.calendario_title))) {

                    startActivity(new Intent(MenuActivity.this,
                    		CalendarioActivity.class));
                } else if (strText.equalsIgnoreCase(getResources().getString(
                        R.string.ayuda_title))) {

                    startActivity(new Intent(MenuActivity.this,
                    		AyudaActivity.class));
                }
            }
          });
    }
}
        