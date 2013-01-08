package com.example.housecontrol;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EquipmentCommActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_equipment_comm);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_equipment_comm, menu);
		return true;
	}

}
