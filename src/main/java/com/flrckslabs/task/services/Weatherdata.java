package com.flrckslabs.task.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.flrckslabs.task.models.Locationdata;
@Service
public class Weatherdata {
	
	@Autowired
	public RestTemplate restTemplate;
	
	
	public Locationdata  getWeatherData(String longt , String lat){
		String url = "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+longt+"&appid=97be7a73853b488b4908b2624b28c546";
		Locationdata result =  restTemplate.getForObject(url, Locationdata.class);
	    ResponseEntity<Locationdata> resultobj= 	 restTemplate.getForEntity(URI.create(url), Locationdata.class);
//		restTemplate.getForEntity(UriComponentsBuilder.fromUriString(url)
//, Locationdata.class);
		System.out.println("result is "+resultobj.getBody().getWeather()[0].getDescription());
		System.out.println(result+ " ---"+ resultobj);
		
		
		return result;
		
	}

}
