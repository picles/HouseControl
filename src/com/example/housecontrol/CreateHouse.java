package com.example.housecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class CreateHouse extends Activity {
	public static String kHouseName = "house_name";
	public static String kNrFloors = "nr_floors";

	private EditText mEditTextHouseName;
	private NumberPicker mNumberPickerNrFloors;
	private Button mButtonNext;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_house);
        
        mEditTextHouseName = (EditText)findViewById(R.id.editTextHouseName);
        
        mNumberPickerNrFloors = (NumberPicker) findViewById(R.id.numberPickerNrFloors); 
        mNumberPickerNrFloors.setMaxValue(3);
        mNumberPickerNrFloors.setMinValue(1);
        
        mButtonNext = (Button)findViewById(R.id.ButtonNext);
        mButtonNext.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				String text = mEditTextHouseName.getText().toString(); 
				
				if (text == null || text.isEmpty() == true) {
					//
					Toast notification = Toast.makeText(getApplicationContext(), "NO!", Toast.LENGTH_SHORT);
					notification.show();
					return;
				}
			
				Bundle params = new Bundle();
				params.putString(kHouseName, text);
				params.putInt(kNrFloors, mNumberPickerNrFloors.getValue());
				
				Intent floorCreator = new Intent(CreateHouse.this, FloorCreator.class);
				floorCreator.putExtras(params);
				startActivity(floorCreator);
			}
        });
        
    
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_house, menu);
        return true;
    }
}
