package com.example.housecontrol;

import java.io.Serializable;

public class Floor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -126484550284579640L;
	private int m_iFloorNb;
	private int m_iWCNb;
	private int m_iBedroomNb;
	private int m_iLivingroomNb;
	private int m_iKitchenNb;
	
	public Floor()
	{
	}

	public int getKitchenNb() {
		return m_iKitchenNb;
	}
	public void setKitchenNb(int kitchenNb) {
		this.m_iKitchenNb = kitchenNb;
	}

	public int getFloorNb() {
		return m_iFloorNb;
	}
	public void setFloorNb(int floorNb) {
		this.m_iFloorNb = floorNb;
	}

	public int getWCNb() {
		return m_iWCNb;
	}
	public void setWCNb(int wCNb) {
		this.m_iWCNb = wCNb;
	}

	public int getBedroomNb() {
		return m_iBedroomNb;
	}
	public void setBedroomNb(int bedroomNb) {
		this.m_iBedroomNb = bedroomNb;
	}

	public int getLivingroomNb() {
		return m_iLivingroomNb;
	}
	public void setLivingroomNb(int livingroomNb) {
		this.m_iLivingroomNb = livingroomNb;
	}
}
