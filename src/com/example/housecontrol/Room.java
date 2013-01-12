package com.example.housecontrol;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -961278434817353367L;
	private int mId;
	private int mFloorNumber;
	private String mRoomName;
	private ArrayList<Equipment> mEquipmentsList;
	
	public Room(int id, int FloorNumber, String RoomName) {
		this.mId = id;
		this.mFloorNumber = FloorNumber;
		this.mRoomName = RoomName;
	}
	
	public int getId() {
		return this.mId;
	}
	
	public void setRoomName(String RoomName) {
		this.mRoomName = RoomName;
	}
	
	public String getRoomName() {
		return this.mRoomName;
	}
	
	public void setFloorNumber(int FloorNumber) {
		this.mFloorNumber = FloorNumber;
	}
	
	public int getFloorNumber() {
		return this.mFloorNumber;
	}
	
	public ArrayList<Equipment> getEquipmentsList()
	{
		return this.mEquipmentsList;
	}
	
	public void setEquipmentsList(ArrayList<Equipment> aEquipmentsList)
	{
		this.mEquipmentsList = aEquipmentsList;
	}
	
}
