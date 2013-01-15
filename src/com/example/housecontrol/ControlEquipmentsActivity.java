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

	public static final int kEditEquipmentIntentKey = 1;
	
	private Room mRoom;
	private ListView mListView;
	private ConfigureEquipmentsListAdapter mListAdapter;
	private long mRoomId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control_equipment);
		mRoomId = getIntent().getLongExtra(ApplicationGlobals.kRoomID, -1);
		HouseDBAdapter dbAdapter = new HouseDBAdapter(getApplicationContext());
		this.mRoom = dbAdapter.getRoomById(mRoomId);
		
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
		Intent intent = new Intent(this, ConfigureEquipIPActivity.class);
		intent.putExtra(ApplicationGlobals.kEquipmentID, eq.getEquipmentId());
		startActivityForResult(intent, kEditEquipmentIntentKey);
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_control_equipment, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent data)
	{
		switch (requestCode) {
		case kEditEquipmentIntentKey:
			HouseDBAdapter dbAdapter = new HouseDBAdapter(getApplicationContext());
			this.mRoom = dbAdapter.getRoomById(this.mRoomId);
			this.mListAdapter.setEquipments(this.mRoom.getEquipmentsList());
			this.mListAdapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
	}
}
