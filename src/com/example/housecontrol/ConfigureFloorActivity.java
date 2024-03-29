package com.example.housecontrol;

import java.util.ArrayList;
import java.util.LinkedList;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ConfigureFloorActivity extends FragmentActivity implements
		ActionBar.TabListener {


	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switOch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	private long mFloorID = -1;
	private boolean mShouldLoadDataFromDB = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_configure_floor_layout);
		
		Intent intent = getIntent();
		
		this.mShouldLoadDataFromDB = intent.getBooleanExtra(ApplicationGlobals.kShouldLoadDataFromDB, false);

		LinkedList<Floor> floors = null;
		mFloorID = intent.getLongExtra(ApplicationGlobals.kFloorID, -1);
		if (this.mShouldLoadDataFromDB == false) {
			floors = this.getFloorListFromIntent(intent);
		} else {
			floors = this.getFloorListFromDB();
		}
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(), floors, this.mFloorID);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_configure_floor_layout, menu);
		
		MenuItem addInitial = menu.add(0, 1, 0, "Add").setIcon(R.drawable.ic_menu_move_up);
		addInitial.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
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
			intent = new Intent(ConfigureFloorActivity.this, ListHouseActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}
	
	
	private LinkedList<Floor> getFloorListFromIntent(Intent aIntent)
	{
		LinkedList<Floor> floors = new LinkedList<Floor>();
		
		Floor floor = (Floor)aIntent.getSerializableExtra(FloorCreator.mSFirstFloorKey);
		floors.add(floor);
		
		floor = (Floor)aIntent.getSerializableExtra(FloorCreator.mSSecondFloorKey);
		if (floor != null) {
			floors.add(floor);
		}
		
		floor = (Floor)aIntent.getSerializableExtra(FloorCreator.mSThirdFloorKey);
		if (floor != null) {
			floors.add(floor);
		}
		return floors;
	}
	
	private LinkedList<Floor> getFloorListFromDB()
	{
		LinkedList<Floor> floors = new LinkedList<Floor>();
		HouseDBAdapter dbAdapter = new HouseDBAdapter(getApplicationContext());
		int numberOfFloors = dbAdapter.getNumberofFloors(this.mFloorID);
		for (int i=0; i<numberOfFloors; ++i) {
			ArrayList<Room> floorArrayList = dbAdapter.getRoomsbyFloorandFloorNb(this.mFloorID, i+1);
			Floor fl = new Floor();
			fl.setBedroomNb(floorArrayList.size());
			fl.setFloorNb(i+1);
			floors.add(fl);
		}
		
		return floors;
	}
	

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		
		private LinkedList<Floor> mFloorsList;
		private long mFloorID;
		
		public SectionsPagerAdapter(FragmentManager fm, LinkedList<Floor> aFloors, long FloorID) {
			super(fm);
			this.mFloorsList = aFloors;
			mFloorID = FloorID;
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			if (mShouldLoadDataFromDB == false) {
				Fragment fragment = new FloorFragment();
				Bundle args = new Bundle();
				args.putSerializable(FloorFragment.kFloorKey, mFloorsList.get(position));
				args.putLong(ApplicationGlobals.kFloorID, this.mFloorID);
				fragment.setArguments(args);
				return fragment;
			} else {
				Fragment fragment = new ConfigureFloorFragment();
				Bundle args = new Bundle();
				args.putSerializable(FloorFragment.kFloorKey, mFloorsList.get(position));
				args.putLong(ApplicationGlobals.kFloorID, this.mFloorID);
				fragment.setArguments(args);
				return fragment;
			}
		}

		@Override
		public int getCount() {
			return this.mFloorsList.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.Floor1).toUpperCase();
			case 1:
				return getString(R.string.Floor2).toUpperCase();
			case 2:
				return getString(R.string.Floor3).toUpperCase();
			}
			return null;
		}
	}
}
