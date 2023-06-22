package com.flrckslabs.task.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	String dt;
	Tempdata main;
	Weather []weather;
	Clouds clouds;
	Wind wind;
	String visibility;
	Sys sys;
	public Response() {}
	@JsonCreator
    public Response(@JsonProperty("dt") String dt,
                    @JsonProperty("main") Tempdata main,
                    @JsonProperty("weather") Weather[] weather,
                    @JsonProperty("clouds") Clouds clouds,
                    @JsonProperty("wind") Wind wind,
                    @JsonProperty("visibility") String visibility,
                    @JsonProperty("sys") Sys sys) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.clouds = clouds;
        this.wind = wind;
        this.visibility = visibility;
        this.sys = sys;
    }
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public Tempdata getMain() {
		return main;
	}
	public void setMain(Tempdata main) {
		this.main = main;
	}
	public Weather[] getWeather() {
		return weather;
	}
	public void setWeather(Weather[] weather) {
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
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public Sys getSys() {
		return sys;
	}
	public void setSys(Sys sys) {
		this.sys = sys;
	}
	

}
