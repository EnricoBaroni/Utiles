package info.enrico.basketapp.Activity;

import info.enrico.basketapp.R;
import info.enrico.basketapp.R.id;
import info.enrico.basketapp.R.layout;
import info.enrico.basketapp.R.raw;

import java.io.InputStream;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class AyudaActivity extends Activity {
	TextView helpText;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	//Pantalla Completa
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayuda);
        
        // As� es como cargamos un fichero que tenemos en 
        // la carpeta RAW de los resources
        InputStream iFile = getResources().openRawResource(R.raw.help);
        
        // Cargamos el textview donde pondremos la chapa
        TextView helpText = (TextView) findViewById(R.id.txtInfoApp);
        
        // Cargamos el contenido del fichero
        String strFile = leerFichero(iFile);
        
        // Y ponemos ese contenido en el TextView
        helpText.setText(strFile);
        
        // Si pinchamos en el bot�n para volver al men�
        // lo que hacemos es cerrar la activity actual.
        Button botonmenu = (Button) findViewById(R.id.botonApps);
       botonmenu.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	AyudaActivity.this.finish();
    	    }
    	});
    }
    
    /**
     * leerFichero
     * Le pasamos un inputStream y se encarga en devolver todo el contenido
     * en forma de un �nico String.
     * Versi�n impresionante y r�pida  tal y como se cuenta en:
     * http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
     *  reading files like a pro
     * @param iFile
     * @return String
     */
	private String leerFichero(InputStream iFile) {
	    	    try {
	    	        return new java.util.Scanner(iFile,"UTF-8").useDelimiter("\\A").next();
	    	    } catch (java.util.NoSuchElementException e) {
	    	        return "";
	    	    } 
	      
	  }
}