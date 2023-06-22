package com.flrckslabs.task.responsedata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind {
	private String speed;
    private String deg;
    
    
	/**
	 * @param speed
	 * @param deg
	 */
    @JsonCreator
	public Wind(@JsonProperty("speed") String speed, @JsonProperty("deg")String deg) {
		super();
		this.speed = speed;
		this.deg = deg;
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

    

}
