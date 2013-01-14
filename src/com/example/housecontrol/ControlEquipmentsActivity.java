package com.example.housecontrol;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class ControlEquipmentsActivity extends Activity {

	private Room mRoom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control_equipment);
		long roomId = getIntent().getLongExtra(ApplicationGlobals.kRoomID, -1);
		HouseDBAdapter dbAdapter = new HouseDBAdapter(getApplicationContext());
		this.mRoom = dbAdapter.getRoomById(roomId);
		
		ConfigureEquipmentsListAdapter adapter = new ConfigureEquipmentsListAdapter(this, R.layout.listitem_control_equipment, this.mRoom.getEquipmentsList());	
		ListView list = (ListView)findViewById(R.id.listView_equipments);
		list.setAdapter(adapter);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_control_equipment, menu);
		return true;
	}

}
