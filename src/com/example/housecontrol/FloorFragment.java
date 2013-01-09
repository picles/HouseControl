package com.example.housecontrol;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class FloorFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	public static final String kFloorKey = "floor_key";
	private long mFloorID = -1;
	private Floor mFloor;
	private RoomListAdapter listadapter;
	
	public FloorFragment() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		this.mFloor = (Floor)getArguments().getSerializable(kFloorKey);
		this.mFloorID = getArguments().getLong(CreateHouse.kFloorID);
		
		View view = inflater.inflate(R.layout.fragment_floor, null);
		
		ListView listview = (ListView)view.findViewById(R.id.listview);
		
		HouseDBAdapter houseadapter = new HouseDBAdapter(getActivity().getApplicationContext());
		ArrayList<Room> rooms = houseadapter.getRoomsbyFloorandFloorNb(this.mFloorID, mFloor.getFloorNb());
		
		listadapter = new RoomListAdapter(getActivity(), R.layout.list_item, rooms);
		
		listview.setTextFilterEnabled(true);
		listview.setAdapter(listadapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Room r = (Room)listadapter.getItem(arg2);
				Bundle params = new Bundle();
				params.putLong(CreateHouse.kRoomID, r.getId());
				params.putString(CreateHouse.kRoomName, r.getRoomName());
				Intent roomIntent = new Intent();
           	 	roomIntent.setClass(getActivity(), EditRoomActivity.class); 
           	 	startActivity(roomIntent);
			}
		});
		
		registerForContextMenu(listview);
		
		return view;
	}
}
