package com.flrckslabs.task.models;

public class Dataresponse {
	
	String cod;
	String message;
	String cnt;
	Locationdata []data;
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public Locationdata[] getData() {
		return data;
	}
	public void setData(Locationdata[] data) {
		this.data = data;
	}
	

}
