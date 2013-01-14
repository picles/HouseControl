package com.example.housecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

public class ControlEquipmentsActivity extends Activity{

	private Room mRoom;
	private ListView mListView;
	private ConfigureEquipmentsListAdapter mListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control_equipment);
		long roomId = getIntent().getLongExtra(ApplicationGlobals.kRoomID, -1);
		HouseDBAdapter dbAdapter = new HouseDBAdapter(getApplicationContext());
		this.mRoom = dbAdapter.getRoomById(roomId);
		
		this.mListAdapter = new ConfigureEquipmentsListAdapter(this, R.layout.listitem_control_equipment, this.mRoom.getEquipmentsList());	
		this.mListView = (ListView)findViewById(R.id.list);
		this.mListView.setAdapter(this.mListAdapter);
		
		registerForContextMenu(mListView);	
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.list) {
	    AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	    menu.add(0, v.getId(), 0, "Edit");
	  }
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		Equipment eq = this.mListAdapter.getItem(menuInfo.position);
		//Intent intent = new Intent(this, cls)
		
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_control_equipment, menu);
		return true;
	}
}
