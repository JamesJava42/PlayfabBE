package com.flrckslabs.task.playfabsession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flrckslabs.task.models.Dataresponse;
import com.flrckslabs.task.models.Locationdata;
import com.flrckslabs.task.models.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.playfab.PlayFabAuthenticationModels;
import com.playfab.PlayFabClientAPI;
import com.playfab.PlayFabClientModels;
import com.playfab.PlayFabClientModels.GetPlayerProfileRequest;
import com.playfab.PlayFabClientModels.GetPlayerProfileResult;
import com.playfab.PlayFabClientModels.GetUserDataRequest;
import com.playfab.PlayFabClientModels.GetUserDataResult;
import com.playfab.PlayFabClientModels.LoginResult;
import com.playfab.PlayFabClientModels.LoginWithEmailAddressRequest;
import com.playfab.PlayFabClientModels.LoginWithPlayFabRequest;
import com.playfab.PlayFabClientModels.RegisterPlayFabUserResult;
import com.playfab.PlayFabClientModels.UpdateUserDataRequest;
import com.playfab.PlayFabClientModels.UpdateUserTitleDisplayNameRequest;
import com.playfab.PlayFabClientModels.UpdateUserTitleDisplayNameResult;
import com.playfab.PlayFabErrors.PlayFabResult;
import com.playfab.PlayFabServerAPI;
import com.playfab.PlayFabSettings;

public class Login {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public static String getSESSION_ID() {
		return SESSION_ID;
	}

	public static void setSESSION_ID(String sESSION_ID) {
		SESSION_ID = sESSION_ID;
	}

	public static String getPLAYER_ID() {
		return PLAYER_ID;
	}

	public static void setPLAYER_ID(String pLAYER_ID) {
		PLAYER_ID = pLAYER_ID;
	}

	public static String getApiUrl() {
		return API_URL;
	}

	public static String getTitleId() {
		return TITLE_ID;
	}

	public static String getSecretKey() {
		return SECRET_KEY;
	}

	public static String getPlayfabApiUrl() {
		return PLAYFAB_API_URL;
	}

	public static String getUpdatePlayer() {
		return UPDATE_PLAYER;
	}
	private static final String API_URL = "https://AA0CF.playfabapi.com/Client/RegisterPlayFabUser";
    private static final String TITLE_ID = "AA0CF";
     private static final String SECRET_KEY = "E1OCQY4I1ADMIFGDRBTI3CUTTHMU11J8ZO9P5K11WAS1E3J573";
//     
     private static final String PLAYFAB_API_URL = "https://AA0CF.playfabapi.com/Admin/GetUserData";
     private static final String UPDATE_PLAYER= "https://AA0CF.playfabapi.com/Client/UpdateUserData";

     
     private static  String SESSION_ID= "4333C40A81EF2103-E4980FD4BAA5AF15-2ABC9B24A96ACBED-AA0CF-8DB6D63F995BB3A-mXXfUVfR8tFjP4ERAnxEYZV9Pxg0jvHBH5WV57afDqM=";
     private static String PLAYER_ID= "230B2B256E4B3E3F";


     
     
     public String playFabDisplayName() throws JsonProcessingException {
    	 PlayFabClientModels.UpdateUserTitleDisplayNameRequest obj = new PlayFabClientModels.UpdateUserTitleDisplayNameRequest();
    	 obj.DisplayName = "Rakehs Reddy";
    	 String json = objectMapper.writeValueAsString(obj.DisplayName);
    	 GetPlayerProfileRequest objname = new GetPlayerProfileRequest();
    	 System.out.println("Obj name "+ objname);
    	 objname.PlayFabId="8BE02DC0F2499D36";
    	 System.out.println("teh  vlues arw"+json);
    	
    	 PlayFabResult<UpdateUserTitleDisplayNameResult> profileResponse1 =  PlayFabClientAPI.UpdateUserTitleDisplayName(obj);
    	  PlayFabResult<GetPlayerProfileResult> profileResponse =  PlayFabClientAPI.GetPlayerProfile(objname);
    	  if(profileResponse != null) {
        	  return profileResponse.Result.PlayerProfile.DisplayName;

    	  }else {
    		  if(profileResponse1 != null) {
    			  System.out.println(profileResponse1.Result+ " ");
    		  }
    		
    		  return "Empty";
    		  
    	  }
    	 
    	 
     }
	
    public String retrivePlayer(String playFabId) {
    	 HttpHeaders headers = new HttpHeaders();
         headers.set("X-SecretKey", SECRET_KEY);
         String url = PLAYFAB_API_URL + "?PlayFabId=" + playFabId + "&TitleId=" + TITLE_ID;
         ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
         if(response.getStatusCode().is2xxSuccessful()) {
      	   System.out.println("Sucessfull");
      	   return "sucess";
         }else {
      	   System.out.println("fail");
      	   return "fail";
         }
//		return playFabId;
    	
    }
    
