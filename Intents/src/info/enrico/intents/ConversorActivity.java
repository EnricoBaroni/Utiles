package info.enrico.intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ConversorActivity extends Activity {

	private static final double PESETAS_TO_EUROS = 166.386;
	private TextView textViewResult; //Resultado
	private EditText editText; //Introducir datos
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversor);	
		
	    textViewResult = (TextView) findViewById(R.id.textView1);
	    editText = (EditText) findViewById(R.id.editText1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conversor, menu);
		return true;
	}

	 /**
     * called when button is pressed
     * @param v
     */
    public void convert (View v){
    	System.out.println("You clicked the button");
    	Log.d("ENRICO", "Button click");
    	
    	double valor = ((Double.parseDouble(editText.getText().toString()))* PESETAS_TO_EUROS);    	
    	Intent intent = new Intent();
    	intent.putExtra("someData", String.valueOf(valor));
    	setResult(RESULT_OK, intent);
    	finish();
    }    
}
