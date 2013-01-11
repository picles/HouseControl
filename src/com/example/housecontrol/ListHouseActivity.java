package com.example.housecontrol;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListHouseActivity extends ListActivity {

	protected ListView houseList;
	private HouseListAdapter listAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_list_house);
		
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
        /*!
         * Create database if it is not created yet
         */
        HouseDBAdapter adapter = new HouseDBAdapter(getApplicationContext());
        
        /*!
         * Fill the listview
         */
        
        houseList = getListView();
        houseList.setTextFilterEnabled(true);
        
        ArrayList<House> house = adapter.getHouses(); 
        listAdapter = new HouseListAdapter(getApplicationContext(), R.layout.house_item,house);
        houseList.setAdapter(listAdapter);
        /*!
         * Register a callback to be invoked when an item in this AdapterView 
         * has been clicked.
         */
        houseList.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ListHouseActivity.this, ConfigureFloorActivity.class);
				Bundle params = new Bundle();
				params.putBoolean(ApplicationGlobals.kShouldLoadDataFromDB, true);
				House h = (House)listAdapter.getItem(arg2);
				params.putLong(ApplicationGlobals.kFloorID,h.getId());
		        intent.putExtras(params);
		        startActivity(intent);
			}
		});
        
        registerForContextMenu(houseList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_house, menu);
		
		MenuItem addNewHouse = menu.add(0, 1, 0, "Add").setIcon(R.drawable.ic_menu_add);
		MenuItem addSettings = menu.add(0, 2, 0, "Settings").setIcon(R.drawable.ic_action_settings);
		addNewHouse.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		addSettings.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
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
		case 1:
			intent = new Intent(ListHouseActivity.this, CreateHouse.class);
			startActivity(intent);
			return true;
		case 2:
			intent = new Intent(ListHouseActivity.this, HouseControlSettings.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
