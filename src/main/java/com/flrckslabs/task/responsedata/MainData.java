package com.flrckslabs.task.responsedata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MainData {
	 private String temp;
     private String feels_like;
     private String temp_min;
     private String temp_max;
     private String pressure;
     private String humidity;
     
     
     
	/**
	 * @param temp
	 * @param feels_like
	 * @param temp_min
	 * @param temp_max
	 * @param pressure
	 * @param humidity
	 */
     @JsonCreator
	public MainData(@JsonProperty("temp") String temp,@JsonProperty("feels_like") String feels_like,@JsonProperty("temp_min") String temp_min,@JsonProperty("temp_max") String temp_max, @JsonProperty("pressure") String pressure,
			@JsonProperty("humidity")	String humidity) {
		super();
		this.temp = temp;
		this.feels_like = feels_like;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.pressure = pressure;
		this.humidity = humidity;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getFeels_like() {
		return feels_like;
	}
	public void setFeels_like(String feels_like) {
		this.feels_like = feels_like;
	}
	public String getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(String temp_min) {
		this.temp_min = temp_min;
	}
	public String getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(String temp_max) {
		this.temp_max = temp_max;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
     
     

}
