package com.flrckslabs.task.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Sys {
	String pop;
	
	@JsonCreator
	public Sys(
			@JsonProperty("pop") String pop) {
		this.pop=pop;
		
	}

	public String getPop() {
		return pop;
	}

	public void setPop(String pop) {
		this.pop = pop;
	}

}
