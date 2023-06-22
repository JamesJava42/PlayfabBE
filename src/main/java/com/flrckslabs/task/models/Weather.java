package com.flrckslabs.task.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
	String id;
	String main;
	String description;
	String icon;

	@JsonCreator
	 public Weather(@JsonProperty("id") String id,
             @JsonProperty("main") String main,
             @JsonProperty("description") String description,
             @JsonProperty("icon") String icon) {
  this.id = id;
  this.main = main;
  this.description = description;
  this.icon = icon;
}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
