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
	
	public long InsertEquipments(long IdRoom, int EquipmentType, 
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
	 
	 public Room getRoomById (final long aId) {
	        SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
	        Cursor crsr = sqliteDB.rawQuery("SELECT * FROM Rooms where _id = "+aId, null);
	        crsr.moveToFirst();
	        Room r = new Room(crsr.getInt(0), crsr.getInt(2), crsr.getString(3));
	        ArrayList<Equipment> equipments = this.getEquipmentsByRoomId(r.getId());
	        r.setEquipmentsList(equipments);
	        return r;
	 }
	 
	 public ArrayList<Room> getRoomsbyFloorandFloorNb(long FloorID, int FloorNumber)
	 {
		  ArrayList<Room> rooms = new ArrayList<Room>();
		  SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
		  Cursor crsr = sqliteDB.rawQuery("SELECT * FROM Rooms WHERE IdFloor =" + FloorID + " AND FloorNumber = " + FloorNumber , null);
	      crsr.moveToFirst();
	      
	      for (int i = 0; i < crsr.getCount(); i++){
	    	  int roomId = crsr.getInt(0);
	    	  Room room = new Room(roomId, crsr.getInt(2), crsr.getString(3) );
	    	  ArrayList<Equipment> equipments = this.getEquipmentsByRoomId(roomId);
	    	  room.setEquipmentsList(equipments);
	    	  rooms.add(room);
	          crsr.moveToNext();
	      }
	      
	      return rooms;
	 }
	 
	 public ArrayList<Equipment> getEquipmentsByRoomId(long aRoomId)
	 {
		  ArrayList<Equipment> equipments = new ArrayList<Equipment>();
		  SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
		  Cursor crsr = sqliteDB.rawQuery("SELECT * FROM Equipments WHERE idRoom = "+aRoomId, null);
	      crsr.moveToFirst();
    	  System.out.println("ZING! " +aRoomId );
	      for (int i = 0; i < crsr.getCount(); i++){
	    	  Equipment equipment = new Equipment(crsr.getInt(0), crsr.getInt(2), crsr.getString(3), crsr.getInt(4));
	    	  System.out.println("Eq Id--> "+ crsr.getInt(0));
	    	  equipments.add(equipment);
	          crsr.moveToNext();
	      }
	      
	      return equipments;	 
	 }
	 
	 public int getNumberofFloors(long FloorID)
	 {
		  int floornb=-1;
		  SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
		  Cursor crsr = sqliteDB.rawQuery("SELECT FloorNumber FROM Floors WHERE _id =" + FloorID , null);
	      crsr.moveToFirst();
	      
	      for (int i = 0; i < crsr.getCount();){
	      	return crsr.getInt(0);
	      }
	      
	      return floornb;
	 }
	 
	 public int getFloorId(long aHouseID)
	 {
		  SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
		  Cursor crsr = sqliteDB.rawQuery("SELECT _id FROM Floors WHERE idLayout =" + aHouseID , null);
	      crsr.moveToFirst();
	      
	      for (int i = 0; i < crsr.getCount();){
	      	return crsr.getInt(0);
	      }
	      
	      return -1;
	 }
	 
	 public boolean updateFloor(long IdEquipment, String IP, int Port)
	 {
		 SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
		 ContentValues args = new ContentValues();
		 args.put("EquipmentIP", IP);
		 args.put("EquipmentPort", Port);
		 int irows = sqlite.update("Equipments", args,   "_id =" + IdEquipment, null);
		 
		 if(irows <=0)
			 return false;
		 else
			 return true;
	 }
	 
	 public String getEquipmentIPById(long IdEquipment)
	 {
		 SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
		  Cursor crsr = sqliteDB.rawQuery("SELECT EquipmentIP FROM Equipments WHERE _id =" + IdEquipment , null);
	      crsr.moveToFirst();
	      
	      for (int i = 0; i < crsr.getCount();){
	      	return crsr.getString(0);
	      }
	      
	      return "";
	 }
	 
	 public int getEquipmentPortById(long IdEquipment)
	 {
		 SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
		  Cursor crsr = sqliteDB.rawQuery("SELECT EquipmentPort FROM Equipments WHERE _id =" + IdEquipment , null);
	      crsr.moveToFirst();
	      
	      for (int i = 0; i < crsr.getCount();){
	      	return crsr.getInt(0);
	      }
	      
	      return 0;
	 }
}
