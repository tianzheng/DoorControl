package com.example.doorcontrol.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Doors implements Serializable{


	private static final long serialVersionUID = 1L;
	/**
	 * �豸ID
	 */
	private	int id;
	/**
	 * �豸���ƣ�os01
	 */
	private	String name;
	/**
	 * true���ţ�false����
	 */
	private  boolean  doorState;

	/**
	 * true���ߣ�false ����
	 */
	public boolean online;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDoorState() {
		return doorState;
	}

	public void setDoorState(boolean doorState) {
		this.doorState = doorState;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	

	


}