    public String addPlayer(String username, String email, String password, String customData) throws JsonProcessingException {
    	System.out.println("Custom data : "+customData);
    	 
	      HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.set("X-SecretKey", SECRET_KEY);
	        
	        
    	String requestBody = String.format("{\"TitleId\":\"%s\",\"Username\":\"%s\",\"Email\":\"%s\",\"Password\":\"%s\",\"CustomData\":%s}",
                TITLE_ID, username, email, password, customData);
    	
    	HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
    	
    	
    	ResponseEntity<String> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                entity,
                String.class
        );
    	
    	
    	if (response.getStatusCode().is2xxSuccessful()) {
           System.out.println("Sucessfull data"+response.getBody());
             String playerID =  extractPlayFabId(response.getBody());
            String player = playerID.substring(0,playerID.indexOf(","));
            String session = playerID.substring(playerID.indexOf(",")+1);
            
            this.PLAYER_ID=player;
            this.SESSION_ID=session;
            System.out.println("the keys are "+ this.PLAYER_ID+ "session is  ----"+this.SESSION_ID);
            
            updatePlayer(customData,player,session);
            return playerID;
        } else {
            return "fails";
        }

    	
    }
	
	private String extractPlayFabId(String body) {
		// TODO Auto-generated method stub
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseJson = objectMapper.readTree(body);
            JsonNode dataNode = responseJson.get("data");
            if (dataNode != null) {
            	
                JsonNode playFabIdNode = dataNode.get("PlayFabId");
                JsonNode sessionToken =  dataNode.get("SessionTicket");
               
                
                if (playFabIdNode != null) {
                    return playFabIdNode.asText()+","+sessionToken.asText();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}

	public String createSession(String playFabId) {
//        PlayFabSettings.staticSettings.DeveloperSecretKey = playFabSecretKey;
		  
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.set("X-SecretKey", SECRET_KEY);
	       
	        String request = String.format("{\"PlayFabId\":\"%s\",\"Keys\":null}", playFabId);

	        HttpEntity<String> entity = new HttpEntity<>(request, headers);
	        System.out.println("entity "+entity);
	        ResponseEntity<String> response = restTemplate.exchange(
	        		PLAYFAB_API_URL,
	                HttpMethod.POST,
	                entity,
	                String.class
	        );
	        System.out.println(response);

           if(response.getStatusCode().is2xxSuccessful()) {
        	   return ""+response.getBody();
           }else {
        	   System.out.println("fail");
        	   return "fail";
           }

        
    }
	
	
//	Reteive the user data
	public String getUserData() {
		PlayFabSettings.ClientSessionTicket=this.SESSION_ID;
		PlayFabSettings.TitleId=TITLE_ID;
		PlayFabSettings.DeveloperSecretKey=SECRET_KEY;
		
//		headers

		HttpHeaders headers = new HttpHeaders();
		headers.add("X-SecretKey", Login.SECRET_KEY);
//		headers.add("X-Authorization", Login.SESSION_ID);
		headers.setContentType(MediaType.APPLICATION_JSON);	
		
		headers.add("Permission", "public");
//		entity obj 
        String request = String.format("{\"PlayFabId\":\"%s\"}", this.PLAYER_ID);
		HttpEntity<String> entity = new HttpEntity<>(request, headers);

		GetUserDataRequest getUserDataRequest = new GetUserDataRequest();
		getUserDataRequest.PlayFabId=Login.PLAYER_ID;
		ResponseEntity<String>   res = restTemplate.postForEntity(Login.PLAYFAB_API_URL, entity, String.class);
		System.out.println("Get all response"+ res);
		
//		PlayFabClientAPI fabClientAPI = new PlayFabClientAPI();
//		PlayFabResult<GetUserDataResult> res =   PlayFabClientAPI.GetUserData(getUserDataRequest);
//		Gson gson = new Gson();
//		System.out.println("The data from api is :"+res);  This is an object address
//		String out = gson.toJson(res);
//		System.out.println("Gson converted data : "+out);  data is in string format 
		return res.toString().substring(',');
	}
	Gson gson = new Gson();
	ObjectMapper objectMapper = new ObjectMapper();

	int value = 1;
	public ResponseEntity<String> updatePlayer(String customdata,String id ,String session) throws JsonProcessingException {
//		PlayFabSettings.ClientSessionTicket="DF385B2FC2342585-E4980FD4BAA5AF15-AE8F3971B70969D8-AA0CF-8DB6B342B8B86F8-VrzwQZ2e7Rqrwzw5D7GhW9Apa5841tfO/NtOfuDk5ZA=";
		id=this.TITLE_ID;
		session=this.SESSION_ID;
		
		
		PlayFabSettings.ClientSessionTicket=session;
		PlayFabSettings.TitleId=id;
		PlayFabSettings.DeveloperSecretKey=SECRET_KEY;
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-SecretKey", SECRET_KEY);
		headers.add("X-Authorization", session);
		headers.setContentType(MediaType.APPLICATION_JSON);		
//		request body
		TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {};
		Map<String, Object> dataMap = new HashMap<>();

		JsonNode body = objectMapper.readTree(customdata);	
		List<String> li = new ArrayList<>();

		Locationdata myModel = objectMapper.treeToValue(body, Locationdata.class);
		System.out.println("MyModel: "+myModel);
		li.add(gson.toJson(myModel));
		dataMap.put("main"+value++, li);   //as list string obj
				
//		dataMap.put("tmp"+value++, body);
//		JsonNode obj = objectMapper.readTree(customdata);
		Map<String,Object> requestdata = new HashMap<>();
		requestdata.put("Data", dataMap);
		
		requestdata.put("Permission", "public");
		String outrequest = gson.toJson(requestdata);
		HttpEntity<String> entity = new HttpEntity<>(outrequest, headers);
		 ResponseEntity<String> response =  restTemplate.postForEntity(UPDATE_PLAYER, entity, String.class);

			System.out.println("--- ||| -----"+ outrequest);
			return response;

		
	}

}
