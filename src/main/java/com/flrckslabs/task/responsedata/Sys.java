package com.flrckslabs.task.responsedata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Sys {
	
	private String sys;
	@JsonCreator
	public Sys(@JsonProperty("sys")String sys) {
		this.sys=sys;
	}
	public String getSys() {
		return sys;
	}
	public void setSys(String sys) {
		this.sys = sys;
	}
	
	

}
