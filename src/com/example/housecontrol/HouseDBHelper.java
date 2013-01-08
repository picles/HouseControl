package com.example.housecontrol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class HouseDBHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "LayoutDB";
	
	private final String createLayoutStatement =  
			"create table IF NOT EXISTS Layouts " +
			"(_id integer primary key autoincrement, " +
	        "LayoutName text not null);";
	
	private final String createFloorStatement =  
			"create table IF NOT EXISTS  Floors " +
			"(_id integer primary key autoincrement, " +
			"IdLayout integer not null, "+		
	        "FloorNumber integer not null);";
	
	private final String createRoomStatement = 
			 "create table IF NOT EXISTS Rooms " +
			 "(_id integer primary key autoincrement, " +
		     "IdFloor integer not null, " +
			 "FloorNumber integer not null, " +
			 "RoomName text not null);";
	
	public final String createEquipmentStatement = 
			 "create table IF NOT EXISTS Equipments" +
			 "(_id integer primary key autoincrement, " +
		     "IdRoom integer not null, " +
			 "EquipmentType integer not null, " +
			 "EquipmentIP text, " +
			 "EquipmentPort integer);";
	
	private Context context;
	
	public HouseDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		 try {
			 arg0.execSQL(this.createLayoutStatement);
			 arg0.execSQL(this.createFloorStatement);
			 arg0.execSQL(this.createRoomStatement);
			 arg0.execSQL(this.createEquipmentStatement);
	    } catch (Throwable t) {
	        Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
	    }
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("DROP TABLE IF EXISTS Layouts");
		arg0.execSQL("DROP TABLE IF EXISTS Equipments");
		arg0.execSQL("DROP TABLE IF EXISTS Rooms");
		arg0.execSQL("DROP TABLE IF EXISTS Floors");
		onCreate(arg0);
	}
}
