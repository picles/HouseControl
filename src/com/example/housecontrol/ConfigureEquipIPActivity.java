package com.example.housecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ConfigureEquipIPActivity extends Activity {

	 private Button mbtnSave;
	 private long mEquipmentID = -1;
	 private EditText mEquipmentIP;
	 private EditText mEquipmentPort;
	 HouseDBAdapter dbAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configure_equip_ip);
		
		Intent myIntent= getIntent(); // gets the previously created intent
		mEquipmentID = myIntent.getLongExtra(ApplicationGlobals.kEquipmentID, -1);
		
		mbtnSave = (Button)findViewById(R.id.ButtonNext);
		mEquipmentIP = (EditText)findViewById(R.id.txbIP);
		mEquipmentPort = (EditText)findViewById(R.id.txbPort);
		dbAdapter = new HouseDBAdapter(getApplicationContext());
		mEquipmentIP.setText(dbAdapter.getEquipmentIPById(mEquipmentID));
		mEquipmentPort.setText(dbAdapter.getEquipmentPortById(mEquipmentID));
		
		mbtnSave.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				dbAdapter.updateFloor(mEquipmentID, mEquipmentIP.getText().toString(), Integer.getInteger(mEquipmentPort.getText().toString(), 0));
			}
        });
        
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_configure_equip_ip, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
