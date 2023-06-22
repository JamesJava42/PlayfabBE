package com.flrckslabs.task.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind {
	String speed;
	String deg;
	String gust;
	 @JsonCreator
	    public Wind(@JsonProperty("speed") String speed,
	                @JsonProperty("deg") String deg,
	                @JsonProperty("gust") String gust) {
	        this.speed = speed;
	        this.deg = deg;
	        this.gust = gust;
	    }
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getDeg() {
		return deg;
	}
	public void setDeg(String deg) {
		this.deg = deg;
	}
	public String getGust() {
		return gust;
	}
	public void setGust(String gust) {
		this.gust = gust;
	}
	

}
