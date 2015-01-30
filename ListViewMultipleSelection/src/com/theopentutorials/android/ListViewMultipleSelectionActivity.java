package com.theopentutorials.android;
 
import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
 
public class ListViewMultipleSelectionActivity extends Activity implements
        OnClickListener {
    Button button;
    Button changebutton;
    ListView listView;
    ArrayAdapter<String> adapter;
    int change = 0; //0 Single, 1 Multiple
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        findViewsById();
 
        String[] sports = getResources().getStringArray(R.array.sports_array);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, sports);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setLongClickable(true);
        listView.setAdapter(adapter);    
        
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {        	
			public boolean onItemLongClick(AdapterView<?> arg0, View v, int position, long id) {
				if(change == 0){
					Log.d("ENRICO", "Item clickado en modo simple");
								 
			        Intent intent = new Intent(getApplicationContext(),
			                ResultActivity.class);
			 
			        String item = listView.getItemAtPosition(position).toString();
			        String[] items = new String[1];
			        items[0] = item;
			        
			        // Create a bundle object
			        Bundle b = new Bundle();
			        b.putStringArray("selectedItems", items);
			 
			        // Add the bundle to the intent.
			        intent.putExtras(b);
			 
			        // start the ResultActivity
			        startActivity(intent);	
				}else{
					Log.d("ENRICO", "Item clickado en modo Multiple");
					Log.d("ENRICO", "Nada deberia ocurrir");
				}
				return false;
			}
        });
        
		Log.d("ENRICO","OnClick Enviar");
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("ENRICO","Dentro OnClick Enviar");
				
				SparseBooleanArray checked = listView.getCheckedItemPositions();
		        ArrayList<String> selectedItems = new ArrayList<String>();
		        for (int i = 0; i < checked.size(); i++) {
		            // Item position in adapter
		            int position = checked.keyAt(i);
		            // Add sport if it is checked i.e.) == TRUE!
		            if (checked.valueAt(i))
		                selectedItems.add(adapter.getItem(position));
		        }
		 
		        String[] outputStrArr = new String[selectedItems.size()];
		 
		        for (int i = 0; i < selectedItems.size(); i++) {
		            outputStrArr[i] = selectedItems.get(i);
		        }
		 
		        Intent intent = new Intent(getApplicationContext(),
		                ResultActivity.class);
		 
		        // Create a bundle object
		        Bundle b = new Bundle();
		        b.putStringArray("selectedItems", outputStrArr);
		 
		        // Add the bundle to the intent.
		        intent.putExtras(b);
		 
		        // start the ResultActivity
		        startActivity(intent);
			}
		});

		
		Log.d("ENRICO","OnClick Cambiar");
		changebutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("ENRICO","Dentro OnClick Cambiar");
				change(view);
			}
		});
    }
 
    private void findViewsById() {
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.testbutton);
        changebutton = (Button) findViewById(R.id.changebutton);
    }
 
    public void change(View v){
    	String[] sports = getResources().getStringArray(R.array.sports_array);
    	if(change == 0){
    		adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_multiple_choice, sports);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);
            change = 1;
    	}else{
    		adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_single_choice, sports);
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            listView.setAdapter(adapter);
            change = 0;
    	}
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}