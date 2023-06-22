package com.flrckslabs.task.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.flrckslabs.task.models.Locationdata;
import com.flrckslabs.task.models.Requestdata;
import com.flrckslabs.task.playfabsession.Login;
import com.flrckslabs.task.services.Weatherdata;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.playfab.PlayFabClientAPI;
import com.playfab.PlayFabClientModels;
import com.playfab.PlayFabProfilesAPI;

@RestController
@RequestMapping("/call")
public class Requestcontroller {
	@Autowired
	private Weatherdata weatherdata;
	@Autowired
	Login login;
	
	ObjectMapper objectMapper = new ObjectMapper();

	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/player/name")
	public ResponseEntity<?> getPlayerName() throws JsonProcessingException{
		 String resp =  login.playFabDisplayName();
		 System.out.println("Display Name :" + resp);
		return new ResponseEntity<>( resp,  HttpStatus.OK);
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get/player")
	public ResponseEntity<?> getPlayerData() throws JsonMappingException, JsonProcessingException{
		 objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//		String result = login.retrivePlayer();
//		String res = login.createSession("9227E82AC7FBC56F");
		String out =  login.getUserData();
		 JsonNode value =  objectMapper.readTree(out);
		  JsonNode outdata =  value.get("Data");
		  List<com.flrckslabs.task.responsedata.Weatherdata> data = new ArrayList<>();
		  
		  for (JsonNode entry : outdata) {
			  
              System.out.println("-------------------------------------");

//              JsonNode key = entry.get("Value");
	            if (entry.get("Value") != null) {
	            	 JsonNode key = entry.get("Value");
//	            	String dataobj =  objectMapper.writeValueAsString(key);
//	               Response outvlue =  objectMapper.readValue(dataobj, Response.class);
////	                 Response  objdata=  objectMapper.treeToValue(key, Response.class);
//	                 data.add(outvlue);
	                String valueJson = key.asText();
	                objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	                System.out.println("The json value is :"+ valueJson);
	               com.flrckslabs.task.responsedata.Weatherdata responsedata  =  objectMapper.readValue(valueJson, com.flrckslabs.task.responsedata.Weatherdata.class);
	                System.out.println(valueJson);
	                data.add(responsedata);
	                System.out.println(responsedata.getDt()+"--------------"+ responsedata.getVisibility());
//	                System.out.println(outvlue);
	            }
	        }
		
		return new ResponseEntity<>(data,  HttpStatus.OK);
	}
	 @CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add/player")
	public ResponseEntity<?> addPlayer(@RequestBody Requestdata requestdata ) throws JsonProcessingException{
		Locationdata result = weatherdata.getWeatherData( requestdata.getLongt(),requestdata.getLat());

		String jsonValue;
        
            jsonValue = objectMapper.writeValueAsString(result);
        try {
        	System.out.println("The value is :"+ login.getPLAYER_ID());
        	if(login.getPLAYER_ID().length() >0) {
        		ResponseEntity<?> resp= 	login.updatePlayer(jsonValue, "", "");
                return new ResponseEntity<>(result.getClouds(), HttpStatus.OK);
        	}else {
        		 String response = login.addPlayer("tito2", "tito1@gmail.com", "tito2#4244", jsonValue);
         		return new ResponseEntity<>(response, HttpStatus.OK);
        	}
           


        }catch(Exception e) {
        	return new ResponseEntity<>("Not Sucess"+ e.getLocalizedMessage(),HttpStatus.OK);
            
        }
	}
	
	
	@GetMapping("/update/player")
	public ResponseEntity<?> updatePlayer(@RequestBody Requestdata requestdata) throws JsonProcessingException{
		String playerid="";
		String sessionid="";
		Locationdata result = weatherdata.getWeatherData( requestdata.getLongt(),requestdata.getLat());

		String jsonValue;
        try {
            jsonValue = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
	 ResponseEntity<String> res = 	login.updatePlayer(jsonValue, playerid,sessionid);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}

}
