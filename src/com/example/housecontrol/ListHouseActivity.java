package com.example.housecontrol;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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
        /*houseList.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(PeopleActivity.this, PersonDetails.class);
		        Person p = (Person)listAdapter.getItem(arg2);
		        intent.putExtra("PERSON_ID", p.getId());
		        startActivity(intent);
			}
		});*/
        
        registerForContextMenu(houseList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_house, menu);
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
