package com.example.doorcontrol.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author 
 * �������ݽ��
 *
 */
public class GetDoorList implements Serializable{
  
  
	private static final long serialVersionUID = 1L;
	/**
	 * ��̨�豸����
	 */
	private int total;
	/**
	 * �ż���
	 */
	private ArrayList<Doors> doors;
	
	
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ArrayList<Doors> getDoors() {
		return doors;
	}

	public void setDoors(ArrayList<Doors> doors) {
		this.doors = doors;
	}



}
