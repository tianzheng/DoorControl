package com.example.doorcontrol.bean;

import java.io.Serializable;


/**
 * @author Tianzheng
 *
 */
public class GetDoorResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 0:成功 ，1：失败
	 */
	private int response =-1;

	/**
	 * 返回相关提示信息
	 */
	private String message;
	
	/**
	 * 数据结果
	 */
	private  GetDoorList result ;
	
	
	
	
	public int getResponse() {
		return response;
	}


	public void setResponse(int response) {
		this.response = response;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public GetDoorList getResult() {
		return result;
	}


	public void setResult(GetDoorList result) {
		this.result = result;
	}


	
	

	
}
