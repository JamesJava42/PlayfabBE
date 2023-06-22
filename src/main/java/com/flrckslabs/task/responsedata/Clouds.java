package com.flrckslabs.task.responsedata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Clouds {
	private int all;

	
	
	
	/**
	 * @param all
	 */
	@JsonCreator
	public Clouds(@JsonProperty("all") int all) {
		super();
		this.all = all;
	}

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	
	

}
