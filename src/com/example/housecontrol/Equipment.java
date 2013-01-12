package com.example.housecontrol;

public class Equipment {
	
	private int mEquipmentId;
	private int mEquipmentType;
	private String mIp;
	private int mPort;
	
	public Equipment(final int aEquipmentId, final int aEquipmentType, final String aIp, final int aPort ) {
		this.mEquipmentId = aEquipmentId;
		this.mEquipmentType = aEquipmentType;
		this.mIp = aIp;
		this.mPort = aPort;
	}

	public int getEquipmentId() {
		return mEquipmentId;
	}

	public void setEquipmentId(final int aEquipmentId) {
		this.mEquipmentId = aEquipmentId;
	}

	public int getEquipmentType() {
		return mEquipmentType;
	}

	public void setEquipmentType(final int aEquipmentType) {
		this.mEquipmentType = aEquipmentType;
	}

	public String getIp() {
		return mIp;
	}

	public void setIp(final String aIp) {
		this.mIp = aIp;
	}

	public int getPort() {
		return mPort;
	}

	public void setPort(final int aPort) {
		this.mPort = aPort;
	}
}
