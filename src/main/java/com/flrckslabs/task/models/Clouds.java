package com.flrckslabs.task.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Clouds {
	String all;
	
	@JsonCreator
	public Clouds(
			@JsonProperty("all") String all) {
		this.all = all;
		
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

}
