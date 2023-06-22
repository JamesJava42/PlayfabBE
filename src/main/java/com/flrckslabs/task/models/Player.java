package com.flrckslabs.task.models;

import java.util.Map;

public class Player {
	private String TitleId;
    private String Username;
    private String Email;
    private String Password;
    private Map<String, Object> CustomData;
	public String getTitleId() {
		return TitleId;
	}
	public void setTitleId(String titleId) {
		TitleId = titleId;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Map<String, Object> getCustomData() {
		return CustomData;
	}
	public void setCustomData(Map<String, Object> customData) {
		CustomData = customData;
	}
    

}
