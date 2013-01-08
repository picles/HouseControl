package com.example.housecontrol;

public class House {
	
	private int mId;
	private String mHouseName;
	
	public House(int id, String Name) {
		this.mId = id;
		this.mHouseName = Name;
	}
	
	public int getId() {
		return this.mId;
	}
	
	public void setHouseName(String name) {
		this.mHouseName = name;
	}
	
	public String getHouseName() {
		return this.mHouseName;
	}
}
