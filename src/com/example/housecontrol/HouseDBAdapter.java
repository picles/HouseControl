package com.example.housecontrol;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class HouseDBAdapter {

	private HouseDBHelper dbHelper;
	
	public HouseDBAdapter(Context context)
	{
		dbHelper = new HouseDBHelper(context);
	}
	
	// Insert Methods
	public boolean InsertLayout(String LayoutName)
	{
		  try {
			  SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
	            ContentValues initialValues = new ContentValues();
	          //  initialValues.put(_ID, personId);
	            initialValues.put("LayoutName", LayoutName);
	            sqlite.insert("Layouts", null, initialValues);

	        } catch (SQLException sqlerror) {
	            Log.v("Insert into table error", sqlerror.getMessage());
	            return false;
	        }
	        return true;
	}
	
	public boolean InsertFloor(int IdLayout, int FloorNumber)
	{
		  try {
	            SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
	            ContentValues initialValues = new ContentValues();
	          //  initialValues.put(_ID, personId);
	            initialValues.put("IdLayout", IdLayout);
	            initialValues.put("FloorNumber", FloorNumber);
	            sqlite.insert("Floors", null, initialValues);

	        } catch (SQLException sqlerror) {
	            Log.v("Insert into table error", sqlerror.getMessage());
	            return false;
	        }
	        return true;
	}
	
	public boolean InsertRoom(int IdFloor, int FloorNumber, String RoomName)
	{
		  try {
	            SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
	            ContentValues initialValues = new ContentValues();
	          //  initialValues.put(_ID, personId);
	            initialValues.put("IdFloor", IdFloor);
	            initialValues.put("FloorNumber", FloorNumber);
	            initialValues.put("RoomName", RoomName);
	            sqlite.insert("Rooms", null, initialValues);

	        } catch (SQLException sqlerror) {
	            Log.v("Insert into table error", sqlerror.getMessage());
	            return false;
	        }
	        return true;
	}
	
	public boolean InsertEquipments(int IdRoom, int EquipmentType, 
			String EquipmentIP, int EquipmentPort)
	{
		  try {
	            SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
	            ContentValues initialValues = new ContentValues();
	          //  initialValues.put(_ID, personId);
	            initialValues.put("IdRoom", IdRoom);
	            initialValues.put("EquipmentType", EquipmentType);
	            initialValues.put("EquipmentIP", EquipmentIP);
	            initialValues.put("EquipmentPort", EquipmentPort);
	            sqlite.insert("Equipments", null, initialValues);

	        } catch (SQLException sqlerror) {
	            Log.v("Insert into table error", sqlerror.getMessage());
	            return false;
	        }
	        return true;
	}
	
	
	 public ArrayList<House> getHouses() {
	        ArrayList<House> house = new ArrayList<House>();
	        SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
	        Cursor crsr = sqliteDB.rawQuery("SELECT * FROM Layouts", null);
	        crsr.moveToFirst();
	        for (int i = 0; i < crsr.getCount(); i++){
	        	house.add(new House(crsr.getInt(0), crsr.getString(1)));
	            crsr.moveToNext();
	        }
	        return house;
	    }
	
}
