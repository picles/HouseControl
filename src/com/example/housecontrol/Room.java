package com.example.housecontrol;

public class Room {
	private int mId;
	private int mFloorNumber;
	private String mRoomName;
	
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
}
