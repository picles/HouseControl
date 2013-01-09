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
	public final static String kHouseName = "house_name";
	public final static String kNrFloors = "nr_floors";
	public final static String kHouseID = "HouseID";
	public final static String kFloorID = "FloorID";
	public final static String kRoomID = "RoomID";
	public final static String kRoomName = "RoomName";
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
					Toast notification = Toast.makeText(getApplicationContext(), "Necessário inserir descrição", Toast.LENGTH_SHORT);
					notification.show();
					return;
				}
			
				HouseDBAdapter houseadapter = new HouseDBAdapter(getApplicationContext());
				
				long lHouseID = houseadapter.InsertLayout(text);
				
				if(lHouseID > 0)
				{
					Bundle params = new Bundle();
					params.putString(kHouseName, text);
					params.putInt(kNrFloors, mNumberPickerNrFloors.getValue());
					params.putLong(kHouseID, lHouseID);
					Intent floorCreator = new Intent(CreateHouse.this, FloorCreator.class);
					floorCreator.putExtras(params);
					startActivity(floorCreator);
				}
				else
				{
					Toast notification = Toast.makeText(getApplicationContext(), "Erro ao inserir o layout. Por favor tente de novo", Toast.LENGTH_SHORT);
					notification.show();
					return;
				}
			}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_house, menu);
        return true;
    }
}
