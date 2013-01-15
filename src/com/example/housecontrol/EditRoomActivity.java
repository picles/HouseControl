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
import android.widget.TextView;
import android.widget.Toast;

public class EditRoomActivity extends Activity {
	
	long mRoomId = -1;
	
	Button mSaveButton = null;
	EditText mEstoreTxb = null;
	EditText mLuzesTxb = null;
	EditText mPortasTxb = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_room);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent myIntent = getIntent(); // gets the previously created intent
		
		String roomTitle = myIntent.getStringExtra(ApplicationGlobals.kRoomName);
		this.mRoomId = myIntent.getLongExtra(ApplicationGlobals.kRoomID, -1);
		System.out.println("EditRoom GetId: "+this.mRoomId);
		
		TextView titleText = (TextView)findViewById(R.id.txtTitleRoom);
		titleText.setText(roomTitle);
		
		this.mEstoreTxb = (EditText)findViewById(R.id.txbEstores);
		this.mLuzesTxb = (EditText)findViewById(R.id.txbLuzes);
		this.mPortasTxb = (EditText)findViewById(R.id.txbPortas);
		
		this.mSaveButton = (Button)findViewById(R.id.save_equipment_button);
		this.mSaveButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				HouseDBAdapter dbAdapter = new HouseDBAdapter(getApplicationContext());
				if (mEstoreTxb.getText() != null) {
					int estore = Integer.parseInt(mEstoreTxb.getText().toString());
					for (int i=0 ; i< estore; ++i) { 
						dbAdapter.InsertEquipments(mRoomId, ApplicationGlobals.kEstoresDBKey , "", 0);
					}
				}
				if (mLuzesTxb.getText() != null) {
					int luzes = Integer.parseInt(mLuzesTxb.getText().toString());
					for (int i=0 ; i< luzes; ++i) { 
						dbAdapter.InsertEquipments(mRoomId, ApplicationGlobals.kLuzesDBKey , "", 0);
					}
				}
				if (mPortasTxb.getText() != null) {
					int portas = Integer.parseInt(mPortasTxb.getText().toString());
					for (int i=0 ; i< portas; ++i) { 
						dbAdapter.InsertEquipments(mRoomId, ApplicationGlobals.kPortasDBKey , "", 0);
					}
				}
				
				Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_edit_room, menu);
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
