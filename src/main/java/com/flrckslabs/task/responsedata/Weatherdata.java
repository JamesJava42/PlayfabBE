package com.flrckslabs.task.responsedata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weatherdata {
	private long dt;
    private MainData main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private int visibility;
    private Sys sys;
    
    
    
    public Weatherdata() {};
    @JsonCreator
	public Weatherdata(@JsonProperty("dt")Long  dt,@JsonProperty("main") MainData main,
			@JsonProperty("weather") List<Weather> weather, @JsonProperty("clouds")Clouds clouds,
			@JsonProperty("wind")Wind wind,@JsonProperty("visibility") int visibility,
			@JsonProperty("sys")Sys sys) {
//		super();
		this.dt = dt;
		this.main = main;
		this.weather = weather;
		this.clouds = clouds;
		this.wind = wind;
		this.visibility = visibility;
		this.sys = sys;
	}
	public long getDt() {
		return dt;
	}
	public void setDt(long dt) {
		this.dt = dt;
	}
	public MainData getMain() {
		return main;
	}
	public void setMain(MainData main) {
		this.main = main;
	}
	public List<Weather> getWeather() {
		return weather;
	}
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}
	public Clouds getClouds() {
		return clouds;
	}
	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public Sys getSys() {
		return sys;
	}
	public void setSys(Sys sys) {
		this.sys = sys;
	}
    
    

}
