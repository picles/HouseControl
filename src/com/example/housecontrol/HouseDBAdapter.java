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
	public long InsertLayout(String LayoutName)
	{
		long lRowID = -1;
		
		  try {
			  SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
	            ContentValues initialValues = new ContentValues();
	          //  initialValues.put(_ID, personId);
	            initialValues.put("LayoutName", LayoutName);
	            lRowID = sqlite.insert("Layouts", null, initialValues);

	        } catch (SQLException sqlerror) {
	            Log.v("Insert into table error", sqlerror.getMessage());
	            return lRowID;
	        }
	        return lRowID;
	}
	
	public long InsertFloor(long IdLayout, int FloorNumber)
	{
		long lRowID = -1;
		  try {
	            SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
	            ContentValues initialValues = new ContentValues();
	          //  initialValues.put(_ID, personId);
	            initialValues.put("IdLayout", IdLayout);
	            initialValues.put("FloorNumber", FloorNumber);
	            lRowID=sqlite.insert("Floors", null, initialValues);

	        } catch (SQLException sqlerror) {
	            Log.v("Insert into table error", sqlerror.getMessage());
	            return lRowID;
	        }
	        return lRowID;
	}
	
	public long InsertRoom(long m_lFloorID, int FloorNumber, String RoomName)
	{
		long lRowID=-1;
		  try {
	            SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
	            ContentValues initialValues = new ContentValues();
	          //  initialValues.put(_ID, personId);
	            initialValues.put("IdFloor", m_lFloorID);
	            initialValues.put("FloorNumber", FloorNumber);
	            initialValues.put("RoomName", RoomName);
	            lRowID = sqlite.insert("Rooms", null, initialValues);

	        } catch (SQLException sqlerror) {
	            Log.v("Insert into table error", sqlerror.getMessage());
	            return lRowID;
	        }
	        return lRowID;
	}
	
	public long InsertEquipments(int IdRoom, int EquipmentType, 
			String EquipmentIP, int EquipmentPort)
	{
		long lRowID = -1;
		  try {
	            SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
	            ContentValues initialValues = new ContentValues();
	          //  initialValues.put(_ID, personId);
	            initialValues.put("IdRoom", IdRoom);
	            initialValues.put("EquipmentType", EquipmentType);
	            initialValues.put("EquipmentIP", EquipmentIP);
	            initialValues.put("EquipmentPort", EquipmentPort);
	            lRowID = sqlite.insert("Equipments", null, initialValues);

	        } catch (SQLException sqlerror) {
	            Log.v("Insert into table error", sqlerror.getMessage());
	            return lRowID;
	        }
	        return lRowID;
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
	 
	 public ArrayList<Room> getRoomsbyFloorandFloorNb(long FloorID, int FloorNumber)
	 {
		  ArrayList<Room> rooms = new ArrayList<Room>();
		  SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
		  Cursor crsr = sqliteDB.rawQuery("SELECT * FROM Rooms WHERE IdFloor =" + FloorID + " AND FloorNumber = " + FloorNumber , null);
	      crsr.moveToFirst();
	      
	      for (int i = 0; i < crsr.getCount(); i++){
	      	rooms.add(new Room(crsr.getInt(0), crsr.getInt(2), crsr.getString(3) ));
	          crsr.moveToNext();
	      }
	      
	      return rooms;
	 }
}