package info.enrico.intents;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends Activity {

	private TextView textViewResult; //Resultado
	private EditText editText; //Introducir variable
	private EditText editText2; //Introducir variable 2
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		 textViewResult = (TextView) findViewById(R.id.textView1);
		 editText = (EditText) findViewById(R.id.editText1);
		 editText2 = (EditText) findViewById(R.id.editText2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.other, menu);
		return true;
	}
	
	/**
     * called when button is pressed
     * @param v
     */
    public void sumar (View v){
    	System.out.println("You clicked the button");
    	Log.d("ENRICO", "Button click");
    	
    	double valor = ((Double.parseDouble(editText.getText().toString() + editText2.getText().toString()))); //Igual esta sumando strings Y POR ESO NO FUNCIONA
    	Intent intent = new Intent();
    	intent.putExtra("otherData", String.valueOf(valor));
    	setResult(RESULT_OK, intent);
    	finish();	
    	     
    }

}
