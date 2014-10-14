package info.enrico.intents;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewConversor; //Resultado
	private TextView textViewAdd; //Resultado
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textViewConversor = (TextView) findViewById(R.id.TextViewConversor);
		textViewAdd = (TextView) findViewById(R.id.TextViewAdd);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void openActivityConversor (View v){		
		Log.d("ENRICO","Opening Conversor Activity");
		Intent intent = new Intent(this, ConversorActivity.class);		    	    
		startActivityForResult(intent,12);
	}

	public void openActivityAdd (View v){		
		Log.d("ENRICO","Opening Add Activity");
		Intent intent = new Intent(this, AddActivity.class);
		startActivityForResult(intent,13);
	}
	
	public void onActivityConvert(int requestCode, int resultCode, Intent num){
		super.onActivityResult(requestCode, resultCode, num);	
		if(requestCode == 12){
			textViewConversor.setText("Resultado: " + num.getStringExtra("someData"));			
		}
	}

	public void onActivityAdd(int requestCode, int resultCode, Intent num){
		super.onActivityResult(requestCode, resultCode, num);	
		if(requestCode == 13){
			textViewAdd.setText("Resultado: " + num.getStringExtra("otherData"));			
		}
	}
}
